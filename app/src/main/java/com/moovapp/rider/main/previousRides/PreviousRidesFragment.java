package com.moovapp.rider.main.previousRides;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.moovapp.rider.R;
import com.moovapp.rider.main.previousRides.rateDriver.RateDriverActivity;
import com.moovapp.rider.utils.LMTFragmentHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lijo Mathew Theckanal on 18-Jul-18.
 */

public class PreviousRidesFragment extends LMTFragmentHelper {

    @BindView(R.id.listView)
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.previous_rides_fragment, container, false);
        ButterKnife.bind(this, view);
        setDummyListView();
        return view;
    }

    private void setDummyListView() {
        String[] x = {"", "", "", "", ""};
        PreviousRidesListAdapter previousRidesListAdapter = new PreviousRidesListAdapter(getContext(), x);
        listView.setAdapter(previousRidesListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), RateDriverActivity.class);
                startActivity(intent);
            }
        });
    }
}
