/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwaySystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import railwaySystem.util.DBConnection;
import railwaySystem.pojo.Passenger;

/**
 *
 * @author shalu
 */
public class PassengerDao {
    public static boolean addPassenger(Passenger p) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into Passenger values(?,?,?,?,?,?,?,)");
        ps.setString(1, p.getPnr());
        ps.setString(2, p.getUserId());
        ps.setString(3, p.getFname());
        ps.setString(4, p.getLname());
        ps.setString(5, p.getTicketId());
        ps.setString(7, p.getGender()+"");
        ps.setInt(6, p.getAge());
        int result=ps.executeUpdate();
        return result==1;
    }
    public static String getNextPnr() throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select max(pnr) from Passenger");
        if(!rs.next())
            return "p101";
        String pnr=rs.getString(1);
        int n=Integer.parseInt(pnr.substring(1));
        return "p"+(n+1);
    }
}
