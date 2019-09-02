package com.singularitycoder.folkcaller.rankings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.singularitycoder.folkcaller.R;

import java.util.ArrayList;

public class RankingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<RankingsModel> rankingsList;
    Context context;

    public RankingsAdapter(ArrayList<RankingsModel> rankingsList, Context context) {
        this.rankingsList = rankingsList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rankings, parent, false);
        return new RankingsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RankingsModel rankingsModel = rankingsList.get(position);
        ((RankingsViewHolder) holder).rank.setText(rankingsModel.getStrRank());
        ((RankingsViewHolder) holder).userImage.setImageResource(rankingsModel.getIntRankUserPic());
        ((RankingsViewHolder) holder).userName.setText(rankingsModel.getStrUserName());
        ((RankingsViewHolder) holder).conversionCount.setText(rankingsModel.getStrConversions());
    }

    @Override
    public int getItemCount() {
        return rankingsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class RankingsViewHolder extends RecyclerView.ViewHolder {

        TextView rank;
        CircularImageView userImage;
        TextView userName;
        TextView conversionCount;

        public RankingsViewHolder(@NonNull View itemView) {
            super(itemView);

            rank = itemView.findViewById(R.id.tv_rank);
            userImage = itemView.findViewById(R.id.img_rank_profile_pic);
            userName = itemView.findViewById(R.id.tv_rank_name);
            conversionCount = itemView.findViewById(R.id.tv_rank_conversions);

        }
    }

}
