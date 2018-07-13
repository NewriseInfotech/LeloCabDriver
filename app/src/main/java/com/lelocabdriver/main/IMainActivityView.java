package com.lelocabdriver.main;

import com.lelocabdriver.notification.RideInfoModel;

/**
 * Created by ashish on 13-05-2017.
 */

public interface IMainActivityView {
    void onLogout();

    void onGetRideInfo(RideInfoModel rideInfoModel);
}
