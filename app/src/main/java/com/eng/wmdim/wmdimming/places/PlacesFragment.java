package com.eng.wmdim.wmdimming.places;

import android.app.MediaRouteButton;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eng.wmdim.wmdimming.R;

public class PlacesFragment extends Fragment {

    private static final String CURRENT_PAGE = "current_tab";
    private TabLayout placesTab;
    private int currentTab;


    public PlacesFragment() {
        // Required empty public constructor
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("onCreateView", "placesFragment");

        View view = inflater.inflate(R.layout.fragment_places, container, false);
        placesTab = (TabLayout) view.findViewById(R.id.placesTabLayout);
        placesTab.addTab(placesTab.newTab().setText(R.string.rooms));
        placesTab.addTab(placesTab.newTab().setText(R.string.outdoors));
        placesTab.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager placesPager = (ViewPager) view.findViewById(R.id.placesPager);
        placesPager.setOffscreenPageLimit(2);
        final PlacesPageAdapter placesPageAdapter = new PlacesPageAdapter(getChildFragmentManager(), placesTab.getTabCount());
        placesPager.setAdapter(placesPageAdapter);
        placesPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(placesTab));

        placesTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                placesPager.setCurrentItem(tab.getPosition());
                currentTab = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        if (savedInstanceState != null) {
            placesPager.setCurrentItem(savedInstanceState.getInt(CURRENT_PAGE));
        }
        return view;
    }


}
