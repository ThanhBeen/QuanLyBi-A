/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entyti.DonHangBan;
import Entyti.DonHangDV;
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
public class DonHangDVDAO extends QuanLyBiADAO<DonHangDV, String>{

    String insert = "Insert into DonHangDV Values(?, ?, ?, ?, ?, ?)";
    String comboBoxMaDV = "Select MaDV from DichVu";
    String selectById = "Select * from DonHangDV Where maDHDV = ?";
    String fillDonGia = "select gia from DichVu where MaDV like ?";
    String fillTenDV = "select ten from DichVu where MaDV like ?";
    String selectAll = "Select * from DonHangDV";
    String up = "update DonHangDV set [TrangThai] = N'Đang xử lý' where MaDHDV = ?";
    String up1 = "update DonHangDV set [TrangThai] = N'Đã thanh toán' where MaDHDV = ?";
    
    @Override
    public void insert(DonHangDV entity) {
        JDBCHelper.update(insert, entity.getMaDHDV(), entity.getMaDV(), 
                entity.getGia(), entity.getSoLuong(), entity.getTongTien(), entity.getTrangThai());
    }

    @Override
    public void update(DonHangDV entity) {
        JDBCHelper.update(up, entity.getMaDHDV());
    }
    
    public void update1(DonHangDV entity) {
        JDBCHelper.update(up1, entity.getMaDHDV());
    }
    
    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DonHangDV> selectAll() {
        return selectBySql(selectAll);
    }

    @Override
    public DonHangDV selectById(String id) {
        List<DonHangDV> list = selectBySql(selectById, id);
            //Kiểm tra nếu list rỗng thì trả về null, ngược lại trả về 1 phần tử get(0), 
            //vì đây là truy vấn theo id nên chỉ trả về 1 giá trị
            if(list.isEmpty()){
                return null;
            }
            return list.get(0); 
    }

    @Override
    protected List<DonHangDV> selectBySql(String sql, Object... args) {
        List<DonHangDV> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while(rs.next()){
                DonHangDV entity = new DonHangDV();
                entity.setMaDHDV(rs.getString(1));
                entity.setMaDV(rs.getString(2));
                entity.setGia(rs.getFloat(3));
                entity.setSoLuong(rs.getInt(4));
                entity.setTongTien(rs.getFloat(5));
                entity.setTrangThai(rs.getString(6));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void comboBoxMaNV(JComboBox cboMaDV) throws SQLException{
        ResultSet rs = JDBCHelper.query(comboBoxMaDV);
        cboMaDV.removeAllItems();
        //cboMaNV.addItem("Chọn mã dịch vụ");
        while(rs.next()){
            cboMaDV.addItem(rs.getString(1));
        }
    }
    public String fillDonGia(JComboBox cboMaDV) throws SQLException{
        ResultSet rs = JDBCHelper.query(fillDonGia, cboMaDV.getSelectedItem());
        if(rs.next()){
            return rs.getString(1);
        }
         return null;
    }
    
    public String fillTen(JComboBox cboMaDV) throws SQLException{
        ResultSet rs = JDBCHelper.query(fillTenDV, cboMaDV.getSelectedItem());
        if(rs.next()){
            return rs.getString(1);
        }
         return null;
    }
}
