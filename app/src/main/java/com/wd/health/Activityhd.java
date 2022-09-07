package com.wd.health;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.wd.common.base.BaseActivity;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.health.databinding.ActivityActivityhdBinding;
import com.wd.health.fragment.Fragment_wel2;
import com.wd.health.fragment.Fragment_wel3;
import com.wd.health.fragment.Fragment_wel4;
import com.wd.health.fragment.Fragment_wel5;
import com.wd.health.fragment.Fragment_wel6;

import java.util.ArrayList;

/**
@author 张梓萁
@date 2022/8/15
@desc 滑动
*/
public class Activityhd extends BaseActivity<MainViewModel, ActivityActivityhdBinding> {

    @Override
    protected void startCoding() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new Fragment_wel5());
        list.add(new Fragment_wel2());
        list.add(new Fragment_wel3());
        list.add(new Fragment_wel4());

        list.add(new Fragment_wel6());
        vdb.hdVp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
//        vdb.hdVp.setAdapter(new  );
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_activityhd;
    }

    @Override
    public void onChanged(Object o) {

    }
}