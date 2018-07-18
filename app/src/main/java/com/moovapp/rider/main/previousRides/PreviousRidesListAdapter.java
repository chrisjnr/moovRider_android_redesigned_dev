package com.moovapp.rider.main.previousRides;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.moovapp.rider.R;

import java.util.List;


/**
 * Created by Lijo Mathew Theckanal on 18-Jul-18.
 */
public class PreviousRidesListAdapter extends ArrayAdapter<String> {

    private static class ViewHolder {
        public final LinearLayout mainLayout;
        public final TextView tvFrom;
        public final TextView tvTo;
        public final TextView tvDate;
        public final TextView tvTime;
        public final TextView tvSeats;
        public final TextView tvAmount;
        public final TextView tvStatus;

        private ViewHolder(LinearLayout mainLayout, TextView tvFrom, TextView tvTo, TextView tvDate, TextView tvTime, TextView tvSeats, TextView tvAmount, TextView tvStatus) {
            this.mainLayout = mainLayout;
            this.tvFrom = tvFrom;
            this.tvTo = tvTo;
            this.tvDate = tvDate;
            this.tvTime = tvTime;
            this.tvSeats = tvSeats;
            this.tvAmount = tvAmount;
            this.tvStatus = tvStatus;
        }

        public static ViewHolder create(LinearLayout mainLayout) {
            TextView tvFrom = (TextView) mainLayout.findViewById(R.id.tvFrom);
            TextView tvTo = (TextView) mainLayout.findViewById(R.id.tvTo);
            TextView tvDate = (TextView) mainLayout.findViewById(R.id.tvDate);
            TextView tvTime = (TextView) mainLayout.findViewById(R.id.tvTime);
            TextView tvSeats = (TextView) mainLayout.findViewById(R.id.tvSeats);
            TextView tvAmount = (TextView) mainLayout.findViewById(R.id.tvAmount);
            TextView tvStatus = (TextView) mainLayout.findViewById(R.id.tvStatus);
            return new ViewHolder(mainLayout, tvFrom, tvTo, tvDate, tvTime, tvSeats, tvAmount, tvStatus);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.previous_rides_list_item, parent, false);
            vh = ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final String item = getItem(position);

        vh.tvFrom.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Bold.ttf"));
        vh.tvTo.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Bold.ttf"));
        vh.tvDate.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Bold.ttf"));
        vh.tvTime.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Bold.ttf"));
        vh.tvSeats.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Bold.ttf"));
        vh.tvAmount.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Bold.ttf"));
        vh.tvStatus.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Bold.ttf"));

        return vh.mainLayout;
    }

    private LayoutInflater mInflater;
    private Context context;

    // Constructors
    public PreviousRidesListAdapter(Context context, List<String> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public PreviousRidesListAdapter(Context context, String[] objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }
}