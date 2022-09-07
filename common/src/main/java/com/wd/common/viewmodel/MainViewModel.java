package com.wd.common.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.wd.common.BuyVideoListBean;
import com.wd.common.ByqScBean;
import com.wd.common.base.BaseViewModel;
import com.wd.common.model.AddMyFileBean;
import com.wd.common.model.AddMyFilePicBean;
import com.wd.common.model.BarageBean;
import com.wd.common.model.BuyBean;
import com.wd.common.model.BuyRecoud;
import com.wd.common.model.CancleScBean;
import com.wd.common.model.CancleZxScBean;
import com.wd.common.model.CollectBean;
import com.wd.common.model.ConsultDoctor;
import com.wd.common.model.DanMuBarBean;
import com.wd.common.model.DelMyFileBean;
import com.wd.common.model.DiseaseBean;
import com.wd.common.model.DoctorConsultBean;
import com.wd.common.model.DoctorDetaBean;
import com.wd.common.model.GrugXqBean;
import com.wd.common.model.HealthTabBean;
import com.wd.common.model.HttpUtils;
import com.wd.common.model.IsSingInBean;
import com.wd.common.model.JkTabBean;
import com.wd.common.model.KeShiList2Bean;
import com.wd.common.model.KeShiListBean;
import com.wd.common.model.KeShiSearch2Bean;
import com.wd.common.model.KeShiSearchBean;
import com.wd.common.model.LoginBean;
import com.wd.common.model.LookMyFileBean;
import com.wd.common.model.MyMoneyBean;
import com.wd.common.model.PatientCircleAddPicBean;
import com.wd.common.model.PatientCircleBean;
import com.wd.common.model.PatientCircleXqBean;
import com.wd.common.model.PutMyFileBean;
import com.wd.common.model.RegBean;
import com.wd.common.model.ScByqListBean;
import com.wd.common.model.ScVideoListBean;
import com.wd.common.model.ScZxBean;
import com.wd.common.model.ScZxListBean;
import com.wd.common.model.SearchBean;
import com.wd.common.model.SendCodeBean;
import com.wd.common.model.SendPatientCircleBean;
import com.wd.common.model.SingInBean;
import com.wd.common.model.UpPicBean;
import com.wd.common.model.UpdateNameBean;
import com.wd.common.model.VideoBean;
import com.wd.common.model.XbaBean;
import com.wd.common.model.XqBean;
import com.wd.common.model.ZxlistBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * <p>项目名称:Zzq0812</p>
 * <p>包名:com.wd.common</p>
 * <p>简述:vm</p>
 *
 * @author 张梓萁
 * @date 2022/8/15
 */
public class MainViewModel extends BaseViewModel {
    public MainViewModel(@NonNull Application application) {
        super(application);
    }
    //轮播图
    private MutableLiveData<XbaBean> xbaBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<XbaBean> getXbaBeanMutableLiveData() {
        HttpUtils.getHttpUtils().getApiService()
                .xba()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XbaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XbaBean value) {
                        xbaBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return xbaBeanMutableLiveData;

    }

