package com.wd.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wd.common.model.ZxlistBean;
import com.wd.home.R;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.home</p>
 * <p>简述:适配器</p>
 *
 * @author 张梓萁
 * @date 2022/8/16
 */
public class ZxListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ZxlistBean.ResultBean> list;
    Context context;

    public ZxListAdapter(List<ZxlistBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==3){
            View view = LayoutInflater.from(context).inflate(R.layout.item_zxlist3, parent,false);
            return new ViewHodel1(view);
        }else if (viewType==1){
            View view = LayoutInflater.from(context).inflate(R.layout.item_zxlist2, parent,false);
            return new ViewHodel(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHodel){
            ViewHodel h= (ViewHodel) holder;
            h.tital.setText(list.get(position).getTitle());
            h.name.setText(list.get(position).getSource());
            h.phone.setText(list.get(position).getReleaseTime()+"");
            String[] sp = list.get(position).getThumbnail().split(";");
            Glide.with(context).load(sp[0]).into(h.pic);
            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    laaa.onclick(list.get(position).getId());
                }
            });
        }else {
            ViewHodel1 h= (ViewHodel1) holder;
            h.tital.setText(list.get(position).getTitle());
            h.name.setText(list.get(position).getSource());
            h.phone.setText(list.get(position).getReleaseTime()+"");
            String[] sp = list.get(position).getThumbnail().split(";");
            Glide.with(context).load(sp[0]).into(h.pic1);
            Glide.with(context).load(sp[1]).into(h.pic2);
            Glide.with(context).load(sp[2]).into(h.pic3);
            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    laaa.onclick(list.get(position).getId());
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        String[] sp = list.get(position).getThumbnail().split(";");
        if(sp.length>1){
            return 3;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHodel extends RecyclerView.ViewHolder {
        TextView tital,name,phone;
        ImageView pic;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            tital=itemView.findViewById(R.id.zxlsit2_tital);
            name=itemView.findViewById(R.id.zxlist2_name);
            phone=itemView.findViewById(R.id.zxlist2_phone);
            pic=itemView.findViewById(R.id.zxlist2_pic);
        }
    }
    class ViewHodel1 extends RecyclerView.ViewHolder {
        TextView tital,name,phone;
        ImageView pic1,pic2,pic3;
        public ViewHodel1(@NonNull View itemView) {
            super(itemView);
            tital=itemView.findViewById(R.id.zxlist3_tital);
            name=itemView.findViewById(R.id.zxlist3_name);
            phone=itemView.findViewById(R.id.zxlist3_phone);
            pic1=itemView.findViewById(R.id.zxlist3_tp1);
            pic2=itemView.findViewById(R.id.zxlist3_tp2);
            pic3=itemView.findViewById(R.id.zxlist3_tp3);
        }
    }
    //接口回调
    public interface dj{
        void onclick(int id);
    }
   private dj laaa;

    public void setLaaa(dj laaa) {
        this.laaa = laaa;
    }
    //接口回调
//        //1.自定义接口  自定义方法  参数为需要用的数据
//        public  interface ClickItemLisenter{
//            void click(Bean.DataBean bean,int positon);
//        }
//        //2.将接口设置为变量
//        private  ClickItemLisenter clickItemLisenter;
//
//        //3.提供set方法
//        public void setClickItemLisenter(ClickItemLisenter clickItemLisenter) {
//            this.clickItemLisenter = clickItemLisenter;
//        }

}
