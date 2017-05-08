package com.example.exoest.mytesting;

import java.util.Date;

/**
 * Created by exoest on 7/5/2017.
 */

public class UserDetails {
    private String name;
    private String gender;
    private String telNo;
    private boolean isSingle;
    private String bloodGroup;
    private Date dateOfBirth;

    public UserDetails() {
    }

    public UserDetails(String name, String gender, String telNo, boolean isSingle, String bloodGroup, Date dateOfBirth) {
        this.name = name;
        this.gender = gender;
        this.telNo = telNo;
        this.isSingle = isSingle;
        this.bloodGroup = bloodGroup;
        this.dateOfBirth = dateOfBirth;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
