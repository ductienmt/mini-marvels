package com.login.component;

import java.util.List;
import javax.swing.JOptionPane;
import mnm.DAO.NhanVienDAO;
import mnm.helper.AuthHelper;
import net.miginfocom.swing.MigLayout;

public class Header extends javax.swing.JPanel {

    private NhanVienDAO nvDAO = new NhanVienDAO();
    private String maNV ;
    private MigLayout layout;

    public Header() {
        initComponents();
    }
    
    private void init(){
        layout = new MigLayout();
    }

    public void setManv(String manv) {
        this.maNV = manv;
    }
    
    

    public void setLbl() {
        String vaiTro = AuthHelper.isManager(maNV);

        if (vaiTro.equals("QUANLY")) {
            lblVaiTro.setText("Quản lý cửa hàng");
        } else if (vaiTro.equals("KHO")) {
            lblVaiTro.setText("Nhân viên kho");
        } else if (vaiTro.equals("3")) {
            lblVaiTro.setText("Nhân viên bán hàng");
        }

        List<String[]> result = nvDAO.getStoreAndEmployeeNames(maNV);

        if (!result.isEmpty()) {
            // Assuming the first row in the result contains employee name and store name
            String[] data = result.get(0);

            // Set the text for lblChiNhanh and lblHoTen
            lblChiNhanh.setText(data[1]);  // Store name
            lblHoTen.setText(data[0]);     // Employee name
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        lblChiNhanh = new javax.swing.JLabel();
        lblVaiTro = new javax.swing.JLabel();
        lblP = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        btnInfo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(157, 153, 255));

        bg.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Chi Nhánh:");

        lblChiNhanh.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblChiNhanh.setForeground(new java.awt.Color(204, 204, 204));
        lblChiNhanh.setText(".............................");

        lblVaiTro.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblVaiTro.setForeground(new java.awt.Color(204, 204, 204));
        lblVaiTro.setText("..........................");

        lblP.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblP.setForeground(new java.awt.Color(204, 204, 204));
        lblP.setText(",");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Xin chào,");

        lblHoTen.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblHoTen.setForeground(new java.awt.Color(204, 204, 204));
        lblHoTen.setText(".......................");

        btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/user.png"))); // NOI18N
        btnInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInfoMouseClicked(evt);
            }
        });

        bg.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bg.setLayer(lblChiNhanh, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bg.setLayer(lblVaiTro, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bg.setLayer(lblP, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bg.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bg.setLayer(lblHoTen, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bg.setLayer(btnInfo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bg.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblChiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblP, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(514, 514, 514)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblHoTen)
                            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(lblChiNhanh)
                                .addComponent(lblP)
                                .addComponent(lblVaiTro))))
                    .addComponent(btnInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInfoMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_btnInfoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    private javax.swing.JLabel btnInfo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblChiNhanh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblP;
    private javax.swing.JLabel lblVaiTro;
    // End of variables declaration//GEN-END:variables
}
