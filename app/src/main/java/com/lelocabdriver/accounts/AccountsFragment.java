package com.lelocabdriver.accounts;

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
import com.lelocabdriver.databinding.AccountsFragmentBinding;

/**
 * Created by ashish on 10-05-2017.
 */

public class AccountsFragment extends BaseFragment implements IAccountsView {
    private AccountsFragmentBinding binding;
    private BaseActivity mActivity;
    private IAccountsPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.accounts_fragment, container, false);
        mActivity = (BaseActivity) getActivity();
        mActivity.setTitle(mActivity.getString(R.string.accounts));
        ((MainActivity) mActivity).displayHomeButton(false);
        presenter = new AccountsFragmentPresenterImpl(mActivity, this);
        presenter.getDriverStatistics();

        return binding.getRoot();
    }

    @Override
    public void onGetDriverStatistics(GetDriverStatisticsResponseModel getDriverStatisticsResponseModel) {
        binding.setDto(getDriverStatisticsResponseModel);
    }
}
