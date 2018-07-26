package com.moovapp.rider.main.upcomingRides;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.moovapp.rider.utils.retrofit.responseModels.ViewCurrentRideResponseModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lijo Mathew Theckanal on 18-Jul-18.
 */

public class UpcomingRidesFragment extends LMTFragmentHelper {

    private final int VIEW_CURRENT_RIDE_API = 1;
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

    private String currentRideId = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upcoming_rides_fragment, container, false);
        ButterKnife.bind(this, view);
        myProgressDialog = new MyProgressDialog(getActivity());
        cd = new ConnectionDetector(getContext());
        appPrefes = new AppPreferences(getContext(), getResources().getString(R.string.app_name));
        callViewCurrentRideApi();
        return view;
    }

    private void setValues(ViewCurrentRideResponseModel.DataEntity data) {
        tvNoRides.setVisibility(View.GONE);
        layoutRide.setVisibility(View.VISIBLE);
        tvRiderName.setText(data.getDriver_details().getFirst_name() + " " + data.getDriver_details().getLast_name());
        tvCarModel.setText(data.getDriver_details().getCar_model());
        rating1.setRating(data.getDriver_details().getRatings());
        tvRiderPhone.setText(data.getDriver_details().getPhone());
//        tvDistance.setText(data.getDistance_to_drive_details().getDistance());
        tvDistance.setText("15 Km");
        tvCarNumber.setText(data.getDriver_details().getLicense_no());
//        tvEta.setText(data.getDistance_to_drive_details().getTime());
        tvEta.setText("15 Minutes");
        try {
            if (data.getDriver_details().getImage().length() > 3) {
                Picasso.get().load(data.getDriver_details().getImage()).placeholder(R.mipmap.user_placeholder).error(R.mipmap.user_placeholder).into(imgRiderImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.tvCancelRide)
    public void tvCancelRideClick() {
        showAlertDialog(getContext(), "Cancel Ride", "Do you really want to cancel the ride?", "Yes", "No", CANCEL_TRIP_DIALOG);
    }

    private void callViewCurrentRideApi() {
        if (cd.isConnectingToInternet()) {
            try {
                myProgressDialog.setProgress(false);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<ViewCurrentRideResponseModel> call = apiService.viewCurrentRide("view/rides/current/user/" + appPrefes.getData(Constants.USER_ID));
                call.enqueue(new retrofit2.Callback<ViewCurrentRideResponseModel>() {
                    @Override
                    public void onResponse(Call<ViewCurrentRideResponseModel> call, Response<ViewCurrentRideResponseModel> response) {
                        myProgressDialog.dismissProgress();
                        try {
                            if (response.body().isStatus()) {
                                currentRideId = response.body().getData().get(0).getRide_id() + "";
                                setValues(response.body().getData().get(0));
                            } else {
                                tvNoRides.setVisibility(View.VISIBLE);
                                layoutRide.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            showServerErrorAlert(getContext(), VIEW_CURRENT_RIDE_API);
                        }
                    }

                    @Override
                    public void onFailure(Call<ViewCurrentRideResponseModel> call, Throwable t) {
                        myProgressDialog.dismissProgress();
                        System.out.println("t.toString : " + t.toString());
                        showServerErrorAlert(getContext(), VIEW_CURRENT_RIDE_API);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                myProgressDialog.dismissProgress();
                showServerErrorAlert(getContext(), VIEW_CURRENT_RIDE_API);
            }
        } else {
            showNoInternetAlert(getContext(), VIEW_CURRENT_RIDE_API);
        }
    }

    private void callCancelRideApi() {
        if (cd.isConnectingToInternet()) {
            try {
                myProgressDialog.setProgress(false);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<CancelRideResponseModel> call = apiService.cancelRide("rides/cancel/" + currentRideId);
                call.enqueue(new retrofit2.Callback<CancelRideResponseModel>() {
                    @Override
                    public void onResponse(Call<CancelRideResponseModel> call, Response<CancelRideResponseModel> response) {
                        myProgressDialog.dismissProgress();
                        try {
                            if (response.body().isStatus()) {
                                showRequestSuccessDialog(getContext(), "Success", response.body().getMessage(), "Okay", SEARCH_FAILED_DIALOG);
                                tvNoRides.setVisibility(View.VISIBLE);
                                layoutRide.setVisibility(View.GONE);
                            } else {
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
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
            case VIEW_CURRENT_RIDE_API:
                callViewCurrentRideApi();
                break;
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
