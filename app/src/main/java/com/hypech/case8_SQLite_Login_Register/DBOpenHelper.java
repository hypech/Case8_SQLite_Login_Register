package com.hypech.case8_SQLite_Login_Register;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    private final SQLiteDatabase db;

    DBOpenHelper(Context context){
        super(context,"db_test.db",null,1);
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS user(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT," +
                "email TEXT," +
                "phonenum TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public void add(String name, String password,String email,String phonenum){
        db.execSQL("INSERT INTO user (name,password,email,phonenum) VALUES(?,?,?,?)",
                new Object[]{name,password,email,phonenum});
    }
    public void delete(String name,String password){
        db.execSQL("DELETE FROM user WHERE name = AND password ="+name+password);
    }
    public void update(String password){
        db.execSQL("UPDATE user SET password = ?",new Object[]{password});
    }

    public Cursor getAllUsers() {
        return db.rawQuery("SELECT * FROM user", null);
    }

    public Cursor getOneUser(String pName, String pPassword) {
        return db.rawQuery("SELECT * FROM user WHERE name =? AND password =?", new String[] {pName, pPassword});
    }

}