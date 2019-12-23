package com.example.project02;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project02.DB.AppDataBase;

import java.util.Date;

@Entity(tableName = AppDataBase.FLIGHT_TABLE)
//@Entity(tableName = "FLIGHT_TABLE")
public class Flight {

    @PrimaryKey(autoGenerate = true)
    public int mLogId;

    private String flightNo;
    private Date mDate;
    private String departure;
    private String arrival;
    private String departureTime;
    private Double price;
    private int tickets;
    private int reservationNo;

    public Flight(String flightNo, String departure, String arrival, String departureTime, Double price, int tickets) {
        this.reservationNo = mLogId;
        this.flightNo = flightNo;
        this.mDate = new Date();
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.price = price;
        this.tickets = tickets;

//        this.flightCapacity = flightCapacity;
    }


    public int getmLogId() {
        return mLogId;
    }

    public void setmLogId(int mLogId) {
        this.mLogId = mLogId;
    }

    public int getReservationNo() {
        return reservationNo;
    }

    public void setReservationNo(int reservationNo) {
        this.reservationNo = reservationNo;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

//    public int getFlightCapacity() {
//        return flightCapacity;
//    }
//
//    public void setFlightCapacity(int flightCapacity) {
//        this.flightCapacity = flightCapacity;
//    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "FlightNo: " + flightNo +
                "\nDeparture: " + departure +
                "\nArrival: " + arrival +
                "\nDeparture Time: " + departureTime +
                "\nTickets left: " + tickets +
                "\nPrice: $" + price;
    }
}
