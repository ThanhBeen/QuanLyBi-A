/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entyti;

/**
 *
 * @author thanh
 */
public class DonHangDV {
    private String maDHDV, maDV;
    private float gia;
    private int soLuong;
    private float tongTien;
    private String ghiChu;

    public DonHangDV() {
    }

    public DonHangDV(String maDHDV, String maDV, float gia, int soLuong, float tongTien, String ghiChu) {
        this.maDHDV = maDHDV;
        this.maDV = maDV;
        this.gia = gia;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
    }

    public String getMaDHDV() {
        return maDHDV;
    }

    public void setMaDHDV(String maDHDV) {
        this.maDHDV = maDHDV;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
}
