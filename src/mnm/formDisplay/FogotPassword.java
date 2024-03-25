/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mnm.formDisplay;

import com.login.main.LoginForm;
import java.awt.event.KeyEvent;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import mnm.DAO.NhanVienDAO;
import mnm.helper.DialogHelper;

/**
 *
 * @author Duc Tien
 */
public class FogotPassword extends javax.swing.JFrame {

    private NhanVienDAO DAO = new NhanVienDAO();
    private final String senderEmail = "dinhtangductien26@gmail.com";
    private final String senderPass = "nkjmtfnbnxlffizn";
    private String random;
    private String email;

    /**
     * Creates new form FogotPassword
     */
    public FogotPassword() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void login() {
        if (validateForm()) {
            String newPassword = String.valueOf(txtPass.getPassword());
            DAO.updatePassword(email, newPassword);
            DialogHelper.alert(this, "Bạn đã thay đổi mật khẩu thành công");
            dispose();
            clear();
            
        }
    }

    private void cancel() {
        boolean can = DialogHelper.confirm(this, "Bạn có muốn thoát chương trình không ?");
        if (can) {
            LoginForm login = new LoginForm();
            login.setVisible(true);
            this.dispose();
        }
    }

    public void sendOTP(String senderEmail, String senderPass, String email, String random) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPass);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("MINIMARVELS");
            message.setText("Để thay đổi mật khẩu trên hệ thống bạn hãy nhập mã dưới đây vào!!\n"
                    + "Mã xác nhận 6 số của bạn là: " + random );

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã được gửi thành công!");
        } catch (MessagingException e) {
            System.out.println("Có lỗi xảy ra trong quá trình gửi email: " + e.getMessage());
        }
    }

    private boolean checkOTP() {
        String code = txtOTP.getText().trim();
        // Check if the code field is empty
        if (code.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã xác nhận");
            return false;
        }

        // Check if the code matches the expected value (random)
        if (!code.equals(random)) {
            JOptionPane.showMessageDialog(this, "Mã xác nhận không chính xác. Vui lòng nhập lại mã xác nhận");
            txtOTP.setText("");
            txtOTP.requestFocus();
            return false;
        }
        return true;
    }

    public static String generateRandomNumber() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }

    private void clear() {
        txtEmail.setText(null);
        txtOTP.setText(null);
        txtPass.setText(null);
        txtPassRe.setText(null);
    }

    private void eventSend() {
        email = txtEmail.getText().trim();

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập email của bạn");
            txtEmail.requestFocus();

            return;
        }
        if (DAO.findEmail(email)) {
            random = generateRandomNumber();
            sendOTP(senderEmail, senderPass, email, random);
            System.out.println(random);
        } else {
            JOptionPane.showMessageDialog(this, "Email này '" + email + "' không tồn tại!");
            txtEmail.setText("");
            txtEmail.requestFocus();

        }
    }

    private boolean validateForm() {
        String password = String.valueOf(txtPass.getPassword());
        String confirmPassword = String.valueOf(txtPassRe.getPassword());

        // Check if the password fields are empty
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu");
            return false;
        }

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không khớp. Vui lòng nhập lại mật khẩu");
            txtPass.setText("");
            txtPassRe.setText("");
            txtPass.requestFocus();
            return false;
        }

        checkOTP();

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        background1 = new com.login.swing.Background();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtEmail = new com.login.swing.TextField();
        btnSendOTP = new com.login.swing.Button();
        txtOTP = new com.login.swing.TextField();
        btnConfirm = new com.login.swing.Button();
        btnSubmit = new com.login.swing.Button();
        btnCancel = new com.login.swing.Button();
        txtPass = new com.login.swing.PasswordField();
        txtPassRe = new com.login.swing.PasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLayeredPane1.setBackground(new java.awt.Color(245, 245, 245));
        jLayeredPane1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/logoproject.png"))); // NOI18N

        txtEmail.setBackground(new java.awt.Color(245, 245, 245));
        txtEmail.setLabelText("Email");
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
        });

        btnSendOTP.setBackground(new java.awt.Color(157, 153, 255));
        btnSendOTP.setForeground(new java.awt.Color(255, 255, 255));
        btnSendOTP.setText("Send OTP");
        btnSendOTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendOTPActionPerformed(evt);
            }
        });

        txtOTP.setBackground(new java.awt.Color(245, 245, 245));
        txtOTP.setLabelText("OTP");
        txtOTP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtOTPKeyPressed(evt);
            }
        });

        btnConfirm.setBackground(new java.awt.Color(157, 153, 255));
        btnConfirm.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnSubmit.setBackground(new java.awt.Color(157, 153, 255));
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(157, 153, 255));
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        txtPass.setBackground(new java.awt.Color(245, 245, 245));
        txtPass.setLabelText("New Password");
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });

        txtPassRe.setBackground(new java.awt.Color(245, 245, 245));
        txtPassRe.setLabelText("Re Password");
        txtPassRe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassReKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPassRe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtOTP, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(12, 12, 12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSendOTP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSendOTP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassRe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addContainerGap(226, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(223, 223, 223))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(background1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            txtOTP.requestFocus();
            eventSend();
        }
    }//GEN-LAST:event_txtEmailKeyPressed

    private void txtOTPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOTPKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (checkOTP()) {
                txtPass.requestFocus();
            } else {
                txtOTP.setText(null);
                txtOTP.requestFocus();
            }

        }
    }//GEN-LAST:event_txtOTPKeyPressed

    private void btnSendOTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendOTPActionPerformed
        // TODO add your handling code here:
        eventSend();
    }//GEN-LAST:event_btnSendOTPActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // TODO add your handling code here:
        checkOTP();
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        login();
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        LoginForm login = new LoginForm();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            txtPassRe.requestFocus();
        }
    }//GEN-LAST:event_txtPassKeyPressed

    private void txtPassReKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassReKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            login();
        }
    }//GEN-LAST:event_txtPassReKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FogotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FogotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FogotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FogotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FogotPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.login.swing.Background background1;
    private com.login.swing.Button btnCancel;
    private com.login.swing.Button btnConfirm;
    private com.login.swing.Button btnSendOTP;
    private com.login.swing.Button btnSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private com.login.swing.TextField txtEmail;
    private com.login.swing.TextField txtOTP;
    private com.login.swing.PasswordField txtPass;
    private com.login.swing.PasswordField txtPassRe;
    // End of variables declaration//GEN-END:variables
}
