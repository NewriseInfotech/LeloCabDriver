package com.lelocabdriver.newride;

import java.io.Serializable;

/**
 * Created by ashish on 12-05-2017.
 */

public class CompleteRideRequestModel implements Serializable {
    private String DestinationLongitude;
    private long RideId;
    private String DestinationAddress;
    private String VehicleId;
    private String DestinationLatitude;
    private String VehicleType;
    private long UserId;
    private String DriverId;
    private int IsInterState;

    public int getIsInterState() {
        return IsInterState;
    }

    public void setIsInterState(int isInterState) {
        IsInterState = isInterState;
    }

    public long getRideId() {
        return RideId;
    }

    public void setRideId(long rideId) {
        RideId = rideId;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public String getDestinationLongitude() {
        return DestinationLongitude;
    }

    public void setDestinationLongitude(String destinationLongitude) {
        DestinationLongitude = destinationLongitude;
    }


    public String getDestinationAddress() {
        return DestinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        DestinationAddress = destinationAddress;
    }

    public String getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(String vehicleId) {
        VehicleId = vehicleId;
    }

    public String getDestinationLatitude() {
        return DestinationLatitude;
    }

    public void setDestinationLatitude(String destinationLatitude) {
        DestinationLatitude = destinationLatitude;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }


    public String getDriverId() {
        return DriverId;
    }

    public void setDriverId(String driverId) {
        DriverId = driverId;
    }
}
