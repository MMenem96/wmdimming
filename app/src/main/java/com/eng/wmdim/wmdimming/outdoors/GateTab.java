package com.eng.wmdim.wmdimming.outdoors;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.eng.wmdim.wmdimming.R;
import com.eng.wmdim.wmdimming.networkutils.NetworkConnectionHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class GateTab extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private ImageView ivGate;
    private ImageView ivLock;
    private ImageView ivUnLock;
    private ProgressBar progressBar;
    private NetworkConnectionHelper networkConnectionHelper;
    private ImageView ivGateStatusIndicator;
    private SwipeRefreshLayout gateSwipRefresher;

    public GateTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gate_tab, container, false);
        ivGateStatusIndicator = (ImageView) view.findViewById(R.id.ivGateStatus);
        gateSwipRefresher = (SwipeRefreshLayout) view.findViewById(R.id.gateSwipRefresher);
        gateSwipRefresher.setOnRefreshListener(this);
        progressBar = (ProgressBar) view.findViewById(R.id.loadGateStatus);
        networkConnectionHelper = new NetworkConnectionHelper(getContext());
        networkConnectionHelper.setProgressBar(progressBar);
        networkConnectionHelper.setGateStatusIndicator(ivGateStatusIndicator);
        networkConnectionHelper.setSwipRefresher(gateSwipRefresher);
        ivGate = (ImageView) view.findViewById(R.id.ivGate);
        ivUnLock = (ImageView) view.findViewById(R.id.ivUnLock);
        ivUnLock.setEnabled(false);
        ivUnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkConnectionHelper.changeCurrentGateStatus(1, ivGate, ivLock, ivUnLock);
            }
        });
        ivLock = (ImageView) view.findViewById(R.id.ivLock);
        ivLock.setEnabled(false);
        ivLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkConnectionHelper.changeCurrentGateStatus(0, ivGate, ivLock, ivUnLock);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        networkConnectionHelper.getCurrentGateStatus(ivGate, ivLock, ivUnLock);

    }

    @Override
    public void onRefresh() {
        networkConnectionHelper.getCurrentGateStatus(ivGate, ivLock, ivUnLock);

    }
}
