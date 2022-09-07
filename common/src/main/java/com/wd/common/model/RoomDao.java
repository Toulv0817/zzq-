package com.wd.common.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.common.model</p>
 * <p>简述:</p>
 *
 * @author 张梓萁
 * @date 2022/8/18
 */
@Dao
public interface RoomDao {
    //全查
    @Query("select * from ROOMBEAN")
    List<RoomBean> quertall();
    //添加
    @Insert
    void add(RoomBean bean);
    //删除
    @Query("delete from  RoomBean where name = :keyword")
    void delete(String keyword);
}
