/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entyti;

/**
 *
 * @author thanh
 */
public class DonHangBan {
    private String maDHB;
    private int maBan;
    private String thoiGianBatDau, thoiGianKetThuc;
    private float gioThue, gia, tongTien;

    public DonHangBan() {
    }

    public DonHangBan(String maDHB, int maBan, String thoiGianBatDau, String thoiGianKetThuc, float gioThue, float gia, float tongTien) {
        this.maDHB = maDHB;
        this.maBan = maBan;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.gioThue = gioThue;
        this.gia = gia;
        this.tongTien = tongTien;
    }

    public String getMaDHB() {
        return maDHB;
    }

    public void setMaDHB(String maDHB) {
        this.maDHB = maDHB;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public String getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(String thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public String getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(String thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public float getGioThue() {
        return gioThue;
    }

    public void setGioThue(float gioThue) {
        this.gioThue = gioThue;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
    
}
