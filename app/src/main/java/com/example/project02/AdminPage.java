package com.example.project02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//TODO: Force quit after 'x' tries
//TODO: Reserve flight constraints layout
//TODO: Handle no seats available when trying to reserve
//TODO: Add a total price when reserving more than 1 ticket
//TODO: Handle test case 8 with a popup window
//TODO: Handle no reservation

//TODO: Test Cases
/*
    Test1: Pass
    Test2: Pass
    Test3: Fail - Needs transaction type
    Test4: Fail - Needs to test password
    Test5: Pass
    Test6: Fail - Needs a display window when reservation made
    Test7: Fail = Needs a display window when reservation made
    Test8: Fail - No Seats Available
    Test9: Fail - No Flight Available
    Test10: Fail - Handle user cancel reservation
    Test11: Fail - Handle user has no reservation to cancel
    Test12: Fail - Handle cancel reservations
    Test13: Pass
    Test14: Fail - Admin add a new flight
    Test15: Fail - Handle invalid Flight Information *Flight# already exists*
    Test16: Fail - Needs to display a window
 */

public class AdminPage extends AppCompatActivity {

    Button userLogBtn;
    Button flightLogBtn;
    Button userFlightLogBtn;
    Button addFlightBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        userLogBtn = (Button) findViewById(R.id.userLogBtn);
        flightLogBtn = (Button) findViewById(R.id.flightLogBtn);
        userFlightLogBtn = (Button) findViewById(R.id.userFlightLog);
        addFlightBtn = (Button) findViewById(R.id.addFlightBtn);


        //View Data__________________________________________________________
        userLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userData();
            }
        });
        flightLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flightData();
            }
        });
        userFlightLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInfoData();
            }
        });

        //Add Data_____________________________________________________________
        addFlightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFlight();
            }
        });
    }

    public void addFlight(){
        Intent intent = new Intent (this, AddFlight.class);
        startActivity(intent);
    }

    public void userData(){
        Intent intent = new Intent(this, UserDataLog.class);
        startActivity(intent);
    }
    public void flightData(){
        Intent intent = new Intent(this, FlightDataLog.class);
        startActivity(intent);
    }
    public void userInfoData(){
        Intent intent = new Intent(this, UserInfoLog.class);
        startActivity(intent);
    }
}
