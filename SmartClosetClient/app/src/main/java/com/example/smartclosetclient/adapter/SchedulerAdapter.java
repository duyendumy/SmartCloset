package com.example.smartclosetclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartclosetclient.AddOutfitActivity;
import com.example.smartclosetclient.R;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.Outfit;
import com.example.smartclosetclient.model.Scheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SchedulerAdapter extends RecyclerView.Adapter<SchedulerHolder> {
    private List<Scheduler> schedulerList;
    private Context context;

    public SchedulerAdapter(Context context, List<Scheduler> schedulerList) {
        this.context = context;
        this.schedulerList = schedulerList;
    }

    @NonNull
    @Override
    public SchedulerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.scheduler,parent,false);
        return new SchedulerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchedulerHolder holder, int position) {
       Scheduler scheduler = schedulerList.get(position);
        Collection<Item> itemCollection = schedulerList.get(position).getOutfit().getItems();
        List<Item> itemList = new ArrayList<Item>();
        for(Item item:itemCollection) {
            itemList.add(item);
        }
        Glide.with(context).load(itemList.get(0).getImagePath()).into(holder.topImage);
        Glide.with(context).load(itemList.get(1).getImagePath()).into(holder.bottomImage);
        long mi = scheduler.getSelectedDate().getTime();
        DateFormat obj = new SimpleDateFormat("dd MMM yyyy");
        Date res = new Date(mi);
        holder.schedulerDate.setText(obj.format(res));

    }

    @Override
    public int getItemCount() {
        return schedulerList.size();
    }
}
