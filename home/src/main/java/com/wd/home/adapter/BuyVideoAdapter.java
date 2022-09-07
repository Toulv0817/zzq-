package com.wd.home.adapter;

import android.content.Context;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.wd.common.BuyVideoListBean;
import com.wd.home.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.home.adapter</p>
 * <p>简述:购买视频列表适配器</p>
 *
 * @author 张梓萁
 * @date 2022/8/30
 */
public class BuyVideoAdapter extends CommonAdapter<BuyVideoListBean.ResultBean> {

    boolean b=false,c=false,d=true;
    public BuyVideoAdapter(Context context, int layoutId, List<BuyVideoListBean.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, BuyVideoListBean.ResultBean resultBean, int position) {

        StandardGSYVideoPlayer sta = holder.getView(R.id.sta);
        //默认播放
//                    if (position == 0) {
//                        sta.startPlayLogic();
//                    }
        //横竖屏
        sta.setRotateViewAuto(true);
    }
}
