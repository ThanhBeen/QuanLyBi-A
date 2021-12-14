package DAO;

import Utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author thanh_rdcaeo8
 */
public class ThongKeDAO {
    private List<Object[]> getListOfArray(String sql, String[] cols, Object...args){
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.query(sql, args);
            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Object[]> getDichVu(){
        String sql = "{CALL sp_DVBanChayNhat}";
        String[] cols = {"MaDV", "Ten", "Gia", "Số lượng", "Doanh thu"};
        return getListOfArray(sql, cols);
    } 
    
    public List<Object[]> getNguoiChoi(){
        String sql = "{CALL sp_LuongNguoiChoiTheoThang}";
        String[] cols = {"Tháng", "Số lượng KH"};
        return getListOfArray(sql, cols);
    } 
    
    public List<Object[]> getNhanVien(){
        String sql = "{CALL sp_NhanVienXS}";
        String[] cols = {"MaNV", "HoTen", "SDT", "Số lượng ĐH"};
        return getListOfArray(sql, cols);
    } 
    
    public List<Object[]> getDoanhThu(Integer thang){
        String sql = "{CALL sp_DoanhThuTheoThang(?)}";
        String[] cols = {"MaBan", "LoaiBan", "Số lượng KH", "Doanh thu"};
        return getListOfArray(sql, cols, thang);
    } 
}
