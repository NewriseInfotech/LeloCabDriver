package com.lelocabdriver.scheduledride;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lelocabdriver.MainActivity;
import com.lelocabdriver.R;
import com.lelocabdriver.baseclasses.BaseActivity;
import com.lelocabdriver.baseclasses.BaseFragment;
import com.lelocabdriver.databinding.ScheduledRideDetailFragmentBinding;

/**
 * Created by ashish on 09-05-2017.
 */
public class ScheduledRideDetailFragment extends BaseFragment {

    private ScheduledRideDetailFragmentBinding binding;
    private MainActivity mActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.scheduled_ride_detail_fragment, container, false);
        mActivity = (MainActivity) getActivity();
        mActivity.setTitle(getString(R.string.schedule_detail));
        return binding.getRoot();
    }
}
