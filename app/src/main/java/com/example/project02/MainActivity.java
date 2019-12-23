package com.example.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project02.DB.AppDataBase;
import com.example.project02.DB.FlightDao;
import com.example.project02.DB.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    UserDao mUserDao;
    FlightDao mFlightDao;
    List<Flight> flights;
    List<User> users;

    TextView userLoggedInTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLoggedInTextView = (TextView) findViewById(R.id.userLoggedInTextView);

        mFlightDao = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbflightname)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getFlightDAO();

        mUserDao = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbname)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDAO();

        users = mUserDao.getUsers();

        userLoggedInTextView.setText("Not logged in");

        if(!users.isEmpty()){
            for(User user : users){
                if(user.isLoggedIn()){
                    userLoggedInTextView.setText(user.getUsername());
                }
                else{
                    userLoggedInTextView.setText("Not logged in");
                }
            }
        }

        if(users.isEmpty()){
            mUserDao.insert(new User("alice5", "csumb100", false));
            mUserDao.insert(new User("brian77", "123ABC", false));
            mUserDao.insert(new User("chris21", "CHRIS21", false));

        }

        flights = mFlightDao.getFlights();
        if(flights.isEmpty()){
            mFlightDao.insert(new Flight("Otter101", "Monterey", "Los Angeles", "10:00(AM)", 150.00, 10));
            mFlightDao.insert(new Flight("Otter102", "Los Angeles", "Monterey", "1:00(PM)", 150.00, 10));
            mFlightDao.insert(new Flight("Otter201", "Monterey", "Seattle", "11:00(AM)", 200.50, 5));
            mFlightDao.insert(new Flight("Otter205", "Monterey", "Seattle", "3:00(PM)", 150.00, 15));
            mFlightDao.insert(new Flight("Otter202", "Seattle", "Monterey", "2:00(PM)", 200.50, 5));
        }

        final Button loginBtn = (Button) findViewById(R.id.loginBtn);
        final Button createAccountBtn = (Button) findViewById(R.id.createAccountBtn);
        final Button reserveSeatBtn = (Button) findViewById(R.id.reserveSeatBtn);
        final Button cancelReservationBtn = (Button) findViewById(R.id.cancelReservationBtn);
        final Button manageSystemBtn = (Button) findViewById(R.id.manageSystemBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        createAccountBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                createAccount();
            }
        });

        reserveSeatBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                reserveSeat();
            }
        });

        cancelReservationBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                cancelReservation();
            }
        });

        manageSystemBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                manageSystem();
            }
        });
    }

    public void login(){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);

    }

    public void createAccount(){
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

    public void reserveSeat(){
        Intent intent = new Intent(this, ReserveSeat.class);
        startActivity(intent);
    }

    public void cancelReservation() {
        Intent intent = new Intent(this, CancelReservation.class);
        startActivity(intent);
    }
    public void manageSystem(){
        Intent intent = new Intent(this, ManageSystem.class);
        startActivity(intent);
    }
}
