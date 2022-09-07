package com.wd.common.base;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.base</p>
 * <p>简述:sp工具类</p>
 *
 * @author 张梓萁
 * @date 2022/8/16
 */
public class SpUtils {
    private static SharedPreferences sp;
        //初始化
        public static void initSp(Context context){
            if(sp==null){
                sp = context.getSharedPreferences("sp",Context.MODE_PRIVATE);
            }
        }

        public static void putBoolean(String str,boolean b){
            sp.edit().putBoolean(str,b).commit();
        }

        public static boolean getBoolean(String str, boolean b){
            return sp.getBoolean(str,b);
        }

        public static void putString(String str,String b){
            sp.edit().putString(str,b).commit();
        }

        public static String getString(String str,String b){
            return sp.getString(str,b);
        }

        //清空
        public static void clear(){
            sp.edit().clear().commit();
        }
}
