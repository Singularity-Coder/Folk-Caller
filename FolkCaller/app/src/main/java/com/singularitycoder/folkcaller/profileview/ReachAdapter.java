package com.singularitycoder.folkcaller.profileview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.singularitycoder.folkcaller.BulkSmsActivity;
import com.singularitycoder.folkcaller.R;

import java.util.ArrayList;

public class ReachAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ProfileView.StatsOrActionsModel> reachList;
    Context context;

    public ReachAdapter(ArrayList<ProfileView.StatsOrActionsModel> reachList, Context context, String dummy) {
        this.reachList = reachList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_reach_overview, parent, false);
        return new ReachOverviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ProfileView.StatsOrActionsModel statsOrActionsModel = reachList.get(position);
        ((ReachOverviewHolder) holder).reachIcon.setImageResource(statsOrActionsModel.getIntIcon());
        ((ReachOverviewHolder) holder).reachLable.setText(statsOrActionsModel.getStrLabel());
        ((ReachOverviewHolder) holder).reachCount.setText(statsOrActionsModel.getStrCount());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return reachList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ReachOverviewHolder extends RecyclerView.ViewHolder {
        ImageView reachIcon;
        TextView reachLable;
        TextView reachCount;
        ConstraintLayout reachConLay;

        public ReachOverviewHolder(@NonNull View itemView) {
            super(itemView);
            reachIcon = itemView.findViewById(R.id.img_contact_overview_icon);
            reachLable = itemView.findViewById(R.id.tv_contact_overview_label);
            reachCount = itemView.findViewById(R.id.tv_contact_overview_activity_count);
            reachConLay = itemView.findViewById(R.id.item_con_lay_reach_overview);
        }
    }
}
