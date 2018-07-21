package com.moovapp.rider.main.settings;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.moovapp.rider.R;
import com.moovapp.rider.main.HomeActivity;
import com.moovapp.rider.utils.AppPreferences;
import com.moovapp.rider.utils.ConnectionDetector;
import com.moovapp.rider.utils.Constants;
import com.moovapp.rider.utils.LMTFragmentHelper;
import com.moovapp.rider.utils.progress.MyProgressDialog;
import com.moovapp.rider.utils.retrofit.ApiClient;
import com.moovapp.rider.utils.retrofit.ApiInterface;
import com.moovapp.rider.utils.retrofit.responseModels.UpdateEmailResponseModel;
import com.moovapp.rider.utils.retrofit.responseModels.UpdatePasswordResponseModel;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lijo Mathew Theckanal on 19-Jul-18.
 */

public class SettingsFragment extends LMTFragmentHelper {

    private final int EDIT_EMAIL_API = 1;
    private final int EMAIL_EDIT_SUCCESS_DIALOG = 2;
    private final int EDIT_PASSWORD_API = 3;
    private final int EMAIL_PASSWORD_SUCCESS_DIALOG = 4;

    private MyProgressDialog myProgressDialog;
    public ConnectionDetector cd;
    public AppPreferences appPrefes;

    private String oldPassword = "";
    private String newPassword = "";
    private String oldEmail = "";
    private String newEmail = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);
        ButterKnife.bind(this, view);
        myProgressDialog = new MyProgressDialog(getActivity());
        cd = new ConnectionDetector(getContext());
        appPrefes = new AppPreferences(getContext(), getResources().getString(R.string.app_name));
        return view;
    }

    @OnClick(R.id.tvResetPassword)
    public void tvResetPasswordClick() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_reset_password);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final EditText edOldPassword = (EditText) dialog.findViewById(R.id.edOldPassword);
        final EditText edNewPassword = (EditText) dialog.findViewById(R.id.edNewPassword);
        final EditText edConfirmPassword = (EditText) dialog.findViewById(R.id.edConfirmPassword);
        final TextView tvCancel = (TextView) dialog.findViewById(R.id.tvCancel);
        final TextView tvOk = (TextView) dialog.findViewById(R.id.tvOk);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAllOkay = true;
                if (edOldPassword.getText().toString().trim().length() < 1) {
                    isAllOkay = false;
                    edOldPassword.setError("This field is required!");
                }
                if (edNewPassword.getText().toString().trim().length() < 1) {
                    isAllOkay = false;
                    edNewPassword.setError("This field is required!");
                }
                if (edConfirmPassword.getText().toString().trim().length() < 1) {
                    isAllOkay = false;
                    edConfirmPassword.setError("This field is required!");
                }
                if (!edConfirmPassword.getText().toString().equals(edNewPassword.getText().toString())) {
                    isAllOkay = false;
                    edConfirmPassword.setError("Passwords don't match!");
                }

                if (isAllOkay) {
                    oldPassword = edOldPassword.getText().toString();
                    newPassword = edNewPassword.getText().toString();
                    callEditPasswordApi();
                    dialog.dismiss();
                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @OnClick(R.id.tvResetNumber)
    public void tvResetNumberClick() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_reset_number);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final EditText edNewNumber = (EditText) dialog.findViewById(R.id.edNewNumber);
        final EditText edOtp = (EditText) dialog.findViewById(R.id.edOtp);
        final TextView tvCancel = (TextView) dialog.findViewById(R.id.tvCancel);
        final TextView tvOk = (TextView) dialog.findViewById(R.id.tvOk);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @OnClick(R.id.tvResetEmail)
    public void tvResetEmailClick() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_reset_email);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final EditText edOldEmail = (EditText) dialog.findViewById(R.id.edOldEmail);
        final EditText edNewEmail = (EditText) dialog.findViewById(R.id.edNewEmail);
        final TextView tvCancel = (TextView) dialog.findViewById(R.id.tvCancel);
        final TextView tvOk = (TextView) dialog.findViewById(R.id.tvOk);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAllOkay = true;
                if (edOldEmail.getText().toString().trim().length() < 1) {
                    isAllOkay = false;
                    edOldEmail.setError("This field is required!");
                }
                if (edNewEmail.getText().toString().trim().length() < 1) {
                    isAllOkay = false;
                    edNewEmail.setError("This field is required!");
                }
                if (isAllOkay) {
                    oldEmail = edOldEmail.getText().toString();
                    newEmail = edNewEmail.getText().toString();
                    callEditEmailApi();
                    dialog.dismiss();
                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void callEditEmailApi() {
        if (cd.isConnectingToInternet()) {
            try {
                myProgressDialog.setProgress(false);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<UpdateEmailResponseModel> call = apiService.updateEmail(appPrefes.getData(Constants.USER_ID), oldEmail, newEmail);
                call.enqueue(new retrofit2.Callback<UpdateEmailResponseModel>() {
                    @Override
                    public void onResponse(Call<UpdateEmailResponseModel> call, Response<UpdateEmailResponseModel> response) {
                        myProgressDialog.dismissProgress();
                        try {
                            if (!response.body().isStatus()) {
                                Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                showRequestSuccessDialog(getContext(), "Success", "Email updated successfully!", "Ok", EMAIL_EDIT_SUCCESS_DIALOG);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            showServerErrorAlert(getContext(), EDIT_EMAIL_API);
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateEmailResponseModel> call, Throwable t) {
                        myProgressDialog.dismissProgress();
                        System.out.println("t.toString : " + t.toString());
                        showServerErrorAlert(getContext(), EDIT_EMAIL_API);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                myProgressDialog.dismissProgress();
                showServerErrorAlert(getContext(), EDIT_EMAIL_API);
            }
        } else {
            showNoInternetAlert(getContext(), EDIT_EMAIL_API);
        }
    }

    private void callEditPasswordApi() {
        if (cd.isConnectingToInternet()) {
            try {
                myProgressDialog.setProgress(false);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<UpdatePasswordResponseModel> call = apiService.updatePassword(appPrefes.getData(Constants.USER_ID), oldPassword, newPassword);
                call.enqueue(new retrofit2.Callback<UpdatePasswordResponseModel>() {
                    @Override
                    public void onResponse(Call<UpdatePasswordResponseModel> call, Response<UpdatePasswordResponseModel> response) {
                        myProgressDialog.dismissProgress();
                        try {
                            if (!response.body().isStatus()) {
                                Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                showRequestSuccessDialog(getContext(), "Success", "Password updated successfully!", "Ok", EMAIL_PASSWORD_SUCCESS_DIALOG);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            showServerErrorAlert(getContext(), EDIT_PASSWORD_API);
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdatePasswordResponseModel> call, Throwable t) {
                        myProgressDialog.dismissProgress();
                        System.out.println("t.toString : " + t.toString());
                        showServerErrorAlert(getContext(), EDIT_PASSWORD_API);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                myProgressDialog.dismissProgress();
                showServerErrorAlert(getContext(), EDIT_PASSWORD_API);
            }
        } else {
            showNoInternetAlert(getContext(), EDIT_PASSWORD_API);
        }
    }

    @Override
    public void retryApiCall(int apiCode) {
        super.retryApiCall(apiCode);
        switch (apiCode) {
            case EDIT_EMAIL_API:
                callEditEmailApi();
                break;
            case EDIT_PASSWORD_API:
                callEditPasswordApi();
                break;
        }
    }
}
