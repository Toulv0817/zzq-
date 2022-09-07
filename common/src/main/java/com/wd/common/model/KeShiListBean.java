package com.wd.common.model;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:科室列表</p>
 *
 * @author 张梓萁
 * @date 2022/8/16
 */
public class KeShiListBean {

    /**
     * result : [{"createTime":1547021902000,"departmentName":"内科 ","id":7,"pic":"http://172.17.8.100/images/health/department_pic/nk.jpg","rank":1},{"createTime":1547021886000,"departmentName":"眼科 ","id":4,"pic":"http://172.17.8.100/images/health/department_pic/yk.jpg","rank":2},{"createTime":1547021883000,"departmentName":"骨科 ","id":2,"pic":"http://172.17.8.100/images/health/department_pic/gk.jpg","rank":3},{"createTime":1547021894000,"departmentName":"小儿科 ","id":5,"pic":"http://172.17.8.100/images/health/department_pic/xek.jpg","rank":4},{"createTime":1547021928000,"departmentName":"传染病科 ","id":12,"pic":"http://172.17.8.100/images/health/department_pic/crbk.jpg","rank":5},{"createTime":1547021919000,"departmentName":"皮肤科","id":9,"pic":"http://172.17.8.100/images/health/department_pic/pfk.jpg","rank":6},{"createTime":1547021901000,"departmentName":"耳鼻喉科 ","id":6,"pic":"http://172.17.8.100/images/health/department_pic/ebhk.jpg","rank":7},{"createTime":1547021924000,"departmentName":"精神病科 ","id":11,"pic":"http://172.17.8.100/images/health/department_pic/jsbk.jpg","rank":8}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * createTime : 1547021902000
     * departmentName : 内科
     * id : 7
     * pic : http://172.17.8.100/images/health/department_pic/nk.jpg
     * rank : 1
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
        private String departmentName;
        private int id;
        private String pic;
        private int rank;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }
    }
}
