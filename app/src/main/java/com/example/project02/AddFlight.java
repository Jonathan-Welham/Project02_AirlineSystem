package com.example.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project02.DB.AppDataBase;
import com.example.project02.DB.FlightDao;

import java.util.List;

public class AddFlight extends AppCompatActivity {

    EditText flightNo;
    EditText departure;
    EditText arrival;
    EditText price;
    EditText tickets;
    EditText departureTime;

    FlightDao mFlightDao;
    List<Flight> flights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        flightNo = (EditText) findViewById(R.id.flightNoEditText);
        departure = (EditText) findViewById(R.id.departureEditText);
        arrival = (EditText) findViewById(R.id.arrivalEditText);
        price = (EditText) findViewById(R.id.priceEditText);
        tickets = (EditText) findViewById(R.id.ticketsEditText);
        departureTime = (EditText) findViewById(R.id.departureTimeEditText);

        final Button acceptBtn = findViewById(R.id.acceptBtn);

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
        String flightNumStr = flightNo.getText().toString();
        String departureStr = departure.getText().toString();
        String arrivalStr = arrival.getText().toString();
        String priceStr = price.getText().toString();
        String ticketsStr = tickets.getText().toString();
        String departureTimeStr = departureTime.getText().toString();

        double priceDouble = Double.parseDouble(priceStr);
        int tix = Integer.parseInt(ticketsStr);

        flights = mFlightDao.getFlights();

        if(flightNumStr.equals("") || departureStr.equals("") || arrivalStr.equals("") ||
            priceStr.equals("") || ticketsStr.equals("")){
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        for(Flight flight : flights){
            if(flight.getFlightNo().equals(flightNumStr)){
                Toast.makeText(this, "Invalid entry: Flight number already exists.", Toast.LENGTH_SHORT).show();
                return;
            }
        }


        mFlightDao.insert(new Flight(flightNumStr, departureStr, arrivalStr, departureTimeStr, priceDouble, tix));
        Toast.makeText(this, "New Flight Added", Toast.LENGTH_SHORT).show();
    }
}


