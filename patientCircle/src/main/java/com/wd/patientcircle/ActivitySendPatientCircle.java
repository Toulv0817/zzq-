package com.wd.patientcircle;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.wd.common.base.BaseActivity;
import com.wd.common.dtsc.GlideEngine;
import com.wd.common.dtsc.Tools;
import com.wd.common.model.KeShiListBean;
import com.wd.common.model.KeShiSearchBean;
import com.wd.common.model.PatientCircleAddPicBean;
import com.wd.common.model.SendPatientCircleBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.patientcircle.databinding.ActivitySendPatientCircleBinding;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

//import com.luck.picture.lib.PictureSelector;
//import com.luck.picture.lib.entity.LocalMedia;
//import com.wd.common.dtsc.GlideEngine;
//import com.wd.common.dtsc.Tools;

/**
@author 张梓萁
@date 2022/8/24
@desc 发病友圈
*/
public class ActivitySendPatientCircle extends BaseActivity<MainViewModel, ActivitySendPatientCircleBinding> {
    private List<LocalMedia> selectList = new ArrayList<>(); // 预览图片
    private RecyclerView jzksrec,zybzrec;
    private PopupWindow popupWindow,zybzpopupWindow;
    int k;
    ArrayList<String> allSelectList=new ArrayList<>();
    private AddPicAdapter adapter;
    private File file;

    @Override
    protected void startCoding() {

        vdb.sendbyqPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //就诊科室的弹框
        View jzksview = LayoutInflater.from(ActivitySendPatientCircle.this).inflate(R.layout.ppw_jzks, null);
        jzksrec = jzksview.findViewById(R.id.jzks_rec);
        vdb.jzks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bvm.getKeShiListBeanMutableLiveData().observe(ActivitySendPatientCircle.this,ActivitySendPatientCircle.this::onChanged);
                popupWindow = new PopupWindow(jzksview, 600, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setContentView(jzksview);
                popupWindow.showAtLocation(jzksview, Gravity.CENTER,0,-75 );
            }
        });
        //主要病症的弹框
        View zybzview = LayoutInflater.from(ActivitySendPatientCircle.this).inflate(R.layout.ppw_zybz, null);
        zybzrec = zybzview.findViewById(R.id.zybz_rec);
        vdb.zybz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivitySendPatientCircle.this, "俺怕怕", Toast.LENGTH_SHORT).show();
                String s = vdb.addKs.getText().toString();

                //查询病症
                bvm.getKeShiSearchBeanMutableLiveData(k).observe(ActivitySendPatientCircle.this,ActivitySendPatientCircle.this::onChanged);

                zybzpopupWindow = new PopupWindow(zybzview, 600, 500, true);
                zybzpopupWindow.setContentView(zybzview);
                zybzpopupWindow.showAtLocation(zybzview, Gravity.CENTER,0,-30 );
            }
        });
        //发布
//        int[] ksid={7,4,2,5,12,9,6,11};
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
                new DatePickerDialog(ActivitySendPatientCircle.this,9,onDateSetListener,Integer.parseInt(split[0]),Integer.parseInt(split[1])-1,Integer.parseInt(split[2])).show();
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
                new DatePickerDialog(ActivitySendPatientCircle.this,9,onDateSetListener,Integer.parseInt(split[0]),Integer.parseInt(split[1])-1,Integer.parseInt(split[2])).show();

            }
        });
        vdb.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tital = vdb.addTital.getText().toString();

                String zb = vdb.addBz.getText().toString();
                String data = vdb.addData.getText().toString();
                String hospital = vdb.addYyname.getText().toString();
                String starttime = vdb.addPalytime.getText().toString();
                String stoptime = vdb.addStoptime.getText().toString();
                String gc = vdb.addGc.getText().toString();
                bvm.getSendPatientCircleBeanMutableLiveData(tital,k,zb,data,hospital,starttime,stoptime,gc,0).observe(ActivitySendPatientCircle.this,ActivitySendPatientCircle.this::onChanged);
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
        adapter = new AddPicAdapter(ActivitySendPatientCircle.this, 6);
        //选中
        adapter.setList(allSelectList);
        vdb.addRec.setAdapter(adapter);
        vdb.addRec.setLayoutManager(new GridLayoutManager(ActivitySendPatientCircle.this,3));
        adapter.setClickItemLisenter(new AddPicAdapter.ClickItemLisenter() {
            @Override
            public void add() {
                //可以添加最大图片数-已经选的图片数
                int size=6-allSelectList.size();
                //调用多图的工具类
                Tools.galleryPictures(ActivitySendPatientCircle.this,size);
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
                PictureSelector.create(ActivitySendPatientCircle.this)
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
        return R.layout.activity_send_patient_circle;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof KeShiListBean){
            KeShiListBean bean= (KeShiListBean) o;
            List<KeShiListBean.ResultBean> result = bean.getResult();
            CommonAdapter<KeShiListBean.ResultBean> adapter = new CommonAdapter<KeShiListBean.ResultBean>(ActivitySendPatientCircle.this, R.layout.item_name, result) {
                @Override
                protected void convert(ViewHolder holder, KeShiListBean.ResultBean resultBean, int position) {
                    holder.setText(R.id.ppw_name, resultBean.getDepartmentName());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            vdb.addKs.setText(resultBean.getDepartmentName());
                            k=resultBean.getId();
                            //查询病症
                            bvm.getKeShiSearchBeanMutableLiveData(resultBean.getId()).observe(ActivitySendPatientCircle.this,ActivitySendPatientCircle.this::onChanged);

                            popupWindow.dismiss();
                        }
                    });
                }
            };
            jzksrec.setAdapter(adapter);
            jzksrec.setLayoutManager(new LinearLayoutManager(ActivitySendPatientCircle.this,LinearLayoutManager.VERTICAL,false));
        }else if (o instanceof KeShiSearchBean){
            KeShiSearchBean bean= (KeShiSearchBean) o;
            List<KeShiSearchBean.ResultBean> result = bean.getResult();
            CommonAdapter<KeShiSearchBean.ResultBean> adapter1 = new CommonAdapter<KeShiSearchBean.ResultBean>(ActivitySendPatientCircle.this, R.layout.item_name, result) {
                @Override
                protected void convert(ViewHolder holder, KeShiSearchBean.ResultBean resultBean, int position) {

                    holder.setText(R.id.ppw_name,resultBean.getName());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            vdb.addBz.setText(resultBean.getName());
                            zybzpopupWindow.dismiss();
                        }
                    });
                }
            };
            zybzrec.setAdapter(adapter1);
            zybzrec.setLayoutManager(new LinearLayoutManager(ActivitySendPatientCircle.this,LinearLayoutManager.VERTICAL,false));


        }else if( o instanceof SendPatientCircleBean){
            SendPatientCircleBean bean= (SendPatientCircleBean) o;
            if(bean.getStatus().equals("0000")){
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                publish_pictures(bean.getResult());
                finish();
            }
        }else if(o instanceof PatientCircleAddPicBean){
            PatientCircleAddPicBean bean= (PatientCircleAddPicBean) o;
            if(bean.getStatus().equals("0000")){
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void publish_pictures(int result) {
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
        bvm.getPatientCircleAddPicBeanMutableLiveData(result,list).observe(ActivitySendPatientCircle.this,ActivitySendPatientCircle.this);
    }
}