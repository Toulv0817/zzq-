package com.wd.common.model;

import com.wd.common.BuyVideoListBean;
import com.wd.common.ByqScBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:</p>
 *
 * @author 张梓萁
 * @date 2022/8/15
 */
public interface ApiService {
    //轮播图
    @GET("share/v1/bannersShow")
    Observable<XbaBean> xba();
    //查询资讯列表
    @GET("share/information/v1/findInformationList")
    Observable<ZxlistBean> zxlist(@Query("plateId") int id,@Query("page") int page,@Query("count") int count);
    //健康资讯模块
    @GET("share/information/v1/findInformationPlateList")
    Observable<JkTabBean> jkzx();
    //登录
    @POST("user/v1/login")
    Observable<LoginBean> login(@Query("email") String email,@Query("pwd") String pwd);
    //注册
    @POST("user/v1/register")
    Observable<RegBean> reg(@Query("email") String email,@Query("code") String code,
                            @Query("pwd1") String pwd1,@Query("pwd2") String pwd2,@Query("invitationCode") String invitationCode);
    //发送验证码
    @POST("user/v1/sendOutEmailCode")
    Observable<SendCodeBean> sendcode(@Query("email") String email);
    //资讯详情
    @GET("share/information/v1/findInformation")
    Observable<XqBean> xq(@Query("infoId") int infoId);
    //查询科室列表
    @GET("share/knowledgeBase/v1/findDepartment")
    Observable<KeShiListBean> kslsit();
    //根据科室查询病症
    @GET("share/knowledgeBase/v1/findDiseaseCategory")
    Observable<KeShiSearchBean> kssearch(@Query("departmentId") int id);
    //查询常见病症详情
    @GET("share/knowledgeBase/v1/findDiseaseKnowledge")
    Observable<DiseaseBean> diseasexq(@Query("id") int id);
    //药品科目列表
    @GET("share/knowledgeBase/v1/findDrugsCategoryList")
    Observable<KeShiList2Bean> ks2lsit();
    //根据药品查询药
    @GET("share/knowledgeBase/v1/findDrugsKnowledgeList")
    Observable<KeShiSearch2Bean> kesearch2(@Query("drugsCategoryId") int id,@Query("page") int page,@Query("count") int count);
//    查询常见药品详情
    @GET("share/knowledgeBase/v1/findDrugsKnowledge")
    Observable<GrugXqBean> grugxq(@Query("id") int id);
    //首页搜索
    @GET("share/v1/homePageSearch")
    Observable<SearchBean> search(@Query("keyWord") String  keyWord);
    //查询健康讲堂类目
    @GET("user/video/v1/findVideoCategoryList")
    Observable<HealthTabBean> jkjt();
    //根据视频类目查询视频列表
    @GET("user/video/v1/findVideoVoList")
    Observable<VideoBean> video(@Query("categoryId") int id,@Query("page") int page,@Query("count") int count);
    //查询问诊医生列表
    @GET("user/inquiry/v1/findDoctorList")
    Observable<DoctorConsultBean> consultdoctor(@Query("deptId") int  deptId,@Query("condition") int condition,@Query("sortBy") int sortBy,
                                                @Query("page") int page,@Query("count") int count );
    //查询视频评论列表  弹幕列表
    @GET("user/video/v1/findVideoCommentList")
    Observable<DanMuBarBean> danmu(@Query("videoId") int videoId);
    //发表视频评论（弹幕）
    @POST("user/video/verify/v1/addVideoComment")
    Observable<BarageBean> fdm(@Query("videoId") int id,@Query("content") String content);
    //健康课堂视频收藏
    @POST("user/video/verify/v1/addUserVideoCollection")
    Observable<CollectBean> sc(@Query("videoId") int id);
    //用户取消视频收藏
    @DELETE ("user/verify/v1/cancelVideoCollection")
    Observable<CancleScBean> qxsc(@Query("videoId") int videoId);
    //健康课堂视频购买
    @POST("user/video/verify/v1/videoBuy")
    Observable<BuyBean> buy(@Query("videoId") int id,@Query("price") int price);
    //咨询医生
     @PUT("user/inquiry/verify/v1/consultDoctor")
    Observable<ConsultDoctor> zxdoctot(@Query("doctorId") int doctorId);
     //病友圈列表展示
    @GET("user/sickCircle/v1/findSickCircleList")
    Observable<PatientCircleBean> byqlist(@Query("departmentId") int id,@Query("page") int page,@Query("count") int count);
    //查询病友圈详情
    @GET("user/sickCircle/v1/findSickCircleInfo")
    Observable<PatientCircleXqBean> cxbyq(@Query("sickCircleId") int id);
//    上传头像
    @Multipart
    @POST("user/verify/v1/modifyHeadPic")
    Observable<UpPicBean> uppic(@Part MultipartBody.Part file);
    //发布病友圈
    @POST("user/sickCircle/verify/v1/publishSickCircle")
    Observable<SendPatientCircleBean> sendbyq(@Body HashMap<String,Object> map);
    //收藏资讯
    @POST("user/verify/v1/addInfoCollection")
    Observable<ScZxBean> sczx(@Query("infoId") int id);
    //取消资讯收藏
    @DELETE("user/verify/v1/cancelInfoCollection")
    Observable<CancleZxScBean> qxzxsc(@Query("infoId") int id);
    //上传用户病友圈相关图片
    @Multipart
    @POST("user/sickCircle/verify/v1/uploadSickCirclePicture")
    Observable<PatientCircleAddPicBean> addbyqpic(@Query("sickCircleId") int sickCircleId,
                                            @Part List<MultipartBody.Part> list );
    //查询用户当天是否签到
    @GET("user/verify/v1/whetherSignToday")
    Observable<IsSingInBean> issingin();
    //签到
    @POST("user/verify/v1/addSign")
    Observable<SingInBean> qd();
    //添加用户档案
    @POST("user/verify/v1/addUserArchives")
    Observable<AddMyFileBean> addmyfile(@Body Map<String,String> map);
    //用户查看自己的档案
    @GET("user/verify/v1/findUserArchives")
    Observable<LookMyFileBean> lookmyfile();
    //上传用户档案相关图片
    @Multipart
    @POST("user/verify/v1/uploadArchivesPicture")
    Observable<AddMyFilePicBean> addfilepic(@Part List<MultipartBody.Part> list );
    //删除档案
    @DELETE("user/verify/v1/deleteUserArchives")
    Observable<DelMyFileBean> delmyfile(@Query("archivesId") int id);
    //修改我的档案
    @PUT("user/verify/v1/updateUserArchives")
    Observable<PutMyFileBean> putmyfile(@Body Map<String ,Object> map );
    //我的钱包
    @GET("user/verify/v1/findUserWallet")
    Observable<MyMoneyBean> mymonkey();


