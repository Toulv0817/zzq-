package com.wd.common.model;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:根据科室查询病症</p>
 *
 * @author 张梓萁
 * @date 2022/8/16
 */
public class KeShiSearchBean {

    /**
     * result : [{"departmentId":2,"id":17,"name":"颈椎病"},{"departmentId":2,"id":18,"name":"滑囊炎"},{"departmentId":2,"id":19,"name":"类风湿性关节炎"},{"departmentId":2,"id":20,"name":"退行性关节病"},{"departmentId":2,"id":21,"name":"颈椎骨关节炎"},{"departmentId":2,"id":22,"name":"椎关节强硬"},{"departmentId":2,"id":23,"name":"椎间盘突出"},{"departmentId":2,"id":24,"name":"背痛"},{"departmentId":2,"id":25,"name":"骨软化"},{"departmentId":2,"id":26,"name":"骨质疏松症"},{"departmentId":2,"id":27,"name":"重症肌无力"},{"departmentId":2,"id":28,"name":"腱鞘炎"},{"departmentId":2,"id":29,"name":"腱炎"},{"departmentId":2,"id":30,"name":"痛性痉挛"},{"departmentId":2,"id":31,"name":"疝"},{"departmentId":2,"id":32,"name":"骨折"},{"departmentId":2,"id":33,"name":"脱位"},{"departmentId":2,"id":34,"name":"扭伤"},{"departmentId":2,"id":35,"name":"肌肉拉伤"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * departmentId : 2
     * id : 17
     * name : 颈椎病
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
        private int departmentId;
        private int id;
        private String name;

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

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
