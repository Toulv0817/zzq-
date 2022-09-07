package com.wd.home;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.wd.common.base.BaseFragment;
import com.wd.common.base.SpUtils;
import com.wd.common.model.KeShiListBean;
import com.wd.common.model.KeShiSearchBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.databinding.FragmentDrugBinding;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class FragmentDrug extends BaseFragment<MainViewModel, FragmentDrugBinding> {


    @Override
    protected void startCoding() {
        bvm.getKeShiListBeanMutableLiveData().observe(this,this);
        bvm.getKeShiSearchBeanMutableLiveData(7).observe(this,this);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_drug;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof KeShiListBean){
            KeShiListBean bean= (KeShiListBean) o;
            ArrayList<String> name = new ArrayList<>();
            List<KeShiListBean.ResultBean> result = bean.getResult();
            for (int i = 0; i < result.size(); i++) {
                name.add(result.get(i).getDepartmentName());
            }
            vdb.drugTab.addTab(new QTabView(getContext()));
            vdb.drugTab.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabView tab, int position) {
                    bvm.getKeShiSearchBeanMutableLiveData(result.get(position).getId());
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
                            .setContent(result.get(position).getDepartmentName())//设置数据 也有设置字体颜色的方法
                            .build();

                    return title;
                }

                @Override
                public int getBackground(int position) {
                    return  R.color.white;
                }
            });
//            for (int i = 0; i < result.size(); i++) {
//                vdb.drugTab.addTab(n);
//            }
        }else if(o instanceof KeShiSearchBean){
            KeShiSearchBean bean= (KeShiSearchBean) o;
            List<KeShiSearchBean.ResultBean> result = bean.getResult();
            CommonAdapter<KeShiSearchBean.ResultBean> adapter = new CommonAdapter<KeShiSearchBean.ResultBean>(getContext(), R.layout.item_drug1, result) {
                @Override
                protected void convert(ViewHolder holder, KeShiSearchBean.ResultBean resultBean, int position) {
                    holder.setText(R.id.bz_name, resultBean.getName());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), ActivityDiseaseXq.class);
                            intent.putExtra("id",resultBean.getId());
                            SpUtils.putString("diseasename",resultBean.getName());
                            startActivity(intent);
                        }
                    });
                }
            };
            vdb.drugRec.setAdapter(adapter);
            vdb.drugRec.setLayoutManager(new GridLayoutManager(getContext(),2));
        }
    }
}