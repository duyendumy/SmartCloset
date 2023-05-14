package com.example.smartclosetclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartclosetclient.R;
import com.example.smartclosetclient.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserHolder> {

    private List<User> userList;
    public UserAdapter(List<User> useList){
        this.userList = useList;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user_item,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = userList.get(position);
        holder.name.setText(user.getUsername());
        holder.email.setText(user.getEmail());
        long mi = user.getCreatedDate().getTime();
        DateFormat obj = new SimpleDateFormat("dd MMM yyyy");
        Date res = new Date(mi);
        holder.userListItem_createdDate.setText(obj.format(res));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
