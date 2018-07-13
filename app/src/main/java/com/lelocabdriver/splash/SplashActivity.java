package com.lelocabdriver.splash;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.lelocabdriver.MainActivity;
import com.lelocabdriver.R;
import com.lelocabdriver.baseclasses.BaseActivity;
import com.lelocabdriver.databinding.SplashActivityBinding;
import com.lelocabdriver.login.LoginActivity;
import com.lelocabdriver.login.LoginResponseModel;
import com.lelocabdriver.utils.Prefs;

/**
 * Created by ashish on 21-04-2017.
 */

public class SplashActivity extends BaseActivity {
    SplashActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.splash_activity);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (Prefs.getObjectFromPref(SplashActivity.this, LoginResponseModel.class.getName()) != null) {
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                } else {
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 3000);
    }


}
