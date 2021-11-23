/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entyti.DichVu;
import Utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author thanh
 */
public class DichVụDAO extends QuanLyBiADAO<DichVu, String>{
    String insert = "Insert into DichVu Values(?, ?, ?, ?, ?, ?)";
    String update = "Update  DichVu set Ten = ?, Gia = ?, DonVi = ?, "
            + "SoLuong = ?, Hinh = ? where maDV like ?";
    String delete = "Delete from DichVu where maDV = ?";
    String selectAll = "select * from DichVu";
    String selectById = "Select * from DichVu Where maDV = ?";
    
    @Override
    public void insert(DichVu entity) {
        JDBCHelper.update(insert, entity.getMaDV(), entity.getTen(), entity.getGia(),
                entity.getDonVi(), entity.getSoLuong(), entity.getHinh());
    }

    @Override
    public void update(DichVu entity) {
        JDBCHelper.update(update, entity.getTen(), entity.getGia(),
                entity.getDonVi(), entity.getSoLuong(), entity.getHinh(), entity.getMaDV());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update(delete, id);
    }

    @Override
    public List<DichVu> selectAll() {
        return selectBySql(selectAll);
    }

    @Override
    public DichVu selectById(String id) {
        List<DichVu> list = selectBySql(selectById, id);
        //Kiểm tra nếu list rỗng thì trả về null, ngược lại trả về 1 phần tử get(0), 
        //vì đây là truy vấn theo id nên chỉ trả về 1 giá trị
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<DichVu> selectBySql(String sql, Object... args) {
        List<DichVu> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while(rs.next()){
                DichVu entity = new DichVu();
                entity.setMaDV(rs.getString(1));
                entity.setTen(rs.getString(2));
                entity.setGia(rs.getFloat(3));
                entity.setDonVi(rs.getString(4));
                entity.setSoLuong(rs.getInt(5));
                entity.setHinh(rs.getString(6));
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
