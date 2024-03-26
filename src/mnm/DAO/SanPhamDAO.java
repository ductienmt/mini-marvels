/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mnm.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mnm.Connect.DatabaseHelper;
import mnm.model.SanPham;

/**
 *
 * @author Duc Tien
 */
public class SanPhamDAO {

    private Connection conn;
    private PreparedStatement sttm;
    private ResultSet rs;

    public int getSoLuongSanPham() {
        int soLuong = 0;
        String sql = "SELECT COUNT(*) AS SoLuong FROM SanPham WHERE TrangThai = 1";

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

    public int insertSanPham(SanPham sanPham) {
        int result = -1;
        String sql = "INSERT INTO SanPham(MaSP, TenSP, DonGia, MaLoai, NhaSanXuat, NguonGoc, MoTa, SoLuongTon, NgaySanXuat, Hinh, TrangThai) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, sanPham.getMaSP());
            sttm.setString(2, sanPham.getTenSP());
            sttm.setFloat(3, sanPham.getDonGia());
            sttm.setString(4, sanPham.getMaLoai());
            sttm.setString(5, sanPham.getNsx());
            sttm.setString(6, sanPham.getNguonGoc());
            sttm.setString(7, sanPham.getMoTa());
            sttm.setInt(8, sanPham.getSoLuongTon());
            sttm.setDate(9, sanPham.getNgaySx());
            sttm.setString(10, sanPham.getHinh());
            int trangThai = 0;
            if (sanPham.getTrangThai() != null && sanPham.getTrangThai()) {
                trangThai = 1;
            }
            sttm.setInt(11, trangThai);

            result = sttm.executeUpdate();
            if (result > 0) {
                System.out.println("Insert thành công");
            } else {
                System.out.println("Insert thất bại");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting SanPham: " + e.toString());
            result = -1;
        } finally {
            closeResources();
        }
        return result;
    }

    public int updateSanPham(SanPham sanPham) {
        int result = -1;
        String sql = "UPDATE SanPham SET TenSP=?, DonGia=?, MaLoai=?, NhaSanXuat=?, NguonGoc=?, MoTa=?, SoLuongTon=?, NgaySanXuat=?, Hinh=?, TrangThai=? WHERE MaSP=?";
        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);

            sttm.setString(1, sanPham.getTenSP());
            sttm.setFloat(2, sanPham.getDonGia());
            sttm.setString(3, sanPham.getMaLoai());
            sttm.setString(4, sanPham.getNsx());
            sttm.setString(5, sanPham.getNguonGoc());
            sttm.setString(6, sanPham.getMoTa());
            sttm.setInt(7, sanPham.getSoLuongTon());
            sttm.setDate(8, sanPham.getNgaySx());
            sttm.setString(9, sanPham.getHinh());
            int trangThai = 0;
            if (sanPham.getTrangThai() != null && sanPham.getTrangThai()) {
                trangThai = 1;
            }
            sttm.setInt(10, trangThai);
            sttm.setString(11, sanPham.getMaSP());

            result = sttm.executeUpdate();
            if (result > 0) {
                System.out.println("Update thành công");
            } else {
                System.out.println("Update thất bại");
            }
        } catch (SQLException e) {
            System.out.println("Error updating SanPham: " + e.toString());
        } finally {
            closeResources();
        }
        return result;
    }

    public SanPham findById(String maSp) {
        String sql = "SELECT * FROM SanPham WHERE MaSP=?";
        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, maSp);
            rs = sttm.executeQuery();
            if (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setMaSP(rs.getString("MaSP"));
                sanPham.setTenSP(rs.getString("TenSP"));
                sanPham.setDonGia(rs.getFloat("DonGia"));
                sanPham.setMaLoai(rs.getString("MaLoai"));
                sanPham.setNsx(rs.getString("NhaSanXuat"));
                sanPham.setNguonGoc(rs.getString("NguonGoc"));
                sanPham.setMoTa(rs.getString("MoTa"));
                sanPham.setSoLuongTon(rs.getInt("SoLuongTon"));
                sanPham.setNgaySx(rs.getDate("NgaySanXuat"));
                sanPham.setHinh(rs.getString("Hinh"));
                Boolean trangthai = true;
                Integer trangthaiInt = rs.getInt("TrangThai");
                if (trangthaiInt == 0) {
                    trangthai = false;
                }
                sanPham.setTrangThai(trangthai);

                return sanPham;
            }
        } catch (SQLException e) {
            System.out.println("Error finding SanPham by ID: " + e.toString());
        } finally {
            closeResources();
        }
        return null;
    }

    public int hideSanPham(String maSP) {
        int result = -1;
        String sql = "UPDATE SanPham SET TrangThai = 0 WHERE MaSP=?";
        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);

            sttm.setString(1, maSP);

            result = sttm.executeUpdate();
            if (result > 0) {
                System.out.println("Ẩn sản phẩm thành công");
            } else {
                System.out.println("Ẩn sản phẩm thất bại");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi ẩn sản phẩm: " + e.toString());
        } finally {
            closeResources();
        }
        return result;
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
