/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwaySystem.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import railwaySystem.util.DBConnection;
import railwaySystem.pojo.Passenger;
import railwaySystem.pojo.Ticket;

/**
 *
 * @author shalu
 */
public class TicketDao {
    public static boolean bookTicketFor(Ticket t,Passenger[] p) throws SQLException{
        Connection conn=DBConnection.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement ps;
        ps=conn.prepareStatement("insert into Ticket values(?,?,?,?,?,?,?,?)");
        ps.setString(1, t.getTicketNo());
        ps.setString(2, t.getTicketStatus()+"");
        ps.setInt(3, t.getNoOfPassengers());
        ps.setString(4, t.getTrainNo());
        ps.setString(5, t.getUserId());
        ps.setString(6, t.getClassType());
        ps.setDate(7, t.getBookingDate());
        ps.setDate(8, t.getJourneyDate());
        ps.addBatch();
        for (Passenger p1 : p) {
            ps=conn.prepareStatement("insert into Passenger values(?,?,?,?,?,?,?)");
            ps.setString(1, p1.getPnr());
            ps.setString(2, p1.getUserId());
            ps.setString(3, p1.getFname());
            ps.setString(4, p1.getLname());
            ps.setString(5, p1.getTicketId());
            ps.setInt(6, p1.getAge());
            ps.setString(7, p1.getGender() + "");
            ps.addBatch();
        }
        int result[]=ps.executeBatch();
        conn.commit();
        conn.setAutoCommit(true);
        return true;
        
    }
    public static String getNextId() throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select max(ticket_id) from Ticket");
        if(!rs.next())
            return "t101";
        String tid=rs.getString(1);
        int n=Integer.parseInt(tid.substring(1));
        return "t"+(n+1);
    }
    public static boolean isValidTicketNo(String ticketNo) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select 1 from Ticket where Ticket_id=?");
        ps.setString(1, ticketNo);
        ResultSet rs=ps.executeQuery();
        return rs.next();
    }
    public static boolean cancelTicket(String ticketNo) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update Ticket set ticket_status='X' where Ticket_id=?");
        ps.setString(1, ticketNo);
        return ps.executeUpdate()==1;
    }
    public static Ticket getTicketById(String tid) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from Ticket where ticket_id=?");
        ps.setString(1, tid);
        ResultSet rs=ps.executeQuery();
        rs.next();
        String id=rs.getString("ticket_id");
        char status=rs.getString("ticket_status").charAt(0);
        int n=rs.getInt("no_of_passenger");
        String trainNo=rs.getString("train_no");
        String userId=rs.getString("user_id");
        String classType=rs.getString("class");
        Date bookingDate=rs.getDate("booking_date");
        Date journeyDate=rs.getDate("journey_date");
        Ticket t=new Ticket(id,status,(byte)n,trainNo,userId,classType,bookingDate,journeyDate);
        return t;
    }
    public static List<Ticket> getAllTickets() throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement ps=conn.createStatement();
        ResultSet rs=ps.executeQuery("select * from Ticket");
        List<Ticket> tickets=new ArrayList<>();
        while(rs.next()){
        String id=rs.getString("ticket_id");
        char status=rs.getString("ticket_status").charAt(0);
        int n=rs.getInt("no_of_passenger");
        String trainNo=rs.getString("train_no");
        String userId=rs.getString("user_id");
        String classType=rs.getString("class");
        Date bookingDate=rs.getDate("booking_date");
        Date journeyDate=rs.getDate("journey_date");
        Ticket t=new Ticket(id,status,(byte)n,trainNo,classType,userId,bookingDate,journeyDate);
        tickets.add(t);
        }
        return tickets;
    }
}
