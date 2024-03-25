/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.admin.form;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.time.LocalDateTime;
import javax.swing.table.DefaultTableModel;
import mnm.Connect.DatabaseHelper;
import mnm.DAO.SanPhamDAO;
import mnm.helper.DateHelper;
import mnm.helper.DialogHelper;
import mnm.model.SanPham;

/**
 *
 * @author Duc Tien
 */
public class FormDanhSachSanPham extends javax.swing.JPanel {

    private Connection conn;
    private PreparedStatement sttm;
    private ResultSet rs;
    private int index = -1;
    private SanPhamDAO spDAO = new SanPhamDAO();
//    private Form2 form2 = new Form2();

    /**
     * Creates new form FormDanhSachSanPham
     */
    public FormDanhSachSanPham() {
        initComponents();
        fillToTable();
    }

    public SanPham getSelectedProductInfo() {
        int selectedRow = tblSanPham.getSelectedRow();

        if (selectedRow != -1) {
            // Lấy thông tin sản phẩm từ dòng được chọn
            String maSp = (String) tblSanPham.getValueAt(selectedRow, 0);
            return spDAO.findById(maSp);
        }

        return null;
    }

    public void addEvenList(ActionListener event) {
        btnDispose.addActionListener(event);
    }

    // Thêm sự kiện cho bảng tblSanPham
    public void addEventTable(MouseAdapter event) {
        tblSanPham.addMouseListener(event);
    }

    private void findSanPham(String searchKeyword) {
        conn = DatabaseHelper.getDBConnect();
        if (conn != null) {
            try {
                String SQL = "SELECT * FROM SanPham "
                        + "WHERE MaSP LIKE ? "
                        + "OR TenSP LIKE ? "
                        + "OR DonGia LIKE ? "
                        + "OR MaLoai LIKE ? "
                        + "OR NhaSanXuat LIKE ? "
                        + "OR NguonGoc LIKE ?";
                sttm = conn.prepareStatement(SQL);

                // Thiết lập các tham số truy vấn
                for (int i = 1; i <= 6; i++) {
                    sttm.setString(i, "%" + searchKeyword + "%");
                }

                rs = sttm.executeQuery();
                fillTableSanPham(rs);
            } catch (SQLException e) {
                e.printStackTrace();
                // Xử lý ngoại lệ SQL theo cách thích hợp
            } finally {
                closeResources();
            }
        }
    }

    public void fillToTable() {
        try {
            // Truy vấn cơ sở dữ liệu để lấy dữ liệu từ bảng HoaDonBan với điều kiện NgayLap >= sevenDaysAgoTimestamp
            String sql = "SELECT * FROM SanPham";
            conn = DatabaseHelper.getDBConnect();
            sttm = conn.prepareStatement(sql);
            rs = sttm.executeQuery();

            // Lấy mô hình của bảng
            DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();

            // Xóa tất cả các dòng hiện tại trong bảng (nếu cần)
            model.setRowCount(0);

            // Thêm dữ liệu từ ResultSet vào bảng
            while (rs.next()) {
                String date = DateHelper.convertDateToString(rs.getDate("NgaySanXuat"));
                model.addRow(new Object[]{
                    rs.getString("MaSP"),
                    rs.getString("TenSP"),
                    rs.getFloat("DonGia"),
                    rs.getString("MaLoai"),
                    rs.getString("NhaSanXuat"),
                    rs.getString("NguonGoc"),
                    rs.getInt("SoLuongTon"),
                    date,
                    rs.getString("MoTa")

                });
            }

            // Không đóng các resource ở đây
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillTableSanPham(ResultSet rs) {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);

        try {
            while (rs.next()) {
                String maSP = rs.getString("MaSP");
                String tenSP = rs.getString("TenSP");
                float donGia = rs.getFloat("DonGia");
                String maLoai = rs.getString("MaLoai");
                String nhaSanXuat = rs.getString("NhaSanXuat");
                String nguonGoc = rs.getString("NguonGoc");
                int soLuongTon = rs.getInt("SoLuongTon");
                String date = DateHelper.convertDateToString(rs.getDate("NgaySanXuat"));
                String moTa = rs.getString("MoTa");

                // Thêm dòng dữ liệu vào JTable
                model.addRow(new Object[]{
                    maSP, tenSP, donGia, maLoai, nhaSanXuat, nguonGoc, soLuongTon, date, moTa
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ SQL theo cách thích hợp
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

    private void selectRow(int rowIndex) {
        tblSanPham.setRowSelectionInterval(rowIndex, rowIndex);
    }
    
    public void clear(){
        txtSearch.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new com.sale.swing.table.Table();
        btnPrev = new com.login.swing.Button();
        btnLast = new com.login.swing.Button();
        btnDispose = new com.login.swing.Button();
        btnNext = new com.login.swing.Button();
        btnFirst = new com.login.swing.Button();
        txtSearch = new com.login.swing.TextField();

        setBackground(new java.awt.Color(249, 249, 249));

        jScrollPane1.setBorder(null);

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaSP", "TenSP", "DonGia", "MaLoai", "NhaSanXuat", "NguonGoc", "SoLuong", "NgaySanXuat", "MoTa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        btnPrev.setBackground(new java.awt.Color(157, 153, 255));
        btnPrev.setForeground(new java.awt.Color(255, 255, 255));
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mnm/image/first.png"))); // NOI18N
        btnPrev.setText("Previous");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(157, 153, 255));
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mnm/image/next.png"))); // NOI18N
        btnLast.setText("Last");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnDispose.setBackground(new java.awt.Color(157, 153, 255));
        btnDispose.setForeground(new java.awt.Color(255, 255, 255));
        btnDispose.setText("Quay lại");

        btnNext.setBackground(new java.awt.Color(157, 153, 255));
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mnm/image/last.png"))); // NOI18N
        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnFirst.setBackground(new java.awt.Color(157, 153, 255));
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mnm/image/prev.png"))); // NOI18N
        btnFirst.setText("First");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        txtSearch.setBackground(new java.awt.Color(249, 249, 249));
        txtSearch.setLabelText("Tìm kiếm");
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnDispose, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFirst, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrev, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLast, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1073, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(582, 582, 582)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnLast, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnDispose, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 80, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        selectRow(index);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        if (tblSanPham.getRowCount() > 0) {
            if (index > 0) {
                index--;
            } else {
                index = tblSanPham.getRowCount() - 1;
            }
            selectRow(index);
        }


    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        if (tblSanPham.getRowCount() > 0) {
            if (index < tblSanPham.getRowCount() - 1) {
                index++;
            } else {
                index = 0;
            }
            selectRow(index);
        }

    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        index = tblSanPham.getRowCount() - 1;
        selectRow(index);
    }//GEN-LAST:event_btnLastActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        if (txtSearch.getText().isEmpty()) {
            fillToTable();
        } else {
            findSanPham(txtSearch.getText());
        }
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.login.swing.Button btnDispose;
    private com.login.swing.Button btnFirst;
    private com.login.swing.Button btnLast;
    private com.login.swing.Button btnNext;
    private com.login.swing.Button btnPrev;
    private javax.swing.JScrollPane jScrollPane1;
    private com.sale.swing.table.Table tblSanPham;
    private com.login.swing.TextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
