package com.wd.home;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.wd.common.base.BaseActivity;
import com.wd.common.base.SpUtils;
import com.wd.common.model.DelMyFileBean;
import com.wd.common.model.LookMyFileBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.databinding.ActivityFileBinding;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.home</p>
 * <p>简述:我的档案</p>
 *
 * @author 张梓萁
 * @date 2022/8/28
 */
public class ActivityFile extends BaseActivity<MainViewModel, ActivityFileBinding> {

    private int id;
    private String[] split;
    private ArrayList<String> list = new ArrayList<String>();
    private String picture;

    @Override
    protected void startCoding() {
        //返回
        vdb.fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //查询我的档案
        bvm.getLookMyFileBeanMutableLiveData().observe(ActivityFile.this,this::onChanged);
        //添加
        vdb.addMyfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                vdb.lookFile.setVisibility(View.VISIBLE);
//                vdb.noMessage.setVisibility(View.GONE);
                    startActivity(new Intent(ActivityFile.this,ActivityAddMyfile.class));
            }
        });
        //删除
        vdb.myfileDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bvm.getDelMyFileBeanMutableLiveData(id).observe(ActivityFile.this,ActivityFile.this::onChanged);
            }
        });
        //修改
        vdb.myfileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityFile.this, Activity_PutMyFile.class);
                intent.putExtra("id",id);
                intent.putExtra("picture",picture);

                startActivity(intent);
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_file;
    }

    @Override
    public void onChanged(Object o) {
        if( o instanceof LookMyFileBean){
            LookMyFileBean bean= (LookMyFileBean) o;
            if(bean.getStatus().equals("0000")){
//                LookMyFileBean.ResultBean result = bean.getResult();
                if(bean.getMessage().equals("用户档案查询成功")){
                    vdb.lookFile.setVisibility(View.VISIBLE);
                    vdb.noMessage.setVisibility(View.GONE);
                    LookMyFileBean.ResultBean result = bean.getResult();
                    vdb.zz.setText(result.getDiseaseMain());
                    vdb.xbs.setText(result.getDiseaseNow());
                    vdb.jwbs.setText(result.getDiseaseBefore());
                    vdb.jl.setText(result.getTreatmentProcess());
                    list.clear();
                    id = result.getId();
                    SpUtils.putString("picture1",result.getPicture());
                    picture = result.getPicture();
                    if(picture!=null){
                        split = result.getPicture().split(",");
                        if(split.length>0){
                            for (int i = 0; i < split.length; i++) {
                                list.add(split[i]);
                            }
                        }else {
                            list.add(result.getPicture());
                        }
                    }




                    CommonAdapter<String> adapter = new CommonAdapter<String>(ActivityFile.this, R.layout.item_pic_rec, list) {
                        @Override
                        protected void convert(ViewHolder holder, String s, int position) {
                            Glide.with(ActivityFile.this).load(s).into((ImageView) holder.getView(R.id.cpic));
                        }
                    };
                    vdb.myfileRec.setAdapter(adapter);
                    vdb.myfileRec.setLayoutManager(new GridLayoutManager(this,3));
                }else {
                    vdb.lookFile.setVisibility(View.GONE);
                    vdb.noMessage.setVisibility(View.VISIBLE);
                }
            }
        }else if(o instanceof DelMyFileBean){
            DelMyFileBean bean= (DelMyFileBean) o;
            if(bean.getStatus().equals("0000")){
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                vdb.lookFile.setVisibility(View.GONE);
                vdb.noMessage.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


        //查询我的档案
        bvm.getLookMyFileBeanMutableLiveData().observe(ActivityFile.this,this::onChanged);


    }
}
