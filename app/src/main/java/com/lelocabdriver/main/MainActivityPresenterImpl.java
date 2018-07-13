package com.lelocabdriver.main;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lelocabdriver.baseclasses.BaseActivity;
import com.lelocabdriver.location.GPSTracker;
import com.lelocabdriver.login.LoginResponseModel;
import com.lelocabdriver.notification.NotificationResponseModel;
import com.lelocabdriver.notification.RideInfoModel;
import com.lelocabdriver.utils.Prefs;
import com.lelocabdriver.webservice.ApiCallbacks;
import com.lelocabdriver.webservice.ApiClient;
import com.lelocabdriver.webservice.ApiInterface;
import com.lelocabdriver.webservice.WebApiConstants;
import com.lelocabdriver.webservice.WebServiceCaller;

import retrofit2.Call;

/**
 * Created by ashish on 13-05-2017.
 */

public class MainActivityPresenterImpl implements IMainActivityPresenter, ApiCallbacks {

    private BaseActivity mActivity;
    private IMainActivityView view;
    private LoginResponseModel loginResponseModel;
    private NotificationResponseModel notificationResponseModel;

    public MainActivityPresenterImpl(BaseActivity mActivity, IMainActivityView view) {
        this.mActivity = mActivity;
        this.view = view;
        loginResponseModel = Prefs.getObjectFromPref(mActivity, LoginResponseModel.class.getName());
        notificationResponseModel = Prefs.getObjectFromPref(mActivity, NotificationResponseModel.class.getName());
    }

    @Override
    public void Logout() {
        GPSTracker gpsTracker = new GPSTracker(mActivity);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("UserId", loginResponseModel.getId());
        jsonObject.addProperty("Latitude", gpsTracker.getLatitude());
        jsonObject.addProperty("Longitude", gpsTracker.getLongitude());

        mActivity.showProgress();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> call = apiService.Logout(jsonObject);
        WebServiceCaller.CallWebApi(call, WebApiConstants.LOGOUT, mActivity, this);
    }

    @Override
    public void updateDriverLocation() {
        GPSTracker gpsTracker = new GPSTracker(mActivity);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("UserId", loginResponseModel.getId());
        jsonObject.addProperty("Latitude", gpsTracker.getLatitude());
        jsonObject.addProperty("Longitude", gpsTracker.getLongitude());
        //TODO for testing purpose
//        gpsTracker.getLocationAddress(gpsTracker.getLatitude(), gpsTracker.getLongitude());
        if (notificationResponseModel == null) {
            jsonObject.addProperty("RideId", "0");
        } else {
            jsonObject.addProperty("RideId", notificationResponseModel.getRideId());
        }
        System.out.print("location " + gpsTracker.getLatitude() + " " + gpsTracker.getLongitude());
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> call = apiService.updateDriverLocation(jsonObject);
        WebServiceCaller.CallWebApi(call, WebApiConstants.UPDATE_DRIVER_LOCATION, mActivity, this);
    }

    @Override
    public void GetUserRideInfo(NotificationResponseModel notificationResponseModel) {
        mActivity.showProgress();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Id", notificationResponseModel.getRideId());
        jsonObject.addProperty("UserId", notificationResponseModel.getUserId());
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> call = apiService.getRideInfo(jsonObject);
        WebServiceCaller.CallWebApi(call, WebApiConstants.GET_USER_RIDE_INFO, mActivity, this);
    }

    @Override
    public void onSuccess(JsonObject jsonObject, Enum anEnum) {
        mActivity.hideProgress();
        if (anEnum == WebApiConstants.LOGOUT) {
            view.onLogout();
        }
        if (anEnum == WebApiConstants.UPDATE_DRIVER_LOCATION) {
            System.out.print("location updated");
        }
        if (anEnum == WebApiConstants.GET_USER_RIDE_INFO)
            view.onGetRideInfo(new Gson().fromJson(WebServiceCaller.getResponsePacket(jsonObject), RideInfoModel.class));

    }

    @Override
    public void onError(JsonObject jsonObject, Enum anEnum) {
        mActivity.hideProgress();
        mActivity.showToast(WebServiceCaller.getResponseMessage(jsonObject));
    }
}
