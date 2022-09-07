package com.wd.home;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.wd.common.base.BaseActivity;
import com.wd.common.base.SpUtils;
import com.wd.common.model.IsSingInBean;
import com.wd.common.model.SingInBean;
import com.wd.common.model.UpPicBean;
import com.wd.common.model.UpdateNameBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.databinding.ActivityMyBinding;
import com.wd.home.view.Activity_MySet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ActivityMy extends BaseActivity<MainViewModel, ActivityMyBinding> {

    private File file;
    private boolean flag;
    private PopupWindow popupWindow;

    @Override
    protected void startCoding() {
        //我的视频
        vdb.myVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){

                    startActivity(new Intent(ActivityMy.this,ActivityBuyVideo.class));
                }else {
                    Toast.makeText(ActivityMy.this, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ActivityMy.this, ActivityLoginReg.class));
                    finish();
                }
            }
        });
        //我的收藏
        vdb.myCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    startActivity(new Intent(ActivityMy.this,ActivityMyCollect.class));
                }else {
                    Toast.makeText(ActivityMy.this, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ActivityMy.this, ActivityLoginReg.class));
                    finish();
                }
            }
        });
        //我的钱包
        vdb.myMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    startActivity(new Intent(ActivityMy.this,ActivityMoney.class));
                }else {
                    Toast.makeText(ActivityMy.this, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ActivityMy.this, ActivityLoginReg.class));
                    finish();
                }
            }
        });
        //我的档案
        vdb.myDangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    startActivity(new Intent(ActivityMy.this,ActivityFile.class));
                }else {
                    Toast.makeText(ActivityMy.this, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ActivityMy.this, ActivityLoginReg.class));
                    finish();
                }

            }
        });
        //是否签到
        bvm.getIsSingInBeanMutableLiveData().observe(this,this::onChanged);
        String pic = SpUtils.getString("pic", "");
        Glide.with(ActivityMy.this).load(pic).circleCrop().into(vdb.myPic);
        //签到
        String s = vdb.myQd.getText().toString();
        if(s.equals("已签到")){

        }else {
            vdb.myQd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(flag){
                        bvm.getSingInBeanMutableLiveData().observe(ActivityMy.this,ActivityMy.this::onChanged);

                    }else {
                        Toast.makeText(ActivityMy.this, "请先登录", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ActivityMy.this, ActivityLoginReg.class));
                        finish();
                    }

                }
            });
        }
        View inflate = LayoutInflater.from(ActivityMy.this).inflate(R.layout.ppw_setname, null);
        TextView name = inflate.findViewById(R.id.setname);
        Button btn=inflate.findViewById(R.id.setbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = name.getText().toString();
                SpUtils.putString("name",s1);
                bvm.getUpdateNameBeanMutableLiveData(s1).observe(ActivityMy.this,ActivityMy.this::onChanged);
            }
        });
        //修改昵称
        vdb.myName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag){
                    popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
                    popupWindow.showAtLocation(inflate, Gravity.CENTER,0,0);
                    popupWindow.setContentView(inflate);


                }else {
                    Toast.makeText(ActivityMy.this, "请先登录", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ActivityMy.this, ActivityLoginReg.class));
                    finish();
                }

            }
        });
        String string = SpUtils.getString("name", "张梓萁");
        vdb.myName.setText(string);
        //点击打开相册换图片
        flag = SpUtils.getBoolean("flag", false);
        vdb.myPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,200);

                }else {
                    Toast.makeText(ActivityMy.this, "请先登录", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ActivityMy.this, ActivityLoginReg.class));
                    finish();
                }
            }
        });


//返回
        vdb.myBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置
        vdb.set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag){
                    startActivity(new Intent(ActivityMy.this, Activity_MySet.class));


                }else {
                    Toast.makeText(ActivityMy.this, "请先登录", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ActivityMy.this, ActivityLoginReg.class));
                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ContentResolver contentResolver = getContentResolver();
        if(requestCode==200 && resultCode==RESULT_OK){
            Glide.with(this).load(data.getData()).circleCrop().into(vdb.myPic);
            Uri data1 = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data1);
                sc(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //存储图片
    private void sc(Bitmap bitmap) {
        file = new File("data/data/com.wd.health/headpic.png");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                //压缩
                bitmap.compress(Bitmap.CompressFormat.JPEG,20,bufferedOutputStream);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
                bvm.getUpPicBeanMutableLiveData(image).observe(this,this::onChanged);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected int initLayout() {
        return R.layout.activity_my;
    }

    @Override
    public void onChanged(Object o) {
            if(o instanceof UpPicBean){
                UpPicBean bean= (UpPicBean) o;
                if(bean.getStatus().equals("0000")){
                    SpUtils.putString("pic",bean.getResult());
                    Toast.makeText(this, "图片上传成功", Toast.LENGTH_SHORT).show();
                }
            }else if (o instanceof IsSingInBean){
                IsSingInBean bean= (IsSingInBean) o;
                if(bean.getStatus().equals("0000")){
                    int result = bean.getResult();
                    if(result==2){
                        //未签到
                        vdb.myQd.setText("签到");
                    }else {
                        //已签到
                        vdb.myQd.setText("已签到");
                    }
                }
            }else if(o instanceof SingInBean){
                SingInBean bean= (SingInBean) o;
                if(bean.getStatus().equals("0000")){
                    Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                    vdb.myQd.setText("已签到");
                }else {
                    Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }else if( o instanceof UpdateNameBean){
                UpdateNameBean bean= (UpdateNameBean) o;
                if(bean.getStatus().equals("0000")){
                    Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                }
            }
    }

    @Override
    protected void onResume() {
        super.onResume();
        flag = SpUtils.getBoolean("flag", false);
    }
}