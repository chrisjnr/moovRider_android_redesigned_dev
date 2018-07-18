package com.moovapp.rider.utils.myGlobalFunctions;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Lijo Mathew Theckanal on 25-12-2017.
 */

public class DpToPx {
    public static int dp2px(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
