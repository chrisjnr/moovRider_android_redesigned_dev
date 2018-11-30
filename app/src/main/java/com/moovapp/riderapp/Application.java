package com.moovapp.riderapp;


import android.content.Context;
import android.support.multidex.MultiDex;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Lijo Mathew Theckanal on 27-Jun-18.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Lato-Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

//    // Add multidex Code or other Application Class here
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}