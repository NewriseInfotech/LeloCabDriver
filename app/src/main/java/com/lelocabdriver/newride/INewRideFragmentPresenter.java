package com.lelocabdriver.newride;

import com.lelocabdriver.notification.RideInfoModel;

/**
 * Created by ashish on 11-05-2017.
 */
public interface INewRideFragmentPresenter {


    void AcceptRide(RideInfoModel rideInfoModel);

    void StartRide(RideInfoModel rideInfoModel, String otp);

    void CompleteRide(RideInfoModel rideInfoModel);

    void CancelRide(RideInfoModel rideInfoModel);

    void GetCompleteRideDetail(RideInfoModel rideInfoModel);
}
