/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwaySystem.gui;

import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import railwaySystem.dao.PassengerDao;
import railwaySystem.dao.TicketDao;
import railwaySystem.dao.TrainDao;
import railwaySystem.dao.UserDao;
import railwaySystem.pojo.User;
import railwaySystem.pojo.Passenger;
import railwaySystem.pojo.Ticket;
import railwaySystem.pojo.Train;

/**
 *
 * @author shalu
 */
public class UserModule {

    private static final Scanner kb = new Scanner(System.in);

    public void login() throws SQLException {
        //Take credentials and if it is valid goto login module.
        System.out.println("Enter correct details to login");
        System.out.println("Enter userid");
        String userid = kb.nextLine();
        System.out.println("Enter password");
        String pass = kb.nextLine();
        boolean log = UserDao.isValidUser(userid, pass);
        if (log) {
            UserProfile.setUserId(userid);
            UserProfile.setFname(UserDao.getNameById(userid));
            loginMenu();
        } else {
            System.out.println("Invalid Credentials!!!");
        }
    }

    public void register() throws SQLException {
        System.out.println("Enter first name");
        String fname = kb.nextLine();
        System.out.println("Enter last name");
        String lname = kb.nextLine();

        System.out.println("Enter age");
        byte age = kb.nextByte();
        kb.nextLine();
        System.out.println("Enter phone number");
        String mob = kb.nextLine();
        System.out.println("Enter email");
        String email = kb.nextLine();
        System.out.println("Enter gender");
        char gender = kb.nextLine().charAt(0);
        System.out.println("Enter password");
        String password = kb.nextLine();

        String id = UserDao.getNextId();
        User o = new User(id, fname, lname, age, mob, email, gender, password);
        boolean check = UserDao.registerUser(o);
        //System.out.println(check);
        if (check) {
            System.out.println("User Registered successfully");
            System.out.println("Your user Id= "+id);
        } else {
            System.out.println("User not registered!!!");
        }

    }

