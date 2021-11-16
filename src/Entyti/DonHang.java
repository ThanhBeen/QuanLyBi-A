/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entyti;

/**
 *
 * @author thanh
 */
public class DonHang {
    private String maDH, maKH, maDHDV, maDHB, maNV;
    private String ngayTao;
    private int khuyenMai;
    private float tongTien;
    private String ghiChu;

    public DonHang() {
    }

    public DonHang(String maDH, String maKH, String maDHDV, String maDHB, String maNV, String ngayTao, int khuyenMai, float tongTien, String ghiChu) {
        this.maDH = maDH;
        this.maKH = maKH;
        this.maDHDV = maDHDV;
        this.maDHB = maDHB;
        this.maNV = maNV;
        this.ngayTao = ngayTao;
        this.khuyenMai = khuyenMai;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
    }

    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaDHDV() {
        return maDHDV;
    }

    public void setMaDHDV(String maDHDV) {
        this.maDHDV = maDHDV;
    }

    public String getMaDHB() {
        return maDHB;
    }

    public void setMaDHB(String maDHB) {
        this.maDHB = maDHB;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
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
