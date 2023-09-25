package com.example.logbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.logbook.models.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Detail extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProfileAdapter profileAdapter;
    private List<Profile> profileList;
    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        profileList = new ArrayList<>();
        profileAdapter = new ProfileAdapter(Detail.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(profileAdapter);
        loadData();

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detail.this, LogBook3.class);
                startActivity(intent);
            }
        });
    }

    private void loadData(){
        profileList =ProfileDatabase.getInstance(this).profileDAO().getListProfile();
        profileAdapter.setData(profileList);
    }

    private void initView(){
        recyclerView = findViewById(R.id.recycleView);
        myToolbar = findViewById(R.id.toolBarDetail);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("View Details");
        myToolbar.setTitleTextColor(getColor(R.color.purple));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}