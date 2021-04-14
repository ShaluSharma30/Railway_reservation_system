/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwaySystem.gui;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import railwaySystem.dao.PassengerDao;
import railwaySystem.dao.TicketDao;
import railwaySystem.dao.TrainDao;
import railwaySystem.gui.UserProfile;
import railwaySystem.pojo.Passenger;
import railwaySystem.pojo.Ticket;

/**
 *
 * @author shalu
 */
public class TicketMethods {
    private static final Scanner kb=new Scanner(System.in);
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

    public static void displayTicket() throws SQLException {
        System.out.println("Enter Ticket Id:");
        String tid = kb.nextLine();
        while (!TicketDao.isValidTicketNo(tid)) {
            System.out.println("Invalid Id!!!");
            tid = kb.nextLine();
        }
        Ticket ticket = TicketDao.getTicketById(tid);
        displayTicket(ticket);
    }
    
    public static void displayAllTickets() throws SQLException{
        List<Ticket> tickets=TicketDao.getAllTickets();
        for(Ticket t:tickets)
            displayTicket(t);
    }
    public static void displayTicket(Ticket ticket){
        System.out.println("--------------------------Ticket Details-----------------------");
        System.out.println("Ticket Id:\t" + ticket.getTicketNo());
        System.out.println("Status:\t" + ticket.getTicketStatus());
        System.out.println("No of Passengers:\t" + ticket.getNoOfPassengers());
        System.out.println("Train No:\t" + ticket.getTrainNo());
        System.out.println("User Id:\t" + ticket.getUserId());
        System.out.println("Class Type:\t" + ticket.getClassType());
        System.out.println("Booking Date:\t" + ticket.getBookingDate());
        System.out.println("Journey Date:\t" + ticket.getJourneyDate());
        System.out.println("-----------------------------------------------------------------");
    }

}
