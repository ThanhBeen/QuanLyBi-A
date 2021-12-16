/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entyti.DonHang;
import Entyti.KhachHang;
import Utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author thanh
 */
public class DonHangDAO extends QuanLyBiADAO<DonHang, String> {

    String insert = "Insert into DonHang values(?, ?, ?, ?, ?, ?, ?, ?)";
    String selectById = "Select * from DonHang Where maDH = ?";
    String comboBoxMaKH = "Select MaKH from KhachHang";
    String comboBoxMaDHDV = "Select MaDHDV from DonHangDV";
    String comboBoxMaDHB = "Select MaDHB from DonHangBan";
    String comboBoxMaNV = "Select MaNV from NhanVien";
    String tienBan = "select tongtien from donhangban where MaDHB = ?";
    String tienDV = "select tongtien from donhangdv where MaDHDV = ?";
    String checkTrangThai = "SELECT TRANGTHAI FROM DONHANGBAN WHERE MADHB = ?";
    String checkTrangThai1 = "SELECT TRANGTHAI FROM DONHANGDV WHERE MaDHDV = ?";

    @Override
    public void insert(DonHang entity) {
        JDBCHelper.update(insert, entity.getMaDH(), entity.getMaKH(), entity.getMaDHDV(),
                entity.getMaDHB(), entity.getMaNV(), entity.getNgayTao(), entity.getKhuyenMai(), entity.getTongTien());
    }

    @Override
    public void update(DonHang entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DonHang> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DonHang selectById(String id) {
        List<DonHang> list = selectBySql(selectById, id);
        //Kiểm tra nếu list rỗng thì trả về null, ngược lại trả về 1 phần tử get(0), 
        //vì đây là truy vấn theo id nên chỉ trả về 1 giá trị
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<DonHang> selectBySql(String sql, Object... args) {
        List<DonHang> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                DonHang entity = new DonHang();
                entity.setMaDH(rs.getString(1));
                entity.setMaKH(rs.getString(2));
                entity.setMaDHDV(rs.getString(3));
                entity.setMaDHB(rs.getString(4));
                entity.setMaNV(rs.getString(5));
                entity.setNgayTao(rs.getString(6));
                entity.setKhuyenMai(rs.getInt(7));
                entity.setTongTien(rs.getFloat(8));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String checkTrangThai(String trangThai) throws SQLException {
        ResultSet rs = JDBCHelper.query(checkTrangThai, trangThai);
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }
    
    public String checkTrangThai1(String trangThai1) throws SQLException {
        ResultSet rs = JDBCHelper.query(checkTrangThai1, trangThai1);
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    /*public String checkTrangThai(JComboBox cboMaDHB) throws SQLException{
        ResultSet rs = JDBCHelper.query(checkTrangThai, cboMaDHB.getSelectedItem());
        if(rs.next()){
            return rs.getString(1);
        }
         return null;
    }*/

    public void comboBoxMaDHDV(JComboBox cboMaDHDV) throws SQLException {
        ResultSet rs = JDBCHelper.query(comboBoxMaDHDV);
        cboMaDHDV.removeAllItems();
        while (rs.next()) {
            cboMaDHDV.addItem(rs.getString(1));
        }
    }

    public void comboBoxMaDHB(JComboBox cboMaDHB) throws SQLException {
        ResultSet rs = JDBCHelper.query(comboBoxMaDHB);
        cboMaDHB.removeAllItems();
        while (rs.next()) {
            cboMaDHB.addItem(rs.getString(1));
        }
    }

    public void comboBoxMaKH(JComboBox cboMaKH) throws SQLException {
        ResultSet rs = JDBCHelper.query(comboBoxMaKH);
        cboMaKH.removeAllItems();
        cboMaKH.addItem("Chọn mã khách hàng");
        while (rs.next()) {
            cboMaKH.addItem(rs.getString(1));
        }
    }

    public void comboBoxMaNV(JComboBox cboMaNV) throws SQLException {
        ResultSet rs = JDBCHelper.query(comboBoxMaNV);
        cboMaNV.removeAllItems();
        cboMaNV.addItem("Chọn mã nhân viên");
        while (rs.next()) {
            cboMaNV.addItem(rs.getString(1));
        }
    }

    public float tienBan(JComboBox cboMaDHB) throws SQLException {
        ResultSet rs = JDBCHelper.query(tienBan, cboMaDHB.getSelectedItem());
        while (rs.next()) {
            return rs.getFloat(1);
        }
        return -1;
    }

    public float tienDV(JComboBox cboMaDHDV) throws SQLException {
        ResultSet rs = JDBCHelper.query(tienDV, cboMaDHDV.getSelectedItem());
        while (rs.next()) {
            return rs.getFloat(1);
        }
        return -1;
    }

    public List<Integer> selectMonths() {
        String sql = "SELECT Distinct MONTH(NGAYTAO) Month FROM DONHANG ORDER BY Month";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
