package com.lelocabdriver.accounts;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lelocabdriver.baseclasses.BaseActivity;
import com.lelocabdriver.login.LoginResponseModel;
import com.lelocabdriver.utils.Prefs;
import com.lelocabdriver.webservice.ApiCallbacks;
import com.lelocabdriver.webservice.ApiClient;
import com.lelocabdriver.webservice.ApiInterface;
import com.lelocabdriver.webservice.WebApiConstants;
import com.lelocabdriver.webservice.WebServiceCaller;

import retrofit2.Call;

/**
 * Created by ashish on 16-05-2017.
 */

public class AccountsFragmentPresenterImpl implements IAccountsPresenter, ApiCallbacks {
    private BaseActivity mActivity;
    private IAccountsView view;
    private LoginResponseModel loginResponseModel;

    public AccountsFragmentPresenterImpl(BaseActivity mActivity, IAccountsView view) {
        this.mActivity = mActivity;
        this.view = view;
        loginResponseModel = Prefs.getObjectFromPref(mActivity, LoginResponseModel.class.getName());
    }

    @Override
    public void getDriverStatistics() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("UserId", loginResponseModel.getId());
        mActivity.showProgress();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> call = apiService.GetDriverStatistics(jsonObject);
        WebServiceCaller.CallWebApi(call, WebApiConstants.GET_DRIVER_STATISTICS, mActivity, this);
    }

    @Override
    public void onSuccess(JsonObject jsonObject, Enum anEnum) {
        mActivity.hideProgress();
        if (anEnum == WebApiConstants.GET_DRIVER_STATISTICS) {
            GetDriverStatisticsResponseModel getDriverStatisticsResponseModel =
                    new Gson().fromJson(WebServiceCaller.getResponsePacket(jsonObject), GetDriverStatisticsResponseModel.class);
            view.onGetDriverStatistics(getDriverStatisticsResponseModel);
        }
    }

    @Override
    public void onError(JsonObject jsonObject, Enum anEnum) {
        mActivity.hideProgress();
        mActivity.showToast(WebServiceCaller.getResponseMessage(jsonObject));

    }
}
