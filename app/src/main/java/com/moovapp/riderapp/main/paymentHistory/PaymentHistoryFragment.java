package com.moovapp.riderapp.main.paymentHistory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.moovapp.riderapp.R;
import com.moovapp.riderapp.utils.AppPreferences;
import com.moovapp.riderapp.utils.ConnectionDetector;
import com.moovapp.riderapp.utils.Constants;
import com.moovapp.riderapp.utils.LMTFragmentHelper;
import com.moovapp.riderapp.utils.progress.MyProgressDialog;
import com.moovapp.riderapp.utils.retrofit.ApiClient;
import com.moovapp.riderapp.utils.retrofit.ApiInterface;
import com.moovapp.riderapp.utils.retrofit.responseModels.PaymentResponseModel;
import com.moovapp.riderapp.utils.retrofit.responseModels.ViewPreviousRidesResponseModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lijo Mathew Theckanal on 18-Jul-18.
 */

public class PaymentHistoryFragment extends LMTFragmentHelper {

    private final int VIEW_PREVIOUS_RIDE_API = 1;
    private static final int DIALOG_PAY = 2;
    private static final int PAYMENT_API = 3;

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.tvNoRides)
    TextView tvNoRides;

    private MyProgressDialog myProgressDialog;
    public ConnectionDetector cd;
    public AppPreferences appPrefes;

    private String rideId = "";
    private String amount = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_history_fragment, container, false);
        ButterKnife.bind(this, view);
        myProgressDialog = new MyProgressDialog(getActivity());
        cd = new ConnectionDetector(getContext());
        appPrefes = new AppPreferences(getContext(), getResources().getString(R.string.app_name));
        callViewPreviousRideApi();
        return view;
    }

    private void showPayDialog(String ride_amount) {
        showAlertDialog(getContext(), "Pay", "Do you want to pay $" + ride_amount, "Pay", "Cancel", DIALOG_PAY);
    }

    private void callViewPreviousRideApi() {
        if (cd.isConnectingToInternet()) {
            try {
                myProgressDialog.setProgress(false);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<ViewPreviousRidesResponseModel> call = apiService.viewPreviousRide("view/rides/user/" + appPrefes.getData(Constants.USER_ID));
                call.enqueue(new retrofit2.Callback<ViewPreviousRidesResponseModel>() {
                    @Override
                    public void onResponse(Call<ViewPreviousRidesResponseModel> call, final Response<ViewPreviousRidesResponseModel> response) {
                        myProgressDialog.dismissProgress();
                        try {
                            if (response.body().isStatus()) {
                                PaymentHistoryListAdapter paymentHistoryListAdapter = new PaymentHistoryListAdapter(getContext(), response.body().getData());
                                listView.setAdapter(paymentHistoryListAdapter);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        if (!response.body().getData().get(i).getRide_payment_status().equalsIgnoreCase("paid")) {
                                            rideId = response.body().getData().get(i).getRide_id() + "";
                                            amount = response.body().getData().get(i).getRide_amount() + "";
                                            showPayDialog(response.body().getData().get(i).getRide_amount());
                                        }
                                    }
                                });
                            } else {
                                tvNoRides.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            showServerErrorAlert(getContext(), VIEW_PREVIOUS_RIDE_API);
                        }
                    }

                    @Override
                    public void onFailure(Call<ViewPreviousRidesResponseModel> call, Throwable t) {
                        myProgressDialog.dismissProgress();
                        System.out.println("t.toString : " + t.toString());
                        showServerErrorAlert(getContext(), VIEW_PREVIOUS_RIDE_API);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                myProgressDialog.dismissProgress();
                showServerErrorAlert(getContext(), VIEW_PREVIOUS_RIDE_API);
            }
        } else {
            showNoInternetAlert(getContext(), VIEW_PREVIOUS_RIDE_API);
        }
    }

    private void callPaymentApi() {
        if (cd.isConnectingToInternet()) {
            try {
                myProgressDialog.setProgress(false);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<PaymentResponseModel> call = apiService.pay(appPrefes.getData(Constants.USER_ID), rideId, amount);
                call.enqueue(new retrofit2.Callback<PaymentResponseModel>() {
                    @Override
                    public void onResponse(Call<PaymentResponseModel> call, final Response<PaymentResponseModel> response) {
                        myProgressDialog.dismissProgress();
                        try {
                            if (response.body().isStatus()) {
                                callViewPreviousRideApi();
                                showRequestSuccessDialog(getContext(), "Success", "Your payment has been done successfully.", "Okay", 11);
                            } else {
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            showServerErrorAlert(getContext(), PAYMENT_API);
                        }
                    }

                    @Override
                    public void onFailure(Call<PaymentResponseModel> call, Throwable t) {
                        myProgressDialog.dismissProgress();
                        System.out.println("t.toString : " + t.toString());
                        showServerErrorAlert(getContext(), PAYMENT_API);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                myProgressDialog.dismissProgress();
                showServerErrorAlert(getContext(), PAYMENT_API);
            }
        } else {
            showNoInternetAlert(getContext(), PAYMENT_API);
        }
    }

    @Override
    public void retryApiCall(int apiCode) {
        super.retryApiCall(apiCode);
        switch (apiCode) {
            case VIEW_PREVIOUS_RIDE_API:
                callViewPreviousRideApi();
                break;
            case PAYMENT_API:
                callPaymentApi();
                break;
        }
    }

    @Override
    public void onClickAlertOkButton(int apiCode) {
        super.onClickAlertOkButton(apiCode);
        switch (apiCode) {
            case DIALOG_PAY:
                callPaymentApi();
                break;
        }
    }
}
