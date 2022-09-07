package com.wd.home;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.stx.xhb.xbanner.XBanner;
import com.wd.common.base.BaseFragment;
import com.wd.common.base.SpUtils;
import com.wd.common.model.JkTabBean;
import com.wd.common.model.KeShiListBean;
import com.wd.common.model.XbaBean;
import com.wd.common.model.ZxlistBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.adapter.ZxListAdapter;
import com.wd.home.databinding.FragmentHomeBinding;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.home</p>
 * <p>简述:首页</p>
 *
 * @author 张梓萁
 * @date 2022/8/15
 */
public class FragmentHome extends BaseFragment<MainViewModel, FragmentHomeBinding>  {
    int i=0;
    String pic="https://t7.baidu.com/it/u=2405382010,1555992666&fm=193&f=GIF";
    private View view;

    @Override
    protected void startCoding() {
        //健康测评
        vdb.jkcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri = Uri.parse("https://www.wjx.cn/jq/33939807.aspx");
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                intent.setData(uri);
//                startActivity(intent);
                startActivity(new Intent(getContext(),ActivityUrl.class));


            }
        });
//        vdb.btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ARouter.getInstance().build("/login/login1").navigation();
//            }
//        });
//        if(view==null){
//            view = vdb.getRoot();
//        }
//
//
//        ViewGroup parent = (ViewGroup) view.getParent();
//        if(parent!=null){
//            parent.removeView(view);
//        }
        vdb.xba.removeAllViews();
        //科室列表
        bvm.getKeShiListBeanMutableLiveData().observe(this,this);
        //搜索
        vdb.homeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ActivitySearch.class));
            }
        });
        //轮播图
        bvm.getXbaBeanMutableLiveData().observe(this,this);
        bvm.getJkzx().observe(this,this);
        bvm.getZxlistBeanMutableLiveData(1,1,5).observe(this,this);
        Glide.with(getContext()).load(pic).circleCrop().into(vdb.homePic);
        //点击去登录
        vdb.homePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SpUtils.getBoolean("flag",false)){
                    Toast.makeText(getContext(), "已经登录过了", Toast.LENGTH_SHORT).show();
                    pic=SpUtils.getString("pic","");
                    Glide.with(getContext()).load(pic).circleCrop().into(vdb.homePic);
                    //去我的页面
                    startActivity(new Intent(getContext(),ActivityMy.class));
                }else {
                    ARouter.getInstance().build("/login/loginreg").navigation();
                }

            }
        });
        //试试arouter
        vdb.zsbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/aa/a").navigation();
            }
        });
        //常见病症
        vdb.cjbz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityZXBD.class);
                intent.putExtra("id",0);
                startActivity(intent);

            }
        });
        //常见药品
        vdb.cjyp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityZXBD.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof XbaBean){
            XbaBean bean= (XbaBean) o;
            List<XbaBean.ResultBean> result = bean.getResult();
            vdb.xba.setData(result,null);
            vdb.xba.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, View view, int position) {
                    Glide.with(getContext()).load(result.get(position).getImageUrl()).into((ImageView) view);
                }
            });

            //页面样式
//            vdb.xba.setPageTransformer(Transformer.Rotate);
//            vdb.xba.setPageChangeDuration(1000);//动画时长
        }else if(o instanceof JkTabBean){
            JkTabBean bean= (JkTabBean) o;
            List<JkTabBean.ResultBean> result = bean.getResult();
            for (int i = 0; i < result.size(); i++) {
                vdb.tab.addTab(vdb.tab.newTab().setText(result.get(i).getName()));
            }
            vdb.tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    bvm.getZxlistBeanMutableLiveData(result.get(tab.getPosition()).getId(),1,5);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        }else if(o instanceof ZxlistBean){
            ZxlistBean bean= (ZxlistBean) o;
            List<ZxlistBean.ResultBean> result = bean.getResult();
            ZxListAdapter adapter = new ZxListAdapter(result, getContext());
            vdb.rec.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
            vdb.rec.setAdapter(adapter);
            adapter.setLaaa(new ZxListAdapter.dj() {
                @Override
                public void onclick(int id) {
                    Intent intent = new Intent(getContext(), ActivityXq.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            });
        }else if(o instanceof KeShiListBean){
            //问诊咨询
            KeShiListBean bean= (KeShiListBean) o;
            List<KeShiListBean.ResultBean> result = bean.getResult();
            CommonAdapter<KeShiListBean.ResultBean> adapter = new CommonAdapter<KeShiListBean.ResultBean>(getContext(), R.layout.item_kslsit, result) {
                @Override
                protected void convert(ViewHolder holder, KeShiListBean.ResultBean keShiListBean, int position) {
                    holder.setText(R.id.ke_name,keShiListBean.getDepartmentName());
                    Glide.with(getContext()).load(keShiListBean.getPic()).into((ImageView) holder.getView(R.id.ke_pic));
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), ActivityConsultation.class);
                            intent.putExtra("id",keShiListBean.getId());
                            startActivity(intent);
                        }
                    });
                }
            };
            vdb.ks1Rec.setLayoutManager(new GridLayoutManager(getContext(),4));
            vdb.ks1Rec.setAdapter(adapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        pic=SpUtils.getString("pic","https://img1.baidu.com/it/u=2296682141,1611753079&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=359");
    }
}
