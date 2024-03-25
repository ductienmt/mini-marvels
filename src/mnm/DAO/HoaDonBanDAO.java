/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mnm.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mnm.Connect.DatabaseHelper;

/**
 *
 * @author Duc Tien
 */
public class HoaDonBanDAO {
    
    private Connection conn;
    private PreparedStatement sttm;
    private ResultSet rs;
    
    public int getSoLuongDonHang() {
        int soLuong = 0;
        String sql = "SELECT COUNT(*) AS SoLuong FROM HoaDonBan";
        
        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            rs = sttm.executeQuery();
            
            if (rs.next()) {
                soLuong = rs.getInt("SoLuong");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            closeResources();
        }
        
        return soLuong;
    }
    
    /**
     * đóng kết nối đến database
     */
    private void closeResources() {
        try {
            if (sttm != null) {
                sttm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.toString());
        }
    }
    
}
