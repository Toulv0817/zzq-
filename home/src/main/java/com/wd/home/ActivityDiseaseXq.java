package com.wd.home;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.common.base.BaseActivity;
import com.wd.common.model.DiseaseBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.adapter.DiseaseAdapter;
import com.wd.home.databinding.ActivityDiseaseXqBinding;

/**
@author 张梓萁
@date 2022/8/17
@desc 病症详情
*/
public class ActivityDiseaseXq extends BaseActivity<MainViewModel, ActivityDiseaseXqBinding> {


    @Override
    protected void startCoding() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        bvm.getDiseaseBeanMutableLiveData(id).observe(this,this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_disease_xq;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof DiseaseBean){
            DiseaseBean bean= (DiseaseBean) o;
            DiseaseBean.ResultBean result = bean.getResult();
            DiseaseAdapter adapter = new DiseaseAdapter(result, ActivityDiseaseXq.this);
            vdb.rec.setAdapter(adapter);
            vdb.rec.setLayoutManager(new LinearLayoutManager(ActivityDiseaseXq.this, RecyclerView.VERTICAL,false));

        }
    }
}