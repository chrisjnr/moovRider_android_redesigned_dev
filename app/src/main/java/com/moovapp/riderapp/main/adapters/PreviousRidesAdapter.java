package com.moovapp.riderapp.main.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moovapp.riderapp.R;
import com.moovapp.riderapp.utils.retrofit.responseModels.ViewPreviousRidesResponseModel;

import java.util.List;

/**
 * Created by Manuel Chris-Ogar on 2/14/2019.
 */
public class PreviousRidesAdapter extends RecyclerView.Adapter<PreviousRidesAdapter.PreviousRidesViewHolder> {

    List<ViewPreviousRidesResponseModel.DataEntity> objects;


    public PreviousRidesAdapter(List<ViewPreviousRidesResponseModel.DataEntity> object){
        this.objects = object;
    }
    @NonNull
    @Override
    public PreviousRidesAdapter.PreviousRidesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from((parent).getContext()).inflate(R.layout.previous_rides_viewholder, parent,false);
        return new PreviousRidesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousRidesAdapter.PreviousRidesViewHolder holder, int position) {
        Log.d("ride", "onBindViewHolder: "+objects.size());
        ViewPreviousRidesResponseModel.DataEntity currentTrip = objects.get(position);
        holder.previousRideDestination.setText(currentTrip.getRide_to());
        Log.d("ride", "onBindViewHolder: "+currentTrip.getRide_booked_on_date());
        holder.previousRideLocation.setText(currentTrip.getRide_from());
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class PreviousRidesViewHolder extends RecyclerView.ViewHolder {
        public  TextView prevRideDateNumber;
        public TextView prevRideMonth;
        public  TextView  prevRideYear;
        public TextView  previousRideLocation;
        public TextView  previousRideDestination;
        public TextView  previousRideTime;

        public PreviousRidesViewHolder(View itemView) {
            super(itemView);
            prevRideDateNumber = itemView.findViewById(R.id.prevRideDateNumber);
            prevRideMonth = itemView.findViewById(R.id.prevRideMonth);
            prevRideYear = itemView.findViewById(R.id.prevRideYear);
            previousRideLocation = itemView.findViewById(R.id.previousRideLocation);
            previousRideDestination = itemView.findViewById(R.id.previousRideDestination);
            previousRideTime = itemView.findViewById(R.id.previousRideTime);

        }
    }
}