    //资讯列表
    private MutableLiveData<ZxlistBean> zxlistBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<ZxlistBean> getZxlistBeanMutableLiveData(int type,int page,int count) {
        HttpUtils.getHttpUtils().getApiService().zxlist(type, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZxlistBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZxlistBean value) {
                        zxlistBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return zxlistBeanMutableLiveData;
    }
    //健康资讯模块
    private MutableLiveData<JkTabBean> jkzx=new MutableLiveData<>();

    public MutableLiveData<JkTabBean> getJkzx() {
        HttpUtils.getHttpUtils().getApiService().jkzx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JkTabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JkTabBean value) {
                        jkzx.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return jkzx;
    }
    //登录
    private MutableLiveData<LoginBean> loginBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<LoginBean> getLoginBeanMutableLiveData(String email,String pwd) {
        HttpUtils.getHttpUtils().getApiService().login(email, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean value) {
                        loginBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return loginBeanMutableLiveData;
    }
    //注册
    private MutableLiveData<RegBean> regBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<RegBean> getRegBeanMutableLiveData(String email,String yzm,String pwd1,String pwd2,String yqm) {
        HttpUtils.getHttpUtils().getApiService().reg(email, yzm, pwd1, pwd2, yqm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegBean value) {
                        regBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return regBeanMutableLiveData;
    }
    //验证码
    private MutableLiveData<SendCodeBean> sendCodeBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<SendCodeBean> getSendCodeBeanMutableLiveData(String email) {
        HttpUtils.getHttpUtils().getApiService().sendcode(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SendCodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SendCodeBean value) {
                        sendCodeBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return sendCodeBeanMutableLiveData;
    }
    //资讯详情
    private MutableLiveData<XqBean> xqBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<XqBean> getXqBeanMutableLiveData(int id) {
        HttpUtils.getHttpUtils().getApiService().xq(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XqBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XqBean value) {
                        xqBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return xqBeanMutableLiveData;
    }
    //查询科室列表
    private MutableLiveData<KeShiListBean> keShiListBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<KeShiListBean> getKeShiListBeanMutableLiveData() {
        HttpUtils.getHttpUtils().getApiService()
                .kslsit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KeShiListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KeShiListBean value) {
                        keShiListBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return keShiListBeanMutableLiveData;
    }
    //根据科室查询病症
    private MutableLiveData<KeShiSearchBean> keShiSearchBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<KeShiSearchBean> getKeShiSearchBeanMutableLiveData(int id) {
        HttpUtils.getHttpUtils().getApiService().kssearch(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KeShiSearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KeShiSearchBean value) {
                        keShiSearchBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return keShiSearchBeanMutableLiveData;
    }
    //查询常见病症详情
    private MutableLiveData<DiseaseBean> diseaseBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<DiseaseBean> getDiseaseBeanMutableLiveData(int id) {
        HttpUtils.getHttpUtils().getApiService().diseasexq(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DiseaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DiseaseBean value) {
                        diseaseBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return diseaseBeanMutableLiveData;
    }

    //药品科室
    private MutableLiveData<KeShiList2Bean> shiList2BeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<KeShiList2Bean> getShiList2BeanMutableLiveData() {
        HttpUtils.getHttpUtils().getApiService().ks2lsit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KeShiList2Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KeShiList2Bean value) {
                        shiList2BeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return shiList2BeanMutableLiveData;
    }
    //根据药品查询药
    private MutableLiveData<KeShiSearch2Bean> keShiSearch2BeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<KeShiSearch2Bean> getKeShiSearch2BeanMutableLiveData(int id,int page,int count) {
        HttpUtils.getHttpUtils().getApiService().kesearch2(id, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KeShiSearch2Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KeShiSearch2Bean value) {
                        keShiSearch2BeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return keShiSearch2BeanMutableLiveData;
    }
    //查询常见药品详情
    protected MutableLiveData<GrugXqBean> grugXqBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<GrugXqBean> getGrugXqBeanMutableLiveData(int id) {
        HttpUtils.getHttpUtils().getApiService().grugxq(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GrugXqBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GrugXqBean value) {
                        grugXqBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return grugXqBeanMutableLiveData;
    }
    //首页搜索
    private MutableLiveData<SearchBean> searchBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<SearchBean> getSearchBeanMutableLiveData(String name) {
        HttpUtils.getHttpUtils().getApiService().search(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchBean value) {
                        searchBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return searchBeanMutableLiveData;
    }
    //查询健康讲堂类目
    private MutableLiveData<HealthTabBean> healthTabBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<HealthTabBean> getHealthTabBeanMutableLiveData() {
        HttpUtils.getHttpUtils().getApiService().jkjt()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HealthTabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HealthTabBean value) {
                        healthTabBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return healthTabBeanMutableLiveData;
    }
    ////根据视频类目查询视频列表
    private MutableLiveData<VideoBean> videoBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<VideoBean> getVideoBeanMutableLiveData(int id,int page,int count) {
        HttpUtils.getHttpUtils().getApiService().video(id, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideoBean value) {
                        videoBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return videoBeanMutableLiveData;
    }
    ////查询问诊医生列表
    private MutableLiveData<DoctorConsultBean> doctorConsultBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<DoctorConsultBean> getDoctorConsultBeanMutableLiveData(int id,int type,int px,int page,int count) {
        HttpUtils.getHttpUtils().getApiService().consultdoctor(id, type, px, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DoctorConsultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DoctorConsultBean value) {
                        doctorConsultBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return doctorConsultBeanMutableLiveData;
    }
    ////查询视频评论列表  弹幕列表
    private MutableLiveData<DanMuBarBean> danMuBarBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<DanMuBarBean> getDanMuBarBeanMutableLiveData(int id) {
        HttpUtils.getHttpUtils().getApiService().danmu(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DanMuBarBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DanMuBarBean value) {
                        danMuBarBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return danMuBarBeanMutableLiveData;
    }
    //健康课堂视频收藏
    private MutableLiveData<CollectBean> collectBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<CollectBean> getCollectBeanMutableLiveData(int id) {
        HttpUtils.getHttpUtils().getApiService().sc(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CollectBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CollectBean value) {
                        collectBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return collectBeanMutableLiveData;
    }
    ////健康课堂视频购买
    private MutableLiveData<BuyBean> buyBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<BuyBean> getBuyBeanMutableLiveData(int id,int price) {
        HttpUtils.getHttpUtils().getApiService().buy(id, price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BuyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BuyBean value) {
                        buyBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return buyBeanMutableLiveData;
    }
    //咨询医生
    private MutableLiveData<ConsultDoctor> consultDoctorMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<ZxlistBean> getZxlistBeanMutableLiveData(int id) {
        HttpUtils.getHttpUtils().getApiService().zxdoctot(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConsultDoctor>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ConsultDoctor value) {
                        consultDoctorMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return zxlistBeanMutableLiveData;
    }
    //发表视频评论  弹幕
    private MutableLiveData<BarageBean> barageBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<BarageBean> getBarageBeanMutableLiveData(int id,String name) {
        HttpUtils.getHttpUtils().getApiService().fdm(id, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BarageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BarageBean value) {
                        barageBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return barageBeanMutableLiveData;
    }
    // //病友圈列表展示
    private MutableLiveData<PatientCircleBean> patientCircleBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<PatientCircleBean> getPatientCircleBeanMutableLiveData(int id,int page,int count) {
        HttpUtils.getHttpUtils().getApiService().byqlist(id, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PatientCircleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PatientCircleBean value) {
                        patientCircleBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return patientCircleBeanMutableLiveData;
    }
    //查询病友圈详情
    private MutableLiveData<PatientCircleXqBean> patientCircleXqBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<PatientCircleXqBean> getPatientCircleXqBeanMutableLiveData(int id) {
        HttpUtils.getHttpUtils().getApiService().cxbyq(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PatientCircleXqBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PatientCircleXqBean value) {
                        patientCircleXqBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return patientCircleXqBeanMutableLiveData;
    }
    //上传图片
    private MutableLiveData<UpPicBean> upPicBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<UpPicBean> getUpPicBeanMutableLiveData(MultipartBody.Part file) {
        HttpUtils.getHttpUtils().getApiService()
                .uppic(file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpPicBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpPicBean value) {
                        upPicBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return upPicBeanMutableLiveData;
    }
    //发病友圈
    private MutableLiveData<SendPatientCircleBean> sendPatientCircleBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<SendPatientCircleBean> getSendPatientCircleBeanMutableLiveData(String tital,int id,String ms,String xq,
                                                                                          String hospital,String starttime,
                                                                                          String stoptime,String process,
                                                                                          int xs) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("title",tital);
        map.put("departmentId",id+"");
        map.put("disease",ms);
        map.put("detail",xq);
        map.put("treatmentHospital",hospital);
        map.put("treatmentStartTime",starttime);
        map.put("treatmentEndTime",stoptime);
        map.put("treatmentProcess",process);
        map.put("amount",xs+"");
        HttpUtils.getHttpUtils().getApiService().sendbyq(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SendPatientCircleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SendPatientCircleBean value) {
                        sendPatientCircleBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return sendPatientCircleBeanMutableLiveData;
    }
    //取消视频收藏
    private MutableLiveData<CancleScBean> cancleScBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<CancleScBean> getCancleScBeanMutableLiveData(int id) {
        HttpUtils.getHttpUtils().getApiService().qxsc(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CancleScBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CancleScBean value) {
                        cancleScBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return cancleScBeanMutableLiveData;
    }
    //收藏资讯
    private MutableLiveData<ScZxBean> scZxBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<ScZxBean> getScZxBeanMutableLiveData(int id) {
        HttpUtils.getHttpUtils().getApiService().sczx(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScZxBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScZxBean value) {
                        scZxBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return scZxBeanMutableLiveData;
    }
    //取消收藏资讯
    private MutableLiveData<CancleZxScBean> cancleZxScBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<CancleZxScBean> getCancleZxScBeanMutableLiveData(int id) {
        HttpUtils.getHttpUtils().getApiService().qxzxsc(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CancleZxScBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CancleZxScBean value) {
                        cancleZxScBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return cancleZxScBeanMutableLiveData;
    }
    //上传病友圈图片
    private MutableLiveData<PatientCircleAddPicBean> patientCircleAddPicBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<PatientCircleAddPicBean> getPatientCircleAddPicBeanMutableLiveData(int id, List<MultipartBody.Part> list) {
        HttpUtils.getHttpUtils().getApiService().addbyqpic(id,list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PatientCircleAddPicBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PatientCircleAddPicBean value) {
                        patientCircleAddPicBeanMutableLiveData.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return patientCircleAddPicBeanMutableLiveData;
    }
    //是否签到
    private MutableLiveData<IsSingInBean> isSingInBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<IsSingInBean> getIsSingInBeanMutableLiveData() {
        HttpUtils.getHttpUtils().getApiService().issingin()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IsSingInBean>() {
                    @Override
                    public void onSubscribe( Disposable d) {

                    }

                    @Override
                    public void onNext( IsSingInBean isSingInBean) {
                        isSingInBeanMutableLiveData.postValue(isSingInBean);
                    }

                    @Override
                    public void onError( Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return isSingInBeanMutableLiveData;
    }
    //签到
    private MutableLiveData<SingInBean> singInBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<SingInBean> getSingInBeanMutableLiveData() {
        HttpUtils.getHttpUtils().getApiService()
                .qd()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SingInBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull SingInBean singInBean) {
                        singInBeanMutableLiveData.postValue(singInBean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return singInBeanMutableLiveData;
    }
    //查询我的档案
    private MutableLiveData<LookMyFileBean> lookMyFileBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<LookMyFileBean> getLookMyFileBeanMutableLiveData() {
        HttpUtils.getHttpUtils().getApiService().lookmyfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LookMyFileBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull LookMyFileBean lookMyFileBean) {
                            lookMyFileBeanMutableLiveData.postValue(lookMyFileBean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return lookMyFileBeanMutableLiveData;
    }
    //添加我的档案
    private MutableLiveData<AddMyFileBean> addmyFileBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<AddMyFileBean> getAddMyFileBeanMutableLiveData(String symptom,String diseasenow,String diseasebefore,String hospital,String process,
                                                                       String starttime,String stoptime) {
        HashMap<String, String> map = new HashMap<>();
        map.put("diseaseMain",symptom);
        map.put("diseaseNow",diseasenow);
        map.put("diseaseBefore",diseasebefore);
        map.put("treatmentHospitalRecent",hospital);
        map.put("treatmentProcess",process);
        map.put("treatmentStartTime",starttime);
        map.put("treatmentEndTime",stoptime);
        HttpUtils.getHttpUtils().getApiService().addmyfile(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddMyFileBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull AddMyFileBean addMyFileBean) {
                        addmyFileBeanMutableLiveData.postValue(addMyFileBean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return addmyFileBeanMutableLiveData;


    }
    //上传用户档案相关图片
    private MutableLiveData<AddMyFilePicBean> addMyFilePicBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<AddMyFilePicBean> getaddMyFilePicBeanMutableLiveData(List<MultipartBody.Part> list) {
        HttpUtils.getHttpUtils().getApiService().addfilepic(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddMyFilePicBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull AddMyFilePicBean addMyFilePicBean) {
                        addMyFilePicBeanMutableLiveData.postValue(addMyFilePicBean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return addMyFilePicBeanMutableLiveData;
    }
    //删除我的档案
    private MutableLiveData<DelMyFileBean> delMyFileBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<DelMyFileBean> getDelMyFileBeanMutableLiveData(int id) {
        HttpUtils.getHttpUtils().getApiService().delmyfile(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DelMyFileBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull DelMyFileBean delMyFileBean) {
                        delMyFileBeanMutableLiveData.postValue(delMyFileBean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return delMyFileBeanMutableLiveData;
    }
    //修改我的档案
    private MutableLiveData<PutMyFileBean> putMyFileBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<PutMyFileBean> getPutMyFileBeanMutableLiveData(int id,String bz,String xbs,String jws,String hosptal,String gc,
                                                                          String starttime,String stop) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("archivesId",id);
        map.put("diseaseMain",bz);
        map.put("diseaseNow",xbs);
        map.put("diseaseBefore",jws);
        map.put("treatmentHospitalRecent",hosptal);
        map.put("treatmentProcess",gc);
        map.put("treatmentStartTime",starttime);
        map.put("treatmentEndTime",stop);
        HttpUtils.getHttpUtils().getApiService().putmyfile(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PutMyFileBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull PutMyFileBean putMyFileBean) {
                        putMyFileBeanMutableLiveData.postValue(putMyFileBean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return putMyFileBeanMutableLiveData;
    }
    ////我的钱包
    private MutableLiveData<MyMoneyBean> myMoneyBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<MyMoneyBean> getMyMoneyBeanMutableLiveData() {
        HttpUtils.getHttpUtils().getApiService().mymonkey()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyMoneyBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull MyMoneyBean myMoneyBean) {
                            myMoneyBeanMutableLiveData.postValue(myMoneyBean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return myMoneyBeanMutableLiveData;
    }
    //查询用户资讯收藏列表
    private MutableLiveData<ScZxListBean> scZxListBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<ScZxListBean> getScZxListBeanMutableLiveData(int page,int count) {
        HttpUtils.getHttpUtils().getApiService().scxzlist(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScZxListBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull ScZxListBean scZxListBean) {
                        scZxListBeanMutableLiveData.postValue(scZxListBean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return scZxListBeanMutableLiveData;
    }
    //用户收藏健康课堂视频列表
    private MutableLiveData<ScVideoListBean> scVideoListBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<ScVideoListBean> getScVideoListBeanMutableLiveData(int page,int count) {
        HttpUtils.getHttpUtils().getApiService().scvideolist(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScVideoListBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull ScVideoListBean scVideoListBean) {
                        scVideoListBeanMutableLiveData.postValue(scVideoListBean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return scVideoListBeanMutableLiveData;
    }
    //查询用户收藏病友圈列表
    private MutableLiveData<ScByqListBean> scByqListBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<ScByqListBean> getScByqListBeanMutableLiveData(int page,int count) {
        HttpUtils.getHttpUtils().getApiService().scbyqlist(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScByqListBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull ScByqListBean scByqListBean) {
                        scByqListBeanMutableLiveData.postValue(scByqListBean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return scByqListBeanMutableLiveData;
    }
    //收藏病友圈
    private MutableLiveData<ByqScBean> byqScBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<ByqScBean> getByqScBeanMutableLiveData(int id) {
        HttpUtils.getHttpUtils().getApiService().scbyq(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ByqScBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull ByqScBean byqScBean) {
                        byqScBeanMutableLiveData.postValue(byqScBean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return byqScBeanMutableLiveData;
    }
    //查询用户购买视频列表
    private MutableLiveData<BuyVideoListBean> buyVideoListBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<BuyVideoListBean> getBuyVideoListBeanMutableLiveData(int page,int count) {
        HttpUtils.getHttpUtils().getApiService().buyvideo(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BuyVideoListBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull BuyVideoListBean buyVideoListBean) {
                        buyVideoListBeanMutableLiveData.postValue(buyVideoListBean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return buyVideoListBeanMutableLiveData;
    }
    //修改用户昵称
    private MutableLiveData<UpdateNameBean> updateNameBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<UpdateNameBean> getUpdateNameBeanMutableLiveData(String name) {
        HttpUtils.getHttpUtils().getApiService().setname(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateNameBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull UpdateNameBean updateNameBean) {
                        updateNameBeanMutableLiveData.postValue(updateNameBean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return updateNameBeanMutableLiveData;
    }
    //查看用户消费记录
    private MutableLiveData<BuyRecoud> buyRecoudMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<BuyRecoud> getBuyRecoudMutableLiveData(int page,int count) {
        HttpUtils.getHttpUtils().getApiService().buyjl(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BuyRecoud>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull BuyRecoud buyRecoud) {
                        buyRecoudMutableLiveData.postValue(buyRecoud);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return buyRecoudMutableLiveData;
    }
    //查询医生明细
    private MutableLiveData<DoctorDetaBean> doctorDetaBeanMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<DoctorDetaBean> getDoctorDetaBeanMutableLiveData(int id) {
        HttpUtils.getHttpUtils().getApiService().yydeta(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DoctorDetaBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull DoctorDetaBean doctorDetaBean) {
                        doctorDetaBeanMutableLiveData.postValue(doctorDetaBean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return doctorDetaBeanMutableLiveData;
    }
}





