package com.singularitycoder.folkcaller.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.singularitycoder.folkcaller.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ModelItemContactCallerAdminChatNotif> notifList;
    Context context;

    public NotificationAdapter(ArrayList<ModelItemContactCallerAdminChatNotif> notifList, Context context) {
        this.notifList = notifList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {

        CircularImageView imgProfileImage;
        TextView tvName, tvSubTitle1, tvSubTitle2, tvDate;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProfileImage = itemView.findViewById(R.id.img_profile_pic);
            tvName = itemView.findViewById(R.id.tv_name);
            tvSubTitle1 = itemView.findViewById(R.id.tv_subtitle_1);
            tvSubTitle2 = itemView.findViewById(R.id.tv_subtitle_2);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }
}
