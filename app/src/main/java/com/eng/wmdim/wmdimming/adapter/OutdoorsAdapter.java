package com.eng.wmdim.wmdimming.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eng.wmdim.wmdimming.R;
import com.eng.wmdim.wmdimming.data.Outdoors;
import com.eng.wmdim.wmdimming.outdoors.OutdoorActivity;

import java.util.List;

public class OutdoorsAdapter extends RecyclerView.Adapter<OutdoorsAdapter.AppViewHolder> {
    private Context context;
    private List<Outdoors> outdoorsList;
    private static final String OUTDOOR_NAME = "outdoorName";

    public OutdoorsAdapter(Context context, List<Outdoors> outdoorsList) {
        this.context = context;
        this.outdoorsList = outdoorsList;
    }

    @NonNull
    @Override
    public OutdoorsAdapter.AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.outdoor_garden_item, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OutdoorsAdapter.AppViewHolder holder, final int position) {

        holder.tvOutdoorName.setText(outdoorsList.get(position).getOutdoorName());
        if (outdoorsList.get(position).getOutdoorName().equals("Garden")) {
            holder.ivOutdoor.setImageResource(R.drawable.ic_garden);

        } else {
            holder.ivOutdoor.setImageResource(R.drawable.ic_store);

        }

        holder.llGarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outdoorActivity = new Intent(context, OutdoorActivity.class);
                outdoorActivity.putExtra(OUTDOOR_NAME, outdoorsList.get(position).getOutdoorName());
                context.startActivity(outdoorActivity);
            }
        });
        Log.e("gardenName", outdoorsList.get(position).getOutdoorName());

    }

    @Override
    public int getItemCount() {
        return outdoorsList.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        private TextView tvOutdoorName;
        private ImageView ivOutdoor;
        private LinearLayout llGarden;

        public AppViewHolder(View itemView) {
            super(itemView);
            tvOutdoorName = (TextView) itemView.findViewById(R.id.tvOutdoorName);
            ivOutdoor = (ImageView) itemView.findViewById(R.id.outdoorImage);
            llGarden = (LinearLayout) itemView.findViewById(R.id.llGardenItem);
        }
    }
}
