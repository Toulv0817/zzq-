package com.wd.home.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import com.bumptech.glide.Glide;
import com.wd.common.base.BaseActivity;
import com.wd.common.base.SpUtils;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.R;
import com.wd.home.databinding.ActivityMySetBinding;

/**
@author 张梓萁
@date 2022/8/19
@desc 设置
*/
public class Activity_MySet extends BaseActivity<MainViewModel, ActivityMySetBinding> {

    @Override
    protected void startCoding() {
        String string = SpUtils.getString("name", "张梓萁");
        String pic = SpUtils.getString("pic", "");
        vdb.name.setText(string);
        Glide.with(this).load(pic).circleCrop().into(vdb.pci);
        vdb.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //退出登录弹框
        vdb.backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_MySet.this);
                builder.setMessage("你确定要退出登录?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SpUtils.putBoolean("flag",false);
                        finish();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity__my_set;
    }

    @Override
    public void onChanged(Object o) {

    }
}