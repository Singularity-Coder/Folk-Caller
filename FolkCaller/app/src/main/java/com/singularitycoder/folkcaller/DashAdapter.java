package com.singularitycoder.folkcaller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.singularitycoder.folkcaller.home.HomeActivity;

import java.util.ArrayList;

public class DashAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int DASH_HEADER = 0;
    private static final int DASH_ITEM = 1;

    ArrayList<HomeActivity.DashModel> dashList;
    Context context;

    public DashAdapter(ArrayList<HomeActivity.DashModel> dashList, Context context) {
        this.dashList = dashList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case DASH_HEADER:
                v = layoutInflater.inflate(R.layout.item_dash_header, parent, false);
                return new DashHeaderViewHolder(v);
            default:
                v = layoutInflater.inflate(R.layout.item_dash, parent, false);
                return new DashViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        HomeActivity.DashModel dashModel = dashList.get(position);
        if (holder instanceof DashViewHolder) {
            ((DashViewHolder) holder).dashImage.setImageResource(dashModel.getIntDashImage());
            ((DashViewHolder) holder).dashTitle.setText(dashModel.getStrDashTitle());
            ((DashViewHolder) holder).dashCount.setText(dashModel.getStrDashCount());
        }

        else if (holder instanceof DashHeaderViewHolder) {
            ((DashHeaderViewHolder) holder).dashHeaderCount.setText(dashModel.getStrDashCount());
        }
    }

    @Override
    public int getItemCount() {
        return dashList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? DASH_HEADER : DASH_ITEM;
    }

    public class DashViewHolder extends RecyclerView.ViewHolder {

        ImageView dashImage;
        TextView dashTitle;
        TextView dashCount;

        public DashViewHolder(@NonNull View itemView) {
            super(itemView);

            dashImage = itemView.findViewById(R.id.img_dash_stat_icon);
            dashTitle = itemView.findViewById(R.id.tv_dash_stat_title);
            dashCount = itemView.findViewById(R.id.tv_dash_stat_count);
        }
    }

    public class DashHeaderViewHolder extends RecyclerView.ViewHolder {

        TextView dashHeaderCount;

        public DashHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            dashHeaderCount = itemView.findViewById(R.id.tv_todays_task_count);
        }
    }

}
