package com.example.smartclosetclient.adapter;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartclosetclient.R;
import com.google.android.material.imageview.ShapeableImageView;

public class SchedulerHolder extends RecyclerView.ViewHolder{

    CardView cardView;
    TextView schedulerDate;
    ShapeableImageView topImage, bottomImage;

    public SchedulerHolder(@NonNull View itemView) {
        super(itemView);
        schedulerDate = itemView.findViewById(R.id.schedulerDate);
        cardView = itemView.findViewById(R.id.cardView);
        topImage = itemView.findViewById(R.id.topImage);
        bottomImage = itemView.findViewById(R.id.bottomImage);
    }
}
