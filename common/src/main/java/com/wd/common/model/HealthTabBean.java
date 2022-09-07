package com.wd.common.model;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:健康讲堂类目</p>
 *
 * @author 张梓萁
 * @date 2022/8/18
 */
public class HealthTabBean {

    /**
     * result : [{"id":1,"name":"儿科"},{"id":2,"name":"常识"},{"id":3,"name":"健身"},{"id":4,"name":"美容"},{"id":5,"name":"心理"},{"id":6,"name":"养生"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * id : 1
     * name : 儿科
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
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
