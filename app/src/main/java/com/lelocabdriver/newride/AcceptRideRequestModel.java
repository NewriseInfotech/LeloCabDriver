package com.lelocabdriver.newride;

import java.io.Serializable;

/**
 * Created by ashish on 11-05-2017.
 */

public class AcceptRideRequestModel implements Serializable {
    private String RideId;
    private String VehicleId;
    private String StartAt;
    private String VehicleType;
    private String UserId;
    private String DriverId;
    private String OTP;

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public String getRideId() {
        return RideId;
    }

    public void setRideId(String rideId) {
        RideId = rideId;
    }

    public String getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(String vehicleId) {
        VehicleId = vehicleId;
    }

    public String getStartAt() {
        return StartAt;
    }

    public void setStartAt(String startAt) {
        StartAt = startAt;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getDriverId() {
        return DriverId;
    }

    public void setDriverId(String driverId) {
        DriverId = driverId;
    }
}
