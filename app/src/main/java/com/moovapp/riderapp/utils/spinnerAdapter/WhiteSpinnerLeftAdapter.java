package com.moovapp.riderapp.utils.spinnerAdapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.moovapp.riderapp.R;

import java.util.List;

/**
 * Created by Lijo Mathew Theckanal on 28-Jun-18.
 */

public class WhiteSpinnerLeftAdapter extends ArrayAdapter<String> {

    LayoutInflater flater;

    public WhiteSpinnerLeftAdapter(Activity context, int resouceId, int textviewId, List<String> list){
        super(context,resouceId,textviewId, list);
        flater = context.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String rowItem = getItem(position);
        View rowview = flater.inflate(R.layout.white_spinner_left_list_item,null,true);
        TextView txtTitle = (TextView) rowview.findViewById(R.id.title);
        txtTitle.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Bold.ttf"));
        txtTitle.setText(rowItem);

        return rowview;
    }
}
