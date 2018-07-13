package com.lelocabdriver.signup;

import com.lelocabdriver.databinding.SignUpActivityBinding;

/**
 * Created by ashish on 22-04-2017.
 */

public class SignUpPresenterImpl implements ISignUpPresenter {
    private SignUpActivityBinding binding;
    private SignUpActivity mActivity;
    private ISignUpView iSignUpView;

    public SignUpPresenterImpl(SignUpActivityBinding binding, SignUpActivity mActivity, ISignUpView iSignUpView) {
        this.binding = binding;
        this.mActivity = mActivity;
        this.iSignUpView = iSignUpView;
    }

    @Override
    public void onSignUpClick(SignUpRequestModel signUpRequestModel) {
        if (isValidateSignUp()) {
            iSignUpView.onSignUp();
        }
    }

    public boolean isValidateSignUp() {

        return true;
    }
}
