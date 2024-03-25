/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mnm.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mnm.Connect.DatabaseHelper;
import mnm.model.CuaHang;

/**
 *
 * @author Duc Tien
 */
public class CuaHangDAO {

    private Connection conn;
    private PreparedStatement sttm;
    private ResultSet rs;

    public int getSoLuongCuaHang() {
        int soLuong = 0;
        String sql = "SELECT COUNT(*) AS SoLuong FROM CuaHang";

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

    public List<CuaHang> getDanhSachCuaHang() {
        List<CuaHang> danhSachCuaHang = new ArrayList<>();
        String sql = "SELECT * FROM CuaHang";

        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            rs = sttm.executeQuery();

            while (rs.next()) {
                String maCH = rs.getString("MaCH");
                String tenCH = rs.getString("TenCH");
                String diaChi = rs.getString("DiaChi");
                String hinh = rs.getString("Hinh");

                CuaHang cuaHang = new CuaHang(maCH, tenCH, diaChi, hinh);
                danhSachCuaHang.add(cuaHang);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            closeResources();
        }

        return danhSachCuaHang;
    }

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
