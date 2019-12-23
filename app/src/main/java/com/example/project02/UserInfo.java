package com.example.project02;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project02.DB.AppDataBase;

import java.util.List;

@Entity(tableName = AppDataBase.USERINFO_TABLE)
public class UserInfo {

    @PrimaryKey(autoGenerate = true)
    public int mLogId;

    private String user;
    private String flight;
    private int tickets;
    private int reservationNo;
//    private double totalPrice;

    public UserInfo(String user, String flight, int tickets, int reservationNo) {
        this.user = user;
        this.flight = flight;
        this.tickets = tickets;
        this.reservationNo = reservationNo;
    }

    public int getLogId() {
        return mLogId;
    }

    public void setLogId(int mLogId) {
        this.mLogId = mLogId;
    }

    public int getTickets() {
        return tickets;
    }

    public int getReservationNo() {
        return reservationNo;
    }

    public void setReservationNo(int reservationNo) {
        this.reservationNo = reservationNo;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Username: " + user +
                "\nFlightNo: " + flight +
                "\nTickets: " + tickets +
                "\nReservationNo: " + reservationNo;
    }
}



