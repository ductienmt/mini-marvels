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
public class ChiTietSanPham {
    private String maCT, maSP, mauSac, chatLieu, tiLe, phienBan;
    private Date ngayNhap;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(String maCT, String maSP, String mauSac, String chatLieu, String tiLe, String phienBan, Date ngayNhap) {
        this.maCT = maCT;
        this.maSP = maSP;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.tiLe = tiLe;
        this.phienBan = phienBan;
        this.ngayNhap = ngayNhap;
    }

    public String getMaCT() {
        return maCT;
    }

    public void setMaCT(String maCT) {
        this.maCT = maCT;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getTiLe() {
        return tiLe;
    }

    public void setTiLe(String tiLe) {
        this.tiLe = tiLe;
    }

    public String getPhienBan() {
        return phienBan;
    }

    public void setPhienBan(String phienBan) {
        this.phienBan = phienBan;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
    
    
}
