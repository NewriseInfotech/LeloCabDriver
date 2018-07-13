package com.lelocabdriver.main;

import com.lelocabdriver.notification.NotificationResponseModel;

/**
 * Created by ashish on 13-05-2017.
 */

public interface IMainActivityPresenter {
    void Logout();

    void updateDriverLocation();

    void GetUserRideInfo(NotificationResponseModel notificationResponseModel);
}
