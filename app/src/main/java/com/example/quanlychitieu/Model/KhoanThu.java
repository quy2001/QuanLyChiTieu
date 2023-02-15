package com.example.quanlychitieu.Model;

import java.util.Date;

public class KhoanThu {
    public int idTenLoaiThu;
    public int idKhoanThu;
    public String tenKhoanThu;
    public String noiDung;
    public float soTien;
    public Date ngayThu;


    public static  final String TB_NAME = "tb_khoanthu";
    public static  final String COL_NAME_ID_PK = "idKhoanThu";
    public static  final String COL_NAME_ID_FK = "idTenLoaiThu";
    public static  final String COL_NAME_TEN = "tenKhoanThu";
    public static  final String COL_NAME_NOIDUNG = "noiDung";
    public static  final String COL_NAME_SOTIEN = "soTien";
    public static  final String COL_NAME_NGAYTHU = "ngayThu";

    public KhoanThu(int idTenLoaiThu, int idKhoanThu, String tenKhoanThu, String noiDung, float soTien, Date ngayThu) {
        this.idTenLoaiThu = idTenLoaiThu;
        this.idKhoanThu = idKhoanThu;
        this.tenKhoanThu = tenKhoanThu;
        this.noiDung = noiDung;
        this.soTien = soTien;
        this.ngayThu = ngayThu;
    }
    public KhoanThu(){

    }

    public int getIdTenLoaiThu() {
        return idTenLoaiThu;
    }

    public void setIdTenLoaiThu(int idTenLoaiThu) {
        this.idTenLoaiThu = idTenLoaiThu;
    }

    public int getIdKhoanThu() {
        return idKhoanThu;
    }

    public void setIdKhoanThu(int idKhoanThu) {
        this.idKhoanThu = idKhoanThu;
    }

    public String getTenKhoanThu() {
        return tenKhoanThu;
    }

    public void setTenKhoanThu(String tenKhoanThu) {
        this.tenKhoanThu = tenKhoanThu;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public float getSoTien() {
        return soTien;
    }

    public void setSoTien(float soTien) {
        this.soTien = soTien;
    }

    public Date getNgayThu() {
        return ngayThu;
    }

    public void setNgayThu(Date ngayThu) {
        this.ngayThu = ngayThu;
    }
}
