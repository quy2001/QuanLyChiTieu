package com.example.quanlychitieu.Model;

public class LoaiThu {
    public int idTenLoaiThu;
    public String tenLoaiThu;

    public static  final String TB_NAME = "tb_loaithu";
    public static  final String COL_NAME_ID = "idTenLoaiThu";
    public static  final String COL_NAME_TEN = "tenLoaiThu";

    public LoaiThu(int idTenLoaiThu, String tenLoaiThu) {
        this.idTenLoaiThu = idTenLoaiThu;
        this.tenLoaiThu = tenLoaiThu;
    }

    public LoaiThu ()
    {

    }

    public int getIdTenLoaiThu() {
        return idTenLoaiThu;
    }

    public void setIdTenLoaiThu(int idTenLoaiThu) {
        this.idTenLoaiThu = idTenLoaiThu;
    }

    public String getTenLoaiThu() {
        return tenLoaiThu;
    }

    public void setTenLoaiThu(String tenLoaiThu) {
        this.tenLoaiThu = tenLoaiThu;
    }

    @Override
    public String toString() {
        return getTenLoaiThu();
    }
}
