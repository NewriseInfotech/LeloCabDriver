package com.lelocabdriver.login;

import java.io.Serializable;

/**
 * Created by ashish on 22-04-2017.
 */

public class LoginRequestModel implements Serializable {
    private String PhoneNumber = "";
    private String Password = "";
    private String DeviceToken = "";
    private boolean IsRemember;
    double Latitude;
    double Longitude;

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDeviceToken() {
        return DeviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        DeviceToken = deviceToken;
    }

    public boolean isRemember() {
        return IsRemember;
    }

    public void setRemember(boolean remember) {
        IsRemember = remember;
    }
}
