package com.e.a.sharedpreferences_fav_list_store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Fav_Acticity extends AppCompatActivity {
    private RecyclerView fav_RecyclerView;
    private List<MyModel> listData;
    private SharedPreferences preferences;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav__acticity);

        preferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);


        listData = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            int position = preferences.getInt(MainActivity.position + i, -1);
            if (position != -1) {
                String user_st = preferences.getString(MainActivity.user_id + position, null);
                String name_st = preferences.getString(MainActivity.name + position, null);
                String phone_st = preferences.getString(MainActivity.number + position, null);
                position = preferences.getInt(MainActivity.position + position, -1);
                MyModel model = new MyModel();
                model.setId(user_st);
                model.setName(name_st);
                model.setMobile(phone_st);
                model.setPosition(position);
                listData.add(model);
            }

        }
        init();
    }

    void init() {
        fav_RecyclerView = findViewById(R.id.rec_fav_id);
        MyFavAdapter myFavAdapter = new MyFavAdapter(this, listData);
        fav_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        fav_RecyclerView.setAdapter(myFavAdapter);
    }
}
