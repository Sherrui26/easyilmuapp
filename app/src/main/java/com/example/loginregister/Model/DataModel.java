package com.example.loginregister.Model;

import java.math.BigInteger;

public class DataModel {

    private int MobNo;
    private String RollNo,Name,EmailId;

    public int getMobNo() {
        return MobNo;
    }

    public void setMobNo(int mobNo) {
        MobNo = mobNo;
    }

    public String getRollNo() {
        return RollNo;
    }

    public void setRollNo(String rollNo) {
        RollNo = rollNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }
}
