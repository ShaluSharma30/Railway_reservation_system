/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwaySystem.gui;

/**
 *
 * @author shalu
 */
public class UserProfile {

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        UserProfile.userId = userId;
    }

    public static String getFname() {
        return fname;
    }

    public static void setFname(String fname) {
        UserProfile.fname = fname;
    }
    private static String userId,fname;
}
