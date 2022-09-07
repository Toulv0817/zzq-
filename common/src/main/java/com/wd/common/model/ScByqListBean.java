package com.wd.common.model;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:病友圈收藏列表</p>
 *
 * @author 张梓萁
 * @date 2022/8/29
 */
public class ScByqListBean {

    /**
     * result : [{"collectionNum":1,"commentNum":0,"createTime":1661776519000,"disease":"高脂蛋白血症","id":476,"sickCircleId":991,"title":"abcd"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * collectionNum : 1
     * commentNum : 0
     * createTime : 1661776519000
     * disease : 高脂蛋白血症
     * id : 476
     * sickCircleId : 991
     * title : abcd
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
        private int collectionNum;
        private int commentNum;
        private long createTime;
        private String disease;
        private int id;
        private int sickCircleId;
        private String title;

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

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getDisease() {
            return disease;
        }

        public void setDisease(String disease) {
            this.disease = disease;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
