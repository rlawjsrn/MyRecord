package com.example.myrecord;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ApiSelectActivity extends AppCompatActivity {
    ImageView imgSelect;
    TextView txtSTitle, txtSAuthor, txtSPrice;
    EditText txtSMemo;
    Button btnIns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiselect);
        getSupportActionBar().hide(); //액션바 숨김
        DBHelper dbHelper = new DBHelper(getApplicationContext());

        RecordVO recordVO = new RecordVO();
        imgSelect = findViewById(R.id.imgSelect);
        txtSTitle = findViewById(R.id.txtSTitle);
        txtSAuthor = findViewById(R.id.txtSAuthor);
        txtSPrice = findViewById(R.id.txtSPrice);
        txtSMemo = findViewById(R.id.txtSMemo);
        btnIns = findViewById(R.id.btnIns);

        Intent getIntent = getIntent();

        //한 건 조회
        Glide.with(imgSelect.getContext()).load(getIntent.getExtras().getString("image")).into(imgSelect);
        System.out.println(getIntent.getExtras().getString("title"));

        txtSTitle.setText(getIntent.getStringExtra("title"));
        txtSAuthor.setText(getIntent.getExtras().getString("author"));
        txtSPrice.setText(getIntent.getExtras().getString("price"));

        //메모 작성(즐겨찾기 버튼 클릭)
        btnIns.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), MyListActivity.class);
            intent.putExtra("memo", "return test");
            setResult(0, intent);

            recordVO.setCoverSmallUrl(getIntent.getExtras().getString("image"));
            recordVO.setTitle(getIntent.getExtras().getString("title"));
            recordVO.setAuthor(getIntent.getExtras().getString("author"));
            recordVO.setPriceStandard(getIntent.getExtras().getString("price"));
            recordVO.setMemo(txtSMemo.getText().toString());

            RecordDAO.insert(dbHelper,recordVO);
            //등록성공 메세지 띄우기
            Toast.makeText(getApplicationContext(), "등록성공:)", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
