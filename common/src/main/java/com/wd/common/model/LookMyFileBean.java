package com.wd.common.model;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:查询我的档案</p>
 *
 * @author 张梓萁
 * @date 2022/8/28
 */
public class LookMyFileBean {


    /**
     * diseaseBefore : 1
     * diseaseMain : 1
     * diseaseNow : 2
     * id : 616
     * picture : http://10.59.9.18/images/health/user/archives/2022-08-29/UaWkKK20220829114858.png,http://10.59.9.18/images/health/user/archives/2022-08-29/1LqCOQ20220829114858.png
     * treatmentEndTime : 1554048000000
     * treatmentHospitalRecent : 1
     * treatmentProcess : 1
     * treatmentStartTime : 1554048000000
     * userId : 72
     */

    private ResultBean result;
    /**
     * result : {"diseaseBefore":"1","diseaseMain":"1","diseaseNow":"2","id":616,"picture":"http://10.59.9.18/images/health/user/archives/2022-08-29/UaWkKK20220829114858.png,http://10.59.9.18/images/health/user/archives/2022-08-29/1LqCOQ20220829114858.png","treatmentEndTime":1554048000000,"treatmentHospitalRecent":"1","treatmentProcess":"1","treatmentStartTime":1554048000000,"userId":72}
     * message : 用户档案查询成功
     * status : 0000
     */

    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        private String diseaseBefore;
        private String diseaseMain;
        private String diseaseNow;
        private int id;
        private String picture;
        private long treatmentEndTime;
        private String treatmentHospitalRecent;
        private String treatmentProcess;
        private long treatmentStartTime;
        private int userId;

        public String getDiseaseBefore() {
            return diseaseBefore;
        }

        public void setDiseaseBefore(String diseaseBefore) {
            this.diseaseBefore = diseaseBefore;
        }

        public String getDiseaseMain() {
            return diseaseMain;
        }

        public void setDiseaseMain(String diseaseMain) {
            this.diseaseMain = diseaseMain;
        }

        public String getDiseaseNow() {
            return diseaseNow;
        }

        public void setDiseaseNow(String diseaseNow) {
            this.diseaseNow = diseaseNow;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public long getTreatmentEndTime() {
            return treatmentEndTime;
        }

        public void setTreatmentEndTime(long treatmentEndTime) {
            this.treatmentEndTime = treatmentEndTime;
        }

        public String getTreatmentHospitalRecent() {
            return treatmentHospitalRecent;
        }

        public void setTreatmentHospitalRecent(String treatmentHospitalRecent) {
            this.treatmentHospitalRecent = treatmentHospitalRecent;
        }

        public String getTreatmentProcess() {
            return treatmentProcess;
        }

        public void setTreatmentProcess(String treatmentProcess) {
            this.treatmentProcess = treatmentProcess;
        }

        public long getTreatmentStartTime() {
            return treatmentStartTime;
        }

        public void setTreatmentStartTime(long treatmentStartTime) {
            this.treatmentStartTime = treatmentStartTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
