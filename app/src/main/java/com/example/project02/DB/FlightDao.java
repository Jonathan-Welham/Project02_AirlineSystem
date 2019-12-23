package com.example.project02.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project02.Flight;
import java.util.List;

@Dao
public interface FlightDao {

    @Insert
    void insert(Flight... flight);

    @Update
    void update(Flight... flight);

    @Delete
    void delete(Flight flight);

    @Query("SELECT * FROM " + AppDataBase.FLIGHT_TABLE)
    Flight getFlight();

    @Query("SELECT * FROM " + AppDataBase.FLIGHT_TABLE)
    List<Flight> getFlights();


}
