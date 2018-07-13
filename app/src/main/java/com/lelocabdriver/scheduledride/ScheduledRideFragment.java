package com.lelocabdriver.scheduledride;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lelocabdriver.MainActivity;
import com.lelocabdriver.R;
import com.lelocabdriver.baseclasses.BaseFragment;
import com.lelocabdriver.databinding.ScheduledRideBinding;
import com.lelocabdriver.utils.DividerItemDecoration;

/**
 * Created by ashish on 08-05-2017.
 */

public class ScheduledRideFragment extends BaseFragment {
    private ScheduledRideBinding binding;
    private MainActivity mActivity;
    private ScheduledRideAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.scheduled_ride_fragment, container, false);
        mActivity = (MainActivity) getActivity();
        adapter = new ScheduledRideAdapter(mActivity);
        mActivity.displayHomeButton(false);
        mActivity.setTitle(mActivity.getString(R.string.scheduled_rides));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.divider));
        binding.recyclerView.setAdapter(adapter);
        return binding.getRoot();
    }
}
