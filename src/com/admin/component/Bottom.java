package com.admin.component;

import com.admin.info.InfoMain;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import mnm.DAO.NhanVienDAO;
import mnm.helper.DialogHelper;

public class Bottom extends javax.swing.JPanel {

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    private float alpha;
    private static String id;

    public Bottom(String id) {
        initComponents();
        setOpaque(false);
        setBackground(new Color(65, 152, 216));
        getImageAndName(id);
        this.id = id;
    }

    private void getImageAndName(String ID) {
        NhanVienDAO nvDao = new NhanVienDAO();
        List<String> imgAndNameList = nvDao.ImgAndName(ID);

        if (!imgAndNameList.isEmpty() && imgAndNameList.size() >= 2) {
            String image = imgAndNameList.get(0);
            String name = imgAndNameList.get(1);
            System.out.println(image);

            // Tiếp tục xử lý với đường dẫn hình (image) và tên (name) ở đây
            setIconAndLbl(image, name);
        } else {
            System.out.println("Không tìm thấy thông tin cho ID: " + ID);
        }
    }

    public void setIconAndLbl(String image, String name) {
        if (image == null) {
            // Set a default image if 'image' is null
            imageAvatar1.setIcon(new ImageIcon(getClass().getResource("/mnm/images/logoproject2.png")));
        } else {
            try {
                // Try to load the specified image
                imageAvatar1.setIcon(new ImageIcon(getClass().getResource("/mnm/images/" + image)));
            } catch (NullPointerException e) {
                // Handle the case where the specified image is null
                e.printStackTrace(); // Log or print the exception
                // Optionally, set a default image or take other appropriate action
            }
        }
        lblName.setText(name);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageAvatar1 = new com.admin.swing.ImageAvatar();
        lblName = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/logoproject1.png"))); // NOI18N
        imageAvatar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imageAvatar1MousePressed(evt);
            }
        });

        lblName.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblName.setForeground(new java.awt.Color(237, 237, 237));
        lblName.setText("Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblName)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblName)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void imageAvatar1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageAvatar1MousePressed
        // TODO add your handling code here:
        // Đặt vị trí của cửa sổ InfoMain nằm kề trên form hiện tại
        int offsetX = 0;  // điều chỉnh giá trị này để đặt khoảng cách theo chiều ngang
        int offsetY = -145; // điều chỉnh giá trị này để đặt khoảng cách theo chiều dọc
        InfoMain info = new InfoMain();
        info.setModel(id);
        info.setVisible(true);
        info.setLocationRelativeTo(this);
        info.setLocation(info.getX() + offsetX, info.getY() + offsetY);
    }//GEN-LAST:event_imageAvatar1MousePressed

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(getBackground());
        g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 20, 20);
        super.paint(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.admin.swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel lblName;
    // End of variables declaration//GEN-END:variables
}
