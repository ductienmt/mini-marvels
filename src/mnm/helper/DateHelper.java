/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mnm.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class DateHelper {
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    // Chuyển đổi từ chuỗi sang Date
    public static Date convertStringToDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            java.util.Date parsedDate = dateFormat.parse(dateString);
            return new Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Chuyển đổi từ Date sang chuỗi
    public static String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(date);
    }
}
