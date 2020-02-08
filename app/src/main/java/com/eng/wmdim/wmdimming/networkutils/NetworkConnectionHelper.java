package com.eng.wmdim.wmdimming.networkutils;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.eng.wmdim.wmdimming.R;
import com.eng.wmdim.wmdimming.adapter.GardenLightsAdapter;
import com.eng.wmdim.wmdimming.adapter.OutdoorsAdapter;
import com.eng.wmdim.wmdimming.data.GardenGateNewStateResponse;
import com.eng.wmdim.wmdimming.data.GardenGateStateResponse;
import com.eng.wmdim.wmdimming.data.GardenLightStateResponse;
import com.eng.wmdim.wmdimming.data.Light;
import com.eng.wmdim.wmdimming.data.Outdoors;
import com.eng.wmdim.wmdimming.outdoors.OutdoorActivity;
import com.eng.wmdim.wmdimming.utils.SharedPrefs;

import org.androidannotations.annotations.Background;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkConnectionHelper {

    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private Context context;
    private Adapter adapter;
    private ApiInterface apiInterface;
    private static String BASE_URL = "http://192.168.1.18";
    private OutdoorsAdapter outdoorsAdapter;
    private GardenLightsAdapter gardenLightsAdapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipRefresher;
    private ImageView couldntLoadData;
    private OkHttpClient okHttpClient;
    int cacheSize = 10 * 1024 * 1024;
    private FragmentActivity activity;
    private ImageView gateStatusIndicator;

    public NetworkConnectionHelper(Context context) {
        this.context = context;
        SharedPrefs sharedPrefs = new SharedPrefs(context);
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        okHttpClient = new OkHttpClient.Builder().cache(cache).build();
        BASE_URL = sharedPrefs.getIPAddress();
        Log.e("BaseURL", BASE_URL);
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public NetworkConnectionHelper(RecyclerView recyclerView, Context context) {
        this.recyclerView = recyclerView;
        this.context = context;
        SharedPrefs sharedPrefs = new SharedPrefs(context);
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        okHttpClient = new OkHttpClient.Builder().cache(cache).build();
        BASE_URL = sharedPrefs.getIPAddress();
        Log.e("BaseURL", BASE_URL);
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        apiInterface = retrofit.create(ApiInterface.class);

    }


    public void getOutdoors() {
        progressBar.setVisibility(View.VISIBLE);
        Call<List<Outdoors>> call = apiInterface.getAllOutdoors();
        call.enqueue(new Callback<List<Outdoors>>() {
            @Override
            public void onResponse(Call<List<Outdoors>> call, Response<List<Outdoors>> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    List<Outdoors> outdoorsList = response.body();
                    outdoorsAdapter = new OutdoorsAdapter(context, outdoorsList);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(outdoorsAdapter);
                    if (couldntLoadData.getVisibility() == View.VISIBLE) {
                        couldntLoadData.setVisibility(View.GONE);
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    if (couldntLoadData.getVisibility() == View.GONE) {
                        couldntLoadData.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Outdoors>> call, Throwable t) {
                t.printStackTrace();
                progressBar.setVisibility(View.GONE);
                if (couldntLoadData.getVisibility() == View.GONE) {
                    couldntLoadData.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void refreshOutdoors(final SwipeRefreshLayout outDoorSwipRefresh) {
        Call<List<Outdoors>> call = apiInterface.getAllOutdoors();
        call.enqueue(new Callback<List<Outdoors>>() {
            @Override
            public void onResponse(Call<List<Outdoors>> call, Response<List<Outdoors>> response) {
                if (response.isSuccessful()) {
                    List<Outdoors> outdoorsList = response.body();
                    outdoorsAdapter = new OutdoorsAdapter(context, outdoorsList);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(outdoorsAdapter);
                    outDoorSwipRefresh.setRefreshing(false);
                    if (couldntLoadData.getVisibility() == View.VISIBLE) {
                        couldntLoadData.setVisibility(View.GONE);
                    }
                } else {
                    if (couldntLoadData.getVisibility() == View.GONE) {
                        couldntLoadData.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Outdoors>> call, Throwable t) {
                t.printStackTrace();
                outDoorSwipRefresh.setRefreshing(false);
                if (couldntLoadData.getVisibility() == View.GONE) {
                    couldntLoadData.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void getAllGardenLights() {
        progressBar.setVisibility(View.VISIBLE);

        Call<List<Light>> call = apiInterface.getAllGardenLights();
        call.enqueue(new Callback<List<Light>>() {
            @Override
            public void onResponse(Call<List<Light>> call, Response<List<Light>> response) {
                if (response.isSuccessful()) {
                    List<Light> lightList = response.body();
                    gardenLightsAdapter.updateAdapter(lightList);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(gardenLightsAdapter);
                    progressBar.setVisibility(View.GONE);
                    if (couldntLoadData.getVisibility() == View.VISIBLE) {
                        couldntLoadData.setVisibility(View.GONE);
                    }
                    if (swipRefresher.isRefreshing()) {
                        swipRefresher.setRefreshing(false);

                    }
                } else {
                    List<Light> lightList = new ArrayList<>();
                    progressBar.setVisibility(View.GONE);
                    if (couldntLoadData.getVisibility() == View.GONE) {
                        couldntLoadData.setVisibility(View.VISIBLE);
                        gardenLightsAdapter.updateAdapter(lightList);
                    }
                    if (swipRefresher.isRefreshing()) {
                        swipRefresher.setRefreshing(false);

                    }
                }


            }

            @Override
            public void onFailure(Call<List<Light>> call, Throwable t) {
                List<Light> lightList = new ArrayList<>();
                t.printStackTrace();
                progressBar.setVisibility(View.INVISIBLE);
                if (couldntLoadData.getVisibility() == View.GONE) {
                    couldntLoadData.setVisibility(View.VISIBLE);
                    gardenLightsAdapter.updateAdapter(lightList);
                }
                if (swipRefresher.isRefreshing()) {
                    swipRefresher.setRefreshing(false);

                }
            }
        });
    }

    public void refreshAllGardenLights() {
        Call<List<Light>> call = apiInterface.getAllGardenLights();
        call.enqueue(new Callback<List<Light>>() {
            @Override
            public void onResponse(Call<List<Light>> call, Response<List<Light>> response) {
                if (response.isSuccessful()) {
                    List<Light> lightList = response.body();
                    gardenLightsAdapter.updateAdapter(lightList);
                    Log.e("lightListNew", lightList.get(1) + "");
                    swipRefresher.setRefreshing(false);
                    if (couldntLoadData.getVisibility() == View.VISIBLE) {
                        couldntLoadData.setVisibility(View.GONE);
                    }

                } else {
                    Log.e("lightListNew", "error");
                    swipRefresher.setRefreshing(false);
                    if (couldntLoadData.getVisibility() == View.GONE) {
                        couldntLoadData.setVisibility(View.VISIBLE);
                    }

                }

            }

            @Override
            public void onFailure(Call<List<Light>> call, Throwable t) {
                t.printStackTrace();
                //    outDoorLightsSwipRefresh.setRefreshing(false);
                List<Light> emptyLights = new ArrayList<Light>();
                swipRefresher.setRefreshing(false);
                if (couldntLoadData.getVisibility() == View.GONE) {
                    couldntLoadData.setVisibility(View.VISIBLE);
                    gardenLightsAdapter.updateAdapter(emptyLights);
                }
            }
        });
    }

    public void changeLightStatus(final boolean b, final int position, final List<Light> lightList, final ImageButton imageButton) {
        progressBar.setVisibility(View.VISIBLE);
        final OutdoorActivity outdoorActivity = (OutdoorActivity) context;
        outdoorActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Log.e("networkAdapterLightPos", lightList.get(position).getLightId() + "");
        final Integer lightId = lightList.get(position).getLightId();
        Call<GardenLightStateResponse> call = apiInterface.changeLightsStatus(lightId);

        call.enqueue(new Callback<GardenLightStateResponse>() {
            @Override
            public void onResponse(Call<GardenLightStateResponse> call, Response<GardenLightStateResponse> response) {
                try {

                    if (response.body().getLightStatus().equals("1")) {
                        imageButton.setImageResource(R.drawable.ic_on);
                        Log.e("LighuccesResponse", response.code() + "");
                        refreshAllGardenLights();
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(context, "Light " + lightId + " is turned on!", Toast.LENGTH_SHORT).show();
                        outdoorActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    } else if (response.body().getLightStatus().equals("0")) {
                        imageButton.setImageResource(R.drawable.ic_off);
                        Log.e("LightStatusCode", response.code() + "");
                        refreshAllGardenLights();
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(context, "Light " + lightId + " is turned off!", Toast.LENGTH_SHORT).show();
                        outdoorActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    progressBar.setVisibility(View.GONE);
                    outdoorActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                }

            }

            @Override
            public void onFailure(Call<GardenLightStateResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                outdoorActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (lightList.get(position).getLightStatus() == 1) {
                    imageButton.setImageResource(R.drawable.ic_on);


                } else if (lightList.get(position).getLightStatus() == 0) {
                    imageButton.setImageResource(R.drawable.ic_off);

                }

            }
        });

    }


    @Background
    public void getCurrentGateStatus(final ImageView ivGate, final ImageView ivLock, final ImageView ivUnLock) {
        progressBar.setVisibility(View.VISIBLE);
        Call<GardenGateStateResponse> call = apiInterface.getGateStatus();
        call.enqueue(new Callback<GardenGateStateResponse>() {
            @Override
            public void onResponse(Call<GardenGateStateResponse> call, Response<GardenGateStateResponse> response) {
                if (swipRefresher.isRefreshing()) {
                    swipRefresher.setRefreshing(false);

                }
                if (response.isSuccessful() && response.body() != null) {
                    ivLock.setEnabled(true);
                    ivUnLock.setEnabled(true);
                    gateStatusIndicator.setImageResource(R.drawable.ic_indicator_on);
                    String status = response.body().getGateStatus();
                    if (status.equals("on")) {
                        ivGate.setImageResource(R.drawable.gate_open);
                        ivLock.setImageResource(R.drawable.ic_locked);
                        ivUnLock.setImageResource(R.drawable.ic_unlocked_filled);
                        progressBar.setVisibility(View.INVISIBLE);
                    } else if (status.equals("off")) {
                        ivGate.setImageResource(R.drawable.gate_closed);
                        ivLock.setImageResource(R.drawable.ic_locked_filled);
                        ivUnLock.setImageResource(R.drawable.ic_unlocked);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                } else {
                    ivLock.setEnabled(false);
                    ivUnLock.setEnabled(false);
                    gateStatusIndicator.setImageResource(R.drawable.ic_indicator_off);
                }

            }

            @Override
            public void onFailure(Call<GardenGateStateResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                gateStatusIndicator.setImageResource(R.drawable.ic_indicator_off);
                if (swipRefresher.isRefreshing()) {
                    swipRefresher.setRefreshing(false);

                }
                ivLock.setEnabled(false);
                ivUnLock.setEnabled(false);

            }
        });


    }

    @Background
    public void changeCurrentGateStatus(Integer newStatusCode, final ImageView ivGate, final ImageView ivLock, final ImageView ivUnLock) {
        progressBar.setVisibility(View.VISIBLE);
        Call<GardenGateNewStateResponse> call = apiInterface.changeGateStatus(newStatusCode);
        call.enqueue(new Callback<GardenGateNewStateResponse>() {
            @Override
            public void onResponse(Call<GardenGateNewStateResponse> call, Response<GardenGateNewStateResponse> response) {
                try {

                    if (response.body().getGate().equals("1")) {
                        Log.e("returnedStatusCode", response.body().getGate() + "");
                        ivGate.setImageResource(R.drawable.gate_open);
                        ivLock.setImageResource(R.drawable.ic_locked);
                        ivUnLock.setImageResource(R.drawable.ic_unlocked_filled);
                        progressBar.setVisibility(View.INVISIBLE);

                    } else if (response.body().getGate().equals("0")) {
                        Log.e("returnedStatusCode", response.body().getGate() + "");
                        ivGate.setImageResource(R.drawable.gate_closed);
                        ivLock.setImageResource(R.drawable.ic_locked_filled);
                        ivUnLock.setImageResource(R.drawable.ic_unlocked);
                        progressBar.setVisibility(View.INVISIBLE);

                    } else {
                        Log.e("returnedStatusCode", response.body().getGate() + "");
                        progressBar.setVisibility(View.INVISIBLE);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    progressBar.setVisibility(View.INVISIBLE);

                }

            }

            @Override
            public void onFailure(Call<GardenGateNewStateResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                gateStatusIndicator.setImageResource(R.drawable.ic_indicator_off);

                ivLock.setEnabled(false);
                ivUnLock.setEnabled(false);
            }
        });

    }


    public void setLightsAdapter(GardenLightsAdapter lightsAdapter) {
        this.gardenLightsAdapter = lightsAdapter;
    }


    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void setSwipRefresher(SwipeRefreshLayout swipRefresher) {
        this.swipRefresher = swipRefresher;
    }

    public void setCouldntLoadData(ImageView couldntLoadData) {
        this.couldntLoadData = couldntLoadData;
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    public void setGateStatusIndicator(ImageView gateStatusIndicator) {
        this.gateStatusIndicator = gateStatusIndicator;
    }


    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }
}