    //收藏病友圈
    @POST("user/verify/v1/addUserSickCollection")
    Observable<ByqScBean> scbyq(@Query("sickCircleId") int id);
    //查询用户资讯收藏列表
    @GET("user/verify/v1/findUserInfoCollectionList")
    Observable<ScZxListBean> scxzlist(@Query("page") int page,@Query("count") int count);
    //用户收藏健康课堂视频列表
    @GET("user/verify/v1/findVideoCollectionList")
    Observable<ScVideoListBean> scvideolist(@Query("page") int page,@Query("count") int count);
    //查询用户收藏病友圈列表
    @GET("user/verify/v1/findUserSickCollectionList")
    Observable<ScByqListBean> scbyqlist(@Query("page") int page,@Query("count") int count);
    //查询用户购买视频列表
    @GET("user/verify/v1/findUserVideoBuyList")
    Observable<BuyVideoListBean> buyvideo(@Query("page") int page,@Query("count") int count);
    //修改用户昵称
    @PUT("user/verify/v1/modifyNickName")
    Observable<UpdateNameBean> setname(@Query("nickName") String nickName);
    //查询用户消费记录
    @GET("user/verify/v1/findUserConsumptionRecordList")
    Observable<BuyRecoud> buyjl(@Query("page") int page,@Query("count") int count);
    //微信登录

    //查询医生明细信息
    @GET("user/inquiry/v1/findDoctorInfo")
    Observable<DoctorDetaBean>  yydeta(@Query("doctorId") int id);
}
