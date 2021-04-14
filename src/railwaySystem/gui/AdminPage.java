/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwaySystem.gui;

import railwaySystem.pojo.Admin;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import railwaySystem.dao.AdminDao;
import railwaySystem.dao.TicketDao;
import railwaySystem.pojo.Ticket;

/**
 *
 * @author shalu
 */
public class AdminPage {
     static Scanner kb=new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("------------WELCOME TO ADMIN MODULE------------");
        System.out.println("Enter admin id:");
        String adminId=kb.nextLine();
        System.out.println("Enter password:");
        String password=kb.nextLine();
        Admin admin=null;
         try {
             admin = AdminDao.validateAdmin(adminId,password);
         } catch (SQLException ex) {
             System.out.println("!!!!Exception!!!!\n"+ex);
             ex.printStackTrace();
         }
        if(admin!=null){
            UserProfile.setUserId(adminId);
            UserProfile.setFname(admin.getName());
            adminMenu();
        }
        else{
            System.out.println("Invalid Credentials!!");
        }
    }
    
    private static void adminMenu() {
        System.out.println("-----------------Hello Admin"+UserProfile.getFname()+"------------------------");
        x:
        while (true) {
            System.out.println("--------------------------Welcome to Admin Login Module----------------------------");
            System.out.println("1. Get train from your source station to destination");
            System.out.println("2. Get train details by Train no.");
            System.out.println("3. Get all train details");
            System.out.println("4. Get all Tickets");
            System.out.println("5. Add Train");
            System.out.println("6. Edit Train Details");
            System.out.println("7. Remove Train");
            System.out.println("8. Add Station");
            System.out.println("9. Remove station");
            System.out.println("10. Back");
            System.out.println("11. Exit");
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
                        TrainMethods.displayTrainFromSrcToDest();
                        break;
                    case 2:
                        TrainMethods.displayTrainByNo();
                        break;
                    case 3:
                        TrainMethods.displayAllTrains();
                        break;
                    case 4:
                        TicketMethods.displayAllTickets();
                        break;
                    case 5:
                        TrainMethods.addTrain();
                        break;
                    case 6:
                        TrainMethods.editTrain();
                        break;
                    case 7:
                        TrainMethods.deleteTrain();
                        break;
                    case 8:
                        StationMethods.addStation();
                        break;
                    case 9:
                        StationMethods.removeStation();
                        break;
                    case 10:
                        return;
                    case 11:
                        System.exit(0);
                    default:
                        System.out.println("Please enter a valid option number");
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
    
    private static void getAllTickets() throws SQLException{
        List<Ticket> tickets=TicketDao.getAllTickets();
        tickets.stream().forEach(t->TicketMethods.displayTicket(t));
    }

}
