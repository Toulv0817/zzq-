package com.wd.home;

import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.wd.common.base.BaseActivity;
import com.wd.common.model.DpTools;
import com.wd.common.model.ScByqListBean;
import com.wd.common.model.ScVideoListBean;
import com.wd.common.model.ScZxListBean;
import com.wd.common.model.ScrollCalculatorHelper;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.adapter.ScVideoAdapter;
import com.wd.home.adapter.ZxScListAdapter;
import com.wd.home.databinding.ActivityMyCollectBinding;
import com.wd.patientcircle.ActivityPatientCircleXq;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
@author 张梓萁
@date 2022/8/29
@desc 我的收藏
*/
public class ActivityMyCollect extends BaseActivity<MainViewModel, ActivityMyCollectBinding> {
    String[] tatil={"健康咨询","健康视频","病友圈"};

    @Override
    protected void startCoding() {
        bvm.getScZxListBeanMutableLiveData(1,100).observe(ActivityMyCollect.this,ActivityMyCollect.this::onChanged);
        for (int i = 0; i < tatil.length; i++) {
            vdb.tab.addTab(vdb.tab.newTab().setText(tatil[i]));
        }
        vdb.tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        //健康资讯
                        bvm.getScZxListBeanMutableLiveData(1,100).observe(ActivityMyCollect.this,ActivityMyCollect.this::onChanged);
                        break;
                    case 1:
                        //健康视频
                        bvm.getScVideoListBeanMutableLiveData(1,100).observe(ActivityMyCollect.this,ActivityMyCollect.this::onChanged);
                        break;
                    case 2:
                        //病友圈
                        bvm.getScByqListBeanMutableLiveData(1,100).observe(ActivityMyCollect.this,ActivityMyCollect.this::onChanged);

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_my_collect;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof ScZxListBean){
            ScZxListBean bean= (ScZxListBean) o;
            if(bean.getStatus().equals("0000")){
                List<ScZxListBean.ResultBean> result = bean.getResult();
                ZxScListAdapter adapter = new ZxScListAdapter(bean.getResult(), this);
                vdb.rec.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
                vdb.rec.setAdapter(adapter);
            }
        }else if(o instanceof ScVideoListBean){
            ScVideoListBean bean= (ScVideoListBean) o;
            if(bean.getStatus().equals("0000")){
                List<ScVideoListBean.ResultBean> result = bean.getResult();
                ScVideoAdapter scVideoAdapter = new ScVideoAdapter(this, R.layout.item_scvideo, result);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                vdb.rec.setAdapter(scVideoAdapter);
                vdb.rec.setLayoutManager(linearLayoutManager);
                vdb.rec.setOnFlingListener(null);
                //获取屏幕高度
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                //自定播放帮助类 限定范围为屏幕一半的上下偏移180   括号里不用在意 因为是一个item一个屏幕
                ScrollCalculatorHelper scrollCalculatorHelper = new ScrollCalculatorHelper(R.id.sta,
                        dm.heightPixels / 2 - DpTools.dip2px(this, 180),
                        dm.heightPixels / 2 + DpTools.dip2px(this, 180));
                //让RelativeLayout能有ViewPage的翻页效果
                PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
                pagerSnapHelper.attachToRecyclerView( vdb.rec);
                //监听
                vdb.rec.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        scrollCalculatorHelper.onScrollStateChanged( vdb.rec,newState);
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                        scrollCalculatorHelper.onScroll(vdb.rec,firstVisibleItemPosition,lastVisibleItemPosition,1);
                    }
                });
            }
        }else if(o instanceof ScByqListBean){
            ScByqListBean bean= (ScByqListBean) o;
            if(bean.getStatus().equals("0000")){
                List<ScByqListBean.ResultBean> result = bean.getResult();
                CommonAdapter<ScByqListBean.ResultBean> adapter = new CommonAdapter<ScByqListBean.ResultBean>(this,R.layout.item_byq, result) {
                    @Override
                    protected void convert(ViewHolder holder, ScByqListBean.ResultBean resultBean, int position) {
                        holder.setText(com.wd.patientcircle.R.id.patient_name,resultBean.getTitle());
                        holder.setText(com.wd.patientcircle.R.id.patient_tiltle,resultBean.getId()+"");
                        holder.setText(com.wd.patientcircle.R.id.patient_time,resultBean.getCreateTime()+"");
                        holder.setText(com.wd.patientcircle.R.id.patient_jy,resultBean.getCollectionNum()+"");
                        holder.setText(com.wd.patientcircle.R.id.patient_sc,resultBean.getCommentNum()+"");
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //跳转详情
                                Intent intent = new Intent(ActivityMyCollect.this, ActivityPatientCircleXq.class);
                                int sickCircleId = resultBean.getSickCircleId();
                                intent.putExtra("id",sickCircleId);
                                startActivity(intent);
                            }
                        });
                    }
                };

                vdb.rec.setAdapter(adapter);
                vdb.rec.setLayoutManager(new LinearLayoutManager(ActivityMyCollect.this));
            }
        }
    }
}