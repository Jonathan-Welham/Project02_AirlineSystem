

//Flight__________________________________________________________________________

package com.example.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project02.DB.AppDataBase;
//import com.example.project02.DB.FlightDao;
import com.example.project02.DB.FlightDao;
import com.example.project02.DB.UserDao;
import com.example.project02.DB.UserInfoDao;
import com.example.project02.Flight;
import com.example.project02.R;
import com.example.project02.User;

import java.util.ArrayList;
import java.util.List;

public class CancelReservation extends AppCompatActivity {

    private ListView listView;
    FlightDao mFlightDao;
    List<Flight> flights;

    UserDao mUserDao;
    List<User> users;

    UserInfoDao mUserInfoDao;
    List<UserInfo> userInfos;

    User activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reservation);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = (ListView) findViewById(R.id.flightListView);

        mUserDao = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbname)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDAO();

        mFlightDao = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbflightname)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getFlightDAO();

        mUserInfoDao = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbuserinfoname)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserInfoDAO();


        users = mUserDao.getUsers();
        for(User user : users){
            if(user.isLoggedIn()){
                activeUser = user;
            }
        }
        userInfos = mUserInfoDao.getUserInfos();
        final List<UserInfo> relativeUserInfo = new ArrayList<>();
        for(UserInfo ui : userInfos){
            if(activeUser.getUsername().equals(ui.getUser())){
                relativeUserInfo.add(ui);
            }
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, relativeUserInfo);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mUserInfoDao.delete(relativeUserInfo.get(position));
                userInfos = mUserInfoDao.getUserInfos();
                ArrayAdapter arrayAdapter = new ArrayAdapter(CancelReservation.this, android.R.layout.simple_list_item_1, userInfos);

                listView.setAdapter(arrayAdapter);

            }
        });


    }
}
