package com.eng.wmdim.wmdimming.outdoors;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.eng.wmdim.wmdimming.R;
import com.eng.wmdim.wmdimming.places.PlacesPageAdapter;

public class OutdoorActivity extends AppCompatActivity {

    private static final String OUTDOOR_NAME = "outdoorName";
    private TabLayout outdoorsTab;
    private TextView tvOutdoorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outdoor);
        tvOutdoorName = (TextView) findViewById(R.id.tvOutdoorName);
        tvOutdoorName.setText(getIntent().getExtras().getString(OUTDOOR_NAME));
        outdoorsTab = (TabLayout) findViewById(R.id.outdoorsTabLayout);
        outdoorsTab.addTab(outdoorsTab.newTab().setText(R.string.lights));
        outdoorsTab.addTab(outdoorsTab.newTab().setText(R.string.gate));
        outdoorsTab.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager outdoorPager = (ViewPager) findViewById(R.id.outdoorPager);
        final OutdoorPageAdapter placesPageAdapter = new OutdoorPageAdapter(getSupportFragmentManager(), outdoorsTab.getTabCount());
        outdoorPager.setAdapter(placesPageAdapter);
        outdoorPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(outdoorsTab));
        outdoorsTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                outdoorPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
