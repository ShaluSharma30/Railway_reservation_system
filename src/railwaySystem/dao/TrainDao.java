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
import java.util.ArrayList;
import java.util.List;
import railwaySystem.util.DBConnection;
import railwaySystem.pojo.Train;

/**
 *
 * @author shalu
 */
public class TrainDao {
    public static boolean addTrain(Train t) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into Train values(?,?,?,?,?,?)");
        ps.setString(1, t.getTrainNo());
        ps.setString(2, t.getTrainName());
        ps.setTime(3, t.getArrivalTime());
        ps.setString(4, t.getTrainType());
        ps.setTime(5, t.getDepartureTime());
        ps.setInt(6, t.getSeats());
        int result=ps.executeUpdate();
        return result==1;
    }
    
    public static boolean removeTrain(String trainNo) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("delete from Train where train_no=?");
        ps.setString(1, trainNo);
        int ans=ps.executeUpdate();
        return ans==1;
    }
    
    public static Train searchTrainByTrainNo(String trainNo) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from Train where trainNo=?");
        ps.setString(1, trainNo);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            return new Train(rs.getString(1),rs.getString(2),rs.getTime(3),rs.getString(4),rs.getTime(5),rs.getInt(6));
        }
        return null;
    }
    public static List<Train> searchTrainByStationCode(String src,String dest) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM train t,starts_at s1,stops_at s2  "
            		+ "WHERE t.train_no=s1.train_no and t.train_no=s2.train_no and "
            		+ "s1.station_code = ? "
            		+ "and s2.station_code = ?");
        ps.setString(1, src);
        ps.setString(2, dest);
        ResultSet rs=ps.executeQuery();
        List<Train> a=new ArrayList<>();
        Train t;
        while(rs.next()){
            t=new Train(rs.getString(1),rs.getString(2),rs.getTime(3),rs.getString(4),rs.getTime(5),rs.getInt(6));
            a.add(t);
        }
        return a;
    }
    
    public static Train getTrainByNo(String trainNo) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from Train where train_no=?");
        ps.setString(1, trainNo);
        ResultSet rs=ps.executeQuery();
        rs.next();
        Train t=new Train(rs.getString(1),rs.getString(2),rs.getTime(3),rs.getString(4),rs.getTime(5),rs.getInt(6));
        return t;
    }
    
    public static List<Train> getAllTrains() throws SQLException{
         Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from Train");
        List<Train> a=new ArrayList<>();
        Train t;
        while(rs.next()){
            t=new Train(rs.getString(1),rs.getString(2),rs.getTime(3),rs.getString(4),rs.getTime(5),rs.getInt(6));
            a.add(t);
        }
        return a;
    }
    public static boolean isValidId(String id) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select 1 from Train where train_no=?");
        ps.setString(1, id);
        ResultSet rs=ps.executeQuery();
        return rs.next();
    }
    
    public static boolean updateTrain(Train train) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update train set train_name=?,arrival_time=?,train_type=?,departure_time=?,availability_of_seats=? where train_no=?");
        ps.setString(1, train.getTrainName());
        ps.setTime(2, train.getArrivalTime());
        ps.setString(3, train.getTrainType());
        ps.setTime(4, train.getDepartureTime());
        ps.setInt(5, train.getSeats());
        ps.setString(6, train.getTrainNo());
        int result=ps.executeUpdate();
        return result==1;
    }
 
}
