package com.lelocabdriver.newride;

import com.lelocabdriver.earnings.GetAllCompleteRideHistoryResponseModel;

/**
 * Created by ashish on 11-05-2017.
 */

public interface INewRideFragmentView {
    void onAcceptRide();

    void onStartRide();

    void onCompleteRide(GetAllCompleteRideHistoryResponseModel getAllCompleteRideHistoryResponseModel);

    void onCancelRide();
}
