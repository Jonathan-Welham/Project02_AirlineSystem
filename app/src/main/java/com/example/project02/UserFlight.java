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
import com.example.project02.DB.UserDao;
import com.example.project02.DB.UserInfoDao;

import java.util.ArrayList;
import java.util.List;

public class UserFlight extends AppCompatActivity {

    UserDao mUserDao;
    FlightDao mFlightDao;
    UserInfoDao mUserInfoDao;

    ListView listView;
    List<User> users;
    List<Flight> flights;

    User activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_flight);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String departure = (String)getIntent().getSerializableExtra("DEPARTURE");
        String arrival = (String)getIntent().getSerializableExtra("ARRIVAL");
        final int tickets = (int) getIntent().getSerializableExtra("TICKETS");

        listView = (ListView) findViewById(R.id.flightListView);

        mUserDao = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbname)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDAO();

        mUserInfoDao = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbuserinfoname)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserInfoDAO();

        mFlightDao = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbflightname)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getFlightDAO();

        users = mUserDao.getUsers();
        for(User user : users){
            if(user.isLoggedIn()){
                activeUser = user;
            }
        }

        flights = mFlightDao.getFlights();
        final List<Flight> relativeFlights = new ArrayList<>();
        for(Flight flight: flights){
            if(flight.getDeparture().equals(departure) && flight.getArrival().equals(arrival) && flight.getTickets() >= tickets){
                relativeFlights.add(flight);
            }
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, relativeFlights);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (tickets <= 7) {
                    if(relativeFlights.get(position).getTickets() > 0 && relativeFlights.get(position).getTickets() - tickets >= 0) {
                        mUserInfoDao.insert(new UserInfo(activeUser.getUsername(), relativeFlights.get(position).getFlightNo(), relativeFlights.get(position).getTickets(), relativeFlights.get(position).getmLogId()));
                        relativeFlights.get(position).setTickets(relativeFlights.get(position).getTickets() - tickets);
                    }
                    mFlightDao.update(relativeFlights.get(position));

                    ArrayAdapter arrayAdapter = new ArrayAdapter(UserFlight.this, android.R.layout.simple_list_item_1, relativeFlights);

                    listView.setAdapter(arrayAdapter);
                }

            }
        });
    }
}
