package com.lelocabdriver.notification;

import java.io.Serializable;

/**
 * Created by ashish on 08-05-2017.
 */

public class RideInfoModel implements Serializable {
    private String Name;
    private String SourceLatitude;
    private String DestinationLongitude;
    private String RideId;
    private String Email;
    private String DestinationAddress;
    private String PhoneNumber;
    private String DestinationLatitude;
    private String SourceLongitude;
    private String UserId;
    private String SourceAddress;
    private int RideStatus;

    /*{"RideId":28,"UserId":5,"Name":"ashish","Email":"ashrma1993@live.com","PhoneNumber":"9571919208","SourceAddress":"Sodala, Jaipur, Rajasthan, India","DestinationAddress":"Jaipur, Rajasthan, India","SourceLatitude":26.9064744,"SourceLongitude":75.7728014,"DestinationLatitude":26.9124336,"DestinationLongitude":75.7872709}}*/
    public String getSourceLatitude() {
        return SourceLatitude;
    }

    public void setSourceLatitude(String sourceLatitude) {
        SourceLatitude = sourceLatitude;
    }

    public int getRideStatus() {
        return RideStatus;
    }

    public void setRideStatus(int rideStatus) {
        RideStatus = rideStatus;
    }

    public String getDestinationLongitude() {
        return DestinationLongitude;
    }

    public void setDestinationLongitude(String destinationLongitude) {
        DestinationLongitude = destinationLongitude;
    }

    public String getRideId() {
        return RideId;
    }

    public void setRideId(String rideId) {
        RideId = rideId;
    }

    public String getDestinationLatitude() {
        return DestinationLatitude;
    }

    public void setDestinationLatitude(String destinationLatitude) {
        DestinationLatitude = destinationLatitude;
    }

    public String getSourceLongitude() {
        return SourceLongitude;
    }

    public void setSourceLongitude(String sourceLongitude) {
        SourceLongitude = sourceLongitude;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getSourceAddress() {
        return SourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        SourceAddress = sourceAddress;
    }

    public String getDestinationAddress() {
        return DestinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        DestinationAddress = destinationAddress;
    }
}