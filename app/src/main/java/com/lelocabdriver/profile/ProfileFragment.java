package com.lelocabdriver.profile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lelocabdriver.MainActivity;
import com.lelocabdriver.R;
import com.lelocabdriver.utils.Prefs;
import com.lelocabdriver.databinding.ProfileFragmentDataBinding;
import com.lelocabdriver.login.LoginResponseModel;

/**
 * Created by ashish on 25-05-2017.
 */

public class ProfileFragment extends Fragment implements IProfileView {

    ProfileFragmentDataBinding binding;
    private MainActivity mActivity;
    private IProfilePresenter presenter;
    private LoginResponseModel loginResponseModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false);
        mActivity = (MainActivity) getActivity();
        presenter = new ProfilePresenterImpl(mActivity, this);
        loginResponseModel = Prefs.getObjectFromPref(mActivity, LoginResponseModel.class.getName());
        binding.setLoginResponseModel(loginResponseModel);
        presenter.getWalletDetail();
        mActivity.setTitle(mActivity.getString(R.string.profile));
        return binding.getRoot();
    }

    @Override
    public void onGetWalletDetail(GetWalletDetailResponseModel getWalletDetailResponseModel) {
        binding.setDto(getWalletDetailResponseModel);
    }
}
