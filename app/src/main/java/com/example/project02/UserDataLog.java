package com.example.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project02.DB.AppDataBase;
import com.example.project02.DB.UserDao;

import java.util.List;

public class UserDataLog extends AppCompatActivity {

    private ListView listView;
    UserDao mUserDao;
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        listView = (ListView) findViewById(R.id.flightListView);

        mUserDao = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbname)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDAO();

        users = mUserDao.getUsers();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, users);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mUserDao.delete(users.get(position));
                users = mUserDao.getUsers();

                ArrayAdapter arrayAdapter = new ArrayAdapter(UserDataLog.this, android.R.layout.simple_list_item_1, users);

                listView.setAdapter(arrayAdapter);

            }
        });
    }
}
