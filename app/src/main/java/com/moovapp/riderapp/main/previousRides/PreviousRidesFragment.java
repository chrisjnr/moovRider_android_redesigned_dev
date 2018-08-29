package com.moovapp.riderapp.main.previousRides;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.moovapp.riderapp.R;
import com.moovapp.riderapp.main.previousRides.rateDriver.RateDriverActivity;
import com.moovapp.riderapp.utils.AppPreferences;
import com.moovapp.riderapp.utils.ConnectionDetector;
import com.moovapp.riderapp.utils.Constants;
import com.moovapp.riderapp.utils.LMTFragmentHelper;
import com.moovapp.riderapp.utils.progress.MyProgressDialog;
import com.moovapp.riderapp.utils.retrofit.ApiClient;
import com.moovapp.riderapp.utils.retrofit.ApiInterface;
import com.moovapp.riderapp.utils.retrofit.responseModels.ViewPreviousRidesResponseModel;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lijo Mathew Theckanal on 18-Jul-18.
 */

public class PreviousRidesFragment extends LMTFragmentHelper {

    private final int VIEW_PREVIOUS_RIDE_API = 1;

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.tvNoRides)
    TextView tvNoRides;

    private MyProgressDialog myProgressDialog;
    public ConnectionDetector cd;
    public AppPreferences appPrefes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.previous_rides_fragment, container, false);
        ButterKnife.bind(this, view);
        myProgressDialog = new MyProgressDialog(getActivity());
        cd = new ConnectionDetector(getContext());
        appPrefes = new AppPreferences(getContext(), getResources().getString(R.string.app_name));
        callViewPreviousRideApi();
        return view;
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
                                PreviousRidesListAdapter previousRidesListAdapter = new PreviousRidesListAdapter(getContext(), response.body().getData());
                                listView.setAdapter(previousRidesListAdapter);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(getActivity(), RateDriverActivity.class);
                                        intent.putExtra("Details", (Serializable) response.body().getData().get(i));
                                        startActivity(intent);
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


    @Override
    public void retryApiCall(int apiCode) {
        super.retryApiCall(apiCode);
        switch (apiCode) {
            case VIEW_PREVIOUS_RIDE_API:
                callViewPreviousRideApi();
                break;
        }
    }
}
