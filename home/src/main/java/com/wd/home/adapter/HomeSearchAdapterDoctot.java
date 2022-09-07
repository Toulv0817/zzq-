package com.wd.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.common.model.SearchBean;
import com.wd.home.R;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.home.adapter</p>
 * <p>简述:适配器</p>
 *
 * @author 张梓萁
 * @date 2022/8/17
 */
public class HomeSearchAdapterDoctot extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<SearchBean.ResultBean.DoctorSearchVoListBean> list;
    Context context;

    public HomeSearchAdapterDoctot(List<SearchBean.ResultBean.DoctorSearchVoListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.doctor_name);
        }
    }
}
