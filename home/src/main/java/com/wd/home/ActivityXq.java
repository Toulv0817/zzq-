package com.wd.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wd.common.base.BaseActivity;
import com.wd.common.model.CancleZxScBean;
import com.wd.common.model.ScZxBean;
import com.wd.common.model.XqBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.databinding.ActivityXqBinding;

public class ActivityXq extends BaseActivity<MainViewModel, ActivityXqBinding> {

    private int id;
    private int whetherCollection;

    @Override
    protected void startCoding() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        bvm.getXqBeanMutableLiveData(id).observe(ActivityXq.this,this);
//        if(whetherCollection ==0){
//            vdb.zxxqSc.setImageResource(R.mipmap.common_button_collection_large_n);
//            vdb.zxxqSc.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //收藏
//                    bvm.getScZxBeanMutableLiveData(id).observe(ActivityXq.this,ActivityXq.this::onChanged);
//                    vdb.zxxqSc.setImageResource(R.mipmap.common_button_collection_large_s);
//                    whetherCollection=1;
//                }
//            });
//        }else {
//            vdb.zxxqSc.setImageResource(R.mipmap.common_button_collection_large_s);
//            vdb.zxxqSc.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //取消收藏
//                    bvm.getCancleZxScBeanMutableLiveData(id).observe(ActivityXq.this,ActivityXq.this::onChanged);
//                    vdb.zxxqSc.setImageResource(R.mipmap.common_button_collection_large_n);
//                    whetherCollection=0;
//                }
//            });
//        }
        vdb.zxxqSc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(whetherCollection ==0){
//                        vdb.zxxqSc.setImageResource(R.mipmap.common_button_collection_large_n);
                    //收藏
                    bvm.getScZxBeanMutableLiveData(id).observe(ActivityXq.this,ActivityXq.this::onChanged);
                    vdb.zxxqSc.setImageResource(R.mipmap.common_button_collection_large_s);
                    whetherCollection=1;
                }else {
//                        vdb.zxxqSc.setImageResource(R.mipmap.common_button_collection_large_s);
                    //取消收藏
                    bvm.getCancleZxScBeanMutableLiveData(id).observe(ActivityXq.this,ActivityXq.this::onChanged);
                    vdb.zxxqSc.setImageResource(R.mipmap.common_button_collection_large_n);
                    whetherCollection=0;
                }
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_xq;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof XqBean){

            XqBean bean= (XqBean) o;
            whetherCollection = bean.getResult().getWhetherCollection();
            Log.i("资讯收藏", whetherCollection +"");
            vdb.zxxqSc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(whetherCollection ==0){
//                        vdb.zxxqSc.setImageResource(R.mipmap.common_button_collection_large_n);
                        //收藏
                        bvm.getScZxBeanMutableLiveData(id).observe(ActivityXq.this,ActivityXq.this::onChanged);
                        vdb.zxxqSc.setImageResource(R.mipmap.common_button_collection_large_s);
                        whetherCollection=1;
                    }else {
//                        vdb.zxxqSc.setImageResource(R.mipmap.common_button_collection_large_s);
                        //取消收藏
                        bvm.getCancleZxScBeanMutableLiveData(id).observe(ActivityXq.this,ActivityXq.this::onChanged);
                        vdb.zxxqSc.setImageResource(R.mipmap.common_button_collection_large_n);
                        whetherCollection=0;
                    }
                }
            });
            if(whetherCollection ==0){
                vdb.zxxqSc.setImageResource(R.mipmap.common_button_collection_large_n);

            }else {
                vdb.zxxqSc.setImageResource(R.mipmap.common_button_collection_large_s);
            }

            vdb.web.loadData(bean.getResult().getContent(),"text/html", "UTF-8");
        }else if( o instanceof ScZxBean){
            ScZxBean bean= (ScZxBean) o;
            if(bean.getStatus().equals("0000")){
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else if(o instanceof CancleZxScBean){
            CancleZxScBean bean= (CancleZxScBean) o;
            if(bean.getStatus().equals("0000")){
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}