package com.example.smartclosetclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartclosetclient.IClickListener;
import com.example.smartclosetclient.IOufitClickListener;
import com.example.smartclosetclient.R;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.Outfit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OutfitSchedulerAdapter extends RecyclerView.Adapter<OutfitHolder>{

    private List<Outfit> outfitList;
    private Context context;
    private IOufitClickListener iOufitClickListener;

    public OutfitSchedulerAdapter(Context context, List<Outfit> outfitList, IOufitClickListener iOufitClickListener) {
        this.context = context;
        this.outfitList = outfitList;
        this.iOufitClickListener = iOufitClickListener;
    }

    @NonNull
    @Override
    public OutfitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.outfit,parent,false);
        return new OutfitHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OutfitHolder holder, int position) {
        Outfit outfit = outfitList.get(position);
        Collection<Item> itemList = outfit.getItems();
        ArrayList<Item> itemArrayList = new ArrayList<Item>();
        for(Item i : itemList){
            itemArrayList.add(i);
        }
        Glide.with(context).load(itemArrayList.get(0).getImagePath()).into(holder.topImage);
        Glide.with(context).load(itemArrayList.get(1).getImagePath()).into(holder.bottomImage);
        holder.topItem.setText(itemArrayList.get(0).getSubCategory().getName());
        holder.bottomItem.setText(itemArrayList.get(1).getSubCategory().getName());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOufitClickListener.clickOutfit(outfit);
            }
        });
    }

    @Override
    public int getItemCount() {
        return outfitList.size();
    }
}
