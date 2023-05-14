package com.example.smartclosetclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartclosetclient.DetailedCategoryActivity;
import com.example.smartclosetclient.ItemDetailedActivity;
import com.example.smartclosetclient.R;
import com.example.smartclosetclient.model.Category;
import com.example.smartclosetclient.model.Item;

import java.util.List;

public class ManagedCategoryApdater extends RecyclerView.Adapter<ManagedCategoryHolder>{
    private List<Category> categoryList;
    private Context context;

    public ManagedCategoryApdater(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ManagedCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item,parent,false);
        return new ManagedCategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagedCategoryHolder holder, int position) {
        Category category = categoryList.get(position);
        Glide.with(context).load(categoryList.get(position).getImagePath()).into(holder.categoryImage);
        holder.categoryName.setText(category.getName());
        holder.categoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = holder.getAdapterPosition();
                Intent intent=new Intent(holder.itemView.getContext(), DetailedCategoryActivity.class);
                Long categoryId = categoryList.get(i).getId();
                String categoryName = categoryList.get(i).getName();
                String categoryImage = categoryList.get(i).getImagePath();
                intent.putExtra("idCategory",String.valueOf(categoryId));
                intent.putExtra("categoryName",categoryName);
                intent.putExtra("categoryImage", categoryImage);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
