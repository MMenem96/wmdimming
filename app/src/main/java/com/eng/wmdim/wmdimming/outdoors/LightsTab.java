package com.eng.wmdim.wmdimming.outdoors;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.eng.wmdim.wmdimming.R;
import com.eng.wmdim.wmdimming.adapter.GardenLightsAdapter;
import com.eng.wmdim.wmdimming.networkutils.NetworkConnectionHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class LightsTab extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rvGardenLights;
    private SwipeRefreshLayout outDoorLightsSwipRefresh;
    private NetworkConnectionHelper networkConnectionHelper;
    private ProgressBar loadLightsStatus;
    private ImageView ivCouldntLoadData;

    public LightsTab() {
        // Required empty public constructor
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lights_tab, container, false);
        outDoorLightsSwipRefresh = (SwipeRefreshLayout) view.findViewById(R.id.outDoorLightsSwipRefresh);
        ivCouldntLoadData = (ImageView) view.findViewById(R.id.ivCouldntLoadData);
        loadLightsStatus = (ProgressBar) view.findViewById(R.id.loadLightsStatus);
        rvGardenLights = (RecyclerView) view.findViewById(R.id.rvGardenLights);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvGardenLights.setLayoutManager(gridLayoutManager);
        networkConnectionHelper = new NetworkConnectionHelper(rvGardenLights, getContext());
        GardenLightsAdapter gardenLightsAdapter = new GardenLightsAdapter(getContext(), networkConnectionHelper);
        networkConnectionHelper.setLightsAdapter(gardenLightsAdapter);
        networkConnectionHelper.setProgressBar(loadLightsStatus);
        networkConnectionHelper.setCouldntLoadData(ivCouldntLoadData);
        networkConnectionHelper.setSwipRefresher(outDoorLightsSwipRefresh);
        networkConnectionHelper.setActivity(getActivity());
        outDoorLightsSwipRefresh.setOnRefreshListener(this);
        outDoorLightsSwipRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        return view;
    }

    @Override
    public void onRefresh() {
        int itemCount = 0;
        try {

            itemCount = rvGardenLights.getAdapter().getItemCount();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if (itemCount != 0) {
            networkConnectionHelper.refreshAllGardenLights();
        } else {
            networkConnectionHelper.getAllGardenLights();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        networkConnectionHelper.getAllGardenLights();
    }
}
