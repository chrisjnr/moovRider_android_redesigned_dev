package com.moovapp.rider.main.wallet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.moovapp.rider.R;
import com.moovapp.rider.main.wallet.payment.PaymentActivity;
import com.moovapp.rider.utils.Constants;
import com.moovapp.rider.utils.LMTBaseActivity;
import com.moovapp.rider.utils.retrofit.ApiClient;
import com.moovapp.rider.utils.retrofit.ApiInterface;
import com.moovapp.rider.utils.retrofit.responseModels.InitPaymentResponseModel;
import com.moovapp.rider.utils.retrofit.responseModels.ViewWalletBalanceResponseModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Lijo Mathew Theckanal on 19-Jul-18.
 */

public class WalletActivity extends LMTBaseActivity {

    private final int VIEW_WALLET_BALANCE_API = 1;

    @BindView(R.id.tvBalance)
    TextView tvBalance;

    public static boolean isWalletReacharged = false;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.wallet_activity);
        ButterKnife.bind(this);
        callViewWalletBalanceApi();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isWalletReacharged) {
            isWalletReacharged = false;
            callViewWalletBalanceApi();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.navBackButton)
    public void navBackButtonClick() {
        finish();
    }

    @OnClick(R.id.tvRecharge)
    public void tvRechargeClick() {
        Intent intent = new Intent(WalletActivity.this, PaymentActivity.class);
        startActivity(intent);
    }

    private void callViewWalletBalanceApi() {
        if (cd.isConnectingToInternet()) {
            try {
                myProgressDialog.setProgress(false);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<ViewWalletBalanceResponseModel> call = apiService.viewWalletBalance("wallet/balance/" + appPrefes.getData(Constants.USER_ID));
                call.enqueue(new retrofit2.Callback<ViewWalletBalanceResponseModel>() {
                    @Override
                    public void onResponse(Call<ViewWalletBalanceResponseModel> call, Response<ViewWalletBalanceResponseModel> response) {
                        myProgressDialog.dismissProgress();
                        try {
                            appPrefes.SaveData(Constants.WALLET_BALANCE, response.body().getWallet_balance() + "");
                            tvBalance.setText(response.body().getWallet_balance() + "");
                        } catch (Exception e) {
                            e.printStackTrace();
                            showServerErrorAlert(VIEW_WALLET_BALANCE_API);
                        }
                    }

                    @Override
                    public void onFailure(Call<ViewWalletBalanceResponseModel> call, Throwable t) {
                        myProgressDialog.dismissProgress();
                        System.out.println("t.toString : " + t.toString());
                        showServerErrorAlert(VIEW_WALLET_BALANCE_API);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                myProgressDialog.dismissProgress();
                showServerErrorAlert(VIEW_WALLET_BALANCE_API);
            }
        } else {
            showNoInternetAlert(VIEW_WALLET_BALANCE_API);
        }
    }

    @Override
    public void retryApiCall(int apiCode) {
        super.retryApiCall(apiCode);
        switch (apiCode) {
            case VIEW_WALLET_BALANCE_API:
                callViewWalletBalanceApi();
                break;
        }
    }
}
