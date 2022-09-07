package com.wd.home;

import android.view.View;
import android.widget.Toast;

import com.wd.common.base.BaseActivity;
import com.wd.common.base.RsaCoder;
import com.wd.common.model.RegBean;
import com.wd.common.model.SendCodeBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.databinding.ActivityRegBinding;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.home</p>
 * <p>简述:注册</p>
 *
 * @author 张梓萁
 * @date 2022/8/16
 */
public class ActivityReg extends BaseActivity<MainViewModel, ActivityRegBinding> {
    @Override
    protected void startCoding() {
        //发送验证码
        vdb.sendYzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = vdb.regEmail.getText().toString();
                    bvm.getSendCodeBeanMutableLiveData(s).observe(ActivityReg.this,ActivityReg.this);
            }
        });
        //注册
        vdb.regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = vdb.regEmail.getText().toString();
                String yzm = vdb.regYzm.getText().toString();
                String p1 = vdb.regPwd1.getText().toString();
                String p2 = vdb.regPwd2.getText().toString();
                String yqm = vdb.regYqm.getText().toString();
                //加密
                try {
                    String pwd1 = RsaCoder.encryptByPublicKey(p1);
                    String pwd2 = RsaCoder.encryptByPublicKey(p2);
                    bvm.getRegBeanMutableLiveData(s,yzm,pwd1,pwd1,yqm).observe(ActivityReg.this,ActivityReg.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_reg;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof SendCodeBean){
            SendCodeBean bean= (SendCodeBean) o;
            if(bean.getStatus().equals("0000")){
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else if(o instanceof RegBean){
            RegBean bean= (RegBean) o;
            if(bean.getStatus().equals("0000")){
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
