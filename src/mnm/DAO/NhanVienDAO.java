/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mnm.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mnm.Connect.DatabaseHelper;
import mnm.helper.DateHelper;
import mnm.model.NhanVien;
import mnm.model.NhanVienQuanTri;

/**
 *
 * @author Duc Tien
 */
public class NhanVienDAO {

    private Connection conn;
    private PreparedStatement sttm;
    private ResultSet rs;
    
    
    /**
     * dùng để insert NhanVien vào csdl
     * @param nhanVien
     * @return 
     */
    public int insert(NhanVien nhanVien) {
        int result = -1;
        String sql = "INSERT INTO NhanVien(MaNV, HoTen, GioiTinh, Email, NgaySinh, MatKhau, VaiTro, GhiChu, Hinh, MaCH) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
       if(nhanVien == null) {
    	   result = -1;
       } else {
           try {
               conn = DatabaseHelper.getDBConnect();
               sttm = conn.prepareStatement(sql);
               sttm.setString(1, nhanVien.getMaNV());
               sttm.setString(2, nhanVien.getHoTen());
               sttm.setString(3, nhanVien.getGioiTinh());
               sttm.setString(4, nhanVien.getEmail());
               sttm.setDate(5, nhanVien.getNgaySinh());
               sttm.setString(6, nhanVien.getMatKhau());
               sttm.setString(7, nhanVien.getVaiTro());
               sttm.setString(8, nhanVien.getGhiChu());
               sttm.setString(9, nhanVien.getHinh());
               sttm.setString(10, nhanVien.getMaCH());

               if (sttm.executeUpdate() > 0) {
                   System.out.println("Insert thành công");
                   result = 1;
               }
           } catch (SQLException e) {
               System.out.println("Error: " + e.toString());
           } finally {
               closeResources();
           }
       }
        return result;
    }
    
    /**
     * dùng để update thông tin NhanVien
     * @param nhanVien
     * @return 
     */
    public int update(NhanVien nhanVien) {
        int result = -1;
        String sql = "UPDATE NhanVien SET HoTen=?, GioiTinh=?, Email=?, NgaySinh=?, MatKhau=?, VaiTro=?, GhiChu=?, Hinh=?, MaCH=? WHERE MaNV=?";
        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, nhanVien.getHoTen());
            sttm.setString(2, nhanVien.getGioiTinh());
            sttm.setString(3, nhanVien.getEmail());
            sttm.setDate(4, nhanVien.getNgaySinh());
            sttm.setString(5, nhanVien.getMatKhau());
            sttm.setString(6, nhanVien.getVaiTro());
            sttm.setString(7, nhanVien.getGhiChu());
            sttm.setString(8, nhanVien.getHinh());
            sttm.setString(9, nhanVien.getMaCH());
            sttm.setString(10, nhanVien.getMaNV());

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
    
    /**
     * dùng để thay đổi thông tin ADMIN (chủ)
     * @param qt
     * @return 
     */
    
    public int updateQT(NhanVienQuanTri qt) {
        int result = -1;
        String sql = "UPDATE NhanVienQuanTri SET HoTen=?, Email=?, Sdt=? WHERE ID=?";
        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, qt.getName());
            sttm.setString(2, qt.getEmail());
            sttm.setString(3, qt.getSdt());
            sttm.setString(4, qt.getId());

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
    
    /**
     * xóa nhân viên
     * @param maNV
     * @return 
     */
    public int delete(String maNV) {
        int result = -1;
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, maNV);

