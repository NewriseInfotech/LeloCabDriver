package com.lelocabdriver.newride;

import android.text.TextUtils;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lelocabdriver.R;
import com.lelocabdriver.baseclasses.BaseActivity;
import com.lelocabdriver.earnings.GetAllCompleteRideHistoryResponseModel;
import com.lelocabdriver.location.GPSTracker;
import com.lelocabdriver.login.LoginRequestModel;
import com.lelocabdriver.login.LoginResponseModel;
import com.lelocabdriver.notification.NotificationResponseModel;
import com.lelocabdriver.notification.RideInfoModel;
import com.lelocabdriver.utils.Prefs;
import com.lelocabdriver.webservice.ApiCallbacks;
import com.lelocabdriver.webservice.ApiClient;
import com.lelocabdriver.webservice.ApiInterface;
import com.lelocabdriver.webservice.WebApiConstants;
import com.lelocabdriver.webservice.WebServiceCaller;

import java.util.Date;

import retrofit2.Call;

/**
 * Created by ashish on 11-05-2017.
 */

public class NewRideFragmentImpl implements INewRideFragmentPresenter, ApiCallbacks {
    private BaseActivity mActivity;
    private INewRideFragmentView view;
    private LoginResponseModel loginResponseModel;
    private RideInfoModel rideInfoModel;
    private GPSTracker gpsTracker;
    private LatLng latLng;

    public NewRideFragmentImpl(BaseActivity mActivity, INewRideFragmentView view) {
        this.mActivity = mActivity;
        this.view = view;
        loginResponseModel = Prefs.getObjectFromPref(mActivity, LoginResponseModel.class.getName());
        gpsTracker = new GPSTracker(mActivity);
    }

    @Override
    public void AcceptRide(RideInfoModel rideInfoModel) {
        AcceptRideRequestModel acceptRideRequestModel = new AcceptRideRequestModel();
        acceptRideRequestModel.setDriverId(loginResponseModel.getId());
        acceptRideRequestModel.setRideId(rideInfoModel.getRideId());
        acceptRideRequestModel.setStartAt(String.valueOf(new Date(System.currentTimeMillis())));
        acceptRideRequestModel.setUserId(rideInfoModel.getUserId());
        acceptRideRequestModel.setVehicleId(loginResponseModel.getVehicleDetail().getVehicleId());
        acceptRideRequestModel.setVehicleType(loginResponseModel.getVehicleDetail().getVehicleTypeId());

        mActivity.showProgress();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> call = apiService.AcceptRide(acceptRideRequestModel);
        WebServiceCaller.CallWebApi(call, WebApiConstants.ACCEPT_RIDE, mActivity, this);
    }

    @Override
    public void StartRide(RideInfoModel rideInfoModel, String otp) {
        if (isValidate(otp)) {
            AcceptRideRequestModel acceptRideRequestModel = new AcceptRideRequestModel();
            acceptRideRequestModel.setDriverId(loginResponseModel.getId());
            acceptRideRequestModel.setRideId(rideInfoModel.getRideId());
            acceptRideRequestModel.setStartAt(String.valueOf(new Date(System.currentTimeMillis())));
            acceptRideRequestModel.setUserId(rideInfoModel.getUserId());
            acceptRideRequestModel.setVehicleId(loginResponseModel.getVehicleDetail().getVehicleId());
            acceptRideRequestModel.setVehicleType(loginResponseModel.getVehicleDetail().getVehicleType());
            acceptRideRequestModel.setOTP(otp);

            mActivity.showProgress();
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<JsonObject> call = apiService.StartRide(acceptRideRequestModel);
            WebServiceCaller.CallWebApi(call, WebApiConstants.START_RIDE, mActivity, this);
        }
    }

    private boolean isValidate(String otp) {
        if (TextUtils.isEmpty(otp)) {
            mActivity.showToast(mActivity.getString(R.string.otp_validation));
            return false;
        }
        return true;
    }

