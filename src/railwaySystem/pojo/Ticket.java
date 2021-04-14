/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwaySystem.pojo;

import java.sql.Date;

/**
 *
 * @author shalu
 */
public class Ticket {

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(Date journeyDate) {
        this.journeyDate = journeyDate;
    }

    public Ticket(String ticketNo, char ticketStatus, byte noOfPassengers, String trainNo,String classType, String userId,Date bDate,Date jDate) {
        this.ticketNo = ticketNo;
        this.ticketStatus = ticketStatus;
        this.noOfPassengers = noOfPassengers;
        this.trainNo = trainNo;
        this.userId = userId;
        this.bookingDate=bDate;
        this.journeyDate=jDate;
        this.classType=classType;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public char getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(char ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public byte getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(byte noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketNo=" + ticketNo + ", ticketStatus=" + ticketStatus + ", noOfPassengers=" + noOfPassengers + ", trainNo=" + trainNo + ", userId=" + userId + '}';
    }
    private String ticketNo;
    private char ticketStatus;
    private byte noOfPassengers;
    private String trainNo,userId,classType;
    private Date bookingDate,journeyDate;
}
