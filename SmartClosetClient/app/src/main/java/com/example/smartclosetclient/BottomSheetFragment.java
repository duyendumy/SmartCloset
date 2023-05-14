package com.example.smartclosetclient;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartclosetclient.adapter.ItemAdapter;
import com.example.smartclosetclient.adapter.ItemOutfitAdapter;
import com.example.smartclosetclient.model.Item;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    private List<Item> itemList;
    private IClickListener iClickListener;

    public BottomSheetFragment(List<Item> itemList, IClickListener iClickListener) {
        this.itemList = itemList;
        this.iClickListener = iClickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottomsheetlayout, null);
        bottomSheetDialog.setContentView(view);
        RecyclerView rcvData = view.findViewById(R.id.item_recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvData.setLayoutManager(linearLayoutManager);
        ItemOutfitAdapter itemOutfitAdapter = new ItemOutfitAdapter(getContext(), itemList, new IClickListener() {
            @Override
            public void clickItems(Item item) {
                iClickListener.clickItems(item);
            }
        });
        rcvData.setAdapter(itemOutfitAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);
        return bottomSheetDialog;
}

}
