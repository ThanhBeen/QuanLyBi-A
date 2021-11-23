/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entyti;

/**
 *
 * @author thanh_rdcaeo8
 */
public class NhanVien {
    private String maNV, matKhau, hoTen, gioiTinh, sdt;
    private boolean vaiTro;
    private String email, hinh;

    public NhanVien() {
    }

    public NhanVien(String maNV, String matKhau, String hoTen, String goiTinh, 
            String sdt, boolean vaiTro, String email, String hinh) {
        this.maNV = maNV;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.gioiTinh = goiTinh;
        this.sdt = sdt;
        this.vaiTro = vaiTro;
        this.email = email;
        this.hinh = hinh;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGoiTinh() {
        return gioiTinh;
    }

    public void setGoiTinh(String goiTinh) {
        this.gioiTinh = goiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
    
    
    
    @Override
    public String toString(){
        return this.hoTen;
    }
}
