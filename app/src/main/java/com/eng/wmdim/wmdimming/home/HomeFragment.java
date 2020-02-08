package com.eng.wmdim.wmdimming.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eng.wmdim.wmdimming.R;

import java.util.Calendar;


public class HomeFragment extends Fragment {

    private TextView tvDayState;
    private ImageView ivDayState;
    private TextView tvFragmentName;

    public HomeFragment() {
        // Required empty public constructor
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvDayState = (TextView) view.findViewById(R.id.tvDayState);
        ivDayState = (ImageView) view.findViewById(R.id.ivDayState);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setDayState();
    }

    private void setDayState() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            tvDayState.setText(R.string.good_morning);
            ivDayState.setImageResource(R.drawable.ic_sun);
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            tvDayState.setText(R.string.good_afternoon);
            ivDayState.setImageResource(R.drawable.ic_sun);
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            tvDayState.setText(R.string.good_evening);
            ivDayState.setImageResource(R.drawable.ic_moon);
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            tvDayState.setText(R.string.good_night);
            ivDayState.setImageResource(R.drawable.ic_moon);
        }

    }
}
