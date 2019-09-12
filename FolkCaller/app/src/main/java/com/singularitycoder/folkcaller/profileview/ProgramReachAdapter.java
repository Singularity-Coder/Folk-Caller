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

public class ProgramReachAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ProfileView.StatsOrActionsModel> reachList;
    Context context;

    public ProgramReachAdapter(ArrayList<ProfileView.StatsOrActionsModel> reachList, Context context, String dummy) {
        this.reachList = reachList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_stats_or_actions, parent, false);
        return new ProgramReachOverviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ProfileView.StatsOrActionsModel statsOrActionsModel = reachList.get(position);
        ((ProgramReachOverviewHolder) holder).reachIcon.setImageResource(statsOrActionsModel.getIntIcon());
        ((ProgramReachOverviewHolder) holder).reachLable.setText(statsOrActionsModel.getStrLabel());
        ((ProgramReachOverviewHolder) holder).reachCount.setText(statsOrActionsModel.getStrCount());
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

    public class ProgramReachOverviewHolder extends RecyclerView.ViewHolder {
        ImageView reachIcon;
        TextView reachLable;
        TextView reachCount;
        ConstraintLayout reachConLay;

        public ProgramReachOverviewHolder(@NonNull View itemView) {
            super(itemView);
            reachIcon = itemView.findViewById(R.id.img_contact_overview_icon);
            reachLable = itemView.findViewById(R.id.tv_contact_overview_label);
            reachCount = itemView.findViewById(R.id.tv_contact_overview_activity_count);
            reachConLay = itemView.findViewById(R.id.item_con_lay_reach_overview);
        }
    }
}
