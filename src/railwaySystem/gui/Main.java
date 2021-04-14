/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwaySystem.gui;


import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import railwaySystem.dao.UserDao;

/**
 *
 * @author shalu
 */
public class Main {
    private static final Scanner kb=new Scanner(System.in);
    public static void main(String[] args) {
       userMenu();
    }
    public static void userMenu(){
        UserModule user=new UserModule();
        x:while(true){
        try {
            System.out.println("------------------------------------------------------");
            System.out.println("1. Login\n2. Register\n3. Change Password by email\n4. Change Password by phone no.\n5. Exit");
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

            switch (n) {
                case 1:
                    user.login();
                    break;
                case 2:
                    user.register();
                    break;
                case 3:
                    changePasswordByEmail();
                    break;
                case 4:
                    changePasswordByPhoneNo();
                    break;
                case 5:
                    break x;
                default:
                    System.out.println("Please enter a valid option number");
            }
        } catch (SQLException ex) {
                System.out.println("!!!!!!Exception!!!!!!");
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
    
        System.out.println("************************THANK YOU***********************************");
    }
    
    static void changePasswordByEmail() throws SQLException{
        System.out.println("Enter user Id:");
        String userId=kb.nextLine();
        while(!UserDao.isValidId(userId)){
            System.out.println("Invalid Id!! Please Enter correct id.");
            userId=kb.nextLine();
        }
        System.out.println("Enter email:");
        String email=kb.nextLine();
        if(!UserDao.isValidEmail(userId,email)){
            System.out.println("Invalid email!! ");
        }
        else{
            UserModule.changePassword(userId);
        }
    }
    
    
    static void changePasswordByPhoneNo() throws SQLException{
        System.out.println("Enter user Id:");
        String userId=kb.nextLine();
        while(!UserDao.isValidId(userId)){
            System.out.println("Invalid Id!! Please Enter correct id.");
            userId=kb.nextLine();
        }
        System.out.println("Enter PhoneNo:");
        String phoneNo=kb.nextLine();
        if(!UserDao.isValidPhoneNo(userId,phoneNo)){
            System.out.println("Invalid Phone no!! ");
        }
        else{
            UserModule.changePassword(userId);
        }
    }
    
 }

 