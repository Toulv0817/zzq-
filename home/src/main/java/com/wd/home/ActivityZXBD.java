package com.wd.home;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.wd.common.base.BaseActivity;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.databinding.ActivityZXBDBinding;

import java.util.ArrayList;

public class ActivityZXBD extends BaseActivity<MainViewModel, ActivityZXBDBinding> {

    String[] name={"常见病症","常见药品"};
    @Override
    protected void startCoding() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        Toast.makeText(this, id+"", Toast.LENGTH_SHORT).show();


        //默认选中

        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new FragmentDrug());
        list.add(new FragmentDrug2());
        vdb.zsbdVp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return name[position];
            }
        });
        vdb.zsbdTab.setupWithViewPager(vdb.zsbdVp);
        if(id==0){
            vdb.zsbdVp.setCurrentItem(0);
        }else {
            vdb.zsbdVp.setCurrentItem(1);
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_z_x_b_d;
    }

    @Override
    public void onChanged(Object o) {

    }
}