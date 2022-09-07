package com.wd.common.model;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:病友圈列表</p>
 *
 * @author 张梓萁
 * @date 2022/8/23
 */
public class PatientCircleBean {

    /**
     * result : [{"amount":0,"collectionNum":0,"commentNum":0,"detail":"天天腰疼","releaseTime":1660492800000,"sickCircleId":368,"title":"感谢医生治好我的病"},{"amount":0,"collectionNum":0,"commentNum":0,"detail":"天天腰疼","releaseTime":1660492800000,"sickCircleId":367,"title":"感谢医生治好我的病"},{"amount":0,"collectionNum":0,"commentNum":0,"detail":"天天腰疼","releaseTime":1660492800000,"sickCircleId":366,"title":"感谢医生治好我的病"},{"amount":0,"collectionNum":0,"commentNum":0,"detail":"天天腰疼","releaseTime":1660492800000,"sickCircleId":365,"title":"感谢医生治好我的病"},{"amount":0,"collectionNum":0,"commentNum":0,"detail":"天天腰疼","releaseTime":1660492800000,"sickCircleId":364,"title":"感谢医生治好我的病"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * amount : 0
     * collectionNum : 0
     * commentNum : 0
     * detail : 天天腰疼
     * releaseTime : 1660492800000
     * sickCircleId : 368
     * title : 感谢医生治好我的病
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
        private int amount;
        private int collectionNum;
        private int commentNum;
        private String detail;
        private long releaseTime;
        private int sickCircleId;
        private String title;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(int collectionNum) {
            this.collectionNum = collectionNum;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public int getSickCircleId() {
            return sickCircleId;
        }

        public void setSickCircleId(int sickCircleId) {
            this.sickCircleId = sickCircleId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
