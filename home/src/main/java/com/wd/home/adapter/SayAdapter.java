package com.wd.home.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.home.R;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.home.adapter</p>
 * <p>简述:适配器</p>
 *
 * @author 张梓萁
 * @date 2022/8/22
 */
public class SayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Message> list;
    Context context;
    private boolean b=false;
    private MediaPlayer mp;
    HashMap<String,Integer> map=new HashMap<>();
    public SayAdapter(List<Message> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==1){

            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_two, parent, false);
            return new ViewHolder2(view);

        }else{

            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_one, parent, false);

            return new ViewHolder1(view);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initmap();
        Message message = list.get(position);
        ContentType contentType = message.getContentType();

        if(holder instanceof ViewHolder1){

            switch (contentType){

                case text:
                    TextContent content = (TextContent) message.getContent();
                    if(content.getText().contains("[emo_")){
                        ((ViewHolder1) holder).yy.setVisibility(View.GONE);
                        ((ViewHolder1) holder).name.setVisibility(View.GONE);
                        ((ViewHolder1) holder).pic1.setVisibility(View.VISIBLE);
                        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
                            if(content.getText().equals(stringIntegerEntry.getKey())){
                                ((ViewHolder1) holder).pic1.setImageResource(stringIntegerEntry.getValue());
                            }
                        }
                    }else {
                        //显示文字隐藏语音图片
                        ((ViewHolder1) holder).yy.setVisibility(View.GONE);
                        ((ViewHolder1) holder).name.setVisibility(View.VISIBLE);
                        ((ViewHolder1) holder).pic1.setVisibility(View.GONE);
                        TextContent content0 = (TextContent) message.getContent();
                        ((ViewHolder1) holder).name.setText(content0.getText());
                    }



                    break;
                case image:
                    ((ViewHolder1) holder).pic1.setVisibility(View.VISIBLE);
                    ((ViewHolder1) holder).name.setVisibility(View.GONE);
                    ((ViewHolder1) holder).yy.setVisibility(View.GONE);
                    ImageContent content1 = (ImageContent) message.getContent();
                    String path = content1.getLocalThumbnailPath();
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    ((ViewHolder1) holder).pic1.setImageBitmap(bitmap);

                    break;
                case voice:

                    ((ViewHolder1) holder).pic1.setVisibility(View.GONE);
                    ((ViewHolder1) holder).name.setVisibility(View.GONE);
                    ((ViewHolder1) holder).yy.setVisibility(View.VISIBLE);
                    VoiceContent content2 = (VoiceContent) message.getContent();
                    int duration = content2.getDuration();
                    ((ViewHolder1) holder).num.setText(duration+"");
                    media(content2);
                    ((ViewHolder1) holder).play.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b){
                                mp.stop();
                                ((ViewHolder1) holder).play.setImageResource(R.drawable.bofang);
                                mp.release();
                                b=false;
                            }else {
                                ((ViewHolder1) holder).play.setImageResource(R.drawable.zhanting);
                                media(content2);
                                if(mp!=null){
                                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                        @Override
                                        public void onPrepared(MediaPlayer mp) {
                                            mp.start();
//                                            media(content2);
                                            b=true;
                                        }
                                    });
                                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            mp.stop();
                                            mp.release();
                                            mp=null;
                                            ((ViewHolder1) holder).play.setImageResource(R.drawable.bofang);
                                            b=false;
                                        }
                                    });
                                }
                            }
                        }
                    });
                    break;

            }


        }else if(holder instanceof ViewHolder2){

            switch (contentType){

                case text:
                    TextContent content = (TextContent) message.getContent();
                    if(content.getText().contains("[emo_")){
                        ((ViewHolder2) holder).yy.setVisibility(View.GONE);
                        ((ViewHolder2) holder).name.setVisibility(View.GONE);
                        ((ViewHolder2) holder).img.setVisibility(View.VISIBLE);
                        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
                            if(content.getText().equals(stringIntegerEntry.getKey())){
                                ((ViewHolder2) holder).img.setImageResource(stringIntegerEntry.getValue());
                            }
                        }
                    }else {
                        //显示文字隐藏语音图片
                        ((ViewHolder2) holder).yy.setVisibility(View.GONE);
                        ((ViewHolder2) holder).name.setVisibility(View.VISIBLE);
                        ((ViewHolder2) holder).img.setVisibility(View.GONE);
                        TextContent content0 = (TextContent) message.getContent();
                        ((ViewHolder2) holder).name.setText(content0.getText());
                    }


                    break;
                case image:

                    ((ViewHolder2) holder).img.setVisibility(View.VISIBLE);
                    ((ViewHolder2) holder).name.setVisibility(View.GONE);
                    ((ViewHolder2) holder).yy.setVisibility(View.GONE);
                    ImageContent content1 = (ImageContent) message.getContent();
                    String path = content1.getLocalThumbnailPath();
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    ((ViewHolder2) holder).img.setImageBitmap(bitmap);

                    break;
                case voice:
                    ((ViewHolder2) holder).yy.setVisibility(View.VISIBLE);
                    ((ViewHolder2) holder).name.setVisibility(View.GONE);
                    ((ViewHolder2) holder).img.setVisibility(View.GONE);
                    VoiceContent content2 = (VoiceContent) message.getContent();
                    int duration = content2.getDuration();
                    ((ViewHolder2) holder).num.setText(duration+"");
                    media(content2);
                    ((ViewHolder2) holder).play.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b){
                                mp.stop();
                                mp.release();
                                ((ViewHolder2) holder).play.setImageResource(R.drawable.bofang);
                                b=false;
                            }else {
                                ((ViewHolder2) holder).play.setImageResource(R.drawable.zhanting);
//                                media(content1);
                                media(content2);
                                if(mp!=null){
                                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                        @Override
                                        public void onPrepared(MediaPlayer mp) {
                                            mp.start();
//                                            media(content2);
                                            b=true;
                                        }
                                    });
                                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            mp.stop();
                                            mp.release();
                                            mp=null;
                                            ((ViewHolder2) holder).play.setImageResource(R.drawable.bofang);
                                            b=false;
                                        }
                                    });
                                }
                            }
                        }
                    });
                    break;

            }



        }
    }
    public void initmap(){
        map.put("[emo_01]",R.drawable.emoji_1f01);
        map.put("[emo_02]",R.drawable.emoji_1f02);
        map.put("[emo_03]",R.drawable.emoji_1f03);
        map.put("[emo_04]",R.drawable.emoji_1f04);
        map.put("[emo_05]",R.drawable.emoji_1f05);
        map.put("[emo_06]",R.drawable.emoji_1f06);
        map.put("[emo_07]",R.drawable.emoji_1f07);
        map.put("[emo_08]",R.drawable.emoji_1f08);
        map.put("[emo_09]",R.drawable.emoji_1f09);
        map.put("[emo_10]",R.drawable.emoji_1f10);
    }
    private void media(VoiceContent content2) {
        mp = new MediaPlayer();
        File file = new File(content2.getLocalPath());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            mp.setDataSource(fileInputStream.getFD());
            mp.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public int getItemViewType(int position) {
        //获取我的信息 id
        UserInfo info = JMessageClient.getMyInfo();
        long userID = info.getUserID();
        //获取用户的id
        UserInfo fromUser = list.get(position).getFromUser();
        long userID1 = fromUser.getUserID();

        if(userID==userID1){

            return 1;
        }


        return 2;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder1 extends RecyclerView.ViewHolder {

        TextView num,name;
        ImageView pic,play,pic1;
        RelativeLayout yy;


        public ViewHolder1(@NonNull View itemView) {
            super(itemView);

            num=itemView.findViewById(R.id.num1);
            name=itemView.findViewById(R.id.name1);
            pic=itemView.findViewById(R.id.pic1);
            pic1=itemView.findViewById(R.id.pic11);
            play=itemView.findViewById(R.id.play1);
            yy=itemView.findViewById(R.id.yy1);


        }
    }




    public class ViewHolder2 extends RecyclerView.ViewHolder {

        TextView num,name;
        ImageView pic,play,img;
        RelativeLayout yy;


        public ViewHolder2(@NonNull View itemView) {
            super(itemView);

            num=itemView.findViewById(R.id.num2);
            name=itemView.findViewById(R.id.name2);
            pic=itemView.findViewById(R.id.pic2);
            play=itemView.findViewById(R.id.play2);
            yy=itemView.findViewById(R.id.yy2);
            img=itemView.findViewById(R.id.pic22);


        }
    }
}
