package com.example.project02;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project02.DB.AppDataBase;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(tableName = AppDataBase.USER_TABLE)
public class User implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int mLogId;

    private String username;
    private Date mDate;
    private String password;
    private boolean admin;

    private boolean loggedIn;

    public User(String username, String password, boolean admin){
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.mDate = new Date();
        this.loggedIn = false;

    }


    public int getLogId() {
        return mLogId;
    }

    public void setLogId(int mLogId) {
        this.mLogId = mLogId;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }




    @NonNull
    @Override
    public String toString() {
        return  mDate + "\n" +"Username: " + username + "\nPassword: " + password;
    }
}
