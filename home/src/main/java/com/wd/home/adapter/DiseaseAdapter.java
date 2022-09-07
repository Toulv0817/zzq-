package com.wd.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.common.base.SpUtils;
import com.wd.common.model.DiseaseBean;
import com.wd.home.R;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.home.adapter</p>
 * <p>简述:适配器</p>
 *
 * @author 张梓萁
 * @date 2022/8/17
 */
public class DiseaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    DiseaseBean.ResultBean list;
    Context context;

    public DiseaseAdapter(DiseaseBean.ResultBean list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_disease, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof ViewHolder){
                String string = SpUtils.getString("diseasename", "病");
                ViewHolder h= (ViewHolder) holder;
                h.name.setText(string);
                h.li.setText(list.getPathology());
                h.zheng.setText(list.getSymptom());
                h.yi.setText(list.getBenefitTaboo());
                h.xi.setText(list.getWesternMedicineTreatment());
                h.zhong.setText(list.getChineseMedicineTreatment());
            }
    }

    @Override
    public int getItemCount() {
        return 1;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,li,zheng,yi,xi,zhong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.disease_name);
            li=itemView.findViewById(R.id.disease_li);
            zheng=itemView.findViewById(R.id.disease_zheng);
            yi=itemView.findViewById(R.id.disease_yi);
            xi=itemView.findViewById(R.id.disease_xi);
            zhong=itemView.findViewById(R.id.disease_zhong);
        }
    }
}
