package com.eng.wmdim.wmdimming.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.eng.wmdim.wmdimming.R;

public class SharedPrefs {

    private Context context;

    public SharedPrefs(Context context) {
        this.context = context;
    }

    synchronized public void setIPAddress(String ipAddress) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString(context.getString(R.string.IP_ADDRESS_KEY), ipAddress).apply();
    }

    public String getIPAddress() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(context.getString(R.string.IP_ADDRESS_KEY), context.getString(R.string.IP_ADDRESS_HINT));
    }
}
