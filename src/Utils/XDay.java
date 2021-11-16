/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author thanh_rdcaeo8
 */
public class XDay {
    static SimpleDateFormat formater = new SimpleDateFormat();
    public static Date toDate(String date, String pattern){
        try {
            formater.applyPattern(pattern);
            return formater.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //Chuyển đổi từ Date sang String, date là Date cần chuyển đổi, 
    //pattern là định dạng time, return String kết quả 
    public static String toString(Date date, String pattern){
        formater.applyPattern(pattern);
        return formater.format(date);
    }
    //Trả về time hiện tại
    public static Date now(){
        return new Date();
    }
    //Bổ sung số ngày vào time, date là time hiện có, days là số ngày cần bổ sung vào date, Date là kêt quả
    public static Date addDay(Date date, long days){
        date.setTime(date.getTime() + days * 24 * 60 *60 *1000);
        return date;
    }
}
