/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwaySystem.pojo;

import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author shalu
 */
public class Train {

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public Train(String trainNo, String trainName, Time arrivalTime,String trainType, Time departureTime, int seats) {
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.arrivalTime = arrivalTime;
        this.trainType=trainType;
        this.departureTime = departureTime;
        this.seats = seats;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Train{" + "trainNo=" + trainNo + ", trainName=" + trainName + ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + ", seats=" + seats + '}';
    }
    private String trainNo,trainName,trainType;
    private Time arrivalTime,departureTime;
    private int seats;
}
