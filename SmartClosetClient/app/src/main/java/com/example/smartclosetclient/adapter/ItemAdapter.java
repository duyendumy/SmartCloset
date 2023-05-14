package com.example.smartclosetclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartclosetclient.ItemDetailedActivity;
import com.example.smartclosetclient.R;
import com.example.smartclosetclient.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

    private List<Item> itemList;
    private Context context;

    public ItemAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Item item = itemList.get(position);
        Glide.with(context).load(itemList.get(position).getImagePath()).into(holder.itemImage);
        holder.category.setText(item.getCategory().getName());
        holder.subCategory.setText(item.getSubCategory().getName());
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = holder.getAdapterPosition();
                Intent intent=new Intent(holder.itemView.getContext(), ItemDetailedActivity.class);
                Long itemId = itemList.get(i).getId();
                Long categoryId = itemList.get(i).getCategory().getId();
                intent.putExtra("idItem",String.valueOf(itemId));
                intent.putExtra("category",itemList.get(i).getCategory().getName());
                intent.putExtra("idCategory",String.valueOf(categoryId));
                intent.putExtra("subCategory",itemList.get(i).getSubCategory().getName());
                intent.putExtra("brand",itemList.get(i).getBrand().getName());
                intent.putExtra("season",itemList.get(i).getSeason().getName());
                intent.putExtra("price",itemList.get(i).getPrice().toString());
                intent.putExtra("color",itemList.get(i).getColor());
                intent.putExtra("description",itemList.get(i).getDescription());
                intent.putExtra("imagePath",itemList.get(i).getImagePath());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
