package com.wd.home;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.wd.common.base.BaseActivity;
import com.wd.common.model.RoomBean;
import com.wd.common.model.RoomDB;
import com.wd.common.model.SearchBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.databinding.ActivitySearchBinding;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class ActivitySearch extends BaseActivity<MainViewModel, ActivitySearchBinding> {
    String[] hotname={"阿胶","小可爱医生","感冒","头痛","神经病","发烧","癫痫","阿莫西林","脱发","腿抽筋","小儿感冒颗粒","神经炎"};
    private RoomDB db;

    int i=0;
    @Override
    protected void startCoding() {
        db = Room.databaseBuilder(ActivitySearch.this, RoomDB.class, "room.db").build();

        //数据库全查
        new Thread(new Runnable() {

            @Override
            public void run() {
                List<RoomBean> quertall = db.dao().quertall();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < quertall.size(); i++) {
                            Button button = new Button(ActivitySearch.this);
                            String name = quertall.get(i).getName();
                            button.setText(quertall.get(i).getName());
                            vdb.liushi.addView(button);
                            //长按删除
                            button.setOnLongClickListener(new View.OnLongClickListener() {
                                @Override
                                public boolean onLongClick(View v) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            db.dao().delete(button.getText().toString());
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    vdb.liushi.removeView(button);
                                                }
                                            });
                                        }
                                    }).start();
                                    return true;
                                }
                            });
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    vdb.searchName.setText(name);
                                }
                            });
                        }
                    }
                });
            }
        }).start();


        //返回
        vdb.searchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        for (int i = 0; i < hotname.length; i++) {
            Button button = new Button(ActivitySearch.this);
            button.setText(hotname[i]);
            String s = hotname[i];
            vdb.liushi1.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vdb.searchName.setText(s);
                }
            });

        }

        //默认请求
//        bvm.getSearchBeanMutableLiveData("生").observe(ActivitySearch.this,ActivitySearch.this);
        vdb.searchName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                i++;

//                if(i==1){