            if (sttm.executeUpdate() > 0) {
                System.out.println("Delete thành công");
                result = 1;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            closeResources();
        }
        return result;
    }
    
    /**
     * trả về list chứa tất cả nhân viên đang làm việc tại hệ thống cửa hàng
     * @return 
     */

    public List<NhanVien> selectAll() {
        List<NhanVien> nhanViens = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            rs = sttm.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(rs.getString("MaNV"));
                nhanVien.setHoTen(rs.getString("HoTen"));
                nhanVien.setGioiTinh(rs.getString("GioiTinh"));
                nhanVien.setEmail(rs.getString("Email"));
                nhanVien.setNgaySinh(rs.getDate("NgaySinh"));
                nhanVien.setMatKhau(rs.getString("MatKhau"));
                nhanVien.setVaiTro(rs.getString("VaiTro"));
                nhanVien.setGhiChu(rs.getString("GhiChu"));
                nhanVien.setHinh(rs.getString("Hinh"));
                nhanVien.setMaCH(rs.getString("MaCH"));
                nhanViens.add(nhanVien);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            closeResources();
        }
        return nhanViens;
    }

    /**
     * trả về thông tin quản trị ADMIN (chủ)
     * @param id
     * @return 
     */
    public NhanVienQuanTri selectQT(String id) {
        NhanVienQuanTri qt = null;
        String sql = "SELECT * FROM NhanVienQuanTri WHERE ID=?";
        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, id);
            rs = sttm.executeQuery();
            if (rs.next()) {
                qt = new NhanVienQuanTri();
                qt.setId(rs.getString("ID"));
                qt.setName(rs.getString("HoTen"));
                qt.setEmail(rs.getString("Email"));
                qt.setSdt(rs.getString("Sdt"));
                qt.setMk(rs.getString("MatKhau"));
                qt.setHinh(rs.getString("Hinh"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            closeResources();
        }
        return qt;
    }
    
    /**
     * lấy hình ảnh và tên của ADMIN (chủ)
     * @param ID
     * @return 
     */

    public List<String> ImgAndName(String ID) {
        List<String> imgAndNameList = new ArrayList<>();
        String sql = "SELECT Hinh, HoTen FROM NhanVienQuanTri WHERE ID=?";
        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, ID);
            rs = sttm.executeQuery();
            while (rs.next()) {
                String hinh = rs.getString("Hinh");
                String name = rs.getString("HoTen");
                imgAndNameList.add(hinh);
                imgAndNameList.add(name);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            closeResources();
        }
        return imgAndNameList;
    }

    /**
     * Lấy được thông tin của nhân viên được xếp cuối cùng ở csdl của hệ thống cửa hàng
     * @return 
     */
    public NhanVien selectNhanVienCuoiCung() {
        NhanVien nhanVien = null;
        String sql = "SELECT TOP 1 * FROM NhanVien ORDER BY MaNV DESC";
        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            rs = sttm.executeQuery();
            if (rs.next()) {
                nhanVien = new NhanVien();
                nhanVien.setMaNV(rs.getString("MaNV"));
                nhanVien.setHoTen(rs.getString("HoTen"));
                nhanVien.setGioiTinh(rs.getString("GioiTinh"));
                nhanVien.setEmail(rs.getString("Email"));
                nhanVien.setNgaySinh(rs.getDate("NgaySinh"));
                nhanVien.setMatKhau(rs.getString("MatKhau"));
                nhanVien.setVaiTro(rs.getString("VaiTro"));
                nhanVien.setGhiChu(rs.getString("GhiChu"));
                nhanVien.setHinh(rs.getString("Hinh"));
                nhanVien.setMaCH(rs.getString("MaCH"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            closeResources();
        }
        return nhanVien;
    }
    
    /**
     * kiểm tra đăng nhập (NhanVien) từ manv và pass được truyền vào
     * @param maNV
     * @param password
     * @return 
     */
    public boolean checkLogin(String maNV, String password) {

        try {
            String query = "SELECT * FROM NhanVien WHERE MaNV = ? AND MatKhau = ?";
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(query);
            sttm.setString(1, maNV);
            sttm.setString(2, password);
            rs = sttm.executeQuery();

            if (rs.next()) {
                // Đăng nhập thành công
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            closeResources();
        }

        // Đăng nhập thất bại
        return false;
    }
    
    /**
     * Kiểm tra vai trò cảu nhân viên
     * @param maNV
     * @return 
     */
    public String checkVaiTro(String maNV) {
        String vaiTro = "";
        try {
            String query = "SELECT VaiTro FROM NhanVien WHERE MaNV = ?";
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(query);

            // Set the parameter value before executing the query
            sttm.setString(1, maNV);

            rs = sttm.executeQuery();
            if (rs.next()) {
                vaiTro = rs.getString("VaiTro");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            closeResources();
        }
        return vaiTro;
    }
    
    /**
     * tìm thông tin nhân viên dựa trên email được truyền vào
     * @param email
     * @return 
     */
    public boolean findEmail(String email) {

        try {
            String query = "SELECT * FROM NhanVien WHERE Email = ?";
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(query);
            sttm.setString(1, email);
            rs = sttm.executeQuery();

            // Nếu tìm thấy email trong cơ sở dữ liệu, trả về true
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            closeResources();
        }

        // Nếu không tìm thấy email trong cơ sở dữ liệu, trả về false
        return false;
    }

    /**
     * thay đổi pass dùng cho Quên mật khẩu, truyền vào email và pass mới
     * @param email
     * @param newPassword 
     */
    public void updatePassword(String email, String newPassword) {
        String sql = "UPDATE NhanVien SET MatKhau=? WHERE Email=?";

        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, newPassword);
            sttm.setString(2, email);

            int rowsAffected = sttm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cập nhật mật khẩu thành công");
            } else {
                System.out.println("Không tìm thấy người dùng với địa chỉ email này");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            closeResources();
        }

    }
    
    /**
     * tìm email dựa trên id được cung cấp
     * @param maNV
     * @return 
     */
    public String selectEmailbyID(String maNV) {
        String sql = "SELECT Email FROM NhanVien WHERE MaNV = ?";
        String email = null;
        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, maNV);
            rs = sttm.executeQuery();
            if (rs.next()) {
                email = rs.getString("Email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return email;
    }

    /**
     * tìm email dựa trên id ADMIN
     * @param maNV
     * @return 
     */
    public String selectEmailbyIDQT(String maNV) {
        String sql = "SELECT Email FROM NhanVienQuanTri WHERE ID = ?";
        String email = null;
        try {
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, maNV);
            rs = sttm.executeQuery();
            if (rs.next()) {
                email = rs.getString("Email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return email;
    }

    /**
     * kiểm tra đăng nhập ADMIN
     * @param maNV
     * @param password
     * @return 
     */
    public boolean checkLoginQT(String maNV, String password) {

        try {
            String query = "SELECT * FROM NhanVienQuanTri WHERE ID = ? AND MatKhau = ?";
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(query);
            sttm.setString(1, maNV);
            sttm.setString(2, password);
            rs = sttm.executeQuery();

            if (rs.next()) {
                // Đăng nhập thành công
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            closeResources();
        }

        // Đăng nhập thất bại
        return false;
    }
    
    /**
     * lấy thông tin cửa hàng và nhân viên
     * @param maNV
     * @return 
     */
    public List<String[]> getStoreAndEmployeeNames(String maNV) {
        List<String[]> result = new ArrayList<>();
        try {
            String query = "SELECT NhanVien.HoTen, CuaHang.TenCH "
                    + "FROM NhanVien "
                    + "JOIN CuaHang ON NhanVien.MaCH = CuaHang.MaCH "
                    + "WHERE NhanVien.MaNV = ?";
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(query);

            // Set the parameter value for the employee code
            sttm.setString(1, maNV);

            rs = sttm.executeQuery();

            while (rs.next()) {
                String employeeName = rs.getString("HoTen");
                String storeName = rs.getString("TenCH");
                String[] row = {employeeName, storeName};
                result.add(row);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            closeResources();
        }
        return result;
    }
    
    public int getSoLuongNhanVien() {
        int soLuong = 0;
        String sql = "SELECT COUNT(*) AS SoLuong FROM NhanVien";

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
