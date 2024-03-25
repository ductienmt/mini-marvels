package com.admin.form;

import com.sale.model.ModelCard;
import com.sale.swing.icon.GoogleMaterialDesignIcons;
import com.sale.swing.icon.IconFontSwing;
import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import mnm.Connect.DatabaseHelper;
import mnm.DAO.HoaDonBanDAO;
import mnm.DAO.NhaPhanPhoiDAO;
import mnm.DAO.NhanVienDAO;
import mnm.DAO.SanPhamDAO;
import mnm.helper.DialogHelper;
import mnm.model.DonHang;
import java.sql.*;
import java.time.LocalDateTime;
import javax.swing.table.DefaultTableModel;

public class Form1 extends javax.swing.JPanel {

    private HoaDonBanDAO hdbDAO = new HoaDonBanDAO();
    private SanPhamDAO spDAO = new SanPhamDAO();
    private NhanVienDAO nvDAO = new NhanVienDAO();
    private NhaPhanPhoiDAO nppDAO = new NhaPhanPhoiDAO();
    private Connection conn = DatabaseHelper.getDBConnect();
    private PreparedStatement sttm;
    private ResultSet rs;

    public Form1() {
        initComponents();
        setOpaque(false);
        initCardData();
//        initTableData();
//        initTableDataSP();
    }

    private void cancel() {
        boolean can = DialogHelper.confirm(this, "Bạn có muốn thoát chương trình không ?");
        if (can) {
            System.exit(0);
        }
    }

    public void initTableData() {
        try {
            // Lấy thời điểm hiện tại
            LocalDateTime currentTime = LocalDateTime.now();

            // Lấy thời điểm 7 ngày trước
            LocalDateTime sevenDaysAgo = currentTime.minusDays(7);

            // Chuyển đổi thời điểm thành định dạng phù hợp cho SQL (java.sql.Timestamp)
            Timestamp sevenDaysAgoTimestamp = Timestamp.valueOf(sevenDaysAgo);

            // Truy vấn cơ sở dữ liệu để lấy dữ liệu từ bảng HoaDonBan với điều kiện NgayLap >= sevenDaysAgoTimestamp
            String sql = "SELECT * FROM HoaDonBan WHERE NgayLap >= ?";
            sttm = conn.prepareStatement(sql);
            sttm.setTimestamp(1, sevenDaysAgoTimestamp);
            rs = sttm.executeQuery();

            // Lấy mô hình của bảng
            DefaultTableModel model = (DefaultTableModel) table1.getModel();

            // Xóa tất cả các dòng hiện tại trong bảng (nếu cần)
            model.setRowCount(0);

            // Thêm dữ liệu từ ResultSet vào bảng
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("MaHDB"),
                    rs.getString("MaNV"),
                    rs.getString("MaCH"),
                    rs.getFloat("TongTien"),
                    rs.getDate("NgayLap")
                });
            }
            System.out.println("fill");

            // Không đóng các resource ở đây
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initTableDataSP() {
        try {
            // Truy vấn cơ sở dữ liệu để lấy dữ liệu từ bảng HoaDonBan
            String sql = "SELECT SanPham.MaSP, TenSP, SoLuongTon, TiLeSP FROM SanPham "
                    + "JOIN ChiTietSanPham ON SanPham.MaSP = ChiTietSanPham.MaSP";

            sttm = conn.prepareStatement(sql);
            rs = sttm.executeQuery();

            // Lấy mô hình của bảng
            DefaultTableModel modelSP = (DefaultTableModel) table2.getModel();

            // Xóa tất cả các dòng hiện tại trong bảng (nếu cần)
            modelSP.setRowCount(0);

            // Thêm dữ liệu từ ResultSet vào bảng
            while (rs.next()) {
                modelSP.addRow(new Object[]{
                    rs.getString("MaSP"),
                    rs.getString("TenSP"),
                    rs.getString("SoLuongTon"),
                    rs.getString("TiLeSP")
                });
            }

            // Không đóng các resource ở đây
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initCardData() {
        int soLuongHDB = hdbDAO.getSoLuongDonHang();
        int soLuongSP = spDAO.getSoLuongSanPham();
        int soLuongNV = nvDAO.getSoLuongNhanVien();
        int soLuongNPP = nppDAO.getSoLuongNhaPhanPhoi();
        Icon icon3 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.LIST, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card1.setData(new ModelCard("Đơn hàng", soLuongHDB, soLuongHDB, icon3));
        Icon icon2 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MONETIZATION_ON, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card2.setData(new ModelCard("Sản phẩm", soLuongSP, soLuongSP, icon2));
        Icon icon1 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SHOPPING_BASKET, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card3.setData(new ModelCard("Nhân sự", soLuongNV, soLuongNV, icon1));
        Icon icon4 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.BUSINESS_CENTER, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card4.setData(new ModelCard("Nhà phân phối", soLuongNPP, soLuongNPP, icon4));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card1 = new com.sale.component.Card();
        jLabel1 = new javax.swing.JLabel();
        card2 = new com.sale.component.Card();
        card3 = new com.sale.component.Card();
        card4 = new com.sale.component.Card();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.sale.swing.table.Table();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new com.sale.swing.table.Table();

        setBackground(new java.awt.Color(249, 249, 249));

        card1.setColorGradient(new java.awt.Color(211, 28, 215));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Dashboard / Home");

        card2.setBackground(new java.awt.Color(10, 30, 214));
        card2.setColorGradient(new java.awt.Color(72, 111, 252));

        card3.setBackground(new java.awt.Color(194, 85, 1));
        card3.setColorGradient(new java.awt.Color(255, 212, 99));

        card4.setBackground(new java.awt.Color(60, 195, 0));
        card4.setColorGradient(new java.awt.Color(208, 255, 90));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(76, 76, 76));
        jLabel5.setText("Đơn hàng gần đây");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Mã nhân viên", "Mã cửa hàng", "Tổng tiền", "Ngày lập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(76, 76, 76));
        jLabel6.setText("Sản phẩm mới nhập");
        jLabel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số lượng", "Tỉ lệ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(card1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(card2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(card3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(card4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.sale.component.Card card1;
    private com.sale.component.Card card2;
    private com.sale.component.Card card3;
    private com.sale.component.Card card4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.sale.swing.table.Table table1;
    private com.sale.swing.table.Table table2;
    // End of variables declaration//GEN-END:variables
}
