package com.wd.home.view;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wd.common.base.BaseActivity;
import com.wd.common.model.VoiceUtils;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.R;
import com.wd.home.adapter.SayAdapter;
import com.wd.home.databinding.ActivitySayBinding;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;

/**
@author 张梓萁
@date 2022/8/22
@desc 聊天
*/
public class ActivitySay extends BaseActivity<MainViewModel, ActivitySayBinding> {

    private String appkey="c7f6a1d56cb8da740fd18bfa";
    private String username="8SSzgs1311590394";
    private Conversation conversation;
    private List<Message> allMessage;
    private SayAdapter sayAdapter;
    private File file;
    private  boolean b=false;
    HashMap<String,Integer> map=new HashMap<>();
    VoiceUtils voiceUtils = new VoiceUtils("data/data/com.wd.health/zzq0823/");
    private String path;
    private boolean a=false;
    int i=0;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(@NonNull android.os.Message msg) {
            super.handleMessage(msg);
            time.setText(i+"s");
            sendEmptyMessageDelayed(i++,1000);
        }
    };
    private View inflate;
    private TextView lz;
    private TextView time;
    private PopupWindow popupWindow;
    private int sj;

    void ppw(View v){
        v = LayoutInflater.from(ActivitySay.this).inflate(R.layout.ppw_yy, null);
        time = v.findViewById(R.id.sj);
        lz = v.findViewById(R.id.lz);
        popupWindow = new PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(v);
        popupWindow.showAtLocation(v, Gravity.CENTER,0,0);
    }
    @Override
    protected void startCoding() {

        vdb.reFh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Conversation.createSingleConversation(username, appkey);
        conversation = JMessageClient.getSingleConversation(username, appkey);
        if(conversation!=null){
            allMessage = conversation.getAllMessage();
            //适配器
            sayAdapter = new SayAdapter(allMessage, ActivitySay.this);
            vdb.sayRec.setAdapter(sayAdapter);
            vdb.sayRec.setLayoutManager(new LinearLayoutManager(ActivitySay.this,LinearLayoutManager.VERTICAL,false));
            vdb.sayBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = vdb.sendName.getText().toString();
                    Message message = JMessageClient.createSingleTextMessage(username, appkey, s);

//                    message.setOnSendCompleteCallback(new BasicCallback() {
//                        @Override
//                        public void gotResult(int i, String s) {
//                            Log.i("aaaaaa",i+"88"+s);
//                            if(i==0){
//                                Toast.makeText(ActivitySay.this, "文字消息发送成功", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
                    send(message);
                }
            });
            //回车发消息
            vdb.sendName.setImeOptions(EditorInfo.IME_ACTION_SEND);
            vdb.sendName.setSingleLine(true);
            vdb.sendName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if(actionId==EditorInfo.IME_ACTION_SEND){
                        String s = vdb.sendName.getText().toString();
                        Message message = JMessageClient.createSingleTextMessage(username, appkey, s);
                        send(message);
                        Toast.makeText(ActivitySay.this, "拉拉", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    return false;
                }
            });
//            sayAdapter = new SayAdapter(allMessage, ActivitySay.this);
//            vdb.sayRec.setAdapter(sayAdapter);
//            vdb.sayRec.setLayoutManager(new LinearLayoutManager(ActivitySay.this,LinearLayoutManager.VERTICAL,false));
        }
        //图片
        vdb.sayPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });
        //语音按钮
        vdb.shuaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b){
                    vdb.shuaa.setImageResource(R.mipmap.yy);
                    vdb.sendName.setVisibility(View.VISIBLE);
                    vdb.azsh.setVisibility(View.GONE);
                    b=false;
                }else {
                    vdb.shuaa.setImageResource(R.mipmap.jp);
                    vdb.sendName.setVisibility(View.GONE);
                    vdb.azsh.setVisibility(View.VISIBLE);
                    b=true;
                }
            }
        });
        //按住说话
        vdb.azsh.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    //按下
                    case MotionEvent.ACTION_DOWN:
                        //开始录制
                            ppw(v);
                            voiceUtils.startRecord();

                            handler.sendEmptyMessageDelayed(i++,1000);
//                            handler.sendEmptyMessageDelayed(1,1000);
                        break;
                    //抬起
                    case MotionEvent.ACTION_UP:
                        handler.removeCallbacksAndMessages(null);
                        popupWindow.dismiss();
                        i=0;
                        long l=event.getEventTime()-event.getDownTime();
                        sj = (int)(l/1000);

