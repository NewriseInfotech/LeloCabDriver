package com.lelocabdriver.login;

import android.text.TextUtils;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lelocabdriver.R;

import com.lelocabdriver.databinding.LoginActivityBinding;
import com.lelocabdriver.location.GPSTracker;
import com.lelocabdriver.utils.Prefs;
import com.lelocabdriver.webservice.ApiCallbacks;
import com.lelocabdriver.webservice.ApiClient;
import com.lelocabdriver.webservice.ApiInterface;
import com.lelocabdriver.webservice.WebApiConstants;
import com.lelocabdriver.webservice.WebServiceCaller;

import retrofit2.Call;

/**
 * Created by ashish on 22-04-2017.
 */

public class LoginPresenterImpl implements ILoginPresenter, ApiCallbacks {
    private LoginActivityBinding binding;
    private LoginActivity mActivity;
    private ILoginView iLoginView;
    private LoginRequestModel loginRequestModel;
    private GPSTracker gps;

    public LoginPresenterImpl(LoginActivityBinding binding, LoginActivity mActivity, ILoginView iLoginView) {
        this.binding = binding;
        this.mActivity = mActivity;
        this.iLoginView = iLoginView;
        gps = new GPSTracker(mActivity);
    }


    private boolean isValidateLogin(LoginRequestModel loginRequestModel) {
        // check if GPS enabled

        if (!gps.canGetLocation()) {
            gps.showSettingsAlert();
            return false;
        }
        if (TextUtils.isEmpty(loginRequestModel.getPhoneNumber())) {
            mActivity.showError(binding.edContactNumber, mActivity.getString(R.string.contact_number_validation));
            return false;
        }
        if (TextUtils.isEmpty(loginRequestModel.getPassword())) {
            mActivity.showError(binding.edPassword, mActivity.getString(R.string.contact_number_validation));
            return false;
        }
        return true;
    }

    @Override
    public void onLoginClick(LoginRequestModel loginRequestModel) {
//        loginRequestModel.setDeviceToken(FirebaseInstanceId.getInstance().getToken());
        loginRequestModel.setRemember(binding.checkRememberMe.isChecked());

        gps = new GPSTracker(mActivity);
        if (!gps.canGetLocation()) {
            gps.showSettingsAlert();
            return;
        }
        loginRequestModel.setLatitude(gps.getLatitude());
        loginRequestModel.setLongitude(gps.getLongitude());

        this.loginRequestModel = loginRequestModel;

        if (isValidateLogin(loginRequestModel)) {
            mActivity.showProgress();
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<JsonObject> call = apiService.login(loginRequestModel);
            WebServiceCaller.CallWebApi(call, WebApiConstants.LOGIN, mActivity, this);
        }
    }

    @Override
    public void onSuccess(JsonObject jsonObject, Enum anEnum) {
        if (anEnum == WebApiConstants.LOGIN) {
            mActivity.hideProgress();
            Prefs.putObjectIntoPref(mActivity, loginRequestModel, LoginRequestModel.class.getName());
            LoginResponseModel loginResponseModel = new Gson().fromJson(WebServiceCaller.getResponsePacket(jsonObject), LoginResponseModel.class);
            Prefs.putObjectIntoPref(mActivity, loginResponseModel, LoginResponseModel.class.getName());
            iLoginView.onLogin(loginResponseModel);
        }
    }

    @Override
    public void onError(JsonObject jsonObject, Enum anEnum) {
        mActivity.hideProgress();
        mActivity.showToast(WebServiceCaller.getResponseMessage(jsonObject));
    }
}
