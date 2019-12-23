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
import com.example.project02.DB.UserInfoDao;

import java.util.List;

import static com.example.project02.DB.AppDataBase.dbuserinfoname;

public class UserInfoLog extends AppCompatActivity {

    private ListView listView;
    UserInfoDao mUserInfoDao;
    List<UserInfo> userInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_log);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = (ListView) findViewById(R.id.flightListView);

        mUserInfoDao = Room.databaseBuilder(this, AppDataBase.class, dbuserinfoname)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserInfoDAO();

        userInfos = mUserInfoDao.getUserInfos();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, userInfos);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mUserInfoDao.delete(userInfos.get(position));
                userInfos = mUserInfoDao.getUserInfos();

                ArrayAdapter arrayAdapter = new ArrayAdapter(UserInfoLog.this, android.R.layout.simple_list_item_1, userInfos);

                listView.setAdapter(arrayAdapter);

            }
        });
    }
}