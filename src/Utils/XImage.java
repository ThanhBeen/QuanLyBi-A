/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author thanh_rdcaeo8
 */
public class XImage {
    public static Image getAppIcon(){
        URL url = XImage.class.getResource("/edusys/icon/fpt.png");
        return new ImageIcon(url).getImage();
    }
    public static boolean save(File src){
        File dst = new File("D:\\edusys\\iconnnnn", src.getName());
        //Nếu thư mục iconnnn chưa tồn tại thì tạo 1 thư mục mới
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());//getAbsolute đường dẫn tuyệt đối
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            return true;
            //Files.copy(from, to, StadardCopyOption.);
        } catch (Exception e) {
            return false;
        }
    }
    public static ImageIcon read(String fileName){
        File path = new File("D:\\edusys\\iconnnnn", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
