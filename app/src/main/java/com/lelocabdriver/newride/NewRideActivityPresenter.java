package com.lelocabdriver.newride;

import com.lelocabdriver.notification.NotificationResponseModel;

import org.json.JSONObject;

/**
 * Created by ashish on 08-05-2017.
 */
public interface NewRideActivityPresenter {
    void GetUserRideInfo(NotificationResponseModel jsonObject);
}
