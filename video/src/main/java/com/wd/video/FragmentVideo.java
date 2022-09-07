package com.wd.video;

import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.kd.easybarrage.Barrage;
import com.kd.easybarrage.BarrageView;
import com.wd.common.base.BaseFragment;
import com.wd.common.model.BarageBean;
import com.wd.common.model.BuyBean;
import com.wd.common.model.CancleScBean;
import com.wd.common.model.CollectBean;
import com.wd.common.model.DanMuBarBean;
import com.wd.common.model.DpTools;
import com.wd.common.model.HealthTabBean;
import com.wd.common.model.ScrollCalculatorHelper;
import com.wd.common.model.VideoBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.video.databinding.FragmentVideoBinding;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.video</p>
 * <p>简述:fragmentvdeo</p>
 *
 * @author 张梓萁
 * @date 2022/8/15
 */
public class FragmentVideo extends BaseFragment<MainViewModel, FragmentVideoBinding> {

    private int id;
    private BarrageView bar;

    @Override
    protected void startCoding() {
        bvm.getHealthTabBeanMutableLiveData().observe(this,this);
        bvm.getVideoBeanMutableLiveData(1,1,5).observe(this,this);
//        bvm.getDanMuBarBeanMutableLiveData(1).observe(getActivity(),this::onChanged);
//        bvm.getDanMuBarBeanMutableLiveData(1).observe(FragmentVideo.this,FragmentVideo.this::onChanged);
//        bvm.getDanMuBarBeanMutableLiveData(1).observe(getActivity(),this);
//        bvm.getDanMuBarBeanMutableLiveData(1).observe(FragmentVideo.this,FragmentVideo.this::onChanged);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_video;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof HealthTabBean){
            HealthTabBean bean= (HealthTabBean) o;
            List<HealthTabBean.ResultBean> result = bean.getResult();
            for (int i = 0; i < result.size(); i++) {
                vdb.videoTab.addTab(vdb.videoTab.newTab().setText(result.get(i).getName()));
            }
            vdb.videoTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    bvm.getVideoBeanMutableLiveData(result.get(tab.getPosition()).getId(),1,5);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }else if (o instanceof VideoBean){
            VideoBean bean= (VideoBean) o;
            List<VideoBean.ResultBean> result = bean.getResult();
//            CommonAdapter<VideoBean.ResultBean> adapter = new CommonAdapter<VideoBean.ResultBean>(getContext(), R.layout.item_video, result) {
//                @Override
//                protected void convert(ViewHolder holder, VideoBean.ResultBean resultBean, int position) {
//                    StandardGSYVideoPlayer sta = holder.getView(R.id.sta);
//                    id = resultBean.getId();
//                    sta.setUp(resultBean.getShearUrl(),true,resultBean.getTitle());
//
//                    //默认播放
////                    if (position == 0) {
////                        sta.startPlayLogic();
////                    }
//                    //横竖屏
//                    sta.setRotateViewAuto(true);
//                }
//            };
            VideoAdapter adapter = new VideoAdapter(getContext(), R.layout.item_video, result);


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            vdb.videoRec.setAdapter(adapter);
            vdb.videoRec.setLayoutManager(linearLayoutManager);
            vdb.videoRec.setOnFlingListener(null);
            //获取屏幕高度
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            //自定播放帮助类 限定范围为屏幕一半的上下偏移180   括号里不用在意 因为是一个item一个屏幕
            ScrollCalculatorHelper scrollCalculatorHelper = new ScrollCalculatorHelper(R.id.sta,
                    dm.heightPixels / 2 - DpTools.dip2px(getContext(), 180),
                    dm.heightPixels / 2 + DpTools.dip2px(getContext(), 180));
            //让RelativeLayout能有ViewPage的翻页效果
            PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
            pagerSnapHelper.attachToRecyclerView( vdb.videoRec);
            //监听
            vdb.videoRec.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    scrollCalculatorHelper.onScrollStateChanged( vdb.videoRec,newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                    scrollCalculatorHelper.onScroll(vdb.videoRec,firstVisibleItemPosition,lastVisibleItemPosition,1);
                }
            });

            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.ppw_fdm, null);
            Button but = inflate.findViewById(R.id.fdm_btn);
            TextView name = inflate.findViewById(R.id.fdm_name);
            PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
            //接口回调
            adapter.setClickItemLisenter(new VideoAdapter.ClickItemLisenter() {
                @Override
                public void dm(int id,boolean b) {

                    if(b){
                        bvm.getDanMuBarBeanMutableLiveData(id).observe(FragmentVideo.this,FragmentVideo.this::onChanged);
                    }else {
                        //销毁弹幕
                        vdb.bar.destroy();
                    }
                }
                //收藏
                @Override
                public void sc(int id, boolean b) {
                       if(b){
                           bvm.getCollectBeanMutableLiveData(id).observe(FragmentVideo.this,FragmentVideo.this::onChanged);
                       }else {
                           bvm.getCancleScBeanMutableLiveData(id).observe(FragmentVideo.this,FragmentVideo.this::onChanged);
                       }
                }
                //购买////发弹幕
                @Override
                public void gm(int id, int price,boolean b) {

                        if(b){


                            bvm.getBuyBeanMutableLiveData(id, price).observe(FragmentVideo.this,FragmentVideo.this::onChanged);
                            popupWindow.setContentView(inflate);
                            popupWindow.showAtLocation(inflate, Gravity.BOTTOM,0,200);
                            but.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String s = name.getText().toString();
                                    Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                                    bvm.getBarageBeanMutableLiveData(id,s).observe(FragmentVideo.this,FragmentVideo.this::onChanged);
                                }
                            });
                        }
                }

            });
        }else if (o instanceof DanMuBarBean){
            DanMuBarBean barBean= (DanMuBarBean) o;
            List<DanMuBarBean.ResultBean> result = barBean.getResult();
            for (int i = 0; i < result.size(); i++) {
//                new Barrage()
//                bar.addBarrage(new Barrage(result.get(i).getContent(),R.color.purple_700,true));
                Barrage barrage = new Barrage(result.get(i).getContent(),R.color.style_color, true);
                vdb.bar.addBarrage(barrage);
            }
        }else if(o instanceof CollectBean){
            CollectBean bean= (CollectBean) o;
            if(bean.getStatus().equals("0000")){
                Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else if(o instanceof BuyBean){
            BuyBean bean= (BuyBean) o;
            if(bean.getStatus().equals("0000")){
                Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else if(o instanceof BarageBean){
            BarageBean barageBean= (BarageBean) o;
            if(barageBean.getStatus().equals("0000")){
                Toast.makeText(getContext(), barageBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else if (o instanceof CancleScBean){
            CancleScBean bean= (CancleScBean) o;
            if(bean.getStatus().equals("0000")){
                Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
