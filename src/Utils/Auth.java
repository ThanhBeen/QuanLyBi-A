/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Entyti.NhanVien;

/**
 *
 * @author thanh_rdcaeo8
 */
public class Auth {
    //Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
    public static NhanVien user = null;
    //Xóa thông tin người dùng khi họ đăng xuất
    public static void clear(){
        Auth.user = null;
    }
    
    //Kiểm tra đăng nhập hay chưa
    public static boolean isLogin(){
        return Auth.user != null;
    }
    
    public static boolean isManager(){
        return Auth.isLogin() && user.isVaiTro();
    }
}
