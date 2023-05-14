package com.example.smartclosetclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartclosetclient.AddOutfitActivity;
import com.example.smartclosetclient.IClickListener;
import com.example.smartclosetclient.ItemDetailedActivity;
import com.example.smartclosetclient.R;
import com.example.smartclosetclient.model.Item;

import java.util.List;

public class ItemOutfitAdapter  extends RecyclerView.Adapter<ItemHolder>{
    private List<Item> itemList;

    private IClickListener iClickListener;

    private Context context;

    public ItemOutfitAdapter(Context context, List<Item> itemList, IClickListener iClickListener ) {
        this.context = context;
        this.itemList = itemList;
        this.iClickListener = iClickListener;
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
                iClickListener.clickItems(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
