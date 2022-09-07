package com.wd.video;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.wd.common.model.VideoBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.video</p>
 * <p>简述:适配</p>
 *
 * @author 张梓萁
 * @date 2022/8/19
 */
public class VideoAdapter extends CommonAdapter<VideoBean.ResultBean> {
    boolean b=false,c=false,d=true;
    public VideoAdapter(Context context, int layoutId, List<VideoBean.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, VideoBean.ResultBean resultBean, int position) {
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
        int whetherCollection = resultBean.getWhetherCollection();
        if(whetherCollection==2){
            //未收藏
            sc.setImageResource(R.mipmap.common_button_collection_large_n);
            c=true;
        }else {

            //收藏
            sc.setImageResource(R.mipmap.common_button_collection_large_s);
            c=false;
        }
        //判断是否已经购买
        int whetherBuy = resultBean.getWhetherBuy();
        if(whetherBuy==2){
            //未购买
            gm.setImageResource(R.mipmap.buy);
            d=false;
        }else {
            //购买
            gm.setImageResource(R.mipmap.bought);
            d=true;
        }
        //弹幕
        holder.setOnClickListener(R.id.dm, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b){
                    clickItemLisenter.dm(resultBean.getId(),b);
                    dm.setImageResource(R.mipmap.common_icon_open_live_commenting_n);
                    b=false;
                }else {
                    clickItemLisenter.dm(resultBean.getId(),b);
                    dm.setImageResource(R.mipmap.common_icon_close_live_commenting_n);
                    b=true;

                }

            }
        });
        //收藏
        holder.setOnClickListener(R.id.sc1, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c){
                    clickItemLisenter.sc(resultBean.getId(),true);
                     sc.setImageResource(R.mipmap.common_button_collection_large_s);
                     c=false;
                }else {
                    clickItemLisenter.sc(resultBean.getId(),false);
                    sc.setImageResource(R.mipmap.common_button_collection_large_n);
                    c=true;
                }
            }
        });
        //购买//发弹幕
        holder.setOnClickListener(R.id.gm, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(d){
                    //购买
                    gm.setImageResource(R.mipmap.bought);
                    clickItemLisenter.gm(resultBean.getId(),resultBean.getPrice(),d);
                    d=false;
                    //发弹幕

                }else {
                    d=true;
                }
            }
        });
    }
    //接口回调
        //1.自定义接口  自定义方法  参数为需要用的数据
        public  interface ClickItemLisenter{
            void dm(int id,boolean b);
            void sc(int id,boolean b);
            void gm(int id,int price,boolean b);
        }
        //2.将接口设置为变量
        private  ClickItemLisenter clickItemLisenter;

        //3.提供set方法
        public void setClickItemLisenter(ClickItemLisenter clickItemLisenter) {
            this.clickItemLisenter = clickItemLisenter;
        }
}
