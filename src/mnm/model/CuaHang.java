/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mnm.model;

/**
 *
 * @author Duc Tien
 */
public class CuaHang {
    String maCH, tenCH, diaChi, hinh;

    public CuaHang() {
    }

    public CuaHang(String maCH, String tenCH, String diaChi, String hinh) {
        this.maCH = maCH;
        this.tenCH = tenCH;
        this.diaChi = diaChi;
        this.hinh = hinh;
    }

    public String getMaCH() {
        return maCH;
    }

    public void setMaCH(String maCH) {
        this.maCH = maCH;
    }

    public String getTenCH() {
        return tenCH;
    }

    public void setTenCH(String tenCH) {
        this.tenCH = tenCH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
    
    
}
