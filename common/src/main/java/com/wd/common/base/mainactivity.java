//package com.wd.common.base;
//
//import android.app.Activity;
//import android.view.View;
//
///**
// * <p>项目名称:Health</p>
// * <p>包名:com.wd.common.base</p>
// * <p>简述:</p>
// *
// * @author 张梓萁
// * @date 2022/8/31
// */
//    public class mainactivity extends Activity implements View.OnClickListener {
//
//        button content;
//
//        imageview image;
//
//        bitmap bitmap;
//
//        protected void oncreate(bundle savedinstancestate) {
//
//            super.oncreate(savedinstancestate);
//
//            setcontentview(r.layout.activity_main);
//
//            content=(button)findviewbyid(r.id.content);
//
//            image=(imageview)findviewbyid(r.id.image);
//
//            content.setonclicklistener(this);;
//
//            image.setonclicklistener(this);;
//
//        }
//
//        /**
//
//         * 获取网络图片
//
//         * @param imageurl 图片网络地址
//
//         * @return bitmap 返回位图
//
//         */
//
//        public bitmap getimageinputstream(string imageurl){
//
//            url url;
//
//            httpurlconnection connection=null;
//
//            bitmap bitmap=null;
//
//            try {
//
//                url = new url(imageurl);
//
//                connection=(httpurlconnection)url.openconnection();
//
//                connection.setconnecttimeout(6000); //超时设置
//
//                connection.setdoinput(true);
//
//                connection.setusecaches(false); //设置不使用缓存
//
//                inputstream inputstream=connection.getinputstream();
//
//                bitmap=bitmapfactory.decodestream(inputstream);
//
//                inputstream.close();
//
//            } catch (exception e) {
//
//                e.printstacktrace();
//
//            }
//
//            return bitmap;
//
//        }
//
//        public void onclick(view v) {
//
//            switch (v.getid()) {
//
//                case r.id.content:
//
////加入网络图片地址
//
//                    new task().execute("https://pic.4j4j.cn/upload/pic/20130617/55695c3c95.jpg");
//
//                    break;
//
//                case r.id.image:
//
////点击图片后将图片保存到sd卡跟目录下的test文件夹内
//
//                    savaimage(bitmap, environment.getexternalstoragedirectory().getpath()+"/test");
//
//                    toast.maketext(getbasecontext(), "图片保存", toast.length_short).show();
//
//                    break;
//
//                default:
//
//                    break;
//
//            }
//
//        }
//
//        handler handler=new handler(){
//
//            public void handlemessage(android.os.message msg) {
//
//                if(msg.what==0x123){
//
//                    image.setimagebitmap(bitmap);
//
//                }
//
//            };
//
//        };
//
//        /**
//
//         * 异步线程下载图片
//
//         *
//
//         */
//
//        class task extends asynctask{
//
//            protected void doinbackground(string... params) {
//
//                bitmap=getimageinputstream((string)params[0]);
//
//                return null;
//
//            }
//
//            protected void onpostexecute(void result) {
//
//                super.onpostexecute(result);
//
//                message message=new message();
//
//                message.what=0x123;
//
//                handler.sendmessage(message);
//
//            }
//
//        }
//
//        /**
//
//         * 保存位图到本地
//
//         * @param bitmap
//
//         * @param path 本地路径
//
//         * @return void
//
//         */
//
//        public void savaimage(bitmap bitmap, string path){
//
//            file file=new file(path);
//
//            fileoutputstream fileoutputstream=null;
//
////文件夹不存在，则创建它
//
//            if(!file.exists()){
//
//                file.mkdir();
//
//            }
//
//            try {
//
//                fileoutputstream=new fileoutputstream(path+"/"+system.currenttimemillis()+".png");
//
//                bitmap.compress(bitmap.compressformat.jpeg, 100,fileoutputstream);
//
//                fileoutputstream.close();
//
//            } catch (exception e) {
//
//                e.printstacktrace();
//
//            }
//
//        }
//
//    }
