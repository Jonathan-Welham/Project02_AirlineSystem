package com.example.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project02.DB.AppDataBase;
import com.example.project02.DB.FlightDao;
import com.example.project02.DB.UserDao;
import com.example.project02.DB.UserInfoDao;

import java.util.List;

public class ReserveSeat extends AppCompatActivity {

    UserDao mUserDao;
    List<User> users;

    FlightDao mFlightDao;
    List<Flight> flights;

    UserInfoDao mUserInfoDao;
    List<UserInfo> userInfos;

    private EditText mDepartureEditText;
    private EditText mArrivalEditText;
    private EditText mTicketsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seat);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDepartureEditText = (EditText) findViewById(R.id.departureEditText);
        mArrivalEditText = (EditText) findViewById(R.id.arrivalEditText);
        mTicketsEditText = (EditText) findViewById(R.id.ticketsEditText);
        final Button acceptBtn = (Button) findViewById(R.id.acceptBtn);

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


        acceptBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 accept();
             }
         });

    }

    public void accept(){
        flights = mFlightDao.getFlights();
        String departure = mDepartureEditText.getText().toString();
        String arrival = mArrivalEditText.getText().toString();
        String tickets = mTicketsEditText.getText().toString();
        if(departure.equals("") || arrival.equals("") || tickets.equals("")){
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        int tix = Integer.parseInt(mTicketsEditText.getText().toString());
        if(tix > 7){
            Toast.makeText(this, "Cannot reserve more than 7 seats", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, UserFlight.class);
        intent.putExtra("DEPARTURE", departure);
        intent.putExtra("ARRIVAL", arrival);
        intent.putExtra("TICKETS", tix);
        startActivity(intent);


    }
}
