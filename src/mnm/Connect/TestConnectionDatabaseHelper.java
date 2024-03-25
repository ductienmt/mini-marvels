/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mnm.Connect;

import java.sql.Connection;
/**
 *
 * @author Duc Tien
 */
public class TestConnectionDatabaseHelper {
    public static void main(String[] args) {
        Connection conn = DatabaseHelper.getDBConnect();
        if(conn != null){
            System.out.println("Connect thành công");
        } else {
            System.out.println("Connect thất bại");
        }
    }
}
