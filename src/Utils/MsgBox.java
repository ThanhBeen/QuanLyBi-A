/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author thanh_rdcaeo8
 */
public class MsgBox {
    //Hiển thị thông báo cho người dùng, parent là cửa sổ thông báo, message là thông báo
    public static void alert(Component parent, String message ){
        JOptionPane.showMessageDialog(parent, message, "Hệ thống quản lý đào tạo",
                JOptionPane.INFORMATION_MESSAGE);
    }
    //Hiển thị thông báo và yêu cầu người dùng xác nhận, parent là cửa sổ thông báo, 
    //message là thông báo, return là trả về kết quả nhận đc
    public static boolean comfirm(Component parent, String message){
        int result = JOptionPane.showConfirmDialog(parent, message," Hệ thống quản lý đào tạo", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }     
    public static String prompt(Component parent, String message){
        return JOptionPane.showInputDialog(parent, message, "Hệ thống quản lý đào tạo", 
                JOptionPane.INFORMATION_MESSAGE);
    }
}
