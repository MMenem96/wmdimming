package com.eng.wmdim.wmdimming.outdoors;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class OutdoorPageAdapter extends FragmentStatePagerAdapter {
    int noOfTabs;

    public OutdoorPageAdapter(FragmentManager fm, int noOfTabs) {
        super(fm);
        this.noOfTabs = noOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new LightsTab();
            case 1:
                return new GateTab();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
