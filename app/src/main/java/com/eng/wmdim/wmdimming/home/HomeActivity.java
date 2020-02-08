package com.eng.wmdim.wmdimming.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.eng.wmdim.wmdimming.R;
import com.eng.wmdim.wmdimming.places.PlacesFragment;
import com.eng.wmdim.wmdimming.settings.SettingsFragment;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView homeButtomNavigationView;
    private Fragment homeFragment;
    private FragmentManager fragmentManager;
    private PlacesFragment placesFragment;
    private SettingsFragment settingsFragment;
    private static int currentFragment;
    private static final String CURRENT_FRAGMENT_KEY = "current-fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeButtomNavigationView = (BottomNavigationView) findViewById(R.id.home_buttom_nav_view);
        homeButtomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        homeFragment = new HomeFragment();
        placesFragment = new PlacesFragment();
        settingsFragment = new SettingsFragment();
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState != null) {
            currentFragment = savedInstanceState.getInt(CURRENT_FRAGMENT_KEY);
            Log.e(CURRENT_FRAGMENT_KEY, ": " + currentFragment);
            switch (currentFragment) {
                case 1:
                    fragmentManager.beginTransaction().replace(R.id.home_fragment_container, homeFragment).commit();

                    break;
                case 2:
                    fragmentManager.beginTransaction().replace(R.id.home_fragment_container, placesFragment).commit();
                    break;
                case 3:
                    fragmentManager.beginTransaction().replace(R.id.home_fragment_container, settingsFragment).commit();

                    break;
                default:
                    fragmentManager.beginTransaction().replace(R.id.home_fragment_container, homeFragment).commit();


            }
        } else {
            fragmentManager.beginTransaction().add(R.id.home_fragment_container, homeFragment).commit();

        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_home:
                    currentFragment = 1;
                    if (homeFragment.isAdded()) {
                        fragmentManager.beginTransaction().replace(R.id.home_fragment_container, homeFragment).addToBackStack(null).commit();
                        fragmentManager.beginTransaction().show(homeFragment).commit();

                        if (!settingsFragment.isHidden()) {
                            fragmentManager.beginTransaction().hide(settingsFragment).commit();
                        }
                        if (!placesFragment.isHidden()) {
                            fragmentManager.beginTransaction().hide(placesFragment).commit();
                        }
                    } else {
                        fragmentManager.beginTransaction().add(R.id.home_fragment_container, homeFragment).addToBackStack(null).commit();
                        fragmentManager.beginTransaction().show(homeFragment).commit();
                        if (!settingsFragment.isHidden()) {
                            fragmentManager.beginTransaction().hide(settingsFragment).commit();
                        }
                        if (!placesFragment.isHidden()) {
                            fragmentManager.beginTransaction().hide(placesFragment).commit();
                        }

                    }
                    return true;
                case R.id.action_places:
                    currentFragment = 2;
                    if (placesFragment.isAdded()) {
                        if (fragmentManager == null || placesFragment == null) {
                            fragmentManager = getSupportFragmentManager();
                            placesFragment = new PlacesFragment();
                            fragmentManager.beginTransaction().replace(R.id.home_fragment_container, placesFragment).addToBackStack(null).commit();
                            fragmentManager.beginTransaction().show(placesFragment).commit();
                        } else {
                            fragmentManager.beginTransaction().replace(R.id.home_fragment_container, placesFragment).addToBackStack(null).commit();
                            fragmentManager.beginTransaction().show(placesFragment).commit();
                        }
                        if (!settingsFragment.isHidden()) {
                            fragmentManager.beginTransaction().hide(settingsFragment).commit();
                        }
                        if (!homeFragment.isHidden()) {
                            fragmentManager.beginTransaction().hide(homeFragment).commit();
                        }
                    } else {
                        if (fragmentManager == null || placesFragment == null) {
                            fragmentManager = getSupportFragmentManager();
                            placesFragment = new PlacesFragment();
                            fragmentManager.beginTransaction().add(R.id.home_fragment_container, placesFragment).commit();
                            fragmentManager.beginTransaction().show(placesFragment).commit();
                        } else {
                            fragmentManager.beginTransaction().add(R.id.home_fragment_container, placesFragment).commit();
                            fragmentManager.beginTransaction().show(placesFragment).commit();
                        }
                        if (!settingsFragment.isHidden()) {
                            fragmentManager.beginTransaction().hide(settingsFragment).commit();
                        }
                        if (!homeFragment.isHidden()) {
                            fragmentManager.beginTransaction().hide(homeFragment).commit();
                        }
                    }
                    Log.e("current_clicked_tab", currentFragment + "");

                    return true;
                case R.id.action_settings:
                    currentFragment = 3;
                    if (settingsFragment.isAdded()) {
                        if (fragmentManager == null || settingsFragment == null) {
                            fragmentManager = getSupportFragmentManager();
                            settingsFragment = new SettingsFragment();
                            fragmentManager.beginTransaction().replace(R.id.home_fragment_container, settingsFragment).addToBackStack(null).commit();
                            fragmentManager.beginTransaction().show(settingsFragment).commit();
                        } else {
                            fragmentManager.beginTransaction().replace(R.id.home_fragment_container, settingsFragment).addToBackStack(null).commit();
                            fragmentManager.beginTransaction().show(settingsFragment).commit();
                        }
                        if (!placesFragment.isHidden()) {
                            fragmentManager.beginTransaction().hide(placesFragment).commit();
                        }
                        if (!homeFragment.isHidden()) {
                            fragmentManager.beginTransaction().hide(homeFragment).commit();
                        }
                    } else {
                        if (fragmentManager == null || settingsFragment == null) {
                            fragmentManager = getSupportFragmentManager();
                            settingsFragment = new SettingsFragment();
                            fragmentManager.beginTransaction().add(R.id.home_fragment_container, settingsFragment).commit();
                            fragmentManager.beginTransaction().show(settingsFragment).commit();
                        } else {
                            fragmentManager.beginTransaction().add(R.id.home_fragment_container, settingsFragment).commit();
                            fragmentManager.beginTransaction().show(settingsFragment).commit();
                        }
                        if (!placesFragment.isHidden()) {
                            fragmentManager.beginTransaction().hide(placesFragment).commit();
                        }
                        if (!homeFragment.isHidden()) {
                            fragmentManager.beginTransaction().hide(homeFragment).commit();
                        }
                    }
                    Log.e("current_clicked_tab", currentFragment + "");

                    return true;

            }
            return false;
        }
    };


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_FRAGMENT_KEY, currentFragment);

    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
