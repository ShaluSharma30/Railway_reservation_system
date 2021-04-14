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
import railwaySystem.pojo.Admin;
import railwaySystem.util.DBConnection;

/**
 *
 * @author shalu
 */
public class AdminDao {
    public static Admin validateAdmin(String id,String pass) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select name from railway_admin where id=? and password=?");
        ps.setString(1,id);
        ps.setString(2, pass);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            return new Admin(id,rs.getString(1));
        }
        return null;
    }
}
