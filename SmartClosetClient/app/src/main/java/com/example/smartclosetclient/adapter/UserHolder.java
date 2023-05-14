package com.example.smartclosetclient.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartclosetclient.R;

public class UserHolder extends RecyclerView.ViewHolder {

    TextView name, email, userListItem_createdDate;
    public UserHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.userListItem_name);
        email = itemView.findViewById(R.id.userListItem_email);
        userListItem_createdDate = itemView.findViewById(R.id.userListItem_createdDate);
    }
}
