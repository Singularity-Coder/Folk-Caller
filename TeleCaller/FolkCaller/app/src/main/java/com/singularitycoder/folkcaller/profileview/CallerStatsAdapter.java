package com.singularitycoder.folkcaller.profileview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.singularitycoder.folkcaller.R;

import java.util.ArrayList;

public class CallerStatsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ProfileView.StatsOrActionsModel> callerStatsList;
    Context context;

    public CallerStatsAdapter(ArrayList<ProfileView.StatsOrActionsModel> callerStatsList, Context context, String dummy) {
        this.callerStatsList = callerStatsList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_stats_or_actions, parent, false);
        return new CallerStatsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ProfileView.StatsOrActionsModel statsOrActionsModel = callerStatsList.get(position);
        ((CallerStatsHolder) holder).callerStatsIcon.setImageResource(statsOrActionsModel.getIntIcon());
        ((CallerStatsHolder) holder).callerStatsLable.setText(statsOrActionsModel.getStrLabel());
        ((CallerStatsHolder) holder).callerStatsCount.setText(statsOrActionsModel.getStrCount());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return callerStatsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class CallerStatsHolder extends RecyclerView.ViewHolder {
        ImageView callerStatsIcon;
        TextView callerStatsLable;
        TextView callerStatsCount;
        ConstraintLayout callerStatsConLay;

        public CallerStatsHolder(@NonNull View itemView) {
            super(itemView);
            callerStatsIcon = itemView.findViewById(R.id.img_contact_overview_icon);
            callerStatsLable = itemView.findViewById(R.id.tv_contact_overview_label);
            callerStatsCount = itemView.findViewById(R.id.tv_contact_overview_activity_count);
            callerStatsConLay = itemView.findViewById(R.id.item_con_lay_reach_overview);
        }
    }
}
