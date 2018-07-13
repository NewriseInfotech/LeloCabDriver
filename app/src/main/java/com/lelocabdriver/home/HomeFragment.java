package com.lelocabdriver.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lelocabdriver.MainActivity;
import com.lelocabdriver.R;
import com.lelocabdriver.accounts.AccountsFragment;
import com.lelocabdriver.baseclasses.BaseFragment;
import com.lelocabdriver.databinding.HomeFragmentBinding;
import com.lelocabdriver.earnings.EarningsFragment;
import com.lelocabdriver.location.LocationService;
import com.lelocabdriver.newride.NewRideActivity;
import com.lelocabdriver.newride.NewRideFragment;
import com.lelocabdriver.notification.NotificationResponseModel;
import com.lelocabdriver.notification.RideInfoModel;
import com.lelocabdriver.raitings.RatingsFragment;
import com.lelocabdriver.scheduledride.ScheduledRideFragment;
import com.lelocabdriver.utils.Prefs;

/**
 * Created by ashish on 30-04-2017.
 */

public class HomeFragment extends BaseFragment implements IHomeView {
    private HomeFragmentBinding binding;
    private MainActivity mActivity;
    private IHomePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        mActivity = (MainActivity) getActivity();
        presenter = new HomePresenterImpl(mActivity, this);
        mActivity.displayHomeButton(true);
        mActivity.setTitle(mActivity.getString(R.string.home));
        binding.setHomeFragment(this);
        presenter.getRunningRide();
        return binding.getRoot();
    }

    public void onClickRating(View view) {
        mActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContent, new RatingsFragment())
                .addToBackStack(RatingsFragment.class.getName())
                .commit();
    }

    public void onClickScheduledRide(View view) {
        mActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContent, new ScheduledRideFragment())
                .addToBackStack(ScheduledRideFragment.class.getName())
                .commit();
    }

    public void onClickEarnings(View view) {
        mActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContent, new EarningsFragment())
                .addToBackStack(EarningsFragment.class.getName())
                .commit();
    }

    public void onClickAccounts(View view) {
        mActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContent, new AccountsFragment())
                .addToBackStack(EarningsFragment.class.getName())
                .commit();
    }

    @Override
    public void onGetRunningRide(RideInfoModel rideInfoModel) {
        NewRideFragment newRideFragment = new NewRideFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(RideInfoModel.class.getName(), rideInfoModel);
        newRideFragment.setArguments(bundle);
        mActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContent, newRideFragment)
                .addToBackStack(HomeFragment.class.getName())
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = new Intent(mActivity, LocationService.class);
        mActivity.startService(intent);
    }
}
