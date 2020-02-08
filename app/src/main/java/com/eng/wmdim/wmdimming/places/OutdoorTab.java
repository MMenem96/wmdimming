package com.eng.wmdim.wmdimming.places;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.eng.wmdim.wmdimming.R;
import com.eng.wmdim.wmdimming.adapter.OutdoorsAdapter;
import com.eng.wmdim.wmdimming.networkutils.NetworkConnectionHelper;

public class OutdoorTab extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rvOutdoorsList;
    private OutdoorsAdapter outdoorsAdapter;
    private SwipeRefreshLayout outDoorSwipRefresh;
    private NetworkConnectionHelper networkConnectionHelper;
    private ImageView ivCouldntLoadData;
    private ProgressBar loadOutdoorStatus;

    public OutdoorTab() {
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_outdoor_tab, container, false);
        loadOutdoorStatus = (ProgressBar) view.findViewById(R.id.loadOutdoorStatus);
        ivCouldntLoadData = (ImageView) view.findViewById(R.id.ivCouldntLoadData);
        rvOutdoorsList = (RecyclerView) view.findViewById(R.id.rvOutdoors);
        outDoorSwipRefresh = (SwipeRefreshLayout) view.findViewById(R.id.outDoorSwipRefresh);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvOutdoorsList.setLayoutManager(gridLayoutManager);
        networkConnectionHelper = new NetworkConnectionHelper(rvOutdoorsList, getContext());
        networkConnectionHelper.setCouldntLoadData(ivCouldntLoadData);
        networkConnectionHelper.setProgressBar(loadOutdoorStatus);
        networkConnectionHelper.getOutdoors();
        outDoorSwipRefresh.setOnRefreshListener(this);
        outDoorSwipRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        return view;
    }

    @Override
    public void onRefresh() {
        networkConnectionHelper.refreshOutdoors(outDoorSwipRefresh);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
