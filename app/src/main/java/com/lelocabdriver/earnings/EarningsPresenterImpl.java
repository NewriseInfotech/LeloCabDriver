package com.lelocabdriver.earnings;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.lelocabdriver.MainActivity;
import com.lelocabdriver.baseclasses.BaseActivity;
import com.lelocabdriver.login.LoginResponseModel;
import com.lelocabdriver.utils.Prefs;
import com.lelocabdriver.webservice.ApiCallbacks;
import com.lelocabdriver.webservice.ApiClient;
import com.lelocabdriver.webservice.ApiInterface;
import com.lelocabdriver.webservice.WebApiConstants;
import com.lelocabdriver.webservice.WebServiceCaller;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by ashish on 14-05-2017.
 */

public class EarningsPresenterImpl implements IEarningsPresenter, ApiCallbacks {
    private BaseActivity mActivity;
    private IEarningsView view;
    private LoginResponseModel loginResponseModel;

    public EarningsPresenterImpl(BaseActivity mActivity, IEarningsView view) {
        this.mActivity = mActivity;
        this.view = view;
        loginResponseModel = Prefs.getObjectFromPref(mActivity, LoginResponseModel.class.getName());
    }

    @Override
    public void getAllCompleteRideHistory() {
        GetAllCompletedRideHistory getAllCompletedRideHistory =
                new GetAllCompletedRideHistory(true, "", loginResponseModel.getId());

        mActivity.showProgress();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> call = apiService.getAllCompleteRideHistory(getAllCompletedRideHistory);
        WebServiceCaller.CallWebApi(call, WebApiConstants.GET_ALL_COMPLETE_RIDE_HISTORY, mActivity, this);
    }

    @Override
    public void onSuccess(JsonObject jsonObject, Enum anEnum) {
        mActivity.hideProgress();
        if (anEnum == WebApiConstants.GET_ALL_COMPLETE_RIDE_HISTORY) {
            ArrayList<GetAllCompleteRideHistoryResponseModel> getAllCompleteRideHistoryResponseModels = new Gson().fromJson(WebServiceCaller.getResponsePacketArray(jsonObject).toString(), new TypeToken<List<GetAllCompleteRideHistoryResponseModel>>() {
            }.getType());
            view.onGetAllCompleteRideHistory(getAllCompleteRideHistoryResponseModels);
        }

    }

    @Override
    public void onError(JsonObject jsonObject, Enum anEnum) {
        mActivity.hideProgress();
        mActivity.showToast(WebServiceCaller.getResponseMessage(jsonObject));
    }
}
