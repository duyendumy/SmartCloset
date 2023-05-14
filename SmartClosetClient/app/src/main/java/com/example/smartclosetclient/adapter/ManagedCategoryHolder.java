package com.example.smartclosetclient.adapter;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartclosetclient.R;
import com.google.android.material.imageview.ShapeableImageView;

public class ManagedCategoryHolder extends RecyclerView.ViewHolder {
    TextView categoryName;
    ShapeableImageView categoryImage;
    RelativeLayout categoryLayout;
    public ManagedCategoryHolder(@NonNull View itemView) {
        super(itemView);
        categoryName = itemView.findViewById(R.id.listCategory);
        categoryImage = itemView.findViewById(R.id.listImage);
        categoryLayout = itemView.findViewById(R.id.categoryLayout);
    }
}
