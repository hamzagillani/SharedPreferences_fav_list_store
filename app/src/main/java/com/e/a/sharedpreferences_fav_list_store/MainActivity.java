package com.e.a.sharedpreferences_fav_list_store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String user_id = "idKey";
    public static final String name = "nameKey";
    public static final String number = "number";
    public static final String position = "position";
    private SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    private List<MyModel> listData;
    private Button fvItems;
    MyModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        init();
        listner();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();

    }

    private void loadData() {
        listData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            model = new MyModel();
            model.setName("Hamza " + i);
            model.setMobile("0308-4095482- " + i);
            model.setId("123abc- " + i);
            model.setFav(false);
            model.setPosition(i);
            listData.add(model);
        }

        MyAdapter adapter = new MyAdapter(this, listData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    void init() {
        recyclerView = findViewById(R.id.recyclerview_main_id);
        fvItems = findViewById(R.id.bt_fav_items_id);

    }

    void listner() {
        fvItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Fav_Acticity.class);
                startActivity(intent);

            }
        });
    }
}
