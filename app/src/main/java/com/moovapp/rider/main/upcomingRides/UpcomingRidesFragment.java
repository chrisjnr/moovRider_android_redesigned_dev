package com.moovapp.rider.main.upcomingRides;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moovapp.rider.R;
import com.moovapp.rider.utils.LMTFragmentHelper;

import butterknife.ButterKnife;

/**
 * Created by Lijo Mathew Theckanal on 18-Jul-18.
 */

public class UpcomingRidesFragment extends LMTFragmentHelper{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upcoming_rides_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
