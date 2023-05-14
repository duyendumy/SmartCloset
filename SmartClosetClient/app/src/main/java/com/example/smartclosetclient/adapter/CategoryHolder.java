package com.example.smartclosetclient.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartclosetclient.R;

public class CategoryHolder extends RecyclerView.ViewHolder{
    ImageView categoryImage;
    TextView recSpecificCategory;

    LinearLayout linearLayout;

    public CategoryHolder(@NonNull View itemView) {
        super(itemView);
        categoryImage =  itemView.findViewById(R.id.recImage);
        recSpecificCategory=  itemView.findViewById(R.id.recSpecificCategory);
        linearLayout = itemView.findViewById(R.id.linearLayout);
    }
}
