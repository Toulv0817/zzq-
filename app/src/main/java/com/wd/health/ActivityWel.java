package com.wd.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.wd.common.base.BaseActivity;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.health.databinding.ActivityWelBinding;

/**
@author 张梓萁
@date 2022/8/15
@desc 欢迎页
*/
public class ActivityWel extends BaseActivity<MainViewModel, ActivityWelBinding> {
    private int i=3;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            vdb.jump.setText(i+"秒");

            if(msg.what==1){

                if(i==0){
                    //跳转
                    Intent intent = new Intent(ActivityWel.this, Activityhd.class);
                    startActivity(intent);
                    finish();
                    handler.removeMessages(1);
                }else {
                    i--;
                    sendEmptyMessageDelayed(1,1000);
                }

            }
        }
    };
    @Override
    protected void startCoding() {
        handler.sendEmptyMessageDelayed(1,1000);
        vdb.jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //跳转
                Intent intent = new Intent(ActivityWel.this, Activityhd.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler!=null){
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_wel;
    }

    @Override
    public void onChanged(Object o) {

    }
}