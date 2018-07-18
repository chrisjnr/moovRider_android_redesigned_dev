package com.moovapp.rider.main.paymentHistory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.moovapp.rider.R;
import com.moovapp.rider.main.previousRides.PreviousRidesListAdapter;
import com.moovapp.rider.utils.LMTFragmentHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lijo Mathew Theckanal on 18-Jul-18.
 */

public class PaymentHistoryFragment extends LMTFragmentHelper {

    @BindView(R.id.listView)
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_history_fragment, container, false);
        ButterKnife.bind(this, view);
        setDummyListView();
        return view;
    }

    private void setDummyListView() {
        String[] x = {"", "", "", ""};
        PaymentHistoryListAdapter paymentHistoryListAdapter = new PaymentHistoryListAdapter(getContext(), x);
        listView.setAdapter(paymentHistoryListAdapter);
    }
}
