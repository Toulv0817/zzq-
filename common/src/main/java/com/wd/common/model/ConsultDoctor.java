package com.wd.common.model;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:咨询医生</p>
 *
 * @author 张梓萁
 * @date 2022/8/22
 */
public class ConsultDoctor {

    /**
     * doctorUserName : VpDYbiCIv/yMrHAuFkF5FrFYxCbQuwrs7Ylz8S1SNBfoy9xSLAe5A1SWQT6iYCGirY0rSOuDUagAe5HpP3atHjveelOQqxrNMAsT3q4rRcTepLHUEGYAgmbLPN+voOR9zdyLWDrryuPWNj0w51yJy4cbuN4DyfYO+hjoDg7Cv+g=
     * message : 查询成功
     * status : 0000
     */

    private String doctorUserName;
    private String message;
    private String status;

    public String getDoctorUserName() {
        return doctorUserName;
    }

    public void setDoctorUserName(String doctorUserName) {
        this.doctorUserName = doctorUserName;
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
