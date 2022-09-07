package com.wd.common.base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.base</p>
 * <p>简述:</p>
 *
 * @author 张梓萁
 * @date 2022/8/31
 */
public class ImageUtils {
    //定义一个静态的Handler
        static Handler handler=new Handler();
        //网络图片的方法 第一个参数表示文件的路径 第二个就是展示的控件
        //图片的三级缓存
        public static void getImage(final  String path, final ImageView img){
            //获取图片的名字（为了防止图片名字重复覆盖掉 使用字符串切割）
            String fileName = path.substring(path.lastIndexOf("/") + 1);
            //根据名字来获取文件
            final File file = new File(Environment.getExternalStorageDirectory(), "/" + fileName);
            //判断文件是否存在于SD中
            if(file.exists()){
                //使用本地图片
                saveImage(img,file);
            }else {
                //不存在的话 网络获取图片并下载到本地
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        URL url =null;
                        try {
                            url= new URL(path);
                            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                            conn.setReadTimeout(5000);
                            conn.setConnectTimeout(5000);
                            conn.setRequestMethod("GET");
                            conn.connect();
                            if(conn.getResponseCode()==200){
                                //创建字节
                                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                                //使用Bitmap工厂缓存图片
                                Bitmap bitmap = BitmapFactory.decodeStream(bis);
                                //将Bitmap格式的图片放到内存卡中
                                //第一个参数 指定图片的类型（png或jpeg）
                                //第二个参数 指定图片的质量（100即可）
                                //第三个参数 使用写入流 将压缩后的图片放到SD卡中
                                bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(file));
                                //开启建议线程 设置图片
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        saveImage(img,file);
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
        //图片的二次采样
        public static void saveImage(ImageView img, File file){
            //bitmap设置图片管理对象
            BitmapFactory.Options options = new BitmapFactory.Options();
            //图片等比例压缩 压缩两倍
            options.inSampleSize=2;
            //压缩图片画质
            options.inPreferredConfig= Bitmap.Config.RGB_565;
            //开始采样 第一参数为文件的绝对路径 第二个蚕食是将压缩后的管理对象放入
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            img.setImageBitmap(bitmap);
        }
}
