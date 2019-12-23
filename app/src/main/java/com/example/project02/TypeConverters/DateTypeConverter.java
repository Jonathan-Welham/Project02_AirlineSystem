package com.example.project02.TypeConverters;

import androidx.room.TypeConverter;

import com.example.project02.Flight;

import java.util.Date;

public class DateTypeConverter {

    @TypeConverter
    public long convertDateToLong(Date date){
        return date.getTime();
    }

    @TypeConverter
    public Date convertLongToDate(long time){
        return new Date(time);
    }

    @TypeConverter
    public Flight convertFlightToText(Flight flight){
        return flight;
    }
}
