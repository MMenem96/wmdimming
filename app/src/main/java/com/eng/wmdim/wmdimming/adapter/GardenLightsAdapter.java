package com.eng.wmdim.wmdimming.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eng.wmdim.wmdimming.R;
import com.eng.wmdim.wmdimming.data.Light;
import com.eng.wmdim.wmdimming.networkutils.NetworkConnectionHelper;

import java.util.List;

public class GardenLightsAdapter extends RecyclerView.Adapter<GardenLightsAdapter.AppViewHolder> {
    private Context context;
    private List<Light> lightList;
    NetworkConnectionHelper networkConnectionHelper;
    RecyclerView rvLights;

    public GardenLightsAdapter(Context context, NetworkConnectionHelper networkConnectionHelper) {
        this.context = context;
        this.networkConnectionHelper = networkConnectionHelper;
    }


    @NonNull
    @Override
    public GardenLightsAdapter.AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.outdoor_light_item, parent, false);
        return new AppViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final GardenLightsAdapter.AppViewHolder holder, final int position) {

        int count = position + 1;
        holder.tvLightName.setText(context.getString(R.string.light) + " " + count);
        int lightStatusIntValue = lightList.get(position).getLightStatus();
        if (lightStatusIntValue == 1) {
            holder.imgBtnTurnLights.setImageResource(R.drawable.ic_on);
            holder.imgBtnTurnLights.setTag(R.drawable.ic_on);
        } else {
            holder.imgBtnTurnLights.setImageResource(R.drawable.ic_off);
            holder.imgBtnTurnLights.setTag(R.drawable.ic_off);


        }

        holder.imgBtnTurnLights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer resource = (Integer) holder.imgBtnTurnLights.getTag();
                if (resource == R.drawable.ic_on) {
                    networkConnectionHelper.changeLightStatus(true, position, lightList, holder.imgBtnTurnLights);
                } else if (resource == R.drawable.ic_off) {
                    networkConnectionHelper.changeLightStatus(false, position, lightList, holder.imgBtnTurnLights);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lightList.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLightName;
        private LinearLayout llLightGarden;
        private ImageButton imgBtnTurnLights;


        public AppViewHolder(View itemView) {
            super(itemView);
            tvLightName = (TextView) itemView.findViewById(R.id.tvGardennLightName);
            llLightGarden = (LinearLayout) itemView.findViewById(R.id.llGardenLights);
            imgBtnTurnLights = (ImageButton) itemView.findViewById(R.id.imgBtnTurnLights);

        }
    }

    public void updateAdapter(List<Light> lightList) {
        this.lightList = lightList;
        notifyDataSetChanged();
    }

}
