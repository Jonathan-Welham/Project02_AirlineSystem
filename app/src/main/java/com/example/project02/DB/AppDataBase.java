package com.example.project02.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.project02.Flight;
import com.example.project02.User;
import com.example.project02.TypeConverters.DateTypeConverter;
import com.example.project02.UserInfo;

@Database(entities = {User.class, Flight.class, UserInfo.class }, version = 1)
@TypeConverters(DateTypeConverter.class)
public abstract class AppDataBase extends RoomDatabase {

    //User___________________________________________________
    public static final String dbname = "db-User";

    public static final String USER_TABLE= "User";

    public abstract UserDao getUserDAO();

    //Flight_________________________________________________
    public static final String dbflightname = "db-Flight";

    public static final String FLIGHT_TABLE= "Flight";

    public abstract FlightDao getFlightDAO();

    //UserInfo_________________________________________________
    public static final String dbuserinfoname = "db-UserInfo";

    public static final String USERINFO_TABLE= "UserInfo";

    public abstract UserInfoDao getUserInfoDAO();
}


