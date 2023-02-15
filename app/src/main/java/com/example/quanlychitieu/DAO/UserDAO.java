package com.example.quanlychitieu.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.core.app.NavUtils;

import com.example.quanlychitieu.Database.DBHelper;
import com.example.quanlychitieu.Model.User;

import java.util.ArrayList;

public class UserDAO {
    DBHelper dbHelper;
    public UserDAO(Context context){
        dbHelper = new DBHelper(context);
    }
//    //đọc
//    public ArrayList<User> getAll(){
//        //tạo danh sách user
//        ArrayList<User> userArrayList = new ArrayList<>();
//        //tạo databas để dùng
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        //tạo con trỏ để lấy dữ liệu (cursor)
//        Cursor cursor = db.rawQuery("SELECT * FROM user",null);
//        //đưa con trỏ về đầu kết quả
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            int idUser = cursor.getInt(0);
//            String email = cursor.getString(1);
//            String password = cursor.getString(2);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return null;
//    }
    //check_thêm
    public Boolean insertData(String email, String password){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = db.insert("user",null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    //kiểm tra email tồn tại hay chưa
    public boolean checkemail(String email){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email = ? ",new String[] {email});
        if (cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //kiểm tra tài khỏan
    public boolean checkuser(String email, String password ){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email = ? and password = ? ",new String[] {email,password});
        if (cursor.getCount()>0)
        {
            return  true;
        }
        else
        {
            return false;
        }
    }
    //sửa
    public void update(){

    }
    //xóa
    public  void delete(){

    }


}
