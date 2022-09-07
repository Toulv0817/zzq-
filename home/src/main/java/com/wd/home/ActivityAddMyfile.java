package com.wd.home;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.wd.common.base.BaseActivity;
import com.wd.common.dtsc.GlideEngine;
import com.wd.common.dtsc.Tools;
import com.wd.common.model.AddMyFileBean;
import com.wd.common.model.AddMyFilePicBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.databinding.ActivityAddMyfileBinding;
import com.wd.patientcircle.AddPicAdapter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
@author 张梓萁
@date 2022/8/29
@desc 添加我的档案
*/
public class ActivityAddMyfile extends BaseActivity<MainViewModel, ActivityAddMyfileBinding> {
    private List<LocalMedia> selectList = new ArrayList<>(); // 预览图片
    ArrayList<String> allSelectList=new ArrayList<>();

    private AddPicAdapter adapter;
    private File file;


    @Override
    protected void startCoding() {
        //返回
        vdb.fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        String[] split = format.split("-");

        //开始时间
        vdb.startTime.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String time = year + "-" + month + "-" + dayOfMonth;
                        vdb.addPalytime.setText(time);
                    }
                };
//                new DatePickerDialog(ActivitySendPatientCircle.this,9,onDateSetListener,2022,8,25).show();
                new DatePickerDialog(ActivityAddMyfile.this,9,onDateSetListener,Integer.parseInt(split[0]),Integer.parseInt(split[1])-1,Integer.parseInt(split[2])).show();
            }
        });
        //停止时间
        vdb.stopTime.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String time = year + "-" + month + "-" + dayOfMonth;
                        vdb.addStoptime.setText(time);
                    }
                };
//                new DatePickerDialog(ActivitySendPatientCircle.this,9,onDateSetListener,2022,8,25).show();
                new DatePickerDialog(ActivityAddMyfile.this,9,onDateSetListener,Integer.parseInt(split[0]),Integer.parseInt(split[1])-1,Integer.parseInt(split[2])).show();

            }
        });
       //保存
        vdb.myfileSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zz = vdb.zz.getText().toString();
                String bzs = vdb.bzs.getText().toString();
                String jwbs = vdb.jwbs.getText().toString();
                String yyname = vdb.addYyname.getText().toString();
                String playtime = vdb.addPalytime.getText().toString();
                String stoptime = vdb.addStoptime.getText().toString();
                String gc = vdb.addGc.getText().toString();
                bvm.getAddMyFileBeanMutableLiveData(zz,bzs,jwbs,yyname,gc,playtime,stoptime).observe(ActivityAddMyfile.this,ActivityAddMyfile.this::onChanged);
            }
        });
        //上传图片
        //判断集合等于空的时候创建对象
        if(null==allSelectList){
            allSelectList=new ArrayList<>();
        }
        initAdapter();
    }
    private void initAdapter() {
        adapter = new AddPicAdapter(ActivityAddMyfile.this, 6);
        //选中
        adapter.setList(allSelectList);
        vdb.addRec.setAdapter(adapter);
        vdb.addRec.setLayoutManager(new GridLayoutManager(ActivityAddMyfile.this,3));
        adapter.setClickItemLisenter(new AddPicAdapter.ClickItemLisenter() {
            @Override
            public void add() {
                //可以添加最大图片数-已经选的图片数
                int size=6-allSelectList.size();
                //调用多图的工具类
                Tools.galleryPictures(ActivityAddMyfile.this,size);
            }

            @Override
            public void del(int position) {
                //删除
                allSelectList.remove(position);
                //在重新把集合放入适配器
                adapter.setList(allSelectList);
            }

            @Override
            public void item(int position, List<String> mlist) {
                selectList.clear();
                for (int i = 0; i < allSelectList.size(); i++) {

                    LocalMedia localMedia = new LocalMedia();
                    localMedia.setPath(allSelectList.get(i));
                    selectList.add(localMedia);

                }
                //图片选择器自带预览
                PictureSelector.create(ActivityAddMyfile.this)
                        .themeStyle(R.style.picture_default_style)
                        .isNotPreviewDownload(true)
                        .imageEngine(GlideEngine.createGlideEngine())//选择器展示不出图片则添加
                        .openExternalPreview(position,selectList);
                //自定义布局预览
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            // 结果回调 把选中的 图片存放的list集合
            selectList = PictureSelector.obtainMultipleResult(data);
            // 调用 转化为path
            showSelectPic(selectList);
        }
    }

    private void showSelectPic(List<LocalMedia> result) {
        for (int i = 0; i < result.size(); i++) {
            String path;
            //判断是否10.0以上
            if (Build.VERSION.SDK_INT >= 29) {
                path = result.get(i).getAndroidQToPath();
            } else {
                path = result.get(i).getPath();
            }
            allSelectList.add(path);
            Log.e("image_path", "图片链接: " + path);
        }

        adapter.setList(allSelectList);
    }
    @Override
    protected int initLayout() {
        return R.layout.activity_add_myfile;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof AddMyFileBean) {
            AddMyFileBean bean= (AddMyFileBean) o;
            if(bean.getStatus().equals("0000")){
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                publish_pictures();
                finish();
            }
        }else if(o instanceof AddMyFilePicBean){
            AddMyFilePicBean bean= (AddMyFilePicBean) o;
            if(bean.getStatus().equals("0000")){
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void publish_pictures() {
        List<MultipartBody.Part> list = new ArrayList<>();
        for (int i = 0; i < allSelectList.size(); i++) {
            file = new File(allSelectList.get(i));
            try {
                file = new Compressor(this).compressToFile(file);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part picture = MultipartBody.Part.createFormData("picture", file.getName(), requestBody);
                list.add(picture);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bvm.getaddMyFilePicBeanMutableLiveData(list).observe(ActivityAddMyfile.this,ActivityAddMyfile.this);
//        bvm.getPatientCircleAddPicBeanMutableLiveData(,list).observe(ActivitySendPatientCircle.this,ActivitySendPatientCircle.this);
    }
}