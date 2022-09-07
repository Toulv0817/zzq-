package com.wd.health;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.wd.common.base.BaseActivity;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.health.databinding.ActivityMainBinding;
import com.wd.home.FragmentHome;
import com.wd.patientcircle.ActivitySendPatientCircle;
import com.wd.patientcircle.FragmentPatientCircle;
import com.wd.video.FragmentVideo;

import java.util.ArrayList;

//@Route(path = "/app/app1")
public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {


    int i=0;
    @Override
    protected void startCoding() {
//        vdb.btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                ARouter.getInstance().build("/login0/login1").navigation();
//            }
//        });


        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new FragmentHome());
        list.add(new FragmentPatientCircle());
        list.add(new FragmentVideo());
        vdb.vp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {

                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                super.destroyItem(container, position, object);

            }
        });
        vdb.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case   R.id.rb_home:
                        i=0;
                        vdb.vp.setCurrentItem(0);
                        vdb.comment.setImageResource(R.mipmap.common_tab_circle_n);
//                        if(vdb.rbHome.isChecked()){
//                            vdb.rbHome.setOnLongClickListener(new View.OnLongClickListener() {
//                                @Override
//                                public boolean onLongClick(View v) {
//                                    Toast.makeText(MainActivity.this, "66666", Toast.LENGTH_SHORT).show();
//                                    return true;
//                                }
//                            });
//                        }
//                        boolean q=true;
//                        if(q){
//
//                        }
                    break;
                    case R.id.rb_video:
                        i=0;
                        vdb.vp.setCurrentItem(2);
                        vdb.comment.setImageResource(R.mipmap.common_tab_circle_n);
                        break;
                }
            }
        });
        vdb.rbHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vdb.rbHome.setChecked(true);
            }
        });
        vdb.rbVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vdb.rbVideo.setChecked(true);
            }
        });
        vdb.vp.setOffscreenPageLimit(3);
        vdb.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==0){
                    vdb.rg.clearCheck();
                    vdb.comment.setImageResource(R.mipmap.common_tab_circle_s);
                    vdb.vp.setCurrentItem(1);
                    i=1;
                }else {
                    //跳转发病友圈
                    startActivity(new Intent(MainActivity.this, ActivitySendPatientCircle.class));
                }
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void onChanged(Object o) {

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        if (vdb.vp.getCurrentItem()==1){
            i=1;
        }else {
            i=0;
        }


    }
}