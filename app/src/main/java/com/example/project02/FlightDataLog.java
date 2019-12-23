package com.example.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project02.DB.AppDataBase;
import com.example.project02.DB.FlightDao;
import com.example.project02.DB.UserInfoDao;

import java.util.List;

import static com.example.project02.DB.AppDataBase.dbflightname;

public class FlightDataLog extends AppCompatActivity {

    private ListView listView;
    FlightDao mFlightDao;
    List<Flight> flights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_log);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = (ListView) findViewById(R.id.flightListView);

        mFlightDao = Room.databaseBuilder(this, AppDataBase.class, dbflightname)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getFlightDAO();

        flights = mFlightDao.getFlights();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, flights);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mUserInfoDao.delete(userInfos.get(position));
                flights = mFlightDao.getFlights();

                ArrayAdapter arrayAdapter = new ArrayAdapter(FlightDataLog.this, android.R.layout.simple_list_item_1, flights);

                listView.setAdapter(arrayAdapter);

            }
        });
    }
}
