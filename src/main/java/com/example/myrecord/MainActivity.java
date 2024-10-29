package com.example.myrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnApi, btnMy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); //액션바 숨김


        //음반 목록 버튼 클릭
        btnApi = findViewById(R.id.btnApi);
        btnApi.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(),ApiListActivity.class);
            startActivity(intent);
        });

        //즐겨찾기 버튼 클릭
        btnMy = findViewById(R.id.btnMy);
        btnMy.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),MyListActivity.class);
            startActivity(intent);
        });

    }
}