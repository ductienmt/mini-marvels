package com.admin.form;

import com.admin.component.CuaHangCard;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import mnm.DAO.CuaHangDAO;
import mnm.helper.DialogHelper;
import mnm.model.CuaHang;

public class Form3 extends javax.swing.JPanel {

    private CuaHangDAO chDAO = new CuaHangDAO();

    public Form3() {
        initComponents();
        setOpaque(false);
        addData();
    }

    private void cancel() {
        boolean can = DialogHelper.confirm(this, "Bạn có muốn thoát chương trình không ?");
        if (can) {
            System.exit(0);
        }
    }

    public void addItem(CuaHang data) {
        CuaHangCard item = new CuaHangCard();

        item.setData(data);
        item.addEvent(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tabs.setSelectedIndex(1);
            }
        });
        panelCuaHang1.add(item);
        panelCuaHang1.repaint();
        panelCuaHang1.revalidate();
    }

    private void addData() {
        int soLuong = chDAO.getSoLuongCuaHang();
        List<CuaHang> danhSachCuaHang = chDAO.getDanhSachCuaHang();
        for (CuaHang cuaHang : danhSachCuaHang) {
            // Thêm từng cửa hàng vào đây
            this.addItem(cuaHang);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelCuaHang1 = new com.admin.swing.PanelCuaHang();
        jPanel2 = new javax.swing.JPanel();

        tabs.setBackground(new java.awt.Color(249, 249, 249));

        jPanel1.setBackground(new java.awt.Color(249, 249, 249));

        jScrollPane2.setBackground(new java.awt.Color(249, 249, 249));
        jScrollPane2.setBorder(null);
        jScrollPane2.setViewportView(panelCuaHang1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1074, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
        );

        tabs.addTab("DANH SÁCH CÁC CỬA HÀNG", jPanel1);

        jPanel2.setBackground(new java.awt.Color(249, 249, 249));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1074, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );

        tabs.addTab("QUẢN LÝ CỬA HÀNG", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private com.admin.swing.PanelCuaHang panelCuaHang1;
    private javax.swing.JTabbedPane tabs;
    // End of variables declaration//GEN-END:variables
}
