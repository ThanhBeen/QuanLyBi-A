/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entyti.KhachHang;
import Entyti.NhanVien;
import Utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thanh
 */
public class KhachHangDAO extends QuanLyBiADAO<KhachHang, String>{
    String insert = "Insert into KhachHang values(?, ?, ?, ?, ?, ?)";
    String update = "Update  KhachHang set HoTen = ?, GioiTinh = ?, SDT = ?, "
            + "MaNV = ?, NgayDK = ? where maKH like ?";
    String delete = "Delete from KhachHang where maKH = ?";
    String selectAll = "select * from KhachHang";
    String selectById = "Select * from KhachHang Where maKH = ?";

    @Override
    public void insert(KhachHang entity) {
        JDBCHelper.update(insert, entity.getMaKH(), entity.getHoTen(),
                entity.getGioiTinh(), entity.getSdt(), entity.getMaNV(), entity.getNgayDK());
    }

    @Override
    public void update(KhachHang entity) {
        JDBCHelper.update(update, entity.getHoTen(),entity.getGioiTinh(), entity.getSdt(),
                entity.getMaNV(), entity.getNgayDK(), entity.getMaKH());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update(delete, id);
    }

    @Override
    public List<KhachHang> selectAll() {
        return selectBySql(selectAll);
    }
        

    @Override
    public KhachHang selectById(String id) {
        List<KhachHang> list = selectBySql(selectById, id);
            //Kiểm tra nếu list rỗng thì trả về null, ngược lại trả về 1 phần tử get(0), 
            //vì đây là truy vấn theo id nên chỉ trả về 1 giá trị
            if(list.isEmpty()){
                return null;
            }
            return list.get(0);    
    }

    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while(rs.next()){
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getString(1));
                entity.setHoTen(rs.getString(2));
                entity.setGioiTinh(rs.getString(3));
                entity.setSdt(rs.getString(4));
                entity.setMaNV(rs.getString(5));
                entity.setNgayDK(rs.getDate(6));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
}
