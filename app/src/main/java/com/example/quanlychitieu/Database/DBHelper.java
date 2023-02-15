package com.example.quanlychitieu.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "quanlychitieu.db";
    public static final int DB_VERSION = 1;

    public DBHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //TABLE USER
        String sql_user = "CREATE TABLE user(idUser INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email TEXT NOT NULL, password TEXT NOT NULL )";
        db.execSQL(sql_user);
        //TABLE LOAI CHI
        String sql_loai_chi = "CREATE TABLE tb_loaichi (idTenLoaiChi INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenLoaiChi TEXT NOT NULL)";
        db.execSQL(sql_loai_chi);
        //TABLE LOAI THU
        String sql_loai_thu = "CREATE TABLE tb_loaithu (idTenLoaiThu INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenLoaiThu TEXT NOT NULL)";
        db.execSQL(sql_loai_thu);
        //TABLE KHOAN CHI
        String sql_khoan_chi = "CREATE TABLE tb_khoanchi (idKhoanChi INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idTenLoaiChi INTEGER NOT NULL REFERENCES tb_loaichi(idTenLoaiChi), tenKhoanChi TEXT NOT NULL, noiDung TEXT NOT NULl, soTien FLOAT, ngayChi DATE)";
        db.execSQL(sql_khoan_chi);
        //TABLE KHOAN THU
        String sql_khoan_thu = "CREATE TABLE tb_khoanthu (idKhoanThu INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idTenLoaiThu INTEGER NOT NULL REFERENCES tb_loaithu(idTenLoaiThu), tenKhoanThu TEXT NOT NULL, noiDung TEXT NOT NULl, soTien FLOAT, ngayThu DATE)";
        db.execSQL(sql_khoan_thu);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_tb_user = "drop table if exists user";
        db.execSQL(drop_tb_user);
        String drop_tb_loai_chi = "drop table if exists tb_loaichi";
        db.execSQL(drop_tb_loai_chi);
        String drop_tb_loai_thu = "drop table if exists tb_loaithu";
        db.execSQL(drop_tb_loai_thu);
        String drop_tb_khoan_chi = "drop table if exists tb_khoanchi";
        db.execSQL(drop_tb_khoan_chi);
        String drop_tb_khoan_thu = "drop table if exists tb_khoanthu";
        db.execSQL(drop_tb_khoan_thu);
        onCreate(db);
    }
}
