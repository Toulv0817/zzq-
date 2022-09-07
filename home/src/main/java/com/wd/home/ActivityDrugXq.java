package com.wd.home;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.common.base.BaseActivity;
import com.wd.common.model.GrugXqBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.adapter.DragAdapter;
import com.wd.home.databinding.ActivityDrugXqBinding;

public class ActivityDrugXq extends BaseActivity<MainViewModel, ActivityDrugXqBinding> {


    @Override
    protected void startCoding() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        bvm.getGrugXqBeanMutableLiveData(id).observe(this,this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_drug_xq;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof GrugXqBean){
            GrugXqBean bean= (GrugXqBean) o;
            GrugXqBean.ResultBean result = bean.getResult();
            DragAdapter adapter = new DragAdapter(result, ActivityDrugXq.this);
            vdb.rec.setAdapter(adapter);
            vdb.rec.setLayoutManager(new LinearLayoutManager(ActivityDrugXq.this,RecyclerView.VERTICAL,false));
        }
    }
}