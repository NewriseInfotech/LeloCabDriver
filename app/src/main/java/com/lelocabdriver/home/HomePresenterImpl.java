package com.lelocabdriver.home;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lelocabdriver.MainActivity;
import com.lelocabdriver.login.LoginResponseModel;
import com.lelocabdriver.notification.RideInfoModel;
import com.lelocabdriver.utils.Prefs;
import com.lelocabdriver.webservice.ApiCallbacks;
import com.lelocabdriver.webservice.ApiClient;
import com.lelocabdriver.webservice.ApiInterface;
import com.lelocabdriver.webservice.WebApiConstants;
import com.lelocabdriver.webservice.WebServiceCaller;

import retrofit2.Call;

/**
 * Created by ashish on 01-06-2017.
 */

public class HomePresenterImpl implements IHomePresenter, ApiCallbacks {

    private MainActivity mActivity;
    private IHomeView view;
    private LoginResponseModel loginResponseModel;

    public HomePresenterImpl(MainActivity mActivity, IHomeView view) {
        this.mActivity = mActivity;
        this.view = view;
        loginResponseModel = Prefs.getObjectFromPref(mActivity, LoginResponseModel.class.getName());
    }

    @Override
    public void getRunningRide() {
        mActivity.showProgress();
        GetRunningRideRequestModel getRunningRideRequestModel = new GetRunningRideRequestModel();
        getRunningRideRequestModel.setUser(false);
        getRunningRideRequestModel.setUserId("");
        getRunningRideRequestModel.setDriverId(loginResponseModel.getId());
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> call = apiService.getRunningRide(getRunningRideRequestModel);
        WebServiceCaller.CallWebApi(call, WebApiConstants.GET_RUNNING_RIDE, mActivity, this);
    }

    @Override
    public void onSuccess(JsonObject jsonObject, Enum anEnum) {
        mActivity.hideProgress();
        RideInfoModel rideInfoModel = new Gson().fromJson(WebServiceCaller.getResponsePacket(jsonObject), RideInfoModel.class);
        view.onGetRunningRide(rideInfoModel);
    }

    @Override
    public void onError(JsonObject jsonObject, Enum anEnum) {
        mActivity.hideProgress();
    }
}
