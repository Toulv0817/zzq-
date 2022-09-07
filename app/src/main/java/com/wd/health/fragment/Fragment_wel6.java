package com.wd.health.fragment;

import android.content.Intent;
import android.view.View;

import com.wd.common.base.BaseFragment;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.health.MainActivity;
import com.wd.health.R;
import com.wd.health.databinding.FragmentWel6Binding;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.fragment</p>
 * <p>简述:</p>
 *
 * @author 张梓萁
 * @date 2022/8/15
 */
public class Fragment_wel6 extends BaseFragment<MainViewModel, FragmentWel6Binding> {
    @Override
    protected void startCoding() {
        vdb.ljjr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_wel6;
    }

    @Override
    public void onChanged(Object o) {

    }
}
