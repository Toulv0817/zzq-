package com.wd.common.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:数据库bean</p>
 *
 * @author 张梓萁
 * @date 2022/8/18
 */
@Entity
public class RoomBean {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public RoomBean(int id, String name) {
        this.id = id;
        this.name = name;
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
