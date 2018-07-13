package com.lelocabdriver.login;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;
import com.lelocabdriver.MainActivity;
import com.lelocabdriver.R;
import com.lelocabdriver.baseclasses.BaseActivity;
import com.lelocabdriver.databinding.LoginActivityBinding;
import com.lelocabdriver.forgotpassword.ForgotPasswordActivity;
import com.lelocabdriver.utils.Prefs;


/**
 * Created by ashish on 21-04-2017.
 */

public class LoginActivity extends BaseActivity implements ILoginView {
    private LoginActivityBinding binding;
    private Toolbar toolbar;
    private LoginRequestModel loginRequestModel;
    private ILoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.login_activity);
        loginRequestModel = Prefs.getObjectFromPref(this, LoginRequestModel.class.getName());
        if (loginRequestModel == null)
            loginRequestModel = new LoginRequestModel();
        presenter = new LoginPresenterImpl(binding, this, this);
//        FirebaseInstanceId.getInstance().getToken();
        binding.setLoginRequestModel(loginRequestModel);
        binding.setPresenter(presenter);
    }


    public void onClickForgotPassword(View view) {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLogin(LoginResponseModel loginResponseModel) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
