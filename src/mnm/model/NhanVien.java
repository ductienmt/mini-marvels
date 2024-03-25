/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mnm.model;
import java.sql.Date;

/**
 *
 * @author Duc Tien
 */
public class NhanVien {
    private String maNV, hoTen, gioiTinh, email, matKhau, vaiTro, ghiChu, hinh, maCH;
    private Date ngaySinh;

    public NhanVien() {
    }

    public NhanVien(String maNV, String hoTen, String gioiTinh, String email, String matKhau, String vaiTro, String ghiChu, String hinh, String maCH, Date ngaySinh) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.matKhau = matKhau;
//        this.vaiTro = vaiTro;
        if(vaiTro == "quanLy"){
            this.vaiTro = "1";
        } else if(vaiTro == "nvKho"){
            this.vaiTro = "2";
        } else if(vaiTro == "nvSale"){
            this.vaiTro = "3";
        }
        this.ghiChu = ghiChu;
        this.hinh = hinh;
        this.maCH = maCH;
        this.ngaySinh = ngaySinh;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMaCH() {
        return maCH;
    }

    public void setMaCH(String maCH) {
        this.maCH = maCH;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    
    
    
    
}
