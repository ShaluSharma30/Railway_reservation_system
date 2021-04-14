/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwaySystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author 91789
 */
public class DBConnection {
    private static Connection conn;
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/railway_system";
            conn=DriverManager.getConnection(url, "root","root");
            System.out.println("connection done successfully!");
            //JOptionPane.showMessageDialog(null, "connection done successfully!");
            
        }
         catch(ClassNotFoundException cnef){
             System.out.println("Cannot load the driver: "+cnef);
             cnef.printStackTrace();
           
       }
       catch(SQLException sqle){
           System.out.println("Problem in DB: "+sqle);
            sqle.printStackTrace();
           
       }
    }
        public static Connection getConnection(){
            return conn;
            
        }
        public static void closeConnection(){
            try{
                if(conn!=null){
                conn.close();
                JOptionPane.showMessageDialog(null, "connection closed successfully!");
                }
            }
            catch(SQLException sqle){
           JOptionPane.showMessageDialog(null,"Problem in closing connection","Error!",JOptionPane.ERROR_MESSAGE);
            sqle.printStackTrace();
           
       }  }
    
}
    
    

