package com.example.myrecord;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static String NAME = "hr.db";
    public static int VERSION = 1;

    public DBHelper(Context context) {

        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {  //onCreate: 콜백 메소드로 앱을 생성할 때 한 번만 호출
        System.out.println("onCreate 호출됨");
        String sql = "create table if not exists record(" //테이블이 없으면 만들겠다!
                + " title text PRIMARY KEY , "
                + " author text, "
                + " priceStandard text, "
                + " coverSmallUrl text," +
                "memo text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
