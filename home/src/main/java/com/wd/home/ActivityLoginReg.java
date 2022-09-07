package com.wd.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wd.common.base.BaseActivity;
import com.wd.common.base.RsaCoder;
import com.wd.common.base.SpUtils;
import com.wd.common.model.LoginBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.databinding.ActivityLoginRegBinding;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

/**
@author 张梓萁
@date 2022/8/16
@desc 登录注册
*/
@Route(path = "/login/loginreg")
public class ActivityLoginReg extends BaseActivity<MainViewModel, ActivityLoginRegBinding> {


    @Override
    protected void startCoding() {
        //微信登录
        vdb.wxLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/wx/login").navigation();
            }
        });
        //登录
        vdb.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取账号密码
                String email=vdb.loginEmail.getText().toString();
                String pwd=vdb.loginPwd.getText().toString();
                //密码加密
                try {
                    String s = RsaCoder.encryptByPublicKey(pwd);
                    bvm.getLoginBeanMutableLiveData(email,s).observe(ActivityLoginReg.this,ActivityLoginReg.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //立即注册
        vdb.ljzc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLoginReg.this,ActivityReg.class));
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_login_reg;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof LoginBean){
            LoginBean bean= (LoginBean) o;
            if(bean.getStatus().equals("0000")){

//                SpUtils.putString("pic",bean.getResult().getHeadPic());
//                SpUtils.putString("userid",bean.getResult().getUserId()+"");
//                SpUtils.putString("sessionid",bean.getResult().getSessionId()+"");
//                SpUtils.putBoolean("flag",true);
//                SpUtils.putString("pic","https://img1.baidu.com/it/u=1857076427,2148820439&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500");
//                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                String jiGuangPwd = bean.getResult().getJiGuangPwd();
                try {
                    String s = RsaCoder.decryptByPublicKey(jiGuangPwd);
                    String s1 = RsaCoder.MD5(s);
                    JMessageClient.login(bean.getResult().getUserName(), s1, new BasicCallback() {
                        @Override
                        public void gotResult(int i, String s) {
                            if(i==0){
                                Log.i("张梓萁的极光IM账号:",bean.getResult().getUserName());
                                SpUtils.putString("name",bean.getResult().getNickName());
                                SpUtils.putString("userid",bean.getResult().getUserId()+"");
                                SpUtils.putString("sessionid",bean.getResult().getSessionId()+"");
                                SpUtils.putString("pic",bean.getResult().getHeadPic());
                                Log.i("id",bean.getResult().getUserId()+","+bean.getResult().getSessionId());
                                SpUtils.putBoolean("flag",true);
                                startActivity(new Intent(ActivityLoginReg.this,ActivityMy.class));
                                Toast.makeText(ActivityLoginReg.this, "极光登录成功", Toast.LENGTH_SHORT).show();
                                finish();

                            }else {
                                Toast.makeText(ActivityLoginReg.this, "2222", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }else {
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}