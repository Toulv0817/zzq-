package com.wd.common;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common</p>
 * <p>简述:购买视频列表</p>
 *
 * @author 张梓萁
 * @date 2022/8/30
 */
public class BuyVideoListBean {

    /**
     * result : [{"createTime":1658106061000,"duration":95,"id":101,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek1.mp4","title":"小儿贫血的检查方法有哪些","videoId":1},{"createTime":1658106068000,"duration":98,"id":102,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek2.mp4","title":"儿童错颌畸形如何预防","videoId":2},{"createTime":1658106075000,"duration":165,"id":103,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek3.mp4","title":"小儿长期不爱吃饭是怎么回事","videoId":3},{"createTime":1658106080000,"duration":128,"id":104,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek4.mp4","title":"新生儿黄疸能预防吗","videoId":4}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * createTime : 1658106061000
     * duration : 95
     * id : 101
     * originalUrl : http://172.17.8.100/video/health/original/ek/ek1.mp4
     * title : 小儿贫血的检查方法有哪些
     * videoId : 1
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
        private long createTime;
        private int duration;
        private int id;
        private String originalUrl;
        private String title;
        private int videoId;

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
    }
}
