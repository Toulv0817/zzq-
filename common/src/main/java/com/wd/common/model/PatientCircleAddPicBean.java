package com.wd.common.model;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:上传病友圈图片</p>
 *
 * @author 张梓萁
 * @date 2022/8/26
 */
public class PatientCircleAddPicBean {

    /**
     * message : 上传成功
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
