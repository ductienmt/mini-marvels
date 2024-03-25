/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mnm.model;

import java.util.Date;

/**
 *
 * @author Duc Tien
 */
public class DonHang {

    String MaHDN, MaNV, MaCH, MaNPP, GhiChu, TongTien;
    Date NgayLap;

    public DonHang() {
    }

    public DonHang(String MaHDN, String MaNV, String MaCH, String MaNPP, String GhiChu, String TongTien, Date NgayLap) {
        this.MaHDN = MaHDN;
        this.MaNV = MaNV;
        this.MaCH = MaCH;
        this.MaNPP = MaNPP;
        this.GhiChu = GhiChu;
        this.TongTien = TongTien;
        this.NgayLap = NgayLap;
    }

    public String getMaHDN() {
        return MaHDN;
    }

    public void setMaHDN(String MaHDN) {
        this.MaHDN = MaHDN;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMaCH() {
        return MaCH;
    }

    public void setMaCH(String MaCH) {
        this.MaCH = MaCH;
    }

    public String getMaNPP() {
        return MaNPP;
    }

    public void setMaNPP(String MaNPP) {
        this.MaNPP = MaNPP;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getTongTien() {
        return TongTien;
    }

    public void setTongTien(String TongTien) {
        this.TongTien = TongTien;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

}