    @Override
    public void CompleteRide(RideInfoModel rideInfoModel) {
        this.rideInfoModel = rideInfoModel;
        latLng = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
        CompleteRideRequestModel completeRideRequestModel = new CompleteRideRequestModel();
        completeRideRequestModel.setVehicleType(loginResponseModel.getVehicleDetail().getVehicleTypeId());
        completeRideRequestModel.setUserId(Long.parseLong(rideInfoModel.getUserId()));
        completeRideRequestModel.setDriverId(loginResponseModel.getId());
        completeRideRequestModel.setDestinationAddress(gpsTracker.getLocationAddress(latLng.latitude, latLng.longitude));
        completeRideRequestModel.setDestinationLatitude(String.valueOf(latLng.latitude));
        completeRideRequestModel.setDestinationLongitude(String.valueOf(latLng.longitude));
        completeRideRequestModel.setRideId(Long.parseLong(rideInfoModel.getRideId()));
        completeRideRequestModel.setVehicleId(loginResponseModel.getVehicleDetail().getVehicleId());
        completeRideRequestModel.setIsInterState(getInterStateCode(rideInfoModel));

        mActivity.showProgress();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> call = apiService.CompleteRide(completeRideRequestModel);
        WebServiceCaller.CallWebApi(call, WebApiConstants.COMPLETE_RIDE, mActivity, this);
    }

    private int getInterStateCode(RideInfoModel rideInfoModel) {
        String addressFrom = gpsTracker.getLocationAddress(Double.parseDouble(rideInfoModel.getSourceLatitude()), Double.parseDouble(rideInfoModel.getSourceLongitude()));
        String addressTo = gpsTracker.getLocationAddress(latLng.latitude, latLng.longitude);
        if (!addressFrom.equals(addressTo))
            if (addressFrom.equals(mActivity.getString(R.string.haryana)) || addressTo.equals(mActivity.getString(R.string.haryana))) {
                return 1;
            } else if (addressFrom.equals(gpsTracker.getString(R.string.up)) || addressTo.equals(gpsTracker.getString(R.string.up))) {
                return 2;
            } else
                return 0;
        else
            return 0;
    }


    @Override
    public void CancelRide(RideInfoModel rideInfoModel) {
        CancelRideRequestModel cancelRideRequestModel = new CancelRideRequestModel();
        cancelRideRequestModel.setRideId(rideInfoModel.getRideId());
        cancelRideRequestModel.setUserId(rideInfoModel.getUserId());
        cancelRideRequestModel.setCancelReason("0");
        cancelRideRequestModel.setUser(false);
        cancelRideRequestModel.setDriverId(loginResponseModel.getId());
        cancelRideRequestModel.setCancelReasonOther("");

        mActivity.showProgress();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> call = apiService.CancelRide(cancelRideRequestModel);
        WebServiceCaller.CallWebApi(call, WebApiConstants.CANCEL_RIDE, mActivity, this);
    }

    @Override
    public void GetCompleteRideDetail(RideInfoModel rideInfoModel) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Id", rideInfoModel.getRideId());
        jsonObject.addProperty("UserId", rideInfoModel.getUserId());
        mActivity.showProgress();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> call = apiService.getCompleteRideDetail(jsonObject);
        WebServiceCaller.CallWebApi(call, WebApiConstants.GET_COMPLETE_RIDE_DETAIL, mActivity, this);
    }

    @Override
    public void onSuccess(JsonObject jsonObject, Enum anEnum) {
        mActivity.hideProgress();
        if (anEnum == WebApiConstants.ACCEPT_RIDE) {
            view.onAcceptRide();
        }
        if (anEnum == WebApiConstants.START_RIDE) {
            view.onStartRide();
        }
        if (anEnum == WebApiConstants.COMPLETE_RIDE) {
            Prefs.putObjectIntoPref(mActivity, null, NotificationResponseModel.class.getName());
            GetCompleteRideDetail(rideInfoModel);
        }
        if (anEnum == WebApiConstants.CANCEL_RIDE) {
            view.onCancelRide();
        }
        if (anEnum == WebApiConstants.GET_COMPLETE_RIDE_DETAIL) {
            GetAllCompleteRideHistoryResponseModel getAllCompleteRideHistoryResponseModel =
                    new Gson().fromJson(WebServiceCaller.getResponsePacket(jsonObject), GetAllCompleteRideHistoryResponseModel.class);
            view.onCompleteRide(getAllCompleteRideHistoryResponseModel);
        }
    }

    @Override
    public void onError(JsonObject jsonObject, Enum anEnum) {
        mActivity.hideProgress();
        mActivity.showToast(WebServiceCaller.getResponseMessage(jsonObject));
    }
}