    private void loginMenu() {
        System.out.println("-----------------Hello "+UserProfile.getFname()+"------------------------");
        x:
        while (true) {
            System.out.println("--------------------------Welcome to Login Module----------------------------");
            System.out.println("1. Get train from your source station to destination");
            System.out.println("2. Get train details by Train no.");
            System.out.println("3. Get all train details");
            System.out.println("4. Book Ticket");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. Get your ticket status");
            System.out.println("7. Logout");
            System.out.println("8. Exit");
            System.out.println("Enter the option no.:");
            byte n;
            try {
                n = kb.nextByte();
                kb.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter numbers only!");
                kb.nextLine();
                continue;
            }
            try {
                switch (n) {
                    case 1:
                        displayTrainFromSrcToDest();
                        break;
                    case 2:
                        displayTrainByNo();
                        break;
                    case 3:
                        displayAllTrains();
                        break;
                    case 4:
                        bookTicket();
                        break;
                    case 5:
                        cancelTicket();
                        break;
                    case 6:
                        displayTicket();
                        break;
                    case 7:
                        return;
                    case 8:
                        System.exit(0);
                    default:
                        System.out.println("Please enter a valid option number");
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    private void bookTicket() throws SQLException {
        System.out.println("-----------------------Welcome to the Ticket Booking Module--------------------");
        System.out.println("Enter Train no.:");
        String trainNo = kb.nextLine();
        while (!TrainDao.isValidId(trainNo)) {
            System.out.println("Invalid Train no.!");
            trainNo = kb.nextLine();
        }
        
        String ticketId = TicketDao.getNextId();
        System.out.println("ID generated..."+ticketId);
        System.out.println("Enter the class of the train:\nGeneral\tSleeper\tAC1\tAC2\tAC3");
        String cl;
        cl = kb.nextLine();
        System.out.println("Enter date of journey:\nYour date of journey should be in the format YYYY-MM-DD");
        System.out.println("Your date of journey should be between:" + LocalDate.now().plusDays(1) + " and " + LocalDate.now().plusDays(4));
        Date jDate = Date.valueOf(kb.nextLine().trim());
        System.out.println("Enter no. of Passenger:");
        byte passNo = kb.nextByte();
        String userId = UserProfile.getUserId();
        System.out.println(new java.util.Date().toString());
        Date bDate = new Date(new java.util.Date().getTime());
        Ticket ticket = new Ticket(ticketId, '1', passNo, trainNo, userId, cl, bDate, jDate);
        Passenger p[] = new Passenger[passNo];
        for (int i = 0; i < passNo; i++) {
            System.out.println("Enter details of Passenger no" + (i+1));
            p[i] = getPassengerDetails(ticketId);

        }
        boolean result = TicketDao.bookTicketFor(ticket, p);
        if (result) {
            System.out.println("***Your ticket is successfully booked***");
            System.out.println("Please Note your ticket id: "+ticketId);
        } else {
            System.out.println("Sorry your ticket is not booked. please try again.");
        }

    }

    private Passenger getPassengerDetails(String ticketId) throws SQLException {
        String pnr = PassengerDao.getNextPnr();
        String userId = UserProfile.getUserId();
        System.out.println("Enter first Name:");
        String fname = kb.next();
        System.out.println("Enter Last Name");
        String lname = kb.next();
        System.out.println("Enter age:");
        byte age = kb.nextByte();
        System.out.println("Enter gender(M/F)");
        char gender = kb.next().charAt(0);
        Passenger passenger = new Passenger(pnr, userId, fname, lname, ticketId, age, gender);
        return passenger;

    }

    private static void cancelTicket() throws SQLException {
        System.out.println("Enter ticket no:");
        String ticketNo = kb.next();
        while (!TicketDao.isValidTicketNo(ticketNo)) {
            System.out.println("Invalid Ticket No.!!");
            ticketNo = kb.next();
        }
        boolean result = TicketDao.cancelTicket(ticketNo);
        if (result) {
            System.out.println("Ticket Cancelled...");
        } else {
            System.out.println("Some problem occured. Please try again.");
        }
    }

    private void displayTicket() throws SQLException {
        System.out.println("Enter Ticket Id:");
        String tid = kb.nextLine();
        while (!TicketDao.isValidTicketNo(tid)) {
            System.out.println("Invalid Id!!!");
            tid = kb.nextLine();
        }
        Ticket ticket = TicketDao.getTicketById(tid);
        System.out.println("--------------------------Ticket Details-----------------------");
        System.out.println("Ticket Id:\t" + ticket.getTicketNo());
        System.out.println("Status:\t" + ticket.getTicketStatus());
        System.out.println("No of Passengers:\t" + ticket.getNoOfPassengers());
        System.out.println("Train No:\t" + ticket.getTrainNo());
        System.out.println("User Id:\t" + ticket.getUserId());
        System.out.println("Class Type:\t" + ticket.getClassType());
        System.out.println("Booking Date:\t" + ticket.getBookingDate().toString());
        System.out.println("Journey Date:\t" + ticket.getJourneyDate().toString());
        System.out.println("-----------------------------------------------------------------");
    }

    private void displayTrainFromSrcToDest() throws SQLException {
        System.out.println("Please enter the sorce station code:");
        String source = kb.nextLine();
        System.out.println("Please enter the destination station code:");
        String destination = kb.nextLine();
        List<Train> trains = TrainDao.searchTrainByStationCode(source, destination);
        trains.stream().forEach(t->displayTrain(t));
    }

    private void displayTrainByNo() throws SQLException {
        System.out.println("Enter Train no:");
        String trainNo = kb.nextLine();
        while (!TrainDao.isValidId(trainNo)) {
            System.out.println("Invalid Train No!");
            trainNo = kb.nextLine();
        }
        Train train = TrainDao.getTrainByNo(trainNo);
        displayTrain(train);
    }
    
    private  void displayAllTrains() throws SQLException{
        List<Train> trains = TrainDao.getAllTrains();
        trains.stream().forEach(t->displayTrain(t));
    }
    
    private void displayTrain(Train train){
        System.out.println("---------------------------------------------------");
        System.out.println("Train Number:\t" + train.getTrainNo());
        System.out.println("Train Name:\t" + train.getTrainName());
        System.out.println("Train arrival time:\t" + train.getArrivalTime());
        System.out.println("Train departure time:\t" + train.getDepartureTime());
        System.out.println("Train Type:\t" + train.getTrainType());
        System.out.println("total seats available:\t" + train.getSeats());
        System.out.println("---------------------------------------------");
    }
    
    public static void changePassword(String userId) throws SQLException{
        String newPassword,rePassword;
            System.out.println("Enter new Password:");
            newPassword=kb.nextLine();
            System.out.println("Retype the password:");
            rePassword=kb.nextLine();
            while(!newPassword.equals(rePassword)){
                System.out.println("Password Does not match!");
            System.out.println("Enter new Password:");
            newPassword=kb.nextLine();
            System.out.println("Retype the password:");
            rePassword=kb.nextLine();
            }
            boolean r=UserDao.changePassword(userId,newPassword);
            if(r)
                System.out.println("Password changed successfully.");
            else
                System.out.println("Cannot change the paasword. Please try again");
    }
}
