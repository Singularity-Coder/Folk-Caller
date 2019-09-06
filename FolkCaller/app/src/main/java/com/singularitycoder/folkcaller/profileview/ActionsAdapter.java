package com.singularitycoder.folkcaller.profileview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.singularitycoder.folkcaller.R;

import java.util.ArrayList;

public class ActionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ProfileView.ActionsModel> actionsList;
    Context context;

    public ActionsAdapter(ArrayList<ProfileView.ActionsModel> actionsList, Context context) {
        this.actionsList = actionsList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_admin_actions, parent, false);
        return new ActionsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProfileView.ActionsModel actionsModel = actionsList.get(position);

        ((ActionsViewHolder) holder).actionIcon.setImageResource(actionsModel.getIntActionIcon());
        ((ActionsViewHolder) holder).actionText.setText(actionsModel.getStrActionText());
    }

    @Override
    public int getItemCount() {
        return actionsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ActionsViewHolder extends RecyclerView.ViewHolder {

        ImageView actionIcon;
        TextView actionText;

        public ActionsViewHolder(@NonNull View itemView) {
            super(itemView);

            actionIcon = itemView.findViewById(R.id.img_admin_action_icon);
            actionText = itemView.findViewById(R.id.tv_admin_action);
        }
    }
}
