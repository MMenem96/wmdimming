package com.eng.wmdim.wmdimming.settings;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eng.wmdim.wmdimming.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {


    private TextView tvChangeIPAddress;

    public SettingsFragment() {
        // Required empty public constructor
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        tvChangeIPAddress = (TextView) view.findViewById(R.id.tvChangeIPAddress);
        tvChangeIPAddress.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvChangeIPAddress:
                showIPAddressDialouge();
                break;
            default:
                return;

        }
    }

    private void showIPAddressDialouge() {

        try {
            IPAddressCustomDialog ipAddressCustomDialog = new IPAddressCustomDialog(this.getContext());
            ipAddressCustomDialog.setCancelable(false);
            ipAddressCustomDialog.show();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}


