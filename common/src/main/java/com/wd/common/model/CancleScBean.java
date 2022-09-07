package com.wd.common.model;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:取消收藏视频</p>
 *
 * @author 张梓萁
 * @date 2022/8/25
 */
public class CancleScBean {

    /**
     * message : 取消成功
     * status : 0000
     */

    private String message;
    private String status;

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
}
