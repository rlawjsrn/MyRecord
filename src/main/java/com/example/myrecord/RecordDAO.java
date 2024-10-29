package com.example.myrecord;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class RecordDAO extends AppCompatActivity {
    DBHelper dbHelper;
    String tableName = "record";

    //등록
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void insert(DBHelper dbhelper, RecordVO recordVO){
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("title", recordVO.getTitle());
        contentValues.put("author", recordVO.getAuthor());
        contentValues.put("priceStandard", recordVO.getPriceStandard());
        contentValues.put("coverSmallUrl", recordVO.getCoverSmallUrl());
        contentValues.put("memo", recordVO.getMemo());

        db.insert("record", null, contentValues);
        dbhelper.close();
    }

    //즐겨찾기 목록 조회
    public static ArrayList<RecordVO> selectAll(DBHelper dbHelper) {

        ArrayList<RecordVO> list = new ArrayList<RecordVO>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from record";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            RecordVO recordVO = new RecordVO();
            recordVO.setTitle(cursor.getString(0));
            recordVO.setAuthor(cursor.getString(1));
            recordVO.setPriceStandard(cursor.getString(2));
            recordVO.setCoverSmallUrl(cursor.getString(3));
            recordVO.setMemo(cursor.getString(4));
            list.add(recordVO);
        }
        db.close();
        return list;
    }

    //수정 버튼

    //삭제버튼
    public static void delete(DBHelper dbhelper, RecordVO recordVO){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.delete("record","title=?",new String[]{recordVO.getTitle()});
        db.close();
    }
}
