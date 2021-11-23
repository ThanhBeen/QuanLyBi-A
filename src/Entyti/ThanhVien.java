/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entyti;

import java.util.Date;

/**
 *
 * @author thanh
 */
public class ThanhVien {
    private String maThe, maKH, maNV;
    private Date ngayDK;

    public ThanhVien() {
    }

    public ThanhVien(String maThe, String maKH, String maNV, Date ngayDK) {
        this.maThe = maThe;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngayDK = ngayDK;
    }

    public String getMaThe() {
        return maThe;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Date getNgayDK() {
        return ngayDK;
    }

    public void setNgayDK(Date ngayDK) {
        this.ngayDK = ngayDK;
    }
    
}
