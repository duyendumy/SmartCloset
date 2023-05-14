package com.example.smartclosetclient.adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartclosetclient.R;

public class OutfitHolder extends RecyclerView.ViewHolder{
    TextView topItem, bottomItem;
    ImageView topImage, bottomImage;
    LinearLayout linearLayout;
    public OutfitHolder(@NonNull View itemView) {
        super(itemView);
        topItem = itemView.findViewById(R.id.topItem);
        bottomItem = itemView.findViewById(R.id.bottomItem);
        topImage = itemView.findViewById(R.id.topImage);
        bottomImage = itemView.findViewById(R.id.bottomImage);
        linearLayout = itemView.findViewById(R.id.linearLayout);
    }
}
