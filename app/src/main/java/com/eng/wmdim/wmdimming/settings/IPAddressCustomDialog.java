package com.eng.wmdim.wmdimming.settings;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eng.wmdim.wmdimming.R;
import com.eng.wmdim.wmdimming.home.HomeActivity;
import com.eng.wmdim.wmdimming.utils.SharedPrefs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPAddressCustomDialog extends Dialog implements View.OnClickListener, TextWatcher {
    private static final Pattern IP_ADDRESS
            = Pattern.compile("http://" +
            "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
            + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
            + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
            + "|[1-9][0-9]|[0-9]))");

    private final SharedPrefs sharedPrefs;
    private EditText etAdminPassword, etIPAddress;
    private Button btnSave, btnCancel;
    private Context context;
    private View view;

    public IPAddressCustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ip_address_dialog);
        sharedPrefs = new SharedPrefs(context);
        etAdminPassword = (EditText) findViewById(R.id.etAdminPassword);
        etAdminPassword.addTextChangedListener(this);
        etIPAddress = (EditText) findViewById(R.id.etIPAddress);
        etIPAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String ip = s.toString();
                Matcher matcher = IP_ADDRESS.matcher(ip);
                if (ip.equals(sharedPrefs.getIPAddress()) || !matcher.matches()) {
                    btnSave.setEnabled(false);
                } else {
                    btnSave.setEnabled(true);
                }
            }
        });
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                saveIpAddressToSharefPrefs();
                break;
            case R.id.btnCancel:
                this.dismiss();
                break;
            default:
                return;
        }

    }

    private void saveIpAddressToSharefPrefs() {
        try {
            String ipAddress = etIPAddress.getText().toString();
            if (!ipAddress.equals("")) {
                sharedPrefs.setIPAddress(ipAddress);
                Toast.makeText(context, "Saved successfully", Toast.LENGTH_SHORT).show();
                this.cancel();
                reload();

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void reload() {
        HomeActivity homeActivity = (HomeActivity) context;
        Intent intent = homeActivity.getIntent();
        homeActivity.overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        homeActivity.finish();
        homeActivity.overridePendingTransition(0, 0);
        homeActivity.startActivity(intent);
    }

    private void textWatcher(View view) {
        this.view = view;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        try {
            String adminPassword = s.toString();
            if (adminPassword.equals(context.getString(R.string.admin_validation_password))) {
                etIPAddress.setEnabled(true);
                etIPAddress.setText(sharedPrefs.getIPAddress());
            } else {
                etIPAddress.setEnabled(false);
                etIPAddress.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
