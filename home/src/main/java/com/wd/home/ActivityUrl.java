package com.wd.home;

import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.wd.common.base.BaseActivity;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.databinding.ActivityUrlBinding;

/**
@author 张梓萁
@date 2022/8/30
@desc 健康测评
*/
public class ActivityUrl extends BaseActivity<MainViewModel, ActivityUrlBinding> {


    @Override
    protected void startCoding() {
            vdb.web.loadUrl("https://www.wjx.cn/jq/33939807.aspx");
            vdb.web.setWebViewClient(new WebViewClient());
        WebSettings settings = vdb.web.getSettings();
        settings.setJavaScriptEnabled(true);

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_url;
    }

    @Override
    public void onChanged(Object o) {

    }
}