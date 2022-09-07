package com.wd.common.model;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:收藏视频列表</p>
 *
 * @author 张梓萁
 * @date 2022/8/29
 */
public class ScVideoListBean {

    /**
     * result : [{"buyNum":63,"createTime":1661518126000,"duration":95,"id":1043,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek1.mp4","price":100,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/ek/ek1.mp4","title":"小儿贫血的检查方法有哪些","videoId":1,"whetherBuy":1},{"buyNum":35,"createTime":1661518045000,"duration":165,"id":1042,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek3.mp4","price":300,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/ek/ek3.mp4","title":"小儿长期不爱吃饭是怎么回事","videoId":3,"whetherBuy":1}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * buyNum : 63
     * createTime : 1661518126000
     * duration : 95
     * id : 1043
     * originalUrl : http://172.17.8.100/video/health/original/ek/ek1.mp4
     * price : 100
     * shearUrl : http://mobile.bwstudent.com/video/health/cut/ek/ek1.mp4
     * title : 小儿贫血的检查方法有哪些
     * videoId : 1
     * whetherBuy : 1
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
        private int buyNum;
        private long createTime;
        private int duration;
        private int id;
        private String originalUrl;
        private int price;
        private String shearUrl;
        private String title;
        private int videoId;
        private int whetherBuy;

        public int getBuyNum() {
            return buyNum;
        }

        public void setBuyNum(int buyNum) {
            this.buyNum = buyNum;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOriginalUrl() {
            return originalUrl;
        }

        public void setOriginalUrl(String originalUrl) {
            this.originalUrl = originalUrl;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getShearUrl() {
            return shearUrl;
        }

        public void setShearUrl(String shearUrl) {
            this.shearUrl = shearUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public int getWhetherBuy() {
            return whetherBuy;
        }

        public void setWhetherBuy(int whetherBuy) {
            this.whetherBuy = whetherBuy;
        }
    }
}