//
//                        time.setText(i+"s");
                        if(sj >2){
                            path = voiceUtils.stopRecord();

                            File file=new File(path);
                            try {
                                Message message=JMessageClient.createSingleVoiceMessage(username,appkey,file, sj);
                                send(message);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
//                        popupWindow.dismiss();

                        break;
                    //移动
                    case MotionEvent.ACTION_MOVE:

                        if(event.getY()<-100){
                            lz.setText("松手取消");
                            voiceUtils.cancelRecord();
                            handler.removeCallbacksAndMessages(null);
                            popupWindow.dismiss();
//                            popupWindow.dismiss();
                        }
                        break;
                    //
                }
                return true;
            }
        });
        initmap();
        //表情
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        for (String s : map.keySet()) {
            list.add(s);
        }
        for (Integer value : map.values()) {
            list1.add(value);
        }
        Log.i("表情",list1.size()+"");
        vdb.expression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a){
                    //显示表情
                    vdb.expressionrec.setVisibility(View.VISIBLE);

                    a=false;
                    vdb.expressionrec.setLayoutManager(new GridLayoutManager(ActivitySay.this,6));
                    CommonAdapter<Integer> adapter = new CommonAdapter<Integer>(ActivitySay.this, R.layout.item_expression, list1) {
                        @Override
                        protected void convert(ViewHolder holder, Integer integer, int position) {
//                            Glide.with(ActivitySay.this).load(integer).into((ImageView) holder.getView(R.id.bq));
                            ImageView pic = holder.getView(R.id.bq);
                            pic.setImageResource(integer);
                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    vdb.sendName.setText(list.get(position));
                                    SpannableString spannableString = new SpannableString("[local]\"+1+\"[/local]");
//                                    spannableString.setSpan(R.mipmap.boy,);
//                                    vdb.sendName.append();
                                    vdb.expressionrec.setVisibility(View.GONE);
                                    a = true;
                                }
                            });
                        }
                    };
                    vdb.expressionrec.setAdapter(adapter);
                }else {
                    vdb.expressionrec.setVisibility(View.GONE);
                    a=true;
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ContentResolver contentResolver = getContentResolver();
        if(requestCode==100 && resultCode==RESULT_OK){
            Uri data1 = data.getData();
//            Uri uri = Objects.requireNonNull(data.getData());
            try {
//                InputStream inputStream = contentResolver.openInputStream(data.getData());
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data1);
                sc(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sc(Bitmap bitmap) {
        file = new File("data/data/com.wd.health/pic.png");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                bitmap.compress(Bitmap.CompressFormat.JPEG,20,bufferedOutputStream);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();

                Message message = JMessageClient.createSingleImageMessage(username, appkey, file);
                send(message);
//                message.setOnSendCompleteCallback(new BasicCallback() {
//                    @Override
//                    public void gotResult(int i, String s) {
//                        if(i==0){
//                            Toast.makeText(ActivitySay.this, "图片发送成功", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
                vdb.sayRec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                sayAdapter = new SayAdapter(allMessage, getApplicationContext());
                vdb.sayRec.setAdapter(sayAdapter);
//                vdb.sayRec.scrollToPosition(allMessage.size()-1);
//                vdb.sayRec.smoothScrollToPosition(allMessage.size()-1);
                sayAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    void send(Message message){
        ContentType contentType = message.getContentType();
        JMessageClient.sendMessage(message);
        allMessage.add(message);
        sayAdapter.notifyDataSetChanged();
//        vdb.sayRec.scrollToPosition(allMessage.size()-1);
//        vdb.sayRec.smoothScrollToPosition(allMessage.size()-1);
        message.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if(i==0){
                    switch (contentType){
                        case text:
                            Toast.makeText(ActivitySay.this, "文字消息发送成功", Toast.LENGTH_SHORT).show();
                            vdb.sayRec.scrollToPosition(allMessage.size()-1);
                            vdb.sayRec.smoothScrollToPosition(allMessage.size()-1);
                            break;
                        case image:
                            Toast.makeText(ActivitySay.this, "图片发送成功", Toast.LENGTH_SHORT).show();
                            vdb.sayRec.scrollToPosition(allMessage.size()-1);
                            vdb.sayRec.smoothScrollToPosition(allMessage.size()-1);
                            break;
                        case voice:
                            Toast.makeText(ActivitySay.this, "语音发送成功", Toast.LENGTH_SHORT).show();
                            vdb.sayRec.scrollToPosition(allMessage.size()-1);
                            vdb.sayRec.smoothScrollToPosition(allMessage.size()-1);
                            break;
                    }

                }
            }
        });
    }
    // TODO: 2022/8/22 表情
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
    @Override
    protected int initLayout() {
        return R.layout.activity_say;
    }

    @Override
    public void onChanged(Object o) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        JMessageClient.registerEventReceiver(this,200);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event){
        Message message = event.getMessage();
        allMessage.add(message);
        vdb.sayRec.scrollToPosition(allMessage.size()-1);
        vdb.sayRec.smoothScrollToPosition(allMessage.size()-1);
        sayAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        JMessageClient.unRegisterEventReceiver(this);
    }
}