package com.moovapp.rider.preLogin;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.moovapp.rider.R;
import com.moovapp.rider.main.HomeActivity;
import com.moovapp.rider.preLogin.signUp.SignUpActivity;
import com.moovapp.rider.utils.Constants;
import com.moovapp.rider.utils.LMTBaseActivity;
import com.moovapp.rider.utils.retrofit.ApiClient;
import com.moovapp.rider.utils.retrofit.ApiInterface;
import com.moovapp.rider.utils.retrofit.responseModels.ForgotPasswordResponseModel;
import com.moovapp.rider.utils.retrofit.responseModels.LoginEmailResponseModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Lijo Mathew Theckanal on 18-Jul-18.
 */

public class LoginActivity extends LMTBaseActivity {

    private final int EMAIL_LOGIN_API = 1;
    private final int FORGOT_PASSWORD_API = 2;
    private final int FORGOT_PASSWORD_SUCCESS_DIALOG = 3;

    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edPassword)
    EditText edPassword;

    private String forgotPasswordEmail = "";

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
        if (edEmail.getText().toString().trim().length() < 1) {
            edEmail.setError("This field is required");
        }
        if (edPassword.getText().toString().trim().length() < 1) {
            edPassword.setError("This field is required");
        }
        if (edEmail.getText().toString().trim().length() > 0 && edPassword.getText().toString().trim().length() > 0) {
            callEmailLoginApi();
        }
    }

    @OnClick(R.id.tvSignUp)
    public void tvSignUpClick() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvForgotPassword)
    public void tvForgotPasswordClick() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_forgot_password);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final EditText edEmail = (EditText) dialog.findViewById(R.id.edEmail);
        final TextView tvCancel = (TextView) dialog.findViewById(R.id.tvCancel);
        final TextView tvOk = (TextView) dialog.findViewById(R.id.tvOk);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edEmail.getText().toString().trim().length() > 0) {
                    forgotPasswordEmail = edEmail.getText().toString();
                    callForgotPasswordApi();
                    dialog.dismiss();
                } else {
                    edEmail.setError("This field is required");
                }
            }
        });
        dialog.show();
    }

    private void callEmailLoginApi() {
        if (cd.isConnectingToInternet()) {
            try {
                myProgressDialog.setProgress(false);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<LoginEmailResponseModel> call = apiService.loginEmail(edEmail.getText().toString(), edPassword.getText().toString(), "android", "", "", "1.0");
                call.enqueue(new retrofit2.Callback<LoginEmailResponseModel>() {
                    @Override
                    public void onResponse(Call<LoginEmailResponseModel> call, Response<LoginEmailResponseModel> response) {
                        myProgressDialog.dismissProgress();
                        try {
                            if (!response.body().isStatus()) {
                                Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                appPrefes.SaveData(Constants.USER_ID, response.body().getData().getUser_details().getU_id() + "");
                                appPrefes.SaveData(Constants.USER_FIRST_NAME, response.body().getData().getUser_details().getU_first_name() + "");
                                appPrefes.SaveData(Constants.ACCESS_TOKEN, response.body().getData().getAccess_token());
                                appPrefes.SaveData(Constants.USER_PROFILE_PIC, response.body().getData().getUser_pic_url());
                                appPrefes.SaveDataBoolean(Constants.USER_LOGGED_IN_STATUS, true);
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            showServerErrorAlert(EMAIL_LOGIN_API);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginEmailResponseModel> call, Throwable t) {
                        myProgressDialog.dismissProgress();
                        System.out.println("t.toString : " + t.toString());
                        showServerErrorAlert(EMAIL_LOGIN_API);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                myProgressDialog.dismissProgress();
                showServerErrorAlert(EMAIL_LOGIN_API);
            }
        } else {
            showNoInternetAlert(EMAIL_LOGIN_API);
        }
    }

    private void callForgotPasswordApi() {
        if (cd.isConnectingToInternet()) {
            try {
                myProgressDialog.setProgress(false);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<ForgotPasswordResponseModel> call = apiService.forgotPassword(forgotPasswordEmail);
                call.enqueue(new retrofit2.Callback<ForgotPasswordResponseModel>() {
                    @Override
                    public void onResponse(Call<ForgotPasswordResponseModel> call, Response<ForgotPasswordResponseModel> response) {
                        myProgressDialog.dismissProgress();
                        try {
                            if (!response.body().isStatus()) {
                                Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                showRequestSuccessDialog("Email Sent", response.body().getMessage(), "Okay", FORGOT_PASSWORD_SUCCESS_DIALOG);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            showServerErrorAlert(FORGOT_PASSWORD_API);
                        }
                    }

                    @Override
                    public void onFailure(Call<ForgotPasswordResponseModel> call, Throwable t) {
                        myProgressDialog.dismissProgress();
                        System.out.println("t.toString : " + t.toString());
                        showServerErrorAlert(FORGOT_PASSWORD_API);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                myProgressDialog.dismissProgress();
                showServerErrorAlert(FORGOT_PASSWORD_API);
            }
        } else {
            showNoInternetAlert(FORGOT_PASSWORD_API);
        }
    }

    @Override
    public void retryApiCall(int apiCode) {
        super.retryApiCall(apiCode);
        switch (apiCode) {
            case EMAIL_LOGIN_API:
                callEmailLoginApi();
                break;
            case FORGOT_PASSWORD_API:
                callForgotPasswordApi();
                break;
        }
    }
}
