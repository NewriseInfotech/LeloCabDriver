package com.lelocabdriver.newride;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lelocabdriver.CompleteRideDetail.CompleteRideDetailFragment;
import com.lelocabdriver.R;
import com.lelocabdriver.baseclasses.BaseActivity;
import com.lelocabdriver.baseclasses.BaseFragment;
import com.lelocabdriver.databinding.NewRideFragmentBinding;
import com.lelocabdriver.earnings.GetAllCompleteRideHistoryResponseModel;
import com.lelocabdriver.location.GPSTracker;
import com.lelocabdriver.login.LoginActivity;
import com.lelocabdriver.notification.NotificationResponseModel;
import com.lelocabdriver.notification.RideInfoModel;
import com.lelocabdriver.utils.Constants;
import com.lelocabdriver.utils.Prefs;

/**
 * Created by ashish on 07-05-2017.
 */
public class NewRideFragment extends BaseFragment implements INewRideFragmentView {

    private NewRideFragmentBinding binding;
    private INewRideFragmentPresenter presenter;
    private BaseActivity mActivity;
    private RideInfoModel rideInfoModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.new_ride_fragment, container, false);
        mActivity = (BaseActivity) getActivity();
        presenter = new NewRideFragmentImpl(mActivity, this);
        rideInfoModel = (RideInfoModel) getArguments().getSerializable(RideInfoModel.class.getName());
        if (rideInfoModel != null) {
            binding.editOtp.setVisibility(rideInfoModel.getRideStatus() == 2 ? View.VISIBLE : View.GONE);
        }
        binding.setRideInfoModel(rideInfoModel);
        binding.setPresenter(presenter);
        binding.setNewRideFragment(this);
        binding.setOtp("");
        return binding.getRoot();
    }

    @Override
    public void onAcceptRide() {
        GPSTracker gpsTracker = new GPSTracker(mActivity);
        binding.llRequest.setVisibility(View.GONE);
        ((EditText) binding.getRoot().findViewById(R.id.edit_otp)).setVisibility(View.VISIBLE);
        binding.llStartCancel.setVisibility(View.VISIBLE);
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=" + gpsTracker.getLongitude() + ","
                        + gpsTracker.getLongitude() +
                        "&daddr=" + rideInfoModel.getSourceLatitude() + "," + rideInfoModel.getSourceLongitude()));
        startActivity(intent);
    }

    @Override
    public void onStartRide() {
        ((EditText) binding.getRoot().findViewById(R.id.edit_otp)).setVisibility(View.GONE);
        binding.llStartCancel.setVisibility(View.GONE);
        binding.btnComplete.setVisibility(View.VISIBLE);
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=" + rideInfoModel.getSourceLatitude() + ","
                        + rideInfoModel.getSourceLongitude() +
                        "&daddr=" + rideInfoModel.getDestinationLatitude() + "," + rideInfoModel.getDestinationLongitude()));
        startActivity(intent);
    }

    @Override
    public void onCompleteRide(GetAllCompleteRideHistoryResponseModel getAllCompleteRideHistoryResponseModel) {
        Prefs.putObjectIntoPref(mActivity, null, NotificationResponseModel.class.getName());
        Bundle bundle = new Bundle();
        bundle.putString(Constants.FROM, NewRideFragment.class.getName());
        mActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContent, CompleteRideDetailFragment.newInstance(getAllCompleteRideHistoryResponseModel, bundle))
                .commit();
    }

    @Override
    public void onCancelRide() {
        mActivity.finish();
    }

    public void onNavigationClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=" + rideInfoModel.getSourceLatitude() + ","
                        + rideInfoModel.getSourceLongitude() +
                        "&daddr=" + rideInfoModel.getDestinationLatitude() + "," + rideInfoModel.getDestinationLongitude()));
        startActivity(intent);
    }


}
