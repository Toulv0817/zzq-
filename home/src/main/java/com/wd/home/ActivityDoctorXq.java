package com.wd.home;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.wd.common.base.BaseActivity;
import com.wd.common.model.DoctorDetaBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.databinding.ActivityDoctorXqBinding;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class ActivityDoctorXq extends BaseActivity<MainViewModel, ActivityDoctorXqBinding> {

    @Override
    protected void startCoding() {
        //返回
        vdb.docXqBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        bvm.getDoctorDetaBeanMutableLiveData(id).observe(this,this::onChanged);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_doctor_xq;
    }

    @Override
    public void onChanged(Object o) {
        if( o instanceof DoctorDetaBean){
            DoctorDetaBean bean= (DoctorDetaBean) o;
            if(bean.getStatus().equals("0000")){
                DoctorDetaBean.ResultBean result = bean.getResult();
                Glide.with(ActivityDoctorXq.this).load(result.getImagePic()).into(vdb.docXqImg);
                vdb.docXqName.setText(result.getDoctorName());
                vdb.docXqDz.setText(result.getInauguralHospital());
                vdb.docXqHp.setText(result.getPraise()+"")  ;
                vdb.docXqJj.setText(result.getPersonalProfile());
                vdb.docXqScly.setText(result.getGoodField());
                result.getDoctorReceiveGiftList();
                List<DoctorDetaBean.ResultBean.CommentListBean> commentList = result.getCommentList();
                CommonAdapter<DoctorDetaBean.ResultBean.CommentListBean> commonAdapter = new CommonAdapter<DoctorDetaBean.ResultBean.CommentListBean>(ActivityDoctorXq.this, R.layout.item_yhpj, commentList) {
                    @Override
                    protected void convert(ViewHolder holder, DoctorDetaBean.ResultBean.CommentListBean commentListBean, int position) {
                        Glide.with(ActivityDoctorXq.this).load(commentListBean.getHeadPic()).into((ImageView) holder.getView(R.id.docPl_head));
                        holder.setText(R.id.docPl_name, commentListBean.getNickName());
                        holder.setText(R.id.docPl_pl, commentListBean.getContent());
                    }
                };
                vdb.docXqPj.setAdapter(commonAdapter);
                vdb.docXqPj.setLayoutManager(new LinearLayoutManager(ActivityDoctorXq.this));
            }
        }

    }
}