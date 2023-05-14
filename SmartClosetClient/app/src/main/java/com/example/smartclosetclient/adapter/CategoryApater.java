package com.example.smartclosetclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartclosetclient.ItemActivity;
import com.example.smartclosetclient.ItemDetailedActivity;
import com.example.smartclosetclient.R;
import com.example.smartclosetclient.model.Category;
import com.example.smartclosetclient.model.Item;

import java.util.List;

public class CategoryApater  extends RecyclerView.Adapter<CategoryHolder>{

    private List<Category> categoryList;
    private Context context;

    public CategoryApater( Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category category = categoryList.get(position);
        Glide.with(context).load(category.getImagePath()).into(holder.categoryImage);
        holder.recSpecificCategory.setText(category.getName());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = holder.getAdapterPosition();
                Intent intent=new Intent(holder.itemView.getContext(), ItemActivity.class);
                Long categoryId = categoryList.get(i).getId();
                intent.putExtra("idCategory",String.valueOf(categoryId));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
