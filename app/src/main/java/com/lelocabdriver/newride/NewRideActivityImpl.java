package com.lelocabdriver.newride;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lelocabdriver.baseclasses.BaseActivity;
import com.lelocabdriver.notification.NotificationResponseModel;
import com.lelocabdriver.notification.RideInfoModel;
import com.lelocabdriver.webservice.ApiCallbacks;
import com.lelocabdriver.webservice.ApiClient;
import com.lelocabdriver.webservice.ApiInterface;
import com.lelocabdriver.webservice.WebApiConstants;
import com.lelocabdriver.webservice.WebServiceCaller;

import retrofit2.Call;

/**
 * Created by ashish on 08-05-2017.
 */

public class NewRideActivityImpl implements NewRideActivityPresenter, ApiCallbacks {

    private BaseActivity mActivity;
    private INewRideActivityView view;
    private int RideId;

    public NewRideActivityImpl(BaseActivity mActivity, INewRideActivityView view) {
        this.mActivity = mActivity;
        this.view = view;
    }

    @Override
    public void GetUserRideInfo(NotificationResponseModel notificationResponseModel) {
        mActivity.showProgress();
        RideId = notificationResponseModel.getRideId();
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
        if (anEnum == WebApiConstants.GET_USER_RIDE_INFO)
            view.onGetRideInfo(new Gson().fromJson(WebServiceCaller.getResponsePacket(jsonObject), RideInfoModel.class));
    }

    @Override
    public void onError(JsonObject jsonObject, Enum anEnum) {
        mActivity.hideProgress();
        mActivity.showToast(WebServiceCaller.getResponseMessage(jsonObject));
    }
}
