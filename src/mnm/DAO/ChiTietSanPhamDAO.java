/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mnm.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mnm.model.ChiTietSanPham;
import java.sql.SQLException;
import mnm.Connect.DatabaseHelper;

/**
 *
 * @author Duc Tien
 */
public class ChiTietSanPhamDAO {

    private Connection conn;
    private PreparedStatement sttm;
    private ResultSet rs;

    public int insert(ChiTietSanPham chiTietSanPham) {
        int result = -1;
        String sql = "INSERT INTO ChiTietSanPham(MaCTTSP, MaSP, ColorSanPham, ChatLieu, NgayNhap, TiLeSP, PhienBan) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, chiTietSanPham.getMaCT());
            sttm.setString(2, chiTietSanPham.getMaSP());
            sttm.setString(3, chiTietSanPham.getMauSac());
            sttm.setString(4, chiTietSanPham.getChatLieu());
            sttm.setDate(5, chiTietSanPham.getNgayNhap());
            sttm.setString(6, chiTietSanPham.getTiLe());
            sttm.setString(7, chiTietSanPham.getPhienBan());

            if (sttm.executeUpdate() > 0) {
                System.out.println("Insert thành công");
                result = 1;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            closeResources();
        }
        return result;
    }

    public int update(ChiTietSanPham chiTietSanPham) {
        int result = -1;
        String sql = "UPDATE ChiTietSanPham SET ColorSanPham=?, ChatLieu=?, NgayNhap=?, TiLeSP=?, PhienBan=? WHERE MaCTTSP=?";

        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);

            sttm.setString(1, chiTietSanPham.getMauSac());
            sttm.setString(2, chiTietSanPham.getChatLieu());
            sttm.setDate(3, chiTietSanPham.getNgayNhap());
            sttm.setString(4, chiTietSanPham.getTiLe());
            sttm.setString(5, chiTietSanPham.getPhienBan());
            sttm.setString(6, chiTietSanPham.getMaCT());

            if (sttm.executeUpdate() > 0) {
                System.out.println("Update thành công");
                result = 1;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            closeResources();
        }
        return result;
    }

    public ChiTietSanPham getChiTietSanPhamByMaSP(String maSP) {
        ChiTietSanPham chiTietSanPham = null;
        String sql = "SELECT * FROM ChiTietSanPham WHERE MaSP = ?";

        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, maSP);
            rs = sttm.executeQuery();

            if (rs.next()) {
                chiTietSanPham = new ChiTietSanPham();
                chiTietSanPham.setMaCT(rs.getString("MaCTTSP"));
                chiTietSanPham.setMaSP(rs.getString("MaSP"));
                chiTietSanPham.setMauSac(rs.getString("ColorSanPham"));
                chiTietSanPham.setChatLieu(rs.getString("ChatLieu"));
                chiTietSanPham.setNgayNhap(rs.getDate("NgayNhap"));
                chiTietSanPham.setTiLe(rs.getString("TiLeSP"));
                chiTietSanPham.setPhienBan(rs.getString("PhienBan"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            closeResources();
        }

        return chiTietSanPham;
    }

    public String getLastMaCTTSP() {
        String lastMaCTTSP = null;
        String sql = "SELECT TOP 1 MaCTTSP FROM ChiTietSanPham ORDER BY MaCTTSP DESC";

        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            rs = sttm.executeQuery();

            if (rs.next()) {
                lastMaCTTSP = rs.getString("MaCTTSP");
            }
        } catch (SQLException e) {
            System.out.println("Error getting last MaCTTSP: " + e.toString());
        } finally {
            closeResources();
        }

        return lastMaCTTSP;
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
            System.out.println("Error closing resources: " + e.toString());
        }
    }
}
