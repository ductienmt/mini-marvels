/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mnm.Connect;
import java.sql.*;

/**
 *
 * @author Duc Tien
 */
public class DatabaseHelper {
    public static final String connectionUrl = "jdbc:sqlserver://localhost:1433;trustServerCertificate=true;"
            + "databaseName=MiniMarvels;user=sa;password=1730310522";
    public static Connection getDBConnect(){
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            System.err.println("Chưa có Driver !" + e.toString());
        }
        try {
            conn = DriverManager.getConnection(connectionUrl);
            return conn;
        } catch (Exception e) {
            System.out.println("Lỗi connect " + e.toString());
        }
        return null;
    }
}

