package com.wd.patientcircle;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.wd.common.ByqScBean;
import com.wd.common.base.BaseActivity;
import com.wd.common.model.PatientCircleXqBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.patientcircle.databinding.ActivityPatientCircleXqBinding;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

/**
@author 张梓萁
@date 2022/8/24
@desc 病友圈详情
*/
public class ActivityPatientCircleXq extends BaseActivity<MainViewModel, ActivityPatientCircleXqBinding> {

    @Override
    protected void startCoding() {

        vdb.userimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        bvm.getPatientCircleXqBeanMutableLiveData(id).observe(this,ActivityPatientCircleXq.this);
        vdb.scByq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bvm.getByqScBeanMutableLiveData(id).observe(ActivityPatientCircleXq.this,ActivityPatientCircleXq.this::onChanged);
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_patient_circle_xq;
    }

    @Override
    public void onChanged(Object o) {
            if(o instanceof PatientCircleXqBean){
                PatientCircleXqBean bean= (PatientCircleXqBean) o;
                PatientCircleXqBean.ResultBean result = bean.getResult();

                vdb.patientxqName.setText(result.getTitle());
                vdb.patientxqBz.setText(result.getDisease());
                vdb.patientxqKs.setText(result.getDepartment());
                vdb.patientxqXq.setText(result.getDetail());
                vdb.patientxqJl.setText(result.getTreatmentProcess());
//                Glide.with(this).load(result.getPicture()).into(vdb.patientxqImg);
                ArrayList<String> list=new ArrayList<>();
                String[] sp = result.getPicture().split(",");
                for (int i = 0; i < sp.length; i++) {
                    list.add(sp[i]);
                }
                vdb.picRec.setAdapter(new CommonAdapter<String>(ActivityPatientCircleXq.this,R.layout.item_pic_rec,list) {
                    @Override
                    protected void convert(ViewHolder holder, String s, int position) {
                        Glide.with(ActivityPatientCircleXq.this).load(s).into((ImageView) holder.getView(R.id.cpic));
                    }
                });
                vdb.picRec.setLayoutManager(new GridLayoutManager(this,3));
//                Glide.with(this).load().into(vdb.userimg);
                vdb.patientxqSc.setText(result.getCollectionNum()+"");
                vdb.patientxqPl.setText(result.getCommentNum()+"");
                Glide.with(this).load(result.getAdoptHeadPic()).into(vdb.patientxqTx);
                vdb.patientxqName1.setText(result.getAdoptNickName());
//                vdb.patientxqTime.setText();
            }else if(o instanceof ByqScBean){
                ByqScBean bean= (ByqScBean) o;
                if(bean.getStatus().equals("0000")){
                    Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
    }
}