package com.example.smartclosetclient.adapter;

import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartclosetclient.R;
import com.google.android.material.imageview.ShapeableImageView;

public class ItemHolder extends RecyclerView.ViewHolder{

    TextView category, subCategory;
    RelativeLayout itemLayout;

    ShapeableImageView itemImage;

    public ItemHolder(@NonNull View itemView) {
        super(itemView);
        category = itemView.findViewById(R.id.listCategory);
        subCategory = itemView.findViewById(R.id.listSubCategory);
        itemLayout = itemView.findViewById(R.id.itemLayout);
        itemImage = itemView.findViewById(R.id.listImage);
    }
}
