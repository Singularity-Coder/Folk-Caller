package com.singularitycoder.folkcaller.profileview;

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

public class AdapterProfileViewCalledBy extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ModelProfileView> mArrayList;
    Context mContext;

    public AdapterProfileViewCalledBy(ArrayList<ModelProfileView> arrayList, Context context) {
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
        ModelProfileView modelProfileView = mArrayList.get(position);

        ((ViewHolderDetailViewCalledBy) holder).profilePic.setImageResource(modelProfileView.getProfileImage());
        ((ViewHolderDetailViewCalledBy) holder).name.setText(modelProfileView.getName());
        ((ViewHolderDetailViewCalledBy) holder).dateTime.setText(modelProfileView.getDateTime());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
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
