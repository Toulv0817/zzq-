package com.wd.patientcircle;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.tabs.TabLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wd.common.base.BaseFragment;
import com.wd.common.model.KeShiListBean;
import com.wd.common.model.PatientCircleBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.patientcircle.databinding.FragmentByqBinding;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.patientcircle</p>
 * <p>简述:病友圈</p>
 *
 * @author 张梓萁
 * @date 2022/8/18
 */
public class FragmentPatientCircle extends BaseFragment<MainViewModel, FragmentByqBinding> {
    private int page=1;
    private int id;
    List<PatientCircleBean.ResultBean> list=new ArrayList<>();
    @Override
    protected void startCoding() {
        bvm.getKeShiListBeanMutableLiveData().observe(getActivity(),this::onChanged);
//        bvm.getPatientCircleBeanMutableLiveData(7,2,100).observe(getActivity(),this::onChanged);
        bvm.getPatientCircleBeanMutableLiveData(7,1,5).observe(FragmentPatientCircle.this,FragmentPatientCircle.this::onChanged);

    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_byq;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof KeShiListBean){
            KeShiListBean bean= (KeShiListBean) o;
            List<KeShiListBean.ResultBean> result = bean.getResult();
            for (int i = 0; i < result.size(); i++) {
                vdb.byqTab.addTab(vdb.byqTab.newTab().setText(result.get(i).getDepartmentName()));
            }
            vdb.byqTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    id = result.get(tab.getPosition()).getId();
                    bvm.getPatientCircleBeanMutableLiveData(result.get(tab.getPosition()).getId(),1,5).observe(FragmentPatientCircle.this,FragmentPatientCircle.this::onChanged);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            vdb.bqySm.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    page=1;
                    bvm.getPatientCircleBeanMutableLiveData(id,page,5);
                    vdb.bqySm.finishRefresh();
                }
            });
            vdb.bqySm.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    page++;
                    bvm.getPatientCircleBeanMutableLiveData(id,page,5);
                    vdb.bqySm.finishLoadMore();
                }
            });
        }else if(o instanceof PatientCircleBean){
            PatientCircleBean bean= (PatientCircleBean) o;
            List<PatientCircleBean.ResultBean> result = bean.getResult();
            if(result.size()>0 ){
                if(page==1){
                    list.clear();
                }
                list.addAll(result);
                if(list.size()>0){
                    CommonAdapter<PatientCircleBean.ResultBean> adapter = new CommonAdapter<PatientCircleBean.ResultBean>(getContext(), R.layout.item_byq, list) {
                        @Override
                        protected void convert(ViewHolder holder, PatientCircleBean.ResultBean resultBean, int position) {
                            holder.setText(R.id.patient_name,resultBean.getTitle());
                            holder.setText(R.id.patient_tiltle,resultBean.getDetail());
                            holder.setText(R.id.patient_time,resultBean.getReleaseTime()+"");
                            holder.setText(R.id.patient_jy,resultBean.getCollectionNum()+"");
                            holder.setText(R.id.patient_sc,resultBean.getCommentNum()+"");
                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //跳转详情
                                    Intent intent = new Intent(getContext(), ActivityPatientCircleXq.class);
                                    int sickCircleId = resultBean.getSickCircleId();
                                    intent.putExtra("id",sickCircleId);
                                    startActivity(intent);
                                }
                            });
                        }
                    };
                    vdb.byqRec.setAdapter(adapter);
                    vdb.byqRec.setLayoutManager(new LinearLayoutManager(getContext()));
                }else {
                    Toast.makeText(getContext(), "该科室没有数据", Toast.LENGTH_SHORT).show();
                }


            }

        }
    }
}
