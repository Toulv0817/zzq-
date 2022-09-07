package com.wd.home;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.wd.common.base.SpUtils;
import com.wd.common.dtsc.GlideEngine;
import com.wd.common.dtsc.Tools;
import com.wd.common.model.AddMyFilePicBean;
import com.wd.common.model.PutMyFileBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.databinding.ActivityPutMyFileBinding;
import com.wd.patientcircle.AddPicAdapter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
@author 张梓萁
@date 2022/8/29
@desc 修改我的档案
*/
public class Activity_PutMyFile extends BaseActivity<MainViewModel, ActivityPutMyFileBinding> {
    private List<LocalMedia> selectList = new ArrayList<>(); // 预览图片
    private List<LocalMedia> selectList1 = new ArrayList<>(); // 预览图片
    ArrayList<String> allSelectList=new ArrayList<>();
    private AddPicAdapter adapter;
    private String[] split1;
//    private List<MultipartBody.Part> list;

    List<MultipartBody.Part> list = new ArrayList<>();
    @Override
    protected void startCoding() {
//        Log.i("ss",allSelectList.size()+"");
        sp();
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
                new DatePickerDialog(Activity_PutMyFile.this,9,onDateSetListener,Integer.parseInt(split[0]),Integer.parseInt(split[1])-1,Integer.parseInt(split[2])).show();
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
                new DatePickerDialog(Activity_PutMyFile.this,9,onDateSetListener,Integer.parseInt(split[0]),Integer.parseInt(split[1])-1,Integer.parseInt(split[2])).show();

            }
        });

        //返回
        vdb.fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        String picture0 = intent.getStringExtra("picture");

//        split1 = picture.split(",");
//        Log.i("张梓萁",split1[0]);

        Log.i("ss",allSelectList.size()+"");
        int id = intent.getIntExtra("id", 0);
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
                bvm.getPutMyFileBeanMutableLiveData(id,zz,bzs,jwbs,yyname,gc,playtime,stoptime).observe(Activity_PutMyFile.this,Activity_PutMyFile.this::onChanged);
                publish_pictures();
            }
        });

        //上传图片
        //判断集合等于空的时候创建对象
//        if(null==allSelectList){
//            allSelectList=new ArrayList<>();
//        }

        initAdapter();
    }
    private void initAdapter() {
        adapter = new AddPicAdapter(Activity_PutMyFile.this, 6);
        //选中
        adapter.setList(allSelectList);
        vdb.addRec.setAdapter(adapter);

        vdb.addRec.setLayoutManager(new GridLayoutManager(Activity_PutMyFile.this,3));
        adapter.setClickItemLisenter(new AddPicAdapter.ClickItemLisenter() {
            @Override
            public void add() {
                //可以添加最大图片数-已经选的图片数
                int size=6-allSelectList.size();
                //调用多图的工具类
                Tools.galleryPictures(Activity_PutMyFile.this,size);
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
                PictureSelector.create(Activity_PutMyFile.this)
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
            for (int i1 = 0; i1 < allSelectList.size(); i1++) {
                Log.e("存入",allSelectList.get(i1));
            }
            Log.i("ss1",allSelectList.size()+"");
            Log.e("image_path", "图片链接: " + path);
        }

        adapter.setList(allSelectList);
    }
    void sp(){
        String picture1 = SpUtils.getString("picture1", "la,o");
        String[] split2 = picture1.split(",");
        LocalMedia localMedia = new LocalMedia();
        for (int i = 0; i < split2.length; i++) {
            allSelectList.add(split2[i]);
        }
    }
    @Override
    protected int initLayout() {
        return R.layout.activity__put_my_file;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof PutMyFileBean){
            PutMyFileBean bean= (PutMyFileBean) o;
            if(bean.getStatus().equals("0000")){
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
//                publish_pictures();

            }
        }else if(o instanceof AddMyFilePicBean){
            AddMyFilePicBean bean= (AddMyFilePicBean) o;
            if(bean.getStatus().equals("0000")){
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    private void publish_pictures() {
//        sp();
        ContentResolver contentResolver = getContentResolver();
        for (int i = 0; i < allSelectList.size(); i++) {

            if(allSelectList.get(i).contains("http://10.59.9.18")){
                try {
                    Log.i("怕2怕",allSelectList.get(i)+"你"+allSelectList.size());
                    Toast.makeText(this, "我进来了", Toast.LENGTH_SHORT).show();
                    String picture1 = SpUtils.getString("picture1", "la,o");
                    String[] split2 = picture1.split(",");
                    LocalMedia localMedia = new LocalMedia();
                    for (int j = 0; j < split2.length; j++) {
//                        allSelectList.add(split2[i]);
                        int i1 = new Random().nextInt();
                        Bitmap bitmap = getimageinputstream(allSelectList.get(i));
//                        Bitmap bitmap = getHttpBitmap(allSelectList.get(i));
//                        savePicture(bitmap);//保存图片到SD卡
//                        savaimage(bitmap,"/data/data/com.wd.zzq/");
                        Log.i("怕3怕","你");
                        File file=new File("storage/emulated/0/");
//文件夹不存在，则创建它
                        if(!file.exists()){
                            try {
                                Log.i("怕4怕","你2");
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                            bitmap.compress(Bitmap.CompressFormat.JPEG,20,bufferedOutputStream);
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                            Log.i("怕4怕","你1");
//            file = new Compressor(this).compressToFile(file);
                            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                            MultipartBody.Part picture = MultipartBody.Part.createFormData("picture", file.getName(), requestBody);
                            list.add(picture);
                            for (int w = 0; w < list.size(); w++) {
                                Log.i("怕",list.get(w)+"");
                            }
                        } catch (Exception e) {

                            e.printStackTrace();

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                Log.i("怕1怕",allSelectList.get(i)+"你"+allSelectList.size());
                File file = new File(allSelectList.get(i));
                Log.i("tp1","1");
                try {
                    file = new Compressor(this).compressToFile(file);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part picture = MultipartBody.Part.createFormData("picture", file.getName(), requestBody);
                    list.add(picture);
                    Log.i("list",list.size()+"p");
                    for (int o = 0; o < list.size(); o++) {
                        Log.i("怕 不",list.get(o)+"");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        bvm.getaddMyFilePicBeanMutableLiveData(list).observe(Activity_PutMyFile.this,Activity_PutMyFile.this);

//        bvm.getPatientCircleAddPicBeanMutableLiveData(,list).observe(ActivitySendPatientCircle.this,ActivitySendPatientCircle.this);
    }



    // TODO: 2022/8/31
    public Bitmap getimageinputstream(String imageurl){
        URL url;
//        httpurlconnection connection=null;
        HttpURLConnection connection=null;
        Bitmap bitmap=null;

        try {

            url = new URL(imageurl);

            connection=(HttpURLConnection)url.openConnection();

            connection.setConnectTimeout(6000); //超时设置

            connection.setDoInput(true);

            connection.setUseCaches(false); //设置不使用缓存

            InputStream inputstream=connection.getInputStream();

            bitmap=BitmapFactory.decodeStream(inputstream);

            inputstream.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return bitmap;

    }

    // TODO: 2022/8/31
    /**

     * 保存位图到本地

     * @param bitmap

     * @param path 本地路径

     * @return void

     */

    public void savaimage(Bitmap bitmap, String  path){
        Log.i("怕3怕","你");
        File file=new File(path);
//文件夹不存在，则创建它
        if(!file.exists()){
            try {
                Log.i("怕4怕","你2");
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG,20,bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            Log.i("怕4怕","你1");
//            file = new Compressor(this).compressToFile(file);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part picture = MultipartBody.Part.createFormData("picture", file.getName(), requestBody);
            list.add(picture);
            for (int i = 0; i < list.size(); i++) {
                Log.i("怕",list.get(i)+"");
            }
        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }
}