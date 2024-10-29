package com.example.myrecord;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    ArrayList<RecordVO> data;
    DBHelper dbHelper;
    public ListAdapter() {
    }

    public ListAdapter(ArrayList<RecordVO> data) {
        this.data = data;
    }

    public int getCount() {return data.size();}

    public Object getItem(int i) {return data.get(i);}

    public long getItemId(int i) {return i;}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.activity_write, viewGroup, false);
        ImageView imgMy1 = view.findViewById(R.id.imgMy1);
        TextView txtMyTitle1 = view.findViewById(R.id.txtMyTitle);
        TextView txtMyPrice1 = view.findViewById(R.id.txtMyAuthor1);
        TextView txtMyAuthor1 = view.findViewById(R.id.txtMyPrice1);
        EditText txtMyMemo1 = view.findViewById(R.id.txtMyMemo1);
        Button btnUpd = view.findViewById(R.id.btnUpd);
        Button btnDel = view.findViewById(R.id.btnDel);
        RecordVO recordVO = new RecordVO();
        Context context = view.getContext();
        dbHelper= new DBHelper(context.getApplicationContext());
        
        txtMyTitle1.setText(data.get(i).getTitle());
        txtMyPrice1.setText(data.get(i).getPriceStandard());
        txtMyAuthor1.setText(data.get(i).getAuthor());
        txtMyMemo1.setText(data.get(i).getMemo());
        Glide.with(imgMy1.getContext()).load(data.get(i).getCoverSmallUrl()).into(imgMy1);

        //삭제버튼
        btnDel.setOnClickListener(v->{
            recordVO.setTitle(data.get(i).getTitle());
            RecordDAO.delete(dbHelper, recordVO);
            //삭제 후 리스트 새로 뿌려주기
            Intent intent = ((Activity)context).getIntent();
            ((Activity)context).finish();
            ((Activity)context).startActivity(intent);
            Toast.makeText((Activity)context, "삭제성공:)", Toast.LENGTH_LONG).show();
        });
        //수정 버튼
        btnUpd.setOnClickListener(v->{
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            recordVO.setTitle(data.get(i).getTitle());
            recordVO.setMemo(txtMyMemo1.getText().toString());
            db.execSQL("update record set memo= '"+recordVO.getMemo()+"' where title='"+recordVO.getTitle()+"'");
            Toast.makeText((Activity)context, "수정성공:)", Toast.LENGTH_LONG).show();
            //수정 후 리스트 새로 뿌려주기
            Intent intent = ((Activity)context).getIntent();
            ((Activity)context).finish();
            ((Activity)context).startActivity(intent);
        });
        return view;
    }
}

