package com.wd.common.model;

import com.wd.common.base.SpUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:拦截器自定义</p>
 *
 * @author 张梓萁
 * @date 2022/8/16
 */
public class HttpInter implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        String userid = SpUtils.getString("userid", "0");
        String sessionid = SpUtils.getString("sessionid", "6666");
        Request request = chain.request();
        Request build = request.newBuilder()
                .addHeader("userId", userid)
                .addHeader("sessionId", sessionid)
                .addHeader("Content-Type","application/json")
                .build();

        return chain.proceed(build);
    }
}
