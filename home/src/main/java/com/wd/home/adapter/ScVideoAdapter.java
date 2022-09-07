package com.wd.home.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.wd.common.model.ScVideoListBean;
import com.wd.home.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.home.adapter</p>
 * <p>简述:收藏视频列表</p>
 *
 * @author 张梓萁
 * @date 2022/8/30
 */
public class ScVideoAdapter extends CommonAdapter<ScVideoListBean.ResultBean> {
    boolean b=false,c=false,d=true;
    public ScVideoAdapter(Context context, int layoutId, List<ScVideoListBean.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ScVideoListBean.ResultBean resultBean, int position) {
        StandardGSYVideoPlayer sta = holder.getView(R.id.sta);
        sta.setUp(resultBean.getShearUrl(),true,resultBean.getTitle());

        //默认播放
//                    if (position == 0) {
//                        sta.startPlayLogic();
//                    }
        //横竖屏
        sta.setRotateViewAuto(true);
        ImageView dm = holder.getView(R.id.dm);
//        dm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        ImageView sc = holder.getView(R.id.sc1);
        ImageView gm = holder.getView(R.id.gm);
        //判断视频是否收藏
//        int whetherCollection = resultBean.getWhetherCollection();
//        if(whetherCollection==2){
//            //未收藏
//            sc.setImageResource(R.mipmap.common_button_collection_large_n);
//            c=true;
//        }else {
//
//            //收藏
//            sc.setImageResource(R.mipmap.common_button_collection_large_s);
//            c=false;
//        }
//        //判断是否已经购买
//        int whetherBuy = resultBean.getWhetherBuy();
//        if(whetherBuy==2){
//            //未购买
//            gm.setImageResource(R.mipmap.buy);
//            d=false;
//        }else {
//            //购买
//            gm.setImageResource(R.mipmap.bought);
//            d=true;
//        }
//        //弹幕
//        holder.setOnClickListener(R.id.dm, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(b){
//                    clickItemLisenter.dm(resultBean.getId(),b);
//                    dm.setImageResource(R.mipmap.common_icon_open_live_commenting_n);
//                    b=false;
//                }else {
//                    clickItemLisenter.dm(resultBean.getId(),b);
//                    dm.setImageResource(R.mipmap.common_icon_close_live_commenting_n);
//                    b=true;
//
//                }
//
//            }
//        });
//        //收藏
//        holder.setOnClickListener(R.id.sc1, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(c){
//                    clickItemLisenter.sc(resultBean.getId(),true);
//                    sc.setImageResource(R.mipmap.common_button_collection_large_s);
//                    c=false;
//                }else {
//                    clickItemLisenter.sc(resultBean.getId(),false);
//                    sc.setImageResource(R.mipmap.common_button_collection_large_n);
//                    c=true;
//                }
//            }
//        });
//        //购买//发弹幕
//        holder.setOnClickListener(R.id.gm, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(d){
//                    //购买
//                    gm.setImageResource(R.mipmap.bought);
//                    clickItemLisenter.gm(resultBean.getId(),resultBean.getPrice(),d);
//                    d=false;
//                    //发弹幕
//
//                }else {
//                    d=true;
//                }
//            }
//        });
    }
}
