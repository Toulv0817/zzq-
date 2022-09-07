package com.wd.home;

import android.util.DisplayMetrics;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.common.BuyVideoListBean;
import com.wd.common.base.BaseActivity;
import com.wd.common.model.DpTools;
import com.wd.common.model.ScrollCalculatorHelper;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.adapter.BuyVideoAdapter;
import com.wd.home.databinding.ActivityBuyVideoBinding;

import java.util.List;

/**
@author 张梓萁
@date 2022/8/30
@desc 我的视频列表
*/
public class ActivityBuyVideo extends BaseActivity<MainViewModel, ActivityBuyVideoBinding> {


    @Override
    protected void startCoding() {
        bvm.getBuyVideoListBeanMutableLiveData(1,100).observe(this,this::onChanged);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_buy_video;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof BuyVideoListBean){
            BuyVideoListBean bean= (BuyVideoListBean) o;
            if(bean.getStatus().equals("0000")){
                List<BuyVideoListBean.ResultBean> result = bean.getResult();
                BuyVideoAdapter buyVideoAdapter = new BuyVideoAdapter(ActivityBuyVideo.this, R.layout.item_video, result);
                vdb.rec.setAdapter(buyVideoAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
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
        }
    }
}