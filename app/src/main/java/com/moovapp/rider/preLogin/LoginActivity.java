package com.moovapp.rider.preLogin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.moovapp.rider.R;
import com.moovapp.rider.main.HomeActivity;
import com.moovapp.rider.preLogin.signUp.SignUpActivity;
import com.moovapp.rider.utils.LMTBaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Lijo Mathew Theckanal on 18-Jul-18.
 */

public class LoginActivity extends LMTBaseActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.tvSignIn)
    public void tvSignInClick() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvSignUp)
    public void tvSignUpClick() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
