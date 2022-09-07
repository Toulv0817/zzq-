package com.wd.common.model;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:健康tab</p>
 *
 * @author 张梓萁
 * @date 2022/8/16
 */
public class JkTabBean {

    /**
     * result : [{"id":1,"name":"健康养生","sort":1},{"id":2,"name":"健康减肥","sort":2},{"id":3,"name":"健康美食","sort":3},{"id":4,"name":"健康美容","sort":4},{"id":5,"name":"慢性疾病","sort":5}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * id : 1
     * name : 健康养生
     * sort : 1
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
        private int sort;

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

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
