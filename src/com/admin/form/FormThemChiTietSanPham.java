/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.admin.form;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import mnm.Connect.DatabaseHelper;
import mnm.DAO.ChiTietSanPhamDAO;
import mnm.helper.DateHelper;
import mnm.helper.DialogHelper;
import mnm.model.ChiTietSanPham;
import java.util.Date;
import mnm.model.SanPham;

/**
 *
 * @author Duc Tien
 */
public class FormThemChiTietSanPham extends javax.swing.JPanel {

    private ChiTietSanPhamDAO ctspDAO = new ChiTietSanPhamDAO();
    private Connection conn;
    private PreparedStatement sttm;
    private ResultSet rs;
    public String mactsp = null;

    /**
     * Creates new form FormThemChiTietSanPham
     */
    public FormThemChiTietSanPham() {
        initComponents();
    }


    public void addEvent(ActionListener event) {
        btnDispose.addActionListener(event);
    }

    public void clear() {
        txtChatlieu.setText(null);
        txtColor.setText(null);
        txtMactsp.setText(null);
        txtMasp.setText(null);
        txtNgaynhap.setText(null);
        txtPhienban.setText(null);
        txtTile.setText(null);
    }

    public ChiTietSanPham getSelectedProductInfo() {
        int selectedRow = tblChiTiet.getSelectedRow();

        if (selectedRow != -1) {
            // Lấy thông tin sản phẩm từ dòng được chọn
            String maSp = (String) tblChiTiet.getValueAt(selectedRow, 1);
            return ctspDAO.getChiTietSanPhamByMaSP(maSp);
        }

        return null;
    }

    private void setModel(ChiTietSanPham data) {
        if (data != null) {
            txtMactsp.setText(data.getMaCT());
            txtMasp.setText(data.getMaSP());
            txtChatlieu.setText(data.getChatLieu());
            txtColor.setText(data.getMauSac());
            txtPhienban.setText(data.getPhienBan());
            txtNgaynhap.setText(DateHelper.convertDateToString(data.getNgayNhap()));
            txtTile.setText(data.getTiLe());
            txtMactsp.setEditable(false);
            txtMasp.setEditable(false);
        } else {
            DialogHelper.alert(this, "Không có dữ liệu");
        }
    }

    private boolean validateForm() {

        // Validate txtMactsp
        String maCTTSP = txtMactsp.getText().trim();
        if (maCTTSP.isEmpty()) {
            DialogHelper.alert(this, "Vui lòng nhập Mã Chi tiết của sản phẩm");
            txtMactsp.requestFocus();
            return false;

            // Handle the error as needed
        }

        // Validate txtMasp
        String maSP = txtMasp.getText().trim();
        if (maSP.isEmpty()) {
            DialogHelper.alert(this, "Vui lòng nhập MaSP");
            txtMasp.requestFocus();
            return false;
            // Handle the error as needed
        }

        // Validate txtColor
        String color = txtColor.getText().trim();
        if (color.isEmpty()) {
            DialogHelper.alert(this, "Vui lòng nhập màu sắc");
            txtColor.requestFocus();
            return false;
            // Handle the error as needed
        }

        // Validate txtChatlieu
        String chatLieu = txtChatlieu.getText().trim();
        if (chatLieu.isEmpty()) {
            DialogHelper.alert(this, "Vui lòng nhập chất liệu");
            txtChatlieu.requestFocus();
            return false;
            // Handle the error as needed
        }

        // Validate txtNgaynhap
        String ngayNhap = txtNgaynhap.getText().trim();
        if (ngayNhap.isEmpty()) {
            DialogHelper.alert(this, "Vui lòng nhập ngày nhập của sản phẩm");
            txtNgaynhap.requestFocus();
            return false;
            // Handle the error as needed
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setLenient(false);  // This will make the date format strict

            try {
                java.util.Date parsedDate = dateFormat.parse(ngayNhap);

                // Check if the parsed date is later than the current date
                Date currentDate = new java.util.Date();
                if (parsedDate.after(currentDate)) {
                    DialogHelper.alert(this, "Vui lòng nhập ngày nhập lại, ngày nhập lớn hơn ngày hiện tại");
                    return false;
                    // Handle the error as needed (e.g., show an error message)
                }

                // Convert the parsed date to java.sql.Date
                Date sqlDate = new Date(parsedDate.getTime());
            } catch (ParseException e) {

                DialogHelper.alert(this, "Vui lòng nhập đúng định dạng (Ví dụ: 01-01-2023)");
                txtNgaynhap.setText(null);
                txtNgaynhap.requestFocus();
                return false;
                // Handle the error as needed (e.g., show an error message)
            }
        }

        // Validate txtTile
        String tiLeSP = txtTile.getText().trim();
        if (tiLeSP.isEmpty()) {
            DialogHelper.alert(this, "Vui lòng nhập tỉ lệ sản phẩm");
            txtTile.requestFocus();
            return false;
            // Handle the error as needed
        }

        // Validate txtPhienban
        String phienBan = txtPhienban.getText().trim();
        if (phienBan.isEmpty()) {
            DialogHelper.alert(this, "Vui lòng nhập phiên bản sản phẩm (BT hoặc LM)");
            txtPhienban.requestFocus();
            return false;
            // Handle the error as needed
        }

        return true;
    }

