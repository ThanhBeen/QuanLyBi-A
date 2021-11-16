/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entyti;

/**
 *
 * @author thanh
 */
public class DichVu {
    private String maDV, ten;
    private float gia;
    private String donVi;
    private int soLuong;
    private String hinh;

    public DichVu() {
    }

    public DichVu(String maDV, String ten, float gia, String donVi, int soLuong, String hinh) {
        this.maDV = maDV;
        this.ten = ten;
        this.gia = gia;
        this.donVi = donVi;
        this.soLuong = soLuong;
        this.hinh = hinh;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
    
}
