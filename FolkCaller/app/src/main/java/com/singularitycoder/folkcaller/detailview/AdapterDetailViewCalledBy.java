package com.singularitycoder.folkcaller.detailview;

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

public class AdapterDetailViewCalledBy extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ModelDetailViewCalledBy> mArrayList;
    Context mContext;

    public AdapterDetailViewCalledBy(ArrayList<ModelDetailViewCalledBy> arrayList, Context context) {
        mArrayList = arrayList;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detailview_called_by, parent, false);
        return new ViewHolderDetailViewCalledBy(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ModelDetailViewCalledBy modelDetailViewCalledBy = mArrayList.get(position);

        ((ViewHolderDetailViewCalledBy) holder).profilePic.setImageResource(modelDetailViewCalledBy.getProfileImage());
        ((ViewHolderDetailViewCalledBy) holder).name.setText(modelDetailViewCalledBy.getName());
        ((ViewHolderDetailViewCalledBy) holder).dateTime.setText(modelDetailViewCalledBy.getDateTime());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class ViewHolderDetailViewCalledBy extends RecyclerView.ViewHolder {

        CircularImageView profilePic;
        TextView name;
        TextView dateTime;

        public ViewHolderDetailViewCalledBy(@NonNull View itemView) {
            super(itemView);

            profilePic = itemView.findViewById(R.id.img_profile_pic);
            name = itemView.findViewById(R.id.tv_name);
            dateTime = itemView.findViewById(R.id.tv_subtitle_1);

        }
    }
}
