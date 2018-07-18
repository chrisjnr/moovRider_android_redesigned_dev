package com.moovapp.rider.preLogin.signUp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moovapp.rider.R;
import com.moovapp.rider.utils.LMTBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Lijo Mathew Theckanal on 18-Jul-18.
 */

public class SignUpActivity extends LMTBaseActivity {

    @BindView(R.id.imgSeekBar)
    ImageView imgSeekBar;
    @BindView(R.id.tvNext)
    TextView tvNext;
    @BindView(R.id.llAlreadyHaveAccount)
    LinearLayout llAlreadyHaveAccount;
    @BindView(R.id.layoutOne)
    View layoutOne;
    @BindView(R.id.layoutTwo)
    View layoutTwo;
    @BindView(R.id.layoutThree)
    View layoutThree;

    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.sign_up_activity);
        ButterKnife.bind(this);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.tvSignIn)
    public void tvSignInClick() {
        finish();
    }

    @OnClick(R.id.tvNext)
    public void tvNextClick() {
        changePage();
    }

    private void changePage() {
        switch (currentPage) {
            case 1:
                llAlreadyHaveAccount.setVisibility(View.INVISIBLE);
                layoutOne.setVisibility(View.GONE);
                layoutTwo.setVisibility(View.VISIBLE);
                imgSeekBar.setImageResource(R.mipmap.slide_bar_two);
                currentPage = 2;
                break;
            case 2:
                layoutTwo.setVisibility(View.GONE);
                layoutThree.setVisibility(View.VISIBLE);
                tvNext.setText("SIGN UP");
                imgSeekBar.setImageResource(R.mipmap.slide_bar_three);
                currentPage = 3;
                break;
            case 3:
                finish();
                break;
        }
    }
}
