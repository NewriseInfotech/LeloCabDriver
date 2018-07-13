package com.lelocabdriver.rides;

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
import com.lelocabdriver.baseclasses.BaseActivity;
import com.lelocabdriver.baseclasses.BaseFragment;
import com.lelocabdriver.databinding.RidesFragmentBinding;

/**
 * Created by ashish on 07-05-2017.
 */

public class RidesFragment extends BaseFragment {

    private RidesFragmentBinding binding;
    private BaseActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.rides_fragment, container, false);
        mActivity = (BaseActivity) getActivity();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        ((MainActivity) mActivity).displayHomeButton(true);
        mActivity.setTitle(getString(R.string.your_rides));

        return binding.getRoot();
    }
}
