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

import com.example.smartclosetclient.adapter.ItemOutfitAdapter;
import com.example.smartclosetclient.adapter.OutfitSchedulerAdapter;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.Outfit;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class BottomSheetOutfitFragment extends BottomSheetDialogFragment {
    private List<Outfit> oufitList;
    private IOufitClickListener iOufitClickListener;

    public BottomSheetOutfitFragment(List<Outfit> oufitList, IOufitClickListener iOufitClickListener) {
        this.oufitList = oufitList;
        this.iOufitClickListener = iOufitClickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottomsheetoutfitlayout, null);
        bottomSheetDialog.setContentView(view);
        RecyclerView rcvData = view.findViewById(R.id.item_recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvData.setLayoutManager(linearLayoutManager);
        OutfitSchedulerAdapter outfitAdapter = new OutfitSchedulerAdapter(getContext(), oufitList, new IOufitClickListener() {
            @Override
            public void clickOutfit(Outfit outfit) {
                iOufitClickListener.clickOutfit(outfit);
            }
        });
        rcvData.setAdapter(outfitAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);
        return bottomSheetDialog;
}

}