    public void fillToTable(String masp) {
        try {
            // Truy vấn cơ sở dữ liệu để lấy dữ liệu từ bảng HoaDonBan với điều kiện NgayLap >= sevenDaysAgoTimestamp
            String sql = "SELECT * FROM ChiTietSanPham WHERE MaSP = ?";
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, masp);
            rs = sttm.executeQuery();

            // Lấy mô hình của bảng
            DefaultTableModel model = (DefaultTableModel) tblChiTiet.getModel();

            // Xóa tất cả các dòng hiện tại trong bảng (nếu cần)
            model.setRowCount(0);

            // Thêm dữ liệu từ ResultSet vào bảng
            while (rs.next()) {
                String date = DateHelper.convertDateToString(rs.getDate("NgayNhap"));
                model.addRow(new Object[]{
                    rs.getString("MaCTTSP"),
                    rs.getString("MaSP"),
                    rs.getString("ColorSanPham"),
                    rs.getString("ChatLieu"),
                    date,
                    rs.getString("TiLeSP"),
                    rs.getString("PhienBan")

                });
            }

            // Không đóng các resource ở đây
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getData(String masp) {
//        String masp = txtMasp.getText();
        if (masp != null) {
            System.out.println(masp);
//            txtMasp.setText(masp);

            // Gọi phương thức DAO để lấy thông tin chi tiết sản phẩm
            ChiTietSanPham chiTietSanPham = ctspDAO.getChiTietSanPhamByMaSP(masp);

            if (chiTietSanPham != null) {
                // Thực hiện các thao tác với thông tin chi tiết sản phẩm
                // Ví dụ: Hiển thị thông tin trên giao diện người dùng
//            displayChiTietSanPham(chiTietSanPham);
//                setModel(chiTietSanPham);
                fillToTable(masp);
            } else {
                DialogHelper.alert(jScrollPane1, "Bạn chưa thêm bất kì chi tiết sản phẩm nào cho sản phẩm này !");
                String mactsplast = ctspDAO.getLastMaCTTSP();

                if (!mactsplast.isEmpty()) {
                    // Tách phần chữ và phần số từ chuỗi mactsplast
                    String letters = mactsplast.replaceAll("[^a-zA-Z]", "");
                    String numbers = mactsplast.replaceAll("[^0-9]", "");
                    int num = Integer.parseInt(numbers) + 1;
                    String formattedNumber = String.format("%04d", num);

                    // Hiển thị phần chữ và phần số trên giao diện người dùng
                    System.out.println("Phần chữ: " + letters);
                    System.out.println("Phần số: " + numbers);

                    txtMasp.setText(masp);
                    mactsp = letters + formattedNumber;
                    System.out.println(mactsp);
                    txtMactsp.setText(mactsp);
                    txtMactsp.setEditable(false);
                    txtMasp.setEditable(false);
                    txtColor.requestFocus();
                } else {
                    System.out.println("Không lấy được mã cuối");
                }
            }
        } else {
            System.out.println("Label is null. Make sure it's properly initialized.");
        }

    }

    private void closeResources() {
        try {
            // Đóng ResultSet sau khi sử dụng
            if (rs != null) {
                rs.close();
            }
            // Đóng PreparedStatement sau khi sử dụng
            if (sttm != null) {
                sttm.close();
            }
            // Đóng kết nối sau khi sử dụng
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ SQL theo cách thích hợp khi đóng các tài nguyên
        }
    }
    
    

    private void add() {
        if (validateForm()) {
            
        }
    }

    private void edit() {
        if (validateForm()) {
            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
            chiTietSanPham.setMaCT(txtMactsp.getText());
            chiTietSanPham.setMaSP(txtMasp.getText());
            chiTietSanPham.setMauSac(txtColor.getText());  // Thay "newColor" bằng giá trị thích hợp
            chiTietSanPham.setChatLieu(txtChatlieu.getText());  // Thay "newMaterial" bằng giá trị thích hợp
            chiTietSanPham.setNgayNhap(DateHelper.convertStringToDate(txtNgaynhap.getText()));  // Thay "new Date()" bằng giá trị thích hợp
            chiTietSanPham.setTiLe(txtTile.getText());  // Thay "newRatio" bằng giá trị thích hợp
            chiTietSanPham.setPhienBan(txtPhienban.getText());  // Thay "newVersion" bằng giá trị thích hợp

            // Gọi phương thức update từ DAO
            
            int result = ctspDAO.update(chiTietSanPham);
            

            if (result > 0) {
                // Cập nhật thành công, có thể thông báo hoặc thực hiện các hành động khác
                System.out.println("Cập nhật thành công");
                fillToTable(txtMasp.getText());
            } else {
                // Cập nhật không thành công, có thể thông báo hoặc xử lý lỗi
                System.out.println("Cập nhật không thành công");
            }
        }
    }

    private void del() {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMasp = new com.login.swing.TextField();
        txtMactsp = new com.login.swing.TextField();
        txtColor = new com.login.swing.TextField();
        txtChatlieu = new com.login.swing.TextField();
        txtNgaynhap = new com.login.swing.TextField();
        txtTile = new com.login.swing.TextField();
        txtPhienban = new com.login.swing.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTiet = new com.admin.swing.TableColumn();
        btnDispose = new com.login.swing.Button();
        button1 = new com.login.swing.Button();
        button2 = new com.login.swing.Button();

        setBackground(new java.awt.Color(249, 249, 249));

        txtMasp.setBackground(new java.awt.Color(249, 249, 249));
        txtMasp.setLabelText("MaSP");

        txtMactsp.setBackground(new java.awt.Color(249, 249, 249));
        txtMactsp.setLabelText("Ma CTSP");

        txtColor.setBackground(new java.awt.Color(249, 249, 249));
        txtColor.setLabelText("Màu sắc");

        txtChatlieu.setBackground(new java.awt.Color(249, 249, 249));
        txtChatlieu.setLabelText("Chất liệu");

        txtNgaynhap.setBackground(new java.awt.Color(249, 249, 249));
        txtNgaynhap.setLabelText("Ngày nhập");

        txtTile.setBackground(new java.awt.Color(249, 249, 249));
        txtTile.setLabelText("Tỉ lệ");

        txtPhienban.setBackground(new java.awt.Color(249, 249, 249));
        txtPhienban.setLabelText("Phiên bản");

        jScrollPane1.setBackground(new java.awt.Color(249, 249, 249));
        jScrollPane1.setBorder(null);

        tblChiTiet.setBackground(new java.awt.Color(249, 249, 249));
        tblChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "MaCTSP", "MaSP", "Màu sắc", "Chất liệu", "Ngày nhập", "Tỉ lệ", "Phiên bản"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblChiTietMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblChiTiet);

        btnDispose.setBackground(new java.awt.Color(157, 153, 255));
        btnDispose.setForeground(new java.awt.Color(255, 255, 255));
        btnDispose.setText("Quay lại");
        btnDispose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisposeActionPerformed(evt);
            }
        });

        button1.setBackground(new java.awt.Color(157, 153, 255));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Lưu");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(157, 153, 255));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Sửa");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDispose, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMactsp, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                            .addComponent(txtMasp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtColor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtChatlieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgaynhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPhienban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMactsp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMasp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtChatlieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNgaynhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPhienban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDispose, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblChiTietMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            // Lấy thông tin sản phẩm từ FormDanhSachSanPham
            ChiTietSanPham selectedProduct = getSelectedProductInfo();
            System.out.println("Selected Product: " + selectedProduct);

            // Nếu có thông tin sản phẩm, truyền cho Form2 và hiển thị nó
            if (selectedProduct != null) {
                setModel(selectedProduct);
                
            }
        }
    }//GEN-LAST:event_tblChiTietMousePressed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        edit();
        
    }//GEN-LAST:event_button2ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        add();
    }//GEN-LAST:event_button1ActionPerformed

    private void btnDisposeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisposeActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnDisposeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.login.swing.Button btnDispose;
    private com.login.swing.Button button1;
    private com.login.swing.Button button2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.admin.swing.TableColumn tblChiTiet;
    private com.login.swing.TextField txtChatlieu;
    private com.login.swing.TextField txtColor;
    private com.login.swing.TextField txtMactsp;
    private com.login.swing.TextField txtMasp;
    private com.login.swing.TextField txtNgaynhap;
    private com.login.swing.TextField txtPhienban;
    private com.login.swing.TextField txtTile;
    // End of variables declaration//GEN-END:variables
}
