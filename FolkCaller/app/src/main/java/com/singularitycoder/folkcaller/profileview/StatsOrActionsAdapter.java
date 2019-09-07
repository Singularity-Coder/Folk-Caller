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

public class StatsOrActionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int VIEW_TYPE_ACTIONS = 0;
    private int VIEW_TYPE_REACH_OVERVIEW = 1;

    ArrayList<ProfileView.StatsOrActionsModel> statsOrActionsList;
    ArrayList<ProfileView.StatsOrActionsModel> reachList;
    Context context;
    int myViewType;

    public StatsOrActionsAdapter(ArrayList<ProfileView.StatsOrActionsModel> statsOrActionsList, Context context) {
        this.statsOrActionsList = statsOrActionsList;
        this.context = context;
    }

    public StatsOrActionsAdapter(ArrayList<ProfileView.StatsOrActionsModel> reachList, Context context, String dummy) {
        this.reachList = reachList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_ACTIONS) {
            myViewType = VIEW_TYPE_ACTIONS;
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_admin_actions, parent, false);
            return new ActionsViewHolder(v);
        }

        if (viewType == VIEW_TYPE_REACH_OVERVIEW) {
            myViewType = VIEW_TYPE_REACH_OVERVIEW;
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_stats_or_actions, parent, false);
            return new ReachOverviewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ActionsViewHolder) {
            ProfileView.StatsOrActionsModel statsOrActionsModel = statsOrActionsList.get(position);
            ((ActionsViewHolder) holder).actionIcon.setImageResource(statsOrActionsModel.getIntIcon());
            ((ActionsViewHolder) holder).actionText.setText(statsOrActionsModel.getStrLabel());
            ((ActionsViewHolder) holder).actionsConLay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (position) {
                        case 0:
                            ((Activity) context).startActivity(new Intent(context, BulkSmsActivity.class));
                            break;
                        case 1:
                            break;
                        default:
                            break;
                    }
                }
            });
        }

        if (holder instanceof ReachOverviewHolder) {
            ProfileView.StatsOrActionsModel statsOrActionsModel = reachList.get(position);
            ((ReachOverviewHolder) holder).reachIcon.setImageResource(statsOrActionsModel.getIntIcon());
            ((ReachOverviewHolder) holder).reachLable.setText(statsOrActionsModel.getStrLabel());
            ((ReachOverviewHolder) holder).reachCount.setText(statsOrActionsModel.getStrCount());
        }
    }

//    @Override
//    public long getItemId(int position) {
//        return position;
//    }

    @Override
    public int getItemCount() {
//        if (myViewType == VIEW_TYPE_ACTIONS) {
//            System.out.println("action hit");
//            return statsOrActionsList.size();
//        } else {
//            System.out.println("reach hit");
//            return reachList.size();
//        }
        return statsOrActionsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (statsOrActionsList.get(position).getStrItemType().equals("AdminAction")) {
            return VIEW_TYPE_ACTIONS;
        } else if (statsOrActionsList.get(position).getStrItemType().equals("Reach")) {
            return VIEW_TYPE_REACH_OVERVIEW;
        } else {
            return position;
        }
    }

    public class ActionsViewHolder extends RecyclerView.ViewHolder {
        ImageView actionIcon;
        TextView actionText;
        ConstraintLayout actionsConLay;

        public ActionsViewHolder(@NonNull View itemView) {
            super(itemView);
            actionIcon = itemView.findViewById(R.id.img_admin_action_icon);
            actionText = itemView.findViewById(R.id.tv_admin_action);
            actionsConLay = itemView.findViewById(R.id.item_con_lay_admin_actions);
        }
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
