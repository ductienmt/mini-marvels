package com.admin.component;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Header extends javax.swing.JPanel {

    /** Độ trong suốt của phần đầu trang (alpha). */
    private float alpha;

    /**
     * Thiết lập độ trong suốt của phần đầu trang.
     *
     * @param alpha Giá trị độ trong suốt cần thiết lập (từ 0.0 đến 1.0).
     */
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public Header() {
        initComponents();
        setOpaque(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(7, 98, 177));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/logoproject2.png"))); // NOI18N
        jLabel1.setText("MiniMarvels");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Ghi đè phương thức paint để vẽ phần đầu trang với độ trong suốt đã thiết lập.
     *
     * @param grphcs Ngữ cảnh đồ họa được sử dụng để vẽ.
     */
    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        super.paint(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
