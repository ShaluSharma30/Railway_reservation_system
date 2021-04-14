/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwaySystem.pojo;

/**
 *
 * @author shalu
 */
public class Passenger {

    public Passenger(String pnr, String userId, String fname, String lname, String ticketId, byte age, char gender) {
        this.pnr = pnr;
        this.userId = userId;
        this.fname = fname;
        this.lname = lname;
        this.ticketId = ticketId;
        this.age = age;
        this.gender = gender;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Passenger{" + "pnr=" + pnr + ", userId=" + userId + ", fname=" + fname + ", lname=" + lname + ", ticketId=" + ticketId + ", age=" + age + ", gender=" + gender + '}';
    }
    private String pnr,userId,fname,lname,ticketId;
    private byte age;
    private char gender;
}
