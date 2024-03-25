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
public class SanPham {
    private String maSP, tenSP, maLoai, nsx, nguonGoc, moTa, hinh;
    private float donGia;
    private int soLuongTon;
    private Date ngaySx;
    

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP, String maLoai, String nsx, String nguonGoc, String moTa, float donGia, int soLuongTon, Date ngaySx, String hinh) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maLoai = maLoai;
        this.nsx = nsx;
        this.nguonGoc = nguonGoc;
        this.moTa = moTa;
        this.donGia = donGia;
        this.soLuongTon = soLuongTon;
        this.ngaySx = ngaySx;
        this.hinh = hinh;
    }
    
    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getNsx() {
        return nsx;
    }

    public void setNsx(String nsx) {
        this.nsx = nsx;
    }

    public String getNguonGoc() {
        return nguonGoc;
    }

    public void setNguonGoc(String nguonGoc) {
        this.nguonGoc = nguonGoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public Date getNgaySx() {
        return ngaySx;
    }

    public void setNgaySx(Date ngaySx) {
        this.ngaySx = ngaySx;
    }

    
    
    
    

}
