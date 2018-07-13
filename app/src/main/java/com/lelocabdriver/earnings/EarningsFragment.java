package com.lelocabdriver.earnings;

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
import com.lelocabdriver.databinding.EarningsFragmentBinding;

import java.util.ArrayList;

/**
 * Created by ashish on 08-05-2017.
 */

public class EarningsFragment extends BaseFragment implements IEarningsView {
    private EarningsFragmentBinding binding;
    private MainActivity mActivity;
    private EarningsAdapter adapter;
    private IEarningsPresenter presenter;
    private ArrayList<GetAllCompleteRideHistoryResponseModel> getAllCompleteRideHistoryResponseModels = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.earnings_fragment, container, false);
        mActivity = (MainActivity) getActivity();
        adapter = new EarningsAdapter(mActivity, getAllCompleteRideHistoryResponseModels);
        presenter = new EarningsPresenterImpl(mActivity, this);
        mActivity.displayHomeButton(false);
        mActivity.setTitle(mActivity.getString(R.string.earnings));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        presenter.getAllCompleteRideHistory();
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onGetAllCompleteRideHistory(ArrayList<GetAllCompleteRideHistoryResponseModel> getAllCompleteRideHistoryResponseModels) {
        adapter.changeData(getAllCompleteRideHistoryResponseModels);
        adapter.notifyDataSetChanged();
    }
}
