/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mnm.helper;

import mnm.DAO.NhanVienDAO;
import mnm.model.NhanVien;

/**
 *
 * @author Duc Tien
 */
public class AuthHelper {

    /*Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập*/
    public static NhanVien USER = null;
    public static NhanVienDAO nvDAO = new NhanVienDAO();

    /*Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất */
    public static void logoff() {
        AuthHelper.USER = null;
    }

    /*    
    * Kiểm tra xem đăng nhập hay chưa 
    * @return đăng nhập hay chưa
     */
    public static boolean authenticated() {
        return AuthHelper.USER != null;
    }

    public static String isManager(String maNV) {
        String vaiTro = nvDAO.checkVaiTro(maNV);
        if (vaiTro.equals("1")) {
            return "QUANLY";
        } else if (vaiTro.equals("2")) {
            return "KHO";
        } else if (vaiTro.equals("3")) {
            return "SALE";
        } else {
            return "NO";
        }
    }
}
