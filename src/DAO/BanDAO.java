/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entyti.Ban;
import Utils.JDBCHelper;
import Entyti.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author thanh_rdcaeo8
 */
public class BanDAO extends QuanLyBiADAO<Ban, Integer>{
    String insert = "Insert into Ban Values(?, ?, ?, ?)";
    String update = "Update  Ban set TenBan = ?, LoaiBan = ?, TrangThai = ? where maBan like ?";
    String delete = "Delete from Ban where maBan = ?";
    String selectAll = "select * from Ban";
    String selectById = "Select * from Ban Where maBan = ?";
    String up = "Update Ban set TrangThai = 'Đang thuê' Where MaBan = ?";
    String up1 = "Update Ban set TrangThai = N'Trống' Where MaBan = ?";
    String set = "select trangthai from ban where maban = ?";


    @Override
    public void insert(Ban entity) {
        JDBCHelper.update(insert, entity.getMaBan(), entity.getTenBan(), entity.getLoaiBan(), entity.getTrangThai());
    }

    @Override
    public void update(Ban entity) {
        JDBCHelper.update(up, entity.getMaBan());
    }
    
    public void update1(Ban entity) {
        JDBCHelper.update(up1, entity.getMaBan());
    }

    @Override
    public void delete(Integer id) {
        JDBCHelper.update(delete, id);
    }

    @Override
    public List<Ban> selectAll() {
        return selectBySql(selectAll);
    }

    @Override
    public Ban selectById(Integer id) {
        List<Ban> list = selectBySql(selectById, id);
            //Kiểm tra nếu list rỗng thì trả về null, ngược lại trả về 1 phần tử get(0), 
            //vì đây là truy vấn theo id nên chỉ trả về 1 giá trị
            if(list.isEmpty()){
                return null;
            }
            return list.get(0); 
    }
     public String setTrangThai(String id) throws SQLException{
        ResultSet rs = JDBCHelper.query(set, id);
        while(rs.next()){
           return rs.getString(1);
        }
        return null;
    }

    @Override
    protected List<Ban> selectBySql(String sql, Object... args) {
        List<Ban> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while(rs.next()){
                Ban entity = new Ban();
                entity.setMaBan(rs.getInt(1));
                entity.setTenBan(rs.getString(2));
                entity.setLoaiBan(rs.getString(3));
                entity.setTrangThai(rs.getString(4));

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
