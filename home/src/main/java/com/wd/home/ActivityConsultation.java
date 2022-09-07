package com.wd.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.wd.common.base.BaseActivity;
import com.wd.common.model.DoctorConsultBean;
import com.wd.common.model.KeShiListBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.databinding.ActivityConsultationBinding;
import com.wd.home.view.ActivitySay;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
@author 张梓萁
@date 2022/8/18
@desc 问诊咨询
*/
public class ActivityConsultation extends BaseActivity<MainViewModel, ActivityConsultationBinding> {

    String[] name={"综合","好评","咨询数","价格"};
    private int id;
    private PopupWindow window;
    private int doctorId;

    @SuppressLint("ResourceType")
    @Override
    protected void startCoding() {
        //科室tab
        bvm.getKeShiListBeanMutableLiveData().observe(this,this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        //医生
        bvm.getDoctorConsultBeanMutableLiveData(id,1,1,1,5).observe(this,this);
        for (int i = 0; i < name.length; i++) {
            vdb.goodTab.addTab(vdb.goodTab.newTab().setText(name[i]));
        }
        View view = LayoutInflater.from(ActivityConsultation.this).inflate(R.layout.ppw_zx,null);

        Button qx = view.findViewById(R.id.qx);
        Button zx = view.findViewById(R.id.zx);
        //点击咨询弹框
        vdb.wzzxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                window = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                window.setContentView(view);
                window.showAtLocation(view, Gravity.CENTER,200,0);

            }
        });
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        //去咨询
        zx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityConsultation.this, ActivitySay.class));
            }
        });
//        vdb.wzzxBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityConsultation.this);
//                builder.setMessage("本次咨询将扣除500H币");
//                builder.setPositiveButton("去咨询", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                builder.show();
//            }
//        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_consultation;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof DoctorConsultBean){
            DoctorConsultBean bean= (DoctorConsultBean) o;
            List<DoctorConsultBean.ResultBean> result = bean.getResult();
            if(result.size()==0){
                Toast.makeText(this, "此科室没有医生", Toast.LENGTH_SHORT).show();
            }else {
                int i=0;
//                Glide.with(ActivityConsultation.this).load(result.get(i).getImagePic()).into(vdb.wzPic);
                vdb.wzName.setText(result.get(i).getDoctorName());
                vdb.wzCity.setText(result.get(i).getInauguralHospital());
                vdb.wzPosition.setText(result.get(i).getJobTitle());
                vdb.wzGood.setText("好评率"+result.get(i).getPraise());
                Glide.with(this).load(result.get(i).getImagePic()).into(vdb.wzPic);
                vdb.wzNum.setText("服务患者数"+result.get(i).getServerNum());
                doctorId = result.get(i).getDoctorId();

                CommonAdapter<DoctorConsultBean.ResultBean> adapter = new CommonAdapter<DoctorConsultBean.ResultBean>(ActivityConsultation.this, R.layout.item_consult, result) {
                    @Override
                    protected void convert(ViewHolder holder, DoctorConsultBean.ResultBean resultBean, int position) {
                        holder.setText(R.id.con_name, resultBean.getDoctorName());
                        Glide.with(ActivityConsultation.this).load(resultBean.getImagePic()).into((ImageView) holder.getView(R.id.con_pic));
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                doctorId=resultBean.getDoctorId();
                                vdb.wzName.setText(resultBean.getDoctorName());
                                vdb.wzCity.setText(resultBean.getInauguralHospital());
                                vdb.wzPosition.setText(resultBean.getJobTitle());
                                Glide.with(ActivityConsultation.this).load(resultBean.getImagePic()).into(vdb.wzPic);
                                vdb.wzGood.setText("好评率"+resultBean.getPraise());
                                vdb.wzNum.setText("服务患者数"+resultBean.getServerNum());
                            }
                        });
                    }
                };
                vdb.rec.setAdapter(adapter);
                vdb.rec.setLayoutManager(new LinearLayoutManager(ActivityConsultation.this, RecyclerView.HORIZONTAL,false));
                vdb.wzPic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ActivityConsultation.this, "allala", Toast.LENGTH_SHORT).show();
                        //跳转医生详情
                        Intent intent = new Intent(ActivityConsultation.this, ActivityDoctorXq.class);
                        intent.putExtra("id",doctorId);
                        startActivity(intent);
                    }
                });
            }
//            for (int i = 0; i < result.size(); i++) {
//
//            }
        }else if(o instanceof KeShiListBean){
            KeShiListBean bean= (KeShiListBean) o;
            List<KeShiListBean.ResultBean> result = bean.getResult();
            for (int i = 0; i < result.size(); i++) {
                vdb.ksTab.addTab(vdb.ksTab.newTab().setText(result.get(i).getDepartmentName()));
            }
            switch (id){
                case 7:vdb.ksTab.getTabAt(0).select();break;
                case 4:vdb.ksTab.getTabAt(1).select();break;
                case 2:vdb.ksTab.getTabAt(2).select();break;
                case 5:vdb.ksTab.getTabAt(3).select();break;
                case 12:vdb.ksTab.getTabAt(4).select();break;
                case 9:vdb.ksTab.getTabAt(5).select();break;
                case 6:vdb.ksTab.getTabAt(6).select();break;
                case 1:vdb.ksTab.getTabAt(7).select();break;
            }
            //科室tab
            vdb.ksTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    bvm.getDoctorConsultBeanMutableLiveData(result.get(tab.getPosition()).getId(),
                            vdb.goodTab.getSelectedTabPosition()+1,1,1,5).observe(ActivityConsultation.this,ActivityConsultation.this);

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            //好评tab
            vdb.goodTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    bvm.getDoctorConsultBeanMutableLiveData(result.get(vdb.ksTab.getSelectedTabPosition()).getId(),
                            tab.getPosition()+1,1,1,5).observe(ActivityConsultation.this,ActivityConsultation.this);

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }
}