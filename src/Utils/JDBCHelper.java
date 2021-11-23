/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thanh_rdcaeo8
 */
public class JDBCHelper {

    static String drive = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String url = "jdbc:sqlserver://localhost:1433;databaseName=QLQuanBi-A";
    static String user = "sa";
    static String pass = "123";

    static {
        try {
            Class.forName(drive);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
    public static PreparedStatement getStmt(String sql, Object...args) throws SQLException{
        Connection con = DriverManager.getConnection(url, user, pass);
        PreparedStatement stmt;
        if(sql.trim().startsWith("{")){
            stmt = con.prepareCall(sql);
        }else{
            stmt = con.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
        return stmt;
    }
    public static int update(String sql, Object...args){
        try {
            PreparedStatement stmt = JDBCHelper.getStmt(sql, args);
            try{
                return stmt.executeUpdate();
            }
            finally{
                stmt.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ResultSet query(String sql, Object...args) throws SQLException{
        PreparedStatement stmt = JDBCHelper.getStmt(sql, args);
        return stmt.executeQuery();
    }
    public static Object value(String sql, Object...args){
        try{
            ResultSet rs = JDBCHelper.query(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
