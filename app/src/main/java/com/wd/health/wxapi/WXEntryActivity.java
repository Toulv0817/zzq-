package com.wd.health.wxapi;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.wxapi</p>
 * <p>简述:微信</p>
 *
 * @author 张梓萁
 * @date 2022/8/31
 */
public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private IWXAPI iwxapi;
    private static String APP_ID="wxe3fcbe8a55cd33ff";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //接收到分享以及登录的intent传递handleIntent方法，处理结果
        iwxapi= WXAPIFactory.createWXAPI(this,APP_ID,false);
        iwxapi.handleIntent(getIntent(),this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        //登录回调
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK://代表成功

                //获取code临时票据
                String code = ((SendAuth.Resp) baseResp).code;
                Log.i("TAG", "code："+ code);

                //请求后台封装好的微信登录接口，获取登录后的json数据
                //请求操作省略……

                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                finish();
                break;
            default:
                finish();
                break;
        }
    }
}
