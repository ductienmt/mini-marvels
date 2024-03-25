/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mnm.model;

/**
 *
 * @author Duc Tien
 */
public class NhanVienQuanTri {
    private String id, name, email, sdt, mk, hinh;

    public NhanVienQuanTri() {
    }

    public NhanVienQuanTri(String id, String name, String email, String sdt, String mk, String hinh) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.sdt = sdt;
        this.mk = mk;
        this.hinh = hinh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
    
    
}
