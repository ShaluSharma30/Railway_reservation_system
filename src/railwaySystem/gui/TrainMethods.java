/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwaySystem.gui;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import railwaySystem.dao.TrainDao;
import railwaySystem.pojo.Train;

/**
 *
 * @author shalu
 */
public class TrainMethods {
    private static final Scanner kb=new Scanner(System.in);
    public static void addTrain() throws SQLException{
        System.out.println("Enter train no.:");
        String trainNo=kb.nextLine();
        System.out.println("Enter train name:");
        String trainName=kb.nextLine();
        System.out.println("Enter train type:");
        String trainType=kb.nextLine();
        System.out.println("Enter arrival time");
        Time arrivalTime=Time.valueOf(kb.nextLine());
        System.out.println("Enter departure time");
        Time departureTime=Time.valueOf(kb.nextLine());
        System.out.println("Enter total seats:");
        int seats=kb.nextInt();
        Train train=new Train(trainNo,trainName,arrivalTime,trainType,departureTime,seats);
        boolean result=TrainDao.addTrain(train);
        if(result)
            System.out.println("Train successfully Added");
        else
            System.out.println("Cannot add the train! Try again.");
    }
    public static void displayTrainFromSrcToDest() throws SQLException {
        System.out.println("Please enter the sorce station code:");
        String source = kb.nextLine();
        System.out.println("Please enter the destination station code:");
        String destination = kb.nextLine();
        List<Train> trains = TrainDao.searchTrainByStationCode(source, destination);
        trains.stream().forEach(t->displayTrain(t));
    }

    public static void displayTrainByNo() throws SQLException {
        System.out.println("Enter Train no:");
        String trainNo = kb.nextLine();
        while (!TrainDao.isValidId(trainNo)) {
            System.out.println("Invalid Train No!");
            trainNo = kb.nextLine();
        }
        Train train = TrainDao.getTrainByNo(trainNo);
        displayTrain(train);
    }
    
    public static void displayAllTrains() throws SQLException{
        List<Train> trains = TrainDao.getAllTrains();
        trains.stream().forEach(t->displayTrain(t));
    }
    
    public static void displayTrain(Train train){
        System.out.println("---------------------------------------------------");
        System.out.println("Train Number:\t" + train.getTrainNo());
        System.out.println("Train Name:\t" + train.getTrainName());
        System.out.println("Train arrival time:\t" + train.getArrivalTime());
        System.out.println("Train departure time:\t" + train.getDepartureTime());
        System.out.println("Train Type:\t" + train.getTrainType());
        System.out.println("total seats available:\t" + train.getSeats());
        System.out.println("---------------------------------------------------");
    }
    
    public static void editTrain() throws SQLException{
        System.out.println("Enter train no:");
        String trainNo=kb.nextLine();
        Train train=TrainDao.getTrainByNo(trainNo);
        displayTrain(train);
        System.out.println("Do you want to edit train name?(y/n)");
        if(kb.nextLine().charAt(0)=='y'){
            System.out.println("Enter train name:");
            train.setTrainName(kb.nextLine());
        }
        System.out.println("Do you want to edit train type?(y/n)");
        if(kb.nextLine().charAt(0)=='y'){
            System.out.println("Enter train type:");
            train.setTrainType(kb.nextLine());
        }
        System.out.println("Do you want to edit arrival time?(y/n)");
        if(kb.nextLine().charAt(0)=='y'){
            System.out.println("Enter new arrival time:");
            train.setArrivalTime(Time.valueOf(kb.nextLine()));
        }
        System.out.println("Do you want to edit departure time?(y/n)");
        if(kb.nextLine().charAt(0)=='y'){
            System.out.println("Enter new departure time:");
            train.setDepartureTime(Time.valueOf(kb.nextLine()));
        }
        System.out.println("Do you want to edit number of seats?(y/n)");
        if(kb.nextLine().charAt(0)=='y'){
            System.out.println("Enter number of seats:");
            train.setSeats(kb.nextInt());
        }
        boolean result=TrainDao.updateTrain(train);
        if(result)
            System.out.println("Successfully updated the train details.");
        else
            System.out.println("Cannot update!! Try again.");
    }
    
    public static void deleteTrain() throws SQLException{
        System.out.println("Enter train no to be deleted:");
        String trainNo=kb.nextLine();
        System.out.println("Are you sure? You want to DELETE the train record with train no= "+trainNo+"(y/n)");
        if(kb.nextLine().charAt(0)=='y'){
            boolean result=TrainDao.removeTrain(trainNo);
            if(result)
                System.out.println("Train record deleted successfully.");
            else
                System.out.println("Cannot delete the record! Try again.");
        }
    }
}
