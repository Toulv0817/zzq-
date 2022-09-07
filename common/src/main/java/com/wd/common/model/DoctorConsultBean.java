package com.wd.common.model;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:问诊医师</p>
 *
 * @author 张梓萁
 * @date 2022/8/19
 */
public class DoctorConsultBean {


    /**
     * result : [{"badNum":2,"doctorId":43,"doctorName":"王越","imagePic":"http://10.59.9.18/images/health/doctor/system_image_pic/system_image2.jpg","inauguralHospital":"北京清华附属医院","jobTitle":"主治医师","praise":"50.00%","praiseNum":21,"serverNum":32,"servicePrice":500},{"badNum":0,"doctorId":39,"doctorName":"侯开文","imagePic":"http://10.59.9.18/images/health/doctor/system_image_pic/system_image5.jpg","inauguralHospital":"医院","jobTitle":"主治医师","praise":"50.00%","praiseNum":22,"serverNum":58,"servicePrice":500},{"badNum":0,"doctorId":34,"doctorName":"czy","imagePic":"http://10.59.9.18/images/health/doctor/system_image_pic/system_image7.jpg","inauguralHospital":"0.0诊所","jobTitle":"主治医师","praise":"50.00%","praiseNum":28,"serverNum":153,"servicePrice":500},{"badNum":0,"doctorId":31,"doctorName":"杨少华","imagePic":"http://10.59.9.18/images/health/doctor/system_image_pic/system_image4.jpg","inauguralHospital":"北京八维","jobTitle":"主治医师","praise":"0.00%","praiseNum":0,"serverNum":80,"servicePrice":500},{"badNum":0,"doctorId":9,"doctorName":"陈医生","imagePic":"http://10.59.9.18/images/health/doctor/system_image_pic/system_image3.jpg","inauguralHospital":"北京女子仁和医院","jobTitle":"主任医师","praise":"50.00%","praiseNum":2,"serverNum":73,"servicePrice":500}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * badNum : 2
     * doctorId : 43
     * doctorName : 王越
     * imagePic : http://10.59.9.18/images/health/doctor/system_image_pic/system_image2.jpg
     * inauguralHospital : 北京清华附属医院
     * jobTitle : 主治医师
     * praise : 50.00%
     * praiseNum : 21
     * serverNum : 32
     * servicePrice : 500
     */

    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private int badNum;
        private int doctorId;
        private String doctorName;
        private String imagePic;
        private String inauguralHospital;
        private String jobTitle;
        private String praise;
        private int praiseNum;
        private int serverNum;
        private int servicePrice;

        public int getBadNum() {
            return badNum;
        }

        public void setBadNum(int badNum) {
            this.badNum = badNum;
        }

        public int getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getImagePic() {
            return imagePic;
        }

        public void setImagePic(String imagePic) {
            this.imagePic = imagePic;
        }

        public String getInauguralHospital() {
            return inauguralHospital;
        }

        public void setInauguralHospital(String inauguralHospital) {
            this.inauguralHospital = inauguralHospital;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getServerNum() {
            return serverNum;
        }

        public void setServerNum(int serverNum) {
            this.serverNum = serverNum;
        }

        public int getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(int servicePrice) {
            this.servicePrice = servicePrice;
        }
    }
}
