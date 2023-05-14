package com.example.smartclosetclient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smartclosetclient.dto.SchedulerDto;
import com.example.smartclosetclient.helper.SharedPrefManager;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.Outfit;
import com.example.smartclosetclient.model.Scheduler;
import com.example.smartclosetclient.model.User;
import com.example.smartclosetclient.retrofit.ItemApi;
import com.example.smartclosetclient.retrofit.OutfitApi;
import com.example.smartclosetclient.retrofit.RetrofitService;
import com.example.smartclosetclient.retrofit.SchedulerApi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSchedulerActivity extends AppCompatActivity {
    private Button saveButton, cancelButton, selectDate, selectOutfit;
    private ImageView bottomImage;
    private ImageView topImage;
    private TextView txtDate,topItem, bottomItem;

    private Outfit selectedOutfit;

    private  List<Outfit> outfitData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scheduler);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);
        selectDate = findViewById(R.id.selectDate);
        selectOutfit = findViewById(R.id.selectOutfit);
        bottomImage = findViewById(R.id.bottomImage);
        topImage = findViewById(R.id.topImage);
        topItem = findViewById(R.id.topItem);
        bottomItem = findViewById(R.id.bottomItem);
        txtDate = findViewById(R.id.txtDate);
        outfitData = new ArrayList<Outfit>();
        Long closetId = SharedPrefManager.getInstance(this).getClosetId();
        selectOutfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOpenBottomFragment(closetId);
            }
        });
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayCalendar();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    saveScheduler();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void saveScheduler() throws ParseException {
        if(txtDate.getText().toString().trim().equalsIgnoreCase("")){
            txtDate.setError("Please select date");
            return;
        }
        if(topItem.getText().toString().trim().equalsIgnoreCase("")){
            return;
        }
        if(bottomItem.getText().toString().trim().equalsIgnoreCase("")){
            return;
        }
        Long closetId = SharedPrefManager.getInstance(this).getClosetId();
        if(txtDate != null && topItem != null && bottomItem != null){
            RetrofitService retrofitService = new RetrofitService();
            SchedulerApi schedulerApi = retrofitService.getRetrofit().create(SchedulerApi.class);
            SchedulerDto schedulerDto = new SchedulerDto();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(txtDate.getText().toString());
            schedulerDto.setSelectedDate(date);
            schedulerDto.setOutfitId(selectedOutfit.getId());
            schedulerDto.setClosetId(closetId);
            schedulerApi.save(schedulerDto).enqueue(new Callback<Scheduler>() {
                @Override
                public void onResponse(Call<Scheduler> call, Response<Scheduler> response) {
                    Toast.makeText(AddSchedulerActivity.this, "Save scheduler successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddSchedulerActivity.this, CalendarActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Scheduler> call, Throwable t) {
                    Toast.makeText(AddSchedulerActivity.this, "Save Outfit failed!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void displayCalendar(){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddSchedulerActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                       txtDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                },
                year, month, day);

        datePickerDialog.show();


    }

    private void clickOpenBottomFragment(Long closetId) {
        RetrofitService retrofitService = new RetrofitService();
        OutfitApi outfitApi = retrofitService.getRetrofit().create(OutfitApi.class);
        outfitApi.getAllOutfits(closetId)
        .enqueue(new Callback<List<Outfit>>() {
        @Override
        public void onResponse(Call<List<Outfit>> call, Response<List<Outfit>> response) {
                     outfitData = response.body();
        }
        @Override
        public void onFailure(Call<List<Outfit>> call, Throwable t) {
         Toast.makeText(AddSchedulerActivity.this, "Failed to load outfits!", Toast.LENGTH_SHORT).show();}
          });
        BottomSheetOutfitFragment bottomSheetOutfitFragment = new BottomSheetOutfitFragment(outfitData, new IOufitClickListener() {
            @Override
            public void clickOutfit(Outfit outfit) {
                selectedOutfit = outfit;
                Collection<Item> itemList = outfit.getItems();
                ArrayList<Item> itemArrayList = new ArrayList<Item>();
                for(Item i : itemList){
                    itemArrayList.add(i);
                }
                Toast.makeText(AddSchedulerActivity.this, outfit.getId().toString(),Toast.LENGTH_SHORT).show();
                Glide.with(AddSchedulerActivity.this).load(itemArrayList.get(0).getImagePath()).into(topImage);
                Glide.with(AddSchedulerActivity.this).load(itemArrayList.get(1).getImagePath()).into(bottomImage);
                topItem.setText(itemArrayList.get(0).getSubCategory().getName());
                bottomItem.setText(itemArrayList.get(1).getSubCategory().getName());
            }
        });
        if(!outfitData.isEmpty()){
        bottomSheetOutfitFragment.show(getSupportFragmentManager(), bottomSheetOutfitFragment.getTag());}
    }
}