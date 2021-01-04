package com.singularitycoder.folkcaller.home;

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

import com.mikhaellopez.circularimageview.CircularImageView;
import com.singularitycoder.folkcaller.R;

import java.util.ArrayList;

public class SmsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<PersonModel> smsList;
    Context context;

    public SmsAdapter(ArrayList<PersonModel> smsList, Context context) {
        this.smsList = smsList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new SmsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        PersonModel personModel = smsList.get(position);

        ((SmsAdapter.SmsViewHolder) holder).imgProfileImage.setImageResource(personModel.getImgProfileImage());

        ((SmsAdapter.SmsViewHolder) holder).tvName.setText(personModel.getStrName());

        ((SmsAdapter.SmsViewHolder) holder).tvSubTitle1.setVisibility(View.GONE);

        ((SmsAdapter.SmsViewHolder) holder).tvSubTitle2.setVisibility(View.VISIBLE);
        ((SmsAdapter.SmsViewHolder) holder).tvSubTitle2.setText(personModel.getStrSubTitle2());
        ((SmsAdapter.SmsViewHolder) holder).tvSubTitle2.setMaxLines(2);

        ((SmsAdapter.SmsViewHolder) holder).tvDate.setVisibility(View.VISIBLE);
        ((SmsAdapter.SmsViewHolder) holder).tvDate.setText(personModel.getStrDate());


        if (null == personModel.strChatCount || ("").equals(personModel.strChatCount)) {
            ((SmsViewHolder) holder).tvChatCount.setVisibility(View.GONE);
            ((SmsAdapter.SmsViewHolder) holder).arrow.setVisibility(View.VISIBLE);
            ((SmsAdapter.SmsViewHolder) holder).tvDate.setTextColor(context.getResources().getColor(R.color.colorBlack));
        } else {
            ((SmsAdapter.SmsViewHolder) holder).arrow.setVisibility(View.GONE);
            ((SmsViewHolder) holder).tvChatCount.setVisibility(View.VISIBLE);
            ((SmsViewHolder) holder).tvChatCount.setText(personModel.getStrChatCount());
            ((SmsAdapter.SmsViewHolder) holder).tvDate.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }

        ((SmsAdapter.SmsViewHolder) holder).personLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return smsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class SmsViewHolder extends RecyclerView.ViewHolder {

        CircularImageView imgProfileImage;
        TextView tvName, tvSubTitle1, tvSubTitle2, tvDate, tvChatCount;
        ImageView arrow, accept, stopper;
        ConstraintLayout personLayout;


        public SmsViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProfileImage = itemView.findViewById(R.id.img_profile_pic);
            tvName = itemView.findViewById(R.id.tv_name);
            tvSubTitle1 = itemView.findViewById(R.id.tv_subtitle_1);
            tvSubTitle2 = itemView.findViewById(R.id.tv_subtitle_2);
            tvDate = itemView.findViewById(R.id.tv_date);
            arrow = itemView.findViewById(R.id.img_arrow);
            accept = itemView.findViewById(R.id.img_accept);
            personLayout = itemView.findViewById(R.id.con_lay_person_container);
            stopper = itemView.findViewById(R.id.img_stopper);
            tvChatCount = itemView.findViewById(R.id.tv_count);
        }
    }
}
