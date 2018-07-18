package com.moovapp.rider.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.moovapp.rider.R;
import com.moovapp.rider.utils.progress.MyProgressDialog;


public class LMTBaseActivity extends AppCompatActivity implements NetworkChangeReceiver.Internet {
    public AppPreferences appPrefes;
    public ProgressDialog progressDialog;
    public ConnectionDetector cd;
    public MyProgressDialog myProgressDialog;

    @Override
    protected void onCreate(Bundle arg0) {
        // Show status bar
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(arg0);
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        appPrefes = new AppPreferences(this, getResources().getString(R.string.app_name));
        cd = new ConnectionDetector(this);
        myProgressDialog = new MyProgressDialog(this);
        NetworkChangeReceiver.internet = this;
    }

    public void showNoInternetAlert(final int apiCode) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_no_internet);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        final TextView tvRetry = (TextView) dialog.findViewById(R.id.tvRetry);
        final TextView tvClose = (TextView) dialog.findViewById(R.id.tvClose);
        tvRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                retryApiCall(apiCode);
            }
        });
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showServerErrorAlert(final int apiCode) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_server_error);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        final TextView tvRetry = (TextView) dialog.findViewById(R.id.tvRetry);
        final TextView tvClose = (TextView) dialog.findViewById(R.id.tvClose);
        tvRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                retryApiCall(apiCode);
            }
        });
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showRequestSuccessDialog(String title, String message, String button, final int code) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_popup);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        final TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        final TextView tvMessage = (TextView) dialog.findViewById(R.id.tvMessage);
        final TextView tvCancel = (TextView) dialog.findViewById(R.id.tvCancel);
        tvTitle.setText(title);
        tvMessage.setText(message);
        tvCancel.setText(button);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSuccessDialogClick(code);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showAlertDialog(String title, String message, String okButton, String cancelButton, final int code) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_alert);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        final TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        final TextView tvMessage = (TextView) dialog.findViewById(R.id.tvMessage);
        final TextView tvOk = (TextView) dialog.findViewById(R.id.tvOk);
        final TextView tvCancel = (TextView) dialog.findViewById(R.id.tvCancel);
        tvTitle.setText(title);
        tvMessage.setText(message);
        tvOk.setText(okButton);
        tvCancel.setText(cancelButton);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAlertOkButton(code);
                dialog.dismiss();
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

    public void retryApiCall(int apiCode) {

    }

    public void onClickAlertOkButton(int apiCode) {

    }

    public void onSuccessDialogClick(int apiCode) {

    }

    @Override
    public void net() {

    }
}
