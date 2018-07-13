package com.lelocabdriver.webservice;

import com.google.gson.JsonObject;
import com.lelocabdriver.earnings.GetAllCompletedRideHistory;
import com.lelocabdriver.forgotpassword.ForgotPasswordRequestModel;
import com.lelocabdriver.home.GetRunningRideRequestModel;
import com.lelocabdriver.login.LoginRequestModel;
import com.lelocabdriver.newride.AcceptRideRequestModel;
import com.lelocabdriver.newride.CancelRideRequestModel;
import com.lelocabdriver.newride.CompleteRideRequestModel;
import com.lelocabdriver.notification.NotificationResponseModel;
import com.lelocabdriver.profile.GetWalletDetailRequestModel;
import com.lelocabdriver.signup.SignUpRequestModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.lelocabdriver.BR.SignUpRequestModel;

/**
 * Created by admin on 15-09-2016.
 */
public interface ApiInterface {

    @POST("DriverAccount/Login")
    Call<JsonObject> login(@Body LoginRequestModel loginRequestModel);

    @POST("DriverAccount/Logout")
    Call<JsonObject> Logout(@Body JsonObject jsonObject);

    @POST("DriverAccount/ForgotPassword")
    Call<JsonObject> password(@Body ForgotPasswordRequestModel forgotPasswordRequestModel);

    @POST("Ride/GetUserRideInfo")
    Call<JsonObject> getRideInfo(@Body JsonObject notificationResponseModel);

    @POST("Ride/AcceptRide")
    Call<JsonObject> AcceptRide(@Body AcceptRideRequestModel acceptRideRequestModel);

    @POST("Ride/StartRide")
    Call<JsonObject> StartRide(@Body AcceptRideRequestModel acceptRideRequestModel);

    @POST("Ride/CompleteRide")
    Call<JsonObject> CompleteRide(@Body CompleteRideRequestModel completeRideRequestModel);

    @POST("Ride/CancelRide")
    Call<JsonObject> CancelRide(@Body CancelRideRequestModel cancelRideRequestModel);

    @POST("Ride/GetAllCompleteRideHistory")
    Call<JsonObject> getAllCompleteRideHistory(@Body GetAllCompletedRideHistory getAllCompletedRideHistory);

    @POST("Ride/GetCompleteRideDetail")
    Call<JsonObject> getCompleteRideDetail(@Body JsonObject jsonObject);

    @POST("DriverAccount/GetDriverStatistics")
    Call<JsonObject> GetDriverStatistics(@Body JsonObject jsonObject);

    @POST("Wallet/GetWalletDetail")
    Call<JsonObject> getWalletDetail(@Body GetWalletDetailRequestModel getWalletDetailRequestModel);

    @POST("DriverAccount/UpdateDriverLocation")
    Call<JsonObject> updateDriverLocation(@Body JsonObject jsonObject);

    @POST("Ride/GetRunningRide")
    Call<JsonObject> getRunningRide(@Body GetRunningRideRequestModel getRunningRideRequestModel);
}
