/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Utils.JDBCHelper;
import Entyti.NhanVien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thanh_rdcaeo8
 */
public class NhanVienDAO extends QuanLyBiADAO<NhanVien, String>{
    String insert = "Insert into NhanVien Values(?, ?, ?, ?, ?, ?, ?, ?)";
    String update = "Update  NhanVien set MatKhau = ?, HoTen = ?, GioiTinh = ?, "
            + "SDT = ?, VaiTro = ?, Email = ?, hinh = ? where maNV like ?";
    String delete = "Delete from NhanVien where maNV = ?";
    String selectAll = "select * from NhanVien";
    String selectById = "Select * from NhanVien Where maNV = ?";


    @Override
    public void insert(NhanVien entity) {
        JDBCHelper.update(insert, entity.getMaNV(), entity.getMatKhau(), entity.getHoTen(),
                entity.getGioiTinh(), entity.getSdt(), entity.isVaiTro(), entity.getEmail(), entity.getHinh());
    }

    @Override
    public void update(NhanVien entity) {
        JDBCHelper.update(update, entity.getMatKhau(), entity.getHoTen(),entity.getGioiTinh(),
                entity.getSdt(), entity.isVaiTro(), entity.getEmail(), entity.getHinh(), entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update(delete, id);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySql(selectAll);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = selectBySql(selectById, id);
        //Kiểm tra nếu list rỗng thì trả về null, ngược lại trả về 1 phần tử get(0), 
        //vì đây là truy vấn theo id nên chỉ trả về 1 giá trị
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while(rs.next()){
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString(1));
                entity.setMatKhau(rs.getString(2));
                entity.setHoTen(rs.getString(3));
                entity.setGioiTinh(rs.getString(4));
                entity.setSdt(rs.getString(5));
                entity.setVaiTro(rs.getBoolean(6));
                entity.setEmail(rs.getString(7));
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
