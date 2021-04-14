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
public class User {

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", fname=" + fname + ", lname=" + lname + ", age=" + age + ", phoneNo=" + phoneNo + ", email=" + email + ", gender=" + gender + ", password="+password+"'}'";
    }

    public User(String userId, String fname, String lname, byte age, String phoneNo, String email, char gender,String password) {
        this.userId = userId;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.phoneNo = phoneNo;
        this.email = email;
        this.gender = gender;
        this.password=password;
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

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
    private String userId,fname,lname;
    private byte age;
    private String phoneNo;
    private String email,password;
    private char gender;
}
