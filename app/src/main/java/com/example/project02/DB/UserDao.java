package com.example.project02.DB;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project02.Flight;
import com.example.project02.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User... user);

    @Update
    void update(User... user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE)
    List<User> getUsers();

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE username = :username" )
    User getUserString(String username);


//    @Query("SELECT * FROM " + AppDataBase.AIRLINE_TABLE + " WHERE mLogId = :logId")
//    User getLogId(int logId);
//
//    @Query("SELECT * FROM " + AppDataBase.AIRLINE_TABLE + " ORDER BY mDate DESC")
//    List<User> getAllLogs();
//



}