//                    new Thread(){
//                        @Override
//                        public void run() {
//                            super.run();
//                            List<RoomBean> quertall = db.dao().quertall();
//                            for (int i = 0; i < quertall.size(); i++) {
//                                Button button = new Button(ActivitySearch.this);
//                                button.setText(quertall.get(i).getName());
//                                vdb.liushi.addView(button);
//                            }
//                        }
//                    }.start();
//                }

                vdb.historysearch.setVisibility(View.VISIBLE);
                vdb.hotsearch.setVisibility(View.VISIBLE);
                vdb.wu.setVisibility(View.GONE);
                vdb.searchRec1.setVisibility(View.GONE);
                vdb.searchRec2.setVisibility(View.GONE);
                vdb.searchRec3.setVisibility(View.GONE);
                vdb.doctor.setVisibility(View.GONE);
                vdb.drug.setVisibility(View.GONE);
                vdb.disease.setVisibility(View.GONE);
            }
        });
        vdb.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = vdb.searchName.getText().toString();
                vdb.hotsearch.setVisibility(View.GONE);

                if(!s.equals("")&&s!=null  ){
                    Button button = new Button(ActivitySearch.this);
                    button.setText(s);
                    vdb.liushi.addView(button);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            vdb.searchName.setText(s);
                        }
                    });
                    //数据库添加
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
//                            new RoomBean(1,s);
                            db.dao().add(new RoomBean(0,s));
                        }
                    }).start();
                    //长按删除
                    button.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    db.dao().delete(button.getText().toString());
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            vdb.liushi.removeView(button);
                                        }
                                    });
                                }
                            }).start();
                            return true;
                        }
                    });

                    vdb.historysearch.setVisibility(View.GONE);
                    bvm.getSearchBeanMutableLiveData(s).observe(ActivitySearch.this,ActivitySearch.this);
                }else {
                    vdb.hotsearch.setVisibility(View.GONE);
                    vdb.hotsearch.setVisibility(View.GONE);
                    vdb.doctor.setVisibility(View.GONE);
                    vdb.searchRec1.setVisibility(View.GONE);
                    vdb.disease.setVisibility(View.GONE);
                    vdb.searchRec3.setVisibility(View.GONE);
                    vdb.drug.setVisibility(View.GONE);
                    vdb.searchRec2.setVisibility(View.GONE);
                    vdb.wu.setVisibility(View.GONE);
                    Toast.makeText(ActivitySearch.this, "请输入要搜索的内容", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof SearchBean){
            SearchBean bean= (SearchBean) o;
            SearchBean.ResultBean result = bean.getResult();
            List<SearchBean.ResultBean.DoctorSearchVoListBean> doctorSearchVoList = result.getDoctorSearchVoList();//医生

            if(doctorSearchVoList!=null){
                vdb.wu.setVisibility(View.GONE);
                vdb.doctor.setVisibility(View.VISIBLE);
                vdb.searchRec1.setVisibility(View.VISIBLE);
                CommonAdapter<SearchBean.ResultBean.DoctorSearchVoListBean> commonAdapter = new CommonAdapter<SearchBean.ResultBean.DoctorSearchVoListBean>(ActivitySearch.this, R.layout.item_searchdoctor, doctorSearchVoList) {
                    @Override
                    protected void convert(ViewHolder holder, SearchBean.ResultBean.DoctorSearchVoListBean doctorSearchVoListBean, int position) {
                        holder.setText(R.id.doctor_name, doctorSearchVoListBean.getDoctorName());
                    }
                };
                vdb.searchRec1.setAdapter(commonAdapter);
                vdb.searchRec1.setLayoutManager(new LinearLayoutManager(ActivitySearch.this, RecyclerView.VERTICAL,false));
            }



            List<SearchBean.ResultBean.DiseaseSearchVoListBean> diseaseSearchVoList = result.getDiseaseSearchVoList();//病症
            if(diseaseSearchVoList!=null){
                vdb.wu.setVisibility(View.GONE);
                vdb.disease.setVisibility(View.VISIBLE);
                vdb.searchRec3.setVisibility(View.VISIBLE);
                CommonAdapter<SearchBean.ResultBean.DiseaseSearchVoListBean> disadapter = new CommonAdapter<SearchBean.ResultBean.DiseaseSearchVoListBean>(ActivitySearch.this, R.layout.item_searchdoctor, diseaseSearchVoList) {
                    @Override
                    protected void convert(ViewHolder holder, SearchBean.ResultBean.DiseaseSearchVoListBean doctorSearchVoListBean, int position) {
                        holder.setText(R.id.doctor_name, doctorSearchVoListBean.getDiseaseName());
                    }
                };
                vdb.searchRec3.setAdapter(disadapter);
                vdb.searchRec3.setLayoutManager(new LinearLayoutManager(ActivitySearch.this, RecyclerView.VERTICAL,false));
            }


            List<SearchBean.ResultBean.DrugsSearchVoListBean> drugsSearchVoList = result.getDrugsSearchVoList();//药品
            if(drugsSearchVoList!=null){
                vdb.wu.setVisibility(View.GONE);
                vdb.drug.setVisibility(View.VISIBLE);
                vdb.searchRec2.setVisibility(View.VISIBLE);
                CommonAdapter<SearchBean.ResultBean.DrugsSearchVoListBean> drugsSearchVoListBeanCommonAdapter = new CommonAdapter<SearchBean.ResultBean.DrugsSearchVoListBean>(ActivitySearch.this, R.layout.item_searchdoctor, drugsSearchVoList) {
                    @Override
                    protected void convert(ViewHolder holder, SearchBean.ResultBean.DrugsSearchVoListBean doctorSearchVoListBean, int position) {
                        holder.setText(R.id.doctor_name, doctorSearchVoListBean.getDrugsName());
                    }
                };
                vdb.searchRec2.setAdapter(drugsSearchVoListBeanCommonAdapter);
                vdb.searchRec2.setLayoutManager(new LinearLayoutManager(ActivitySearch.this, RecyclerView.VERTICAL,false));
            }
            if(drugsSearchVoList.size()==0 && diseaseSearchVoList.size()==0  && doctorSearchVoList.size()==0){
                vdb.doctor.setVisibility(View.GONE);
                vdb.searchRec1.setVisibility(View.GONE);
                vdb.disease.setVisibility(View.GONE);
                vdb.searchRec3.setVisibility(View.GONE);
                vdb.drug.setVisibility(View.GONE);
                vdb.searchRec2.setVisibility(View.GONE);
                vdb.wu.setVisibility(View.VISIBLE);
            }
            if(drugsSearchVoList.size()==0){
                vdb.drug.setVisibility(View.GONE);
                vdb.searchRec2.setVisibility(View.GONE);
            }
            if(diseaseSearchVoList.size()==0){
                vdb.disease.setVisibility(View.GONE);
                vdb.searchRec3.setVisibility(View.GONE);
            }
            if(doctorSearchVoList.size()==0){
                vdb.doctor.setVisibility(View.GONE);
                vdb.searchRec1.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(vdb.liushi!=null){
            vdb.liushi.recomputeViewAttributes(null);
            vdb.liushi.removeAllViews();
        }
    }
}