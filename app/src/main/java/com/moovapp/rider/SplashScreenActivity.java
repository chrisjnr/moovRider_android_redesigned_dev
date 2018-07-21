package com.moovapp.rider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.moovapp.rider.main.HomeActivity;
import com.moovapp.rider.preLogin.LoginActivity;
import com.moovapp.rider.utils.Constants;
import com.moovapp.rider.utils.LMTBaseActivity;

import butterknife.ButterKnife;

/**
 * Created by Lijo Mathew Theckanal on 17-Jul-18.
 */

public class SplashScreenActivity extends LMTBaseActivity {

    private final int SPLASH_SCREEN_TIME = 1500;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.splash_screen_activity);
        ButterKnife.bind(this);
        delayFlow();
    }

    private void delayFlow() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(appPrefes.getDataBoolean(Constants.USER_LOGGED_IN_STATUS)){
                    Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_SCREEN_TIME);
    }
}
