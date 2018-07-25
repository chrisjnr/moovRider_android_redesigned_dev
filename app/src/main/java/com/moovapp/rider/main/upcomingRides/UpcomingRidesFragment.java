package com.moovapp.rider.main.upcomingRides;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ornolfr.ratingview.RatingView;
import com.moovapp.rider.R;
import com.moovapp.rider.utils.AppPreferences;
import com.moovapp.rider.utils.ConnectionDetector;
import com.moovapp.rider.utils.Constants;
import com.moovapp.rider.utils.LMTFragmentHelper;
import com.moovapp.rider.utils.progress.MyProgressDialog;
import com.moovapp.rider.utils.retrofit.ApiClient;
import com.moovapp.rider.utils.retrofit.ApiInterface;
import com.moovapp.rider.utils.retrofit.responseModels.CancelRideResponseModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lijo Mathew Theckanal on 18-Jul-18.
 */

public class UpcomingRidesFragment extends LMTFragmentHelper {

    private final int CANCEL_TRIP_API = 6;
    private final int SEARCH_FAILED_DIALOG = 5;
    private final int CANCEL_TRIP_DIALOG = 7;

    @BindView(R.id.layoutRide)
    View layoutRide;
    @BindView(R.id.tvNoRides)
    TextView tvNoRides;

    @BindView(R.id.tvRiderName)
    TextView tvRiderName;
    @BindView(R.id.imgRiderImage)
    ImageView imgRiderImage;
    @BindView(R.id.tvCarModel)
    TextView tvCarModel;
    @BindView(R.id.rating1)
    RatingView rating1;
    @BindView(R.id.tvRiderPhone)
    TextView tvRiderPhone;
    @BindView(R.id.tvDistance)
    TextView tvDistance;
    @BindView(R.id.tvCarNumber)
    TextView tvCarNumber;
    @BindView(R.id.tvEta)
    TextView tvEta;

    private MyProgressDialog myProgressDialog;
    public ConnectionDetector cd;
    public AppPreferences appPrefes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upcoming_rides_fragment, container, false);
        ButterKnife.bind(this, view);
        myProgressDialog = new MyProgressDialog(getActivity());
        cd = new ConnectionDetector(getContext());
        appPrefes = new AppPreferences(getContext(), getResources().getString(R.string.app_name));
        setValues();
        return view;
    }

    private void setValues() {
        if (appPrefes.getData(Constants.TEMP_DRIVER_NAME).equals("")) {
            tvNoRides.setVisibility(View.VISIBLE);
            layoutRide.setVisibility(View.GONE);
        } else {
            tvNoRides.setVisibility(View.GONE);
            layoutRide.setVisibility(View.VISIBLE);
            tvRiderName.setText(appPrefes.getData(Constants.TEMP_DRIVER_NAME));
            tvCarModel.setText(appPrefes.getData(Constants.TEMP_CAR_MODEL));
            rating1.setRating(Integer.parseInt(appPrefes.getData(Constants.TEMP_RATING)));
            tvRiderPhone.setText(appPrefes.getData(Constants.TEMP_PHONE));
            tvDistance.setText(appPrefes.getData(Constants.TEMP_DISTANCE));
            tvCarNumber.setText(appPrefes.getData(Constants.TEMP_CAR_NUMBER));
            tvEta.setText(appPrefes.getData(Constants.TEMP_ETA));
            try {
                if (appPrefes.getData(Constants.TEMP_PHOTO).length() > 3) {
                    Picasso.get().load(appPrefes.getData(Constants.TEMP_PHOTO)).placeholder(R.mipmap.user_placeholder).error(R.mipmap.user_placeholder).into(imgRiderImage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.tvCancelRide)
    public void tvCancelRideClick() {
        showAlertDialog(getContext(), "Cancel Ride", "Do you really want to cancel the ride?", "Yes", "No", CANCEL_TRIP_DIALOG);
    }

    private void callCancelRideApi() {
        if (cd.isConnectingToInternet()) {
            try {
                myProgressDialog.setProgress(false);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<CancelRideResponseModel> call = apiService.cancelRide("rides/cancel/" + appPrefes.getData(Constants.TEMP_RIDE_ID));
                call.enqueue(new retrofit2.Callback<CancelRideResponseModel>() {
                    @Override
                    public void onResponse(Call<CancelRideResponseModel> call, Response<CancelRideResponseModel> response) {
                        myProgressDialog.dismissProgress();
                        try {
                            if (response.body().isStatus()) {
                                showRequestSuccessDialog(getContext(), "Success", response.body().getMessage(), "Okay", SEARCH_FAILED_DIALOG);
                                appPrefes.SaveData(Constants.TEMP_DRIVER_NAME, "");
                                appPrefes.SaveData(Constants.TEMP_CAR_MODEL, "");
                                appPrefes.SaveData(Constants.TEMP_RATING, "");
                                appPrefes.SaveData(Constants.TEMP_PHONE, "");
                                appPrefes.SaveData(Constants.TEMP_DISTANCE, "");
                                appPrefes.SaveData(Constants.TEMP_CAR_NUMBER, "");
                                appPrefes.SaveData(Constants.TEMP_ETA, "");
                                appPrefes.SaveData(Constants.TEMP_PHOTO, "");
                                appPrefes.SaveData(Constants.TEMP_RIDE_ID, "");
                                tvNoRides.setVisibility(View.VISIBLE);
                                layoutRide.setVisibility(View.GONE);
                            } else {
                                showServerErrorAlert(getContext(), CANCEL_TRIP_API);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            showServerErrorAlert(getContext(), CANCEL_TRIP_API);
                        }
                    }

                    @Override
                    public void onFailure(Call<CancelRideResponseModel> call, Throwable t) {
                        myProgressDialog.dismissProgress();
                        System.out.println("t.toString : " + t.toString());
                        showServerErrorAlert(getContext(), CANCEL_TRIP_API);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                myProgressDialog.dismissProgress();
                showServerErrorAlert(getContext(), CANCEL_TRIP_API);
            }
        } else {
            showNoInternetAlert(getContext(), CANCEL_TRIP_API);
        }
    }

    @Override
    public void retryApiCall(int apiCode) {
        super.retryApiCall(apiCode);
        switch (apiCode) {
            case CANCEL_TRIP_API:
                callCancelRideApi();
                break;
        }
    }

    @Override
    public void onClickAlertOkButton(int apiCode) {
        super.onClickAlertOkButton(apiCode);
        switch (apiCode) {
            case CANCEL_TRIP_DIALOG:
                callCancelRideApi();
                break;
        }
    }
}
