package com.wd.common.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

import cn.jpush.im.android.api.JMessageClient;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.base</p>
 * <p>简述:</p>
 *
 * @author 张梓萁
 * @date 2022/8/15
 */
public class Init extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JMessageClient.setDebugMode(true);
        JMessageClient.init(this);
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

    }
}
