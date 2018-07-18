package com.moovapp.rider.main.wallet;

import android.content.Context;
import android.os.Bundle;

import com.moovapp.rider.R;
import com.moovapp.rider.utils.LMTBaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Lijo Mathew Theckanal on 19-Jul-18.
 */

public class WalletActivity extends LMTBaseActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.wallet_activity);
        ButterKnife.bind(this);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.navBackButton)
    public void navBackButtonClick() {
        finish();
    }
}
