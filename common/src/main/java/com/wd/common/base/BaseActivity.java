package com.wd.common.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import cn.jpush.im.android.api.JMessageClient;

/**
 * <p>项目名称:Zzq0812</p>
 * <p>包名:com.wd.common.base</p>
 * <p>简述:基类activity</p>
 *
 * @author 张梓萁
 * @date 2022/8/15
 */
public abstract class BaseActivity <O extends BaseViewModel,V extends ViewDataBinding> extends AppCompatActivity implements Observer {
    public O bvm;
    public V vdb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SpUtils.initSp(this);

        vdb= DataBindingUtil.setContentView(this,initLayout());
        initViewModel();
        startCoding();
    }

    protected abstract void startCoding();

    private void initViewModel() {
        if(bvm==null){
            Class cl=null;
            Type type = getClass().getGenericSuperclass();
            if(type instanceof ParameterizedType){
               cl= (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
            }else {
                cl=getClass();
            }
            bvm= (O) ViewModelProviders.of(this).get(cl);
        }
    }


    protected abstract int initLayout();
}
