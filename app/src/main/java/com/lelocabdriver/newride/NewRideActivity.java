package com.lelocabdriver.newride;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.lelocabdriver.R;
import com.lelocabdriver.baseclasses.BaseActivity;
import com.lelocabdriver.notification.NotificationResponseModel;
import com.lelocabdriver.notification.RideInfoModel;
import com.lelocabdriver.utils.Prefs;

import java.util.List;

/**
 * Created by ashish on 07-05-2017.
 */

public class NewRideActivity extends BaseActivity implements INewRideActivityView {
    private NotificationResponseModel notificationResponseModel;
    private NewRideActivityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_ride_activity);
        presenter = new NewRideActivityImpl(this, this);
        notificationResponseModel = (NotificationResponseModel) getIntent().getSerializableExtra(NotificationResponseModel.class.getName());
        Prefs.putObjectIntoPref(this, notificationResponseModel, NotificationResponseModel.class.getName());
        presenter.GetUserRideInfo(notificationResponseModel);
    }

    private void showNewRide(RideInfoModel rideInfoModel) {
        Fragment fragment = null;
        Class fragmentClass = null;
        fragmentClass = NewRideFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(RideInfoModel.class.getName(), rideInfoModel);
        fragment.setArguments(bundle);
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
    }

    @Override
    public void onGetRideInfo(RideInfoModel rideInfoModel) {
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> services = activityManager
                .getRunningTasks(Integer.MAX_VALUE);
        boolean isActivityFound = false;

        if (services.get(0).topActivity.getPackageName().toString()
                .equalsIgnoreCase(getApplicationContext().getPackageName().toString())) {
            isActivityFound = true;
        }

        if (isActivityFound) {
            showNewRide(rideInfoModel);
        }
    }
}
