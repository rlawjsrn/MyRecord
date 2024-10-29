package com.example.myrecord;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

public class ApiListActivity extends AppCompatActivity {
    DBHelper dbHelper;
    ListView apiList;
    EditText txtSearch;
    Button btnSrc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apilist);
        getSupportActionBar().hide(); //액션바 숨김
        dbHelper=new DBHelper(getApplicationContext());
        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();
        apiList = findViewById(R.id.apiList);
        txtSearch = findViewById(R.id.txtSearch);
        btnSrc = findViewById(R.id.btnSrc);

        //API 가져오기
            ArrayList<RecordVO> list = new ArrayList<RecordVO>();
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            //api url 주소 가져오기
            String url = "http://book.interpark.com/api/bestSeller.api?key=83C2D4527883243469FAE8A9DEDEBBDB90E0BF39636701BF11CE5DDC429F750B&totalResults=10&output=json&categoryId=300";
            //url에서 정보(데이터)를 가져옵니다아
            StringRequest request =new StringRequest(url,
                    response -> {
                Log.i("asd",response);
                        Map<String,Object> map = gson.fromJson(response, Map.class);
                        System.out.println(map);
                        //데이터 가공(?)
                        for(int i=0; i<30; i++){
                            RecordVO recordVO = new RecordVO();
                            recordVO.setTitle(((Map<String, String>)((ArrayList)(map.get("item"))).get(i)).get("title"));
                            recordVO.setAuthor(((Map<String, String>)((ArrayList)(map.get("item"))).get(i)).get("author"));
                            recordVO.setPriceStandard(String.format("%1$,.0f",((Map<String, String>)((ArrayList)(map.get("item"))).get(i)).get("priceStandard")));
                            recordVO.setCoverSmallUrl(((Map<String, String>)((ArrayList)(map.get("item"))).get(i)).get("coverSmallUrl"));
                            list.add(recordVO);
                        }
                        RecordAdapter recordAdapter = new RecordAdapter(list);
                        apiList.setAdapter(recordAdapter);
                    },
                    error -> {
                        Log.d("request", error.toString());
                    });
            queue.add(request);

        //리스트 클릭 이벤트(한 건 조회)
        apiList.setOnItemClickListener((adapterView, view, idx, l) -> {
            Intent intent = new Intent(getApplicationContext(),ApiSelectActivity.class);
            System.out.println(""+list.get(idx).getCoverSmallUrl());
            intent.putExtra("image",""+list.get(idx).getCoverSmallUrl()); //보내는 image값
            intent.putExtra("title",""+list.get(idx).getTitle()); //보내는 title값
            intent.putExtra("author",""+list.get(idx).getAuthor()); //보내는 author값
            intent.putExtra("price",""+list.get(idx).getPriceStandard()); //보내는 price값
            startActivity(intent);
        });

        //검색 기능
        btnSrc.setOnClickListener(v->{
            ArrayList<RecordVO> list2 = new ArrayList<RecordVO>();
            String search = txtSearch.getText().toString();
            for(int i =0; i<list.size(); i++){
                if((list.get(i).getTitle()).contains(search)){
                    list2.add(list.get(i));
                }
            }
            RecordAdapter recordAdapter1 = new RecordAdapter(list2);
            apiList.setAdapter(recordAdapter1);
        });

        }

    }

