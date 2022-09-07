package com.wd.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.common.model.GrugXqBean;
import com.wd.home.R;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.home.adapter</p>
 * <p>简述:适配器</p>
 *
 * @author 张梓萁
 * @date 2022/8/17
 */
public class DragAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    GrugXqBean.ResultBean list;
    Context context;

    public DragAdapter(GrugXqBean.ResultBean list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_drugxq, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder){
            ViewHolder h= (ViewHolder) holder;
            h.name.setText(list.getName());
            h.ji.setText(list.getTaboo());
            h.cf.setText(list.getComponent());
            h.gong.setText(list.getEffect());
            h.yong.setText(list.getUsage());
            h.yao.setText(list.getShape());
            h.bao.setText(list.getPacking());
            h.bu.setText(list.getSideEffects());
            h.cang.setText(list.getStorage());
            h.zhu.setText(list.getMindMatter());
            h.pi.setText(list.getApprovalNumber());
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,cf,ji,gong,yong,yao,bao,bu,cang,zhu,pi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.drug_name);
            cf=itemView.findViewById(R.id.drug_cf);
            ji=itemView.findViewById(R.id.drug_ji);
            gong=itemView.findViewById(R.id.drug_gong);
            yong=itemView.findViewById(R.id.drug_yong);
            yao=itemView.findViewById(R.id.drug_yao);
            bao=itemView.findViewById(R.id.drug_bao);
            bu=itemView.findViewById(R.id.drug_bu);
            cang=itemView.findViewById(R.id.drug_cang);
            zhu=itemView.findViewById(R.id.drug_zhu);
            pi=itemView.findViewById(R.id.drug_pi);
        }
    }
}
