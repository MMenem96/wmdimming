package com.eng.wmdim.wmdimming.places;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PlacesPageAdapter extends FragmentStatePagerAdapter {
    int noOfTabs;

    public PlacesPageAdapter(FragmentManager fm, int noOfTabs) {
        super(fm);
        this.noOfTabs = noOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new RoomTab();
            case 1:
                return new OutdoorTab();
            default:
                return new RoomTab();
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
