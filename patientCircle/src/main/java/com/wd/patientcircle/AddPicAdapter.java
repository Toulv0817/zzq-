package com.wd.patientcircle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.common.dtsc.Tools;

import java.util.List;

//import com.wd.common.dtsc.Tools;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.patientcircle</p>
 * <p>简述:多图上传适配器</p>
 *
 * @author 张梓萁
 * @date 2022/8/26
 */
public class AddPicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<String> list;
    //最大图片数量
    private int picMax;

    public AddPicAdapter(Context context, int picMax) {
        this.context = context;
        this.picMax = picMax;
    }

    public void setList(List<String> list) {
        this.list = list;
        //每次放入数据刷新适配器 展示新的图片
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_addpic, parent,false);
        return new ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder h= (ViewHolder) holder;
        //当前图片位置大于图片数量并且小于最大减1
        if(position>=list.size() && position<=(picMax-1)){
            //显示添加图片按钮
            Tools.showGlide(context,h.pic,"");
            h.jian.setVisibility(View.GONE);
        }else {
//            显示本地网络图片,显示删除按钮
            Tools.showGlide(context,h.pic,list.get(position));
//            Glide.with(context).load(list.get(position)).into(h.pic);
            h.jian.setVisibility(View.VISIBLE);

        }
        //删除
        h.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //要删除的下标
                clickItemLisenter.del(position);
            }
        });
        //
        h.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position>=list.size() && position<=(picMax-1)){
                    clickItemLisenter.add();
                }else {
                    //点击查看图片，并将最新list传入activity
                    clickItemLisenter.item(position,list);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if(list==null || list.size()==0){
            return 1;
        }else {
            return this.list.size()>=picMax?picMax:this.list.size()+1;
        }
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pic,jian;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic=itemView.findViewById(R.id.duo_pic);
            jian=itemView.findViewById(R.id.duo_del);
        }
    }
    //接口回调
    //1.自定义接口  自定义方法  参数为需要用的数据
    public  interface ClickItemLisenter{
        void add();

        void del(int position);

        void item(int position,List<String> mlist);
    }
    //2.将接口设置为变量
    private  ClickItemLisenter clickItemLisenter;

    //3.提供set方法
    public void setClickItemLisenter(ClickItemLisenter clickItemLisenter) {
        this.clickItemLisenter = clickItemLisenter;
    }
}
