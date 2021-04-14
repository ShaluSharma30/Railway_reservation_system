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
import railwaySystem.pojo.User;

/**
 *
 * @author shalu
 */
public class UserDao {
    public static boolean isValidId(String id) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select 1 from user_details where user_id=?");
        ps.setString(1,id);
        ResultSet rs=ps.executeQuery();
        return rs.next();
    }
    
    public static boolean isValidEmail(String id,String email) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select email from user_details where user_id=?");
        ps.setString(1,id);
        ResultSet rs=ps.executeQuery();
        return rs.getString("email").equals(email);
    }
    
    public static boolean isValidPhoneNo(String id,String phoneNo) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select phoneNo from user_details where user_id=?");
        ps.setString(1,id);
        ResultSet rs=ps.executeQuery();
        return rs.getString("phone_no").equals(phoneNo);
    }
    
    public static boolean isValidUser(String userId,String password) throws SQLException{
         Connection con = DBConnection.getConnection();
            
            PreparedStatement pst = con.prepareStatement("SELECT 1 FROM user_details WHERE user_id = ? and password = ?");
            
            pst.setString(1, userId);
            pst.setString(2, password);
            
            ResultSet rs = pst.executeQuery();
            return rs.next();
    }
    public static boolean registerUser(User obj) throws SQLException{
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into user_details values(?,?,?,?,?,?,?,?)");
            pst.setString(1,obj.getUserId());
            pst.setString(2,obj.getFname());
            pst.setString(3,obj.getLname());
            pst.setInt(4,obj.getAge());
            pst.setString(5,obj.getPhoneNo());
            pst.setString(6,obj.getEmail());
            pst.setString(7,obj.getGender()+"");
            pst.setString(8,obj.getPassword());
            int count = pst.executeUpdate();
            return count==1;
    }
    public static boolean changePassword(String userId,String newPassword) throws SQLException{
        Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement("UPDATE user_details SET password = ?"
                        + " WHERE user_id = ?");
                pst.setString(1, newPassword);
                pst.setString(2, userId);
                int count = pst.executeUpdate();
                return count==1;
    }    
    
    public static String getNextId() throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select max(user_id) from user_details");
        if(!rs.next())
            return "u101";
        String uid=rs.getString(1);
        int n=Integer.parseInt(uid.substring(1));
        return "u"+(n+1);
    }
    public static String getNameById(String id) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select fname from user_details where user_id=?");
        ps.setString(1, id);
        ResultSet rs=ps.executeQuery();
        rs.next();
        return rs.getString(1);
    }
}
