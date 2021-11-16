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
    
}
