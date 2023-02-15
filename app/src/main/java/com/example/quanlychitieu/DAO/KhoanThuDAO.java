package com.example.quanlychitieu.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlychitieu.Database.DBHelper;
import com.example.quanlychitieu.Model.KhoanChi;
import com.example.quanlychitieu.Model.KhoanThu;
import com.example.quanlychitieu.Model.LoaiThu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class KhoanThuDAO {
    DBHelper dbHelper;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    public KhoanThuDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    //lấy danh sách khoản thu
    public ArrayList<KhoanThu> getAll()  {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<KhoanThu> khoanThuArrayList = new ArrayList<>();
//        String SELECT = "SELECT * FROM " + KhoanThu.TB_NAME;
//        Cursor cursor =db.rawQuery(SELECT,null);
        String order ="ngayThu DESC";
        Cursor cursor =db.query(KhoanThu.TB_NAME , null , null,null,null,null,order);
        //dua con tro ve dau ket qua
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id1 = cursor.getInt(0);
            int id2 = cursor.getInt(1);
            String ten = cursor.getString(2);
            String noiDung = cursor.getString(3);
            float soTien = cursor.getFloat(4);
            Date ngay = null;
            try {
                ngay = format.parse(cursor.getString(5));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            KhoanThu khoanThu = new KhoanThu(id2,id1,ten,noiDung,soTien,ngay);
            khoanThuArrayList.add(khoanThu);
            cursor.moveToNext();
        }
        cursor.close();//dong con tro
        db.close();//dong csdl
        return khoanThuArrayList;
    }
    //tìm kiếm theo tên khoản thu
    public ArrayList<KhoanThu> getByName(String key)  {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<KhoanThu> khoanThuArrayList = new ArrayList<>();
        String whereClause = "tenKhoanThu like ?";
        String[] whereArgs = {"%" + key + "%"};
        Cursor cursor = db.query("tb_khoanthu",null,whereClause,whereArgs,null,null,null);
        //dua con tro ve dau ket qua
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id1 = cursor.getInt(0);
            int id2 = cursor.getInt(1);
            String ten = cursor.getString(2);
            String noiDung = cursor.getString(3);
            float soTien = cursor.getFloat(4);
            Date ngay = null;
            try {
                ngay = format.parse(cursor.getString(5));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            KhoanThu khoanThu = new KhoanThu(id2,id1,ten,noiDung,soTien,ngay);
            khoanThuArrayList.add(khoanThu);
            cursor.moveToNext();
        }
        cursor.close();//dong con tro
        db.close();//dong csdl
        return khoanThuArrayList;
    }
    //lấy danh sách khoản chi ngày hôm nay
    public ArrayList<KhoanThu> getByDate(String ngayThu)  {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<KhoanThu> khoanThuArrayList = new ArrayList<>();
        String whereClause = "ngayThu like ?";
        String[] whereArgs = {ngayThu};
        Cursor cursor = db.query("tb_khoanthu",null,whereClause,whereArgs,null,null,null);
        //dua con tro ve dau ket qua
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id1 = cursor.getInt(0);
            int id2 = cursor.getInt(1);
            String ten = cursor.getString(2);
            String noiDung = cursor.getString(3);
            float soTien = cursor.getFloat(4);
            Date ngay = null;
            try {
                ngay = format.parse(cursor.getString(5));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            KhoanThu khoanThu = new KhoanThu(id2,id1,ten,noiDung,soTien,ngay);
            khoanThuArrayList.add(khoanThu);
            cursor.moveToNext();
        }
        cursor.close();//dong con tro
        db.close();//dong csdl
        return khoanThuArrayList;
    }
    //tìm kiếm theo spiner
    public ArrayList<KhoanThu> getBySpinner(String spinner)  {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<KhoanThu> khoanThuArrayList = new ArrayList<>();
        String whereClause = "idTenLoaiThu like ?";
        String[] whereArgs = { spinner};
        Cursor cursor = db.query("tb_khoanthu",null,whereClause,whereArgs,null,null,null);
        //dua con tro ve dau ket qua
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id1 = cursor.getInt(0);
            int id2 = cursor.getInt(1);
            String ten = cursor.getString(2);
            String noiDung = cursor.getString(3);
            float soTien = cursor.getFloat(4);
            Date ngay = null;
            try {
                ngay = format.parse(cursor.getString(5));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            KhoanThu khoanThu = new KhoanThu(id2,id1,ten,noiDung,soTien,ngay);
            khoanThuArrayList.add(khoanThu);
            cursor.moveToNext();
        }
        cursor.close();//dong con tro
        db.close();//dong csdl
        return khoanThuArrayList;
    }

    //tìm kiếm theo ngày
    public ArrayList<KhoanThu> getByDateFromTo(String from, String to)  {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<KhoanThu> khoanThuArrayList = new ArrayList<>();
        String whereClause = "ngayThu BETWEEN ? AND ?";
        String[] whereArgs = {from.trim(),to.trim()};
        Cursor cursor = db.query("tb_khoanthu",null,whereClause,whereArgs,null,null,null);
        //dua con tro ve dau ket qua
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id1 = cursor.getInt(0);
            int id2 = cursor.getInt(1);
            String ten = cursor.getString(2);
            String noiDung = cursor.getString(3);
            float soTien = cursor.getFloat(4);
            Date ngay = null;
            try {
                ngay = format.parse(cursor.getString(5));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            KhoanThu khoanThu = new KhoanThu(id2,id1,ten,noiDung,soTien,ngay);
            khoanThuArrayList.add(khoanThu);
            cursor.moveToNext();
        }
        cursor.close();//dong con tro
        db.close();//dong csdl
        return khoanThuArrayList;
    }

    //thêm khoản thu
    public long insert(KhoanThu khoanThu){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KhoanThu.COL_NAME_ID_FK,khoanThu.getIdTenLoaiThu());
        contentValues.put(KhoanThu.COL_NAME_TEN,khoanThu.getTenKhoanThu());
        contentValues.put(KhoanThu.COL_NAME_NOIDUNG,khoanThu.getNoiDung());
        contentValues.put(KhoanThu.COL_NAME_SOTIEN,khoanThu.getSoTien());
        contentValues.put(KhoanThu.COL_NAME_NGAYTHU,format.format(khoanThu.getNgayThu()));
        long result=db.insert(KhoanThu.TB_NAME,null,contentValues);
        return result;
    }
    //sửa khoản thu
    public long update(KhoanThu khoanThu){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KhoanThu.COL_NAME_ID_FK,khoanThu.getIdTenLoaiThu());
        contentValues.put(KhoanThu.COL_NAME_TEN,khoanThu.getTenKhoanThu());
        contentValues.put(KhoanThu.COL_NAME_NOIDUNG,khoanThu.getNoiDung());
        contentValues.put(KhoanThu.COL_NAME_SOTIEN,khoanThu.getSoTien());
        contentValues.put(KhoanThu.COL_NAME_NGAYTHU,format.format(khoanThu.getNgayThu()));
        long result=db.update(KhoanThu.TB_NAME,contentValues,"idKhoanThu =? ",new String[]{String.valueOf(khoanThu.getIdKhoanThu())});
        return result;
    }
    //xóa khoản thu
    public long delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = db.delete(KhoanThu.TB_NAME,"idKhoanThu = " + id,null);
        return result;
    }

    public ArrayList<KhoanThu> getKhoanThuTheoDK(String sql, String... a) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<KhoanThu> khoanThuArrayList = new ArrayList<>();
        Cursor cursor =db.rawQuery(sql,a);
        //dua con tro ve dau ket qua
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id1 = cursor.getInt(0);
            int id2 = cursor.getInt(1);
            String ten = cursor.getString(2);
            String noiDung = cursor.getString(3);
            float soTien = cursor.getFloat(4);
            Date ngay = null;
            try {
                ngay = format.parse(cursor.getString(5));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            KhoanThu khoanThu = new KhoanThu(id2,id1,ten,noiDung,soTien,ngay);
            khoanThuArrayList.add(khoanThu);
            cursor.moveToNext();
        }
        cursor.close();//dong con tro
        db.close();//dong csdl
        return khoanThuArrayList;
    }

}
