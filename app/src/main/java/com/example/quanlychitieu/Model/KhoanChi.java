package com.example.quanlychitieu.Model;

import java.util.Date;

public class KhoanChi {

    public int idTenLoaiChi;
    public int idKhoanChi;
    public String tenKhoanChi;
    public String noiDung;
    public float soTien;
    public Date ngayChi;


    public static  final String TB_NAME = "tb_khoanchi";
    public static  final String COL_NAME_ID_PK = "idKhoanChi";
    public static  final String COL_NAME_ID_FK = "idTenLoaiChi";
    public static  final String COL_NAME_TEN = "tenKhoanChi";
    public static  final String COL_NAME_NOIDUNG = "noiDung";
    public static  final String COL_NAME_SOTIEN = "soTien";
    public static  final String COL_NAME_NGAYCHI = "ngayChi";

    public KhoanChi(int idTenLoaiChi, int idKhoanChi, String tenKhoanChi, String noiDung, float soTien, Date ngayChi) {
        this.idTenLoaiChi = idTenLoaiChi;
        this.idKhoanChi = idKhoanChi;
        this.tenKhoanChi = tenKhoanChi;
        this.noiDung = noiDung;
        this.soTien = soTien;
        this.ngayChi = ngayChi;
    }
    public KhoanChi(){

    }

    public int getIdTenLoaiChi() {
        return idTenLoaiChi;
    }

    public void setIdTenLoaiChi(int idTenLoaiChi) {
        this.idTenLoaiChi = idTenLoaiChi;
    }

    public int getIdKhoanChi() {
        return idKhoanChi;
    }

    public void setIdKhoanChi(int idKhoanChi) {
        this.idKhoanChi = idKhoanChi;
    }

    public String getTenKhoanChi() {
        return tenKhoanChi;
    }

    public void setTenKhoanChi(String tenKhoanChi) {
        this.tenKhoanChi = tenKhoanChi;
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

    public Date getNgayChi() {
        return ngayChi;
    }

    public void setNgayChi(Date ngayChi) {
        this.ngayChi = ngayChi;
    }
}
