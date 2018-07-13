package com.lelocabdriver.profile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lelocabdriver.MainActivity;
import com.lelocabdriver.utils.Prefs;
import com.lelocabdriver.login.LoginResponseModel;
import com.lelocabdriver.webservice.ApiCallbacks;
import com.lelocabdriver.webservice.ApiClient;
import com.lelocabdriver.webservice.ApiInterface;
import com.lelocabdriver.webservice.WebApiConstants;
import com.lelocabdriver.webservice.WebServiceCaller;

import retrofit2.Call;

/**
 * Created by ashish on 25-05-2017.
 */

public class ProfilePresenterImpl implements IProfilePresenter, ApiCallbacks {
    private MainActivity mActivity;
    private IProfileView view;
    private ApiInterface apiService;
    private LoginResponseModel loginResponseModel;

    public ProfilePresenterImpl(MainActivity mActivity, IProfileView view) {
        this.mActivity = mActivity;
        this.view = view;
        loginResponseModel = Prefs.getObjectFromPref(mActivity, LoginResponseModel.class.getName());
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void getWalletDetail() {
        mActivity.showProgress();
        GetWalletDetailRequestModel getWalletDetailRequestModel = new GetWalletDetailRequestModel();
        getWalletDetailRequestModel.setDriverId(String.valueOf(loginResponseModel.getId()));
        getWalletDetailRequestModel.setUser(false);
        getWalletDetailRequestModel.setUserId("0");
        Call<JsonObject> call = apiService.getWalletDetail(getWalletDetailRequestModel);
        WebServiceCaller.CallWebApi(call, WebApiConstants.GET_WALLET_DETAIL, mActivity, this);
    }

    @Override
    public void onSuccess(JsonObject jsonObject, Enum anEnum) {
        mActivity.hideProgress();
        if (anEnum == WebApiConstants.GET_WALLET_DETAIL) {
            GetWalletDetailResponseModel getWalletDetailResponseModel = new Gson()
                    .fromJson(WebServiceCaller.getResponsePacket(jsonObject), GetWalletDetailResponseModel.class);
            view.onGetWalletDetail(getWalletDetailResponseModel);
        }

    }

    @Override
    public void onError(JsonObject jsonObject, Enum anEnum) {
        mActivity.hideProgress();
        mActivity.showToast(WebServiceCaller.getResponseMessage(jsonObject));
    }
}
