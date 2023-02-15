package com.example.quanlychitieu.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlychitieu.Database.DBHelper;
import com.example.quanlychitieu.Model.KhoanChi;
import com.example.quanlychitieu.Model.LoaiChi;

import java.util.ArrayList;

public class LoaiChiDAO {
    DBHelper dbHelper;

    public LoaiChiDAO(Context context) {
        dbHelper = new DBHelper(context);

    }

    //lay danh sach
    public ArrayList<LoaiChi> getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<LoaiChi> loaiChiArrayList = new ArrayList<>();
        String SELECT = "SELECT * FROM " + LoaiChi.TB_NAME;
        Cursor cursor = db.rawQuery(SELECT, null);
        //dua con tro ve dau ket qua
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            LoaiChi loaiChi = new LoaiChi(id, ten);
            loaiChiArrayList.add(loaiChi);
            cursor.moveToNext();
        }
        cursor.close();//dong con tro
        db.close();//dong csdl
        return loaiChiArrayList;
    }

    //Them
    public long insert(LoaiChi loaiChi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LoaiChi.COL_NAME_TEN, loaiChi.getTenLoaiChi());
        long result = db.insert(LoaiChi.TB_NAME, null, contentValues);
        return result;
    }

    //sua
    public long update(LoaiChi loaiChi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LoaiChi.COL_NAME_TEN, loaiChi.getTenLoaiChi());
        long result = db.update(LoaiChi.TB_NAME, contentValues, "idTenLoaiChi = " + loaiChi.getIdTenLoaiChi(), null);
        return result;
    }

    //xoa
    public long delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = db.delete(LoaiChi.TB_NAME, "idTenLoaiChi = " + id, null);
        return result;
    }

    //lay danh sach theo dieu kien
    public ArrayList<LoaiChi> getLoaiChiTheoDK(String sql, String... a) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<LoaiChi> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, a);
        //dua con tro ve dau ket qua
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            LoaiChi loaiChi = new LoaiChi(id, ten);
            list.add(loaiChi);
            cursor.moveToNext();
        }
        cursor.close();//dong con tro
        db.close();//dong csdl
        return list;
    }

    //Lấy tên loai chi theo id loai chi
    public String getTenLoaiChi(int id) {
        String sql = "SELECT * FROM tb_loaichi WHERE idTenLoaiChi=?";
        ArrayList<LoaiChi> list = getLoaiChiTheoDK(sql, String.valueOf(id));
        return list.get(0).getTenLoaiChi();
    }
    //xóa loại chi thì khoản chi sẽ bị xóa theo
    public long delete_LienKet(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result1 = db.delete(LoaiChi.TB_NAME, "idTenLoaiChi = " + id, null);
        long result2 = db.delete(KhoanChi.TB_NAME, "idTenLoaiChi = " + id, null);
        return result1;
    }
    public ArrayList<LoaiChi> checkGetIDLoaiChi(int id){
        String sql = "SELECT * FROM tb_loaichi as lc INNER JOIN tb_khoanchi as kc ON lc.idTenLoaiChi = kc.idTenLoaiChi WHERE lc.idTenLoaiChi=?";
        ArrayList<LoaiChi> list = getLoaiChiTheoDK(sql, String.valueOf(id));
        return list;
    }
}
