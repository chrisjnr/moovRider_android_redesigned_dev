package com.moovapp.riderapp.main.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numTabs;
    public PagerAdapter(FragmentManager fm, int noOfTabs) {
        super(fm);
        this.numTabs = noOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new SignIn();
            case 1: return new SignUp();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
