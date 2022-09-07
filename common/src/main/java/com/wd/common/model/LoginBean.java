package com.wd.common.model;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:登录</p>
 *
 * @author 张梓萁
 * @date 2022/8/16
 */
public class LoginBean {

    /**
     * age : 0
     * bankFlag : 0
     * email : 1791064827@qq.com
     * faceFlag : 1
     * headPic : http://10.59.9.18/images/health/user/head_pic/default/default_head_1.jpg
     * height : 0
     * idCardFlag : 0
     * jiGuangPwd : Th9DYT/yb6IjpDJ5t0F8cHoykU23JCZCe7rv1TgsAY3w9/sLU0IpLqncMwfkbR0hC90U1+K9FQJt92Mxu98xUtPawIbS3LbPWyDCUe8tGapKeac9d0nMsMJYwfRpr1331AqLZZJimyU7orwylSiR9kWh4xbMDOYjYHClR2KjSe0=
     * nickName : qo_LWTVT
     * sessionId : 1660634037253128
     * sex : 1
     * userId : 128
     * userName : nvOJnh1791064827
     * weight : 0
     * whetherBingWeChat : 1
     */

    private ResultBean result;
    /**
     * result : {"age":0,"bankFlag":0,"email":"1791064827@qq.com","faceFlag":1,"headPic":"http://10.59.9.18/images/health/user/head_pic/default/default_head_1.jpg","height":0,"idCardFlag":0,"jiGuangPwd":"Th9DYT/yb6IjpDJ5t0F8cHoykU23JCZCe7rv1TgsAY3w9/sLU0IpLqncMwfkbR0hC90U1+K9FQJt92Mxu98xUtPawIbS3LbPWyDCUe8tGapKeac9d0nMsMJYwfRpr1331AqLZZJimyU7orwylSiR9kWh4xbMDOYjYHClR2KjSe0=","nickName":"qo_LWTVT","sessionId":"1660634037253128","sex":1,"userId":128,"userName":"nvOJnh1791064827","weight":0,"whetherBingWeChat":1}
     * message : 登录成功
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
        private int age;
        private int bankFlag;
        private String email;
        private int faceFlag;
        private String headPic;
        private int height;
        private int idCardFlag;
        private String jiGuangPwd;
        private String nickName;
        private String sessionId;
        private int sex;
        private int userId;
        private String userName;
        private int weight;
        private int whetherBingWeChat;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getBankFlag() {
            return bankFlag;
        }

        public void setBankFlag(int bankFlag) {
            this.bankFlag = bankFlag;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getFaceFlag() {
            return faceFlag;
        }

        public void setFaceFlag(int faceFlag) {
            this.faceFlag = faceFlag;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getIdCardFlag() {
            return idCardFlag;
        }

        public void setIdCardFlag(int idCardFlag) {
            this.idCardFlag = idCardFlag;
        }

        public String getJiGuangPwd() {
            return jiGuangPwd;
        }

        public void setJiGuangPwd(String jiGuangPwd) {
            this.jiGuangPwd = jiGuangPwd;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getWhetherBingWeChat() {
            return whetherBingWeChat;
        }

        public void setWhetherBingWeChat(int whetherBingWeChat) {
            this.whetherBingWeChat = whetherBingWeChat;
        }
    }
}
