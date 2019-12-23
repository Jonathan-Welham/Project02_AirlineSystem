package com.example.project02.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project02.User;
import com.example.project02.UserInfo;

import java.util.List;

@Dao
public interface UserInfoDao {

    @Insert
    void insert(UserInfo... userInfos);

    @Update
    void update(UserInfo... userInfos);

    @Delete
    void delete(UserInfo... userInfos);

    @Query("SELECT * FROM " + AppDataBase.USERINFO_TABLE)
    List<UserInfo> getUserInfos();


}
