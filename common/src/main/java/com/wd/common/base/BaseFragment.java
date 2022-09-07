package com.wd.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <p>项目名称:Zzq0812</p>
 * <p>包名:com.wd.common.base</p>
 * <p>简述:fragment基类</p>
 *
 * @author 张梓萁
 * @date 2022/8/15
 */
public abstract class BaseFragment<O extends BaseViewModel,V extends ViewDataBinding> extends Fragment implements Observer {
    public O bvm;
    public V vdb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vdb= DataBindingUtil.inflate(inflater,initLayout(),container,false);
        return vdb.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SpUtils.initSp(getContext());
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
