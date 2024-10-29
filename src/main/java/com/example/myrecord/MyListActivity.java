package com.example.myrecord;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MyListActivity extends AppCompatActivity {
    ListView listView;
    TextView listHint;
    ArrayList<RecordVO> list = new ArrayList<RecordVO>();
    DBHelper dbHelper;
    Button btnDel;
    RecordVO recordVO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylist);
        getSupportActionBar().hide(); //액션바 숨김
        listView = findViewById(R.id.listView);
        dbHelper = new DBHelper(getApplicationContext());
        recordVO = new RecordVO();
        btnDel = findViewById(R.id.btnDel);
        listHint=findViewById(R.id.listHint);

        //DAO에서 목록조회 메서드 호출
        list = RecordDAO.selectAll(dbHelper);

        //listView초기화 -> 어댑터 지정
        ListAdapter adapter = new ListAdapter(list);
        listView.setAdapter(adapter);

        if(listView.getAdapter().getCount() == 0){
            listHint.setVisibility(View.VISIBLE);
        }

    }
}
