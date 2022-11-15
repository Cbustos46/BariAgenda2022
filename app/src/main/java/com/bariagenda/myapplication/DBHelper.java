package com.bariagenda.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


@SuppressWarnings("ALL")
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario(usuario TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuario");
    }

    public Boolean insertarDatos(String usuario,String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("usuario",usuario);
        values.put("password",password);
        long result= db.insert("usuario",null,values);
        if (result==-1) return false;
        else
            return true;
    }
    public Boolean verificarUsuario(String usuario){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from usuario where usuario=?", new String[]{usuario});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean verificarPassword(String usuario, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from usuario where usuario=? and password=?", new String[]{usuario,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
