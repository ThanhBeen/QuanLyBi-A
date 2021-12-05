/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entyti.DonHangBan;
import Entyti.KhachHang;
import Utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author thanh
 */
public class DonHangBanDAO extends QuanLyBiADAO<DonHangBan, String>{
    
    String insert = "Insert into DonHangBan Values(?, ?, ?, ?, ?, ?, ?)";
    String selectById = "Select * from DonHangBan Where maDHB = ?";
    String comboBoxMaBan = "Select MaBan from Ban";
    String fillDonGia = "select gia from DonHangBan where MaBan = ?";
    
    @Override
    public void insert(DonHangBan entity) {
        JDBCHelper.update(insert, entity.getMaDHB(), entity.getMaBan(), entity.getThoiGianBatDau(), 
                entity.getThoiGianKetThuc(), entity.getGioThue(), entity.getGia(), entity.getTongTien());
    }

    @Override
    public void update(DonHangBan entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DonHangBan> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DonHangBan selectById(String id) {
        List<DonHangBan> list = selectBySql(selectById, id);
            //Kiểm tra nếu list rỗng thì trả về null, ngược lại trả về 1 phần tử get(0), 
            //vì đây là truy vấn theo id nên chỉ trả về 1 giá trị
            if(list.isEmpty()){
                return null;
            }
            return list.get(0);  
    }

    @Override
    protected List<DonHangBan> selectBySql(String sql, Object... args) {
        List<DonHangBan> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while(rs.next()){
                DonHangBan entity = new DonHangBan();
                entity.setMaDHB(rs.getString(1));
                entity.setMaBan(rs.getInt(2));
                entity.setThoiGianBatDau(rs.getString(3));
                entity.setThoiGianKetThuc(rs.getString(4));
                entity.setGioThue(rs.getFloat(5));
                entity.setGia(rs.getFloat(6));
                entity.setTongTien(rs.getFloat(7));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public void comboBoxMaBan(JComboBox cboMaNV) throws SQLException{
        ResultSet rs = JDBCHelper.query(comboBoxMaBan);
        cboMaNV.removeAllItems();
        //wcboMaNV.addItem("Chọn mã bàn");
        while(rs.next()){
            cboMaNV.addItem(rs.getString(1));
        }
    }
    public String fillDonGia(JComboBox cboMaNV) throws SQLException{
        ResultSet rs = JDBCHelper.query(fillDonGia, cboMaNV.getSelectedItem());
        if(rs.next()){
            return rs.getString(1);
        }
         return null;
    }
}
