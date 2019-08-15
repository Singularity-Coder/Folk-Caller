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

public class AdapterDetailViewComments extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ModelDetailViewComments> mArrayList;
    Context mContext;

    public AdapterDetailViewComments(ArrayList<ModelDetailViewComments> arrayList, Context context) {
        mArrayList = arrayList;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detailview_comments, parent, false);
        return new ViewHolderDetailViewComments(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ModelDetailViewComments ModelDetailViewComments = mArrayList.get(position);

        ((ViewHolderDetailViewComments) holder).profilePic.setImageResource(ModelDetailViewComments.getProfileImage());
        ((ViewHolderDetailViewComments) holder).name.setText(ModelDetailViewComments.getName());
        ((ViewHolderDetailViewComments) holder).dateTime.setText(ModelDetailViewComments.getDateTime());
        ((ViewHolderDetailViewComments) holder).comment.setText(ModelDetailViewComments.getComment());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class ViewHolderDetailViewComments extends RecyclerView.ViewHolder {

        CircularImageView profilePic;
        TextView name;
        TextView dateTime;
        TextView comment;

        public ViewHolderDetailViewComments(@NonNull View itemView) {
            super(itemView);

            profilePic = itemView.findViewById(R.id.img_profile_pic);
            name = itemView.findViewById(R.id.tv_name);
            dateTime = itemView.findViewById(R.id.tv_subtitle_1);
            comment = itemView.findViewById(R.id.tv_subtitle_2);

        }
    }
}
