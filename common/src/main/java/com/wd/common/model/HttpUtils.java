package com.wd.common.model;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:工具类</p>
 *
 * @author 张梓萁
 * @date 2022/8/15
 */
public class HttpUtils {
    private static HttpUtils httpUtils=null;
        private final ApiService apiService;

        public HttpUtils() {
            //初始化
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(new HttpInter())
                    .build();
            //初始化Retrofit
            Retrofit build = new Retrofit.Builder()
                    .baseUrl("http://10.59.9.18/health/")//域名
                    .client(client)//自定义client
                    .addConverterFactory(GsonConverterFactory.create())//支持Gson转换
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava
                    .build();
            apiService = build.create(ApiService.class);
        }

        public static HttpUtils getHttpUtils() {
            if(httpUtils==null){
                synchronized (HttpUtils.class){
                    if(httpUtils==null){
                        httpUtils=new HttpUtils();
                    }
                }
            }
            return httpUtils;
        }

        public ApiService getApiService() {
            return apiService;
        }
}
