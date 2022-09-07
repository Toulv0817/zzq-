package com.wd.health;

import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wd.common.base.BaseActivity;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.health.databinding.ActivityMainWxBinding;
@Route(path = "/wx/login")
public class MainActivityWx extends BaseActivity<MainViewModel, ActivityMainWxBinding> {


    @Override
    protected void startCoding() {
        vdb.wxLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否安装过微信
                if(WXUtils.success(MainActivityWx.this)){
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wx_login_duzun";
                    WXUtils.reg(MainActivityWx.this).sendReq(req);
                }else {
                    Toast.makeText(MainActivityWx.this, "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main_wx;
    }

    @Override
    public void onChanged(Object o) {

    }
}