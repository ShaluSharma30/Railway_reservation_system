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
import railwaySystem.pojo.Station;

/**
 *
 * @author shalu
 */
public class StationDao {
    public static boolean addStation(Station s) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into Station values(?,?)");
        ps.setString(1, s.getStationCode());
        ps.setString(2, s.getStationName());
        int result=ps.executeUpdate();
        return result==1;
    }
    public static boolean removeStation(String code) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("delete from station where station_code=?");
        ps.setString(1, code);
        int result=ps.executeUpdate();
        return result==1;
    }
    
    public static List<Station> getAllStations() throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from Station");
        List<Station> stations=new ArrayList<>();
        while(rs.next()){
            stations.add(new Station(rs.getString(1),rs.getString(2)));
        }
        return stations;
    }
    
}
