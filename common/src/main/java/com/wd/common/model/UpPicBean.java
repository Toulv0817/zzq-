package com.wd.common.model;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:上传图片</p>
 *
 * @author 张梓萁
 * @date 2022/8/24
 */
public class UpPicBean {

    /**
     * result : http://10.59.9.18/images/health/user/head_pic/2022-08-24/rlDBZV20220824160821.png
     * message : 上传成功
     * status : 0000
     */

    private String result;
    private String message;
    private String status;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
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
}
