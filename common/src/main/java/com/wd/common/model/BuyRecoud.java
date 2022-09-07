package com.wd.common.model;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:购买记录</p>
 *
 * @author 张梓萁
 * @date 2022/8/31
 */
public class BuyRecoud {

    /**
     * result : [{"changeNum":-88,"createTime":1661861878000,"direction":2,"remark":"购买健康课堂视频","type":17},{"changeNum":-150,"createTime":1661861871000,"direction":2,"remark":"购买健康课堂视频","type":17},{"changeNum":-50,"createTime":1661861864000,"direction":2,"remark":"购买健康课堂视频","type":17},{"changeNum":0,"createTime":1661754220000,"direction":2,"type":6},{"changeNum":0,"createTime":1661566964000,"direction":2,"type":6}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * changeNum : -88
     * createTime : 1661861878000
     * direction : 2
     * remark : 购买健康课堂视频
     * type : 17
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
        private int changeNum;
        private long createTime;
        private int direction;
        private String remark;
        private int type;

        public int getChangeNum() {
            return changeNum;
        }

        public void setChangeNum(int changeNum) {
            this.changeNum = changeNum;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
