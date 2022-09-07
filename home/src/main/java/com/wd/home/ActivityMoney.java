package com.wd.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wd.common.base.BaseActivity;
import com.wd.common.model.BuyRecoud;
import com.wd.common.model.MyMoneyBean;
import com.wd.common.viewmodel.MainViewModel;
import com.wd.home.databinding.ActivityMoneyBinding;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class ActivityMoney extends BaseActivity<MainViewModel, ActivityMoneyBinding> {


    @Override
    protected void startCoding() {
        vdb.myMonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bvm.getMyMoneyBeanMutableLiveData().observe(this,this::onChanged);
        bvm.getBuyRecoudMutableLiveData(1,100).observe(this,this::onChanged);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_money;
    }

    @Override
    public void onChanged(Object o) {
        if(o instanceof MyMoneyBean){
            MyMoneyBean bean= (MyMoneyBean) o;
            if(bean.getStatus().equals("0000")){
                int result = bean.getResult();
                vdb.money.setText(result+"");
            }
        }else if (o instanceof BuyRecoud){
            BuyRecoud bean= (BuyRecoud) o;
            if(bean.getStatus().equals("0000")){
                List<BuyRecoud.ResultBean> result = bean.getResult();
                CommonAdapter<BuyRecoud.ResultBean> adapter = new CommonAdapter<BuyRecoud.ResultBean>(ActivityMoney.this, R.layout.item_mymonkeyjl, result) {
                    @Override
                    protected void convert(ViewHolder holder, BuyRecoud.ResultBean resultBean, int position) {
                        holder.setText(R.id.buy_name, resultBean.getRemark());
                        holder.setText(R.id.buy_num, resultBean.getChangeNum() + "￥");
                    }
                };
                vdb.myMonRec.setAdapter(adapter);
                vdb.myMonRec.setLayoutManager(new LinearLayoutManager(ActivityMoney.this));
            }
        }
    }
}