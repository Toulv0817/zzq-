package com.wd.common.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:</p>
 *
 * @author 张梓萁
 * @date 2022/8/18
 */
@Database(entities = RoomBean.class,version = 1,exportSchema = false)
public abstract  class RoomDB extends RoomDatabase {
    public abstract RoomDao dao();
}
