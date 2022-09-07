package com.wd.common.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * <p>项目名称:Zzq0812</p>
 * <p>包名:com.wd.common</p>
 * <p>简述:vm基类</p>
 *
 * @author 张梓萁
 * @date 2022/8/15
 */
public class BaseViewModel extends AndroidViewModel {
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }
}
