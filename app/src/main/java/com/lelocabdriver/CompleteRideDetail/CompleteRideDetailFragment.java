package com.lelocabdriver.CompleteRideDetail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lelocabdriver.MainActivity;
import com.lelocabdriver.R;
import com.lelocabdriver.baseclasses.BaseActivity;
import com.lelocabdriver.databinding.CompleteRideDetailFragmentBinding;
import com.lelocabdriver.earnings.GetAllCompleteRideHistoryResponseModel;
import com.lelocabdriver.newride.NewRideFragment;
import com.lelocabdriver.utils.Constants;

/**
 * Created by ashish on 14-05-2017.
 */
public class CompleteRideDetailFragment extends Fragment {

    private BaseActivity mActivity;
    private CompleteRideDetailFragmentBinding binding;
    private GetAllCompleteRideHistoryResponseModel getAllCompleteRideHistoryResponseModel;
    private String from;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.complete_ride_detail_fragment, container, false);
        getAllCompleteRideHistoryResponseModel = (GetAllCompleteRideHistoryResponseModel) getArguments().getSerializable(GetAllCompleteRideHistoryResponseModel.class.getName());
        mActivity = (BaseActivity) getActivity();
        mActivity.setTitle("Ride Detail");
        from = getArguments().getString(Constants.FROM);
        if (from.equals(NewRideFragment.class.getName()))
            binding.btnFinish.setVisibility(View.VISIBLE);
        binding.setDto(getAllCompleteRideHistoryResponseModel);
        binding.setCompleteRideDetailFragment(this);
        return binding.getRoot();
    }

    public static CompleteRideDetailFragment newInstance(GetAllCompleteRideHistoryResponseModel getAllCompleteRideHistoryResponseModel, Bundle bundle) {
        CompleteRideDetailFragment completeRideDetailFragment = new CompleteRideDetailFragment();
        bundle.putSerializable(GetAllCompleteRideHistoryResponseModel.class.getName(), getAllCompleteRideHistoryResponseModel);
        completeRideDetailFragment.setArguments(bundle);
        return completeRideDetailFragment;
    }

    public void onClickFinish(View view) {
        Intent intent = new Intent(mActivity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
