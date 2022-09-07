package com.wd.home;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.wd.common.base.BaseFragment;
import com.wd.common.base.SpUtils;
import com.wd.common.model.KeShiList2Bean;
import com.wd.common.model.KeShiSearch2Bean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.databinding.FragmentDrug2Binding;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.home</p>
 * <p>简述:</p>
 *
 * @author 张梓萁
 * @date 2022/8/16
 */
public class FragmentDrug2 extends BaseFragment<MainViewModel, FragmentDrug2Binding> {

    @Override
    protected void startCoding() {
        bvm.getShiList2BeanMutableLiveData().observe(this,this);
        bvm.getKeShiSearch2BeanMutableLiveData(2,1,1000).observe(this,this);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_drug2;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof KeShiList2Bean){
            KeShiList2Bean bean= (KeShiList2Bean) o;
            ArrayList<String> name = new ArrayList<>();
            List<KeShiList2Bean.ResultBean> result = bean.getResult();
            for (int i = 0; i < result.size(); i++) {
                name.add(result.get(i).getName());
            }
            vdb.drugTab.addTab(new QTabView(getContext()));
            vdb.drugTab.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabView tab, int position) {
                    bvm.getKeShiSearch2BeanMutableLiveData(result.get(position).getId(),1,1000);
                }

                @Override
                public void onTabReselected(TabView tab, int position) {

                }
            });
            vdb.drugTab.setTabAdapter(new TabAdapter() {
                @Override
                public int getCount() {
                    return result.size();
                }

                @Override
                public ITabView.TabBadge getBadge(int position) {
                    return null;
                }

                @Override
                public ITabView.TabIcon getIcon(int position) {
                    return null;
                }

                @Override
                public ITabView.TabTitle getTitle(int position) {
                    QTabView.TabTitle title = new QTabView.TabTitle.Builder()
                            .setContent(result.get(position).getName())//设置数据 也有设置字体颜色的方法

                            .build();

                    return title;
                }

                @Override
                public int getBackground(int position) {
                    return  R.color.white ;
                }
            });
//            for (int i = 0; i < result.size(); i++) {
//                vdb.drugTab.addTab(n);
//            }
        }else if(o instanceof KeShiSearch2Bean){
            KeShiSearch2Bean bean= (KeShiSearch2Bean) o;
            List<KeShiSearch2Bean.ResultBean> result = bean.getResult();
            CommonAdapter<KeShiSearch2Bean.ResultBean> adapter = new CommonAdapter<KeShiSearch2Bean.ResultBean>(getContext(), R.layout.item_drug2, result) {
                @Override
                protected void convert(ViewHolder holder, KeShiSearch2Bean.ResultBean resultBean, int position) {
                    holder.setText(R.id.bz_name, resultBean.getName());
                    Glide.with(getContext()).load(resultBean.getPicture()).into((ImageView) holder.getView(R.id.bz_pic));
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), ActivityDrugXq.class);
                            intent.putExtra("id",resultBean.getId());
                            SpUtils.putString("diseasename",resultBean.getName());
                            startActivity(intent);
                        }
                    });
                }
            };
            vdb.drugRec1.setAdapter(adapter);
            vdb.drugRec1.setLayoutManager(new GridLayoutManager(getContext(),2));
        }
    }
}
