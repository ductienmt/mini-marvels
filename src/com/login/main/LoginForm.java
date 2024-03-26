package com.login.main;

import com.admin.form.Form1;
import com.admin.form.MainForm;
import com.login.component.Header;
import com.login.swing.PanelTransparent;
import com.sale.swing.icon.GoogleMaterialDesignIcons;
import com.sale.swing.icon.IconFontSwing;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
import javax.swing.JPanel;
import mnm.ADMIN.Main;
import mnm.ADMIN.MainADMIN;
import mnm.DAO.NhanVienDAO;
import mnm.SALE.MainSALE;
import mnm.formDisplay.FogotPassword;
import mnm.formDisplay.QuenMatKhau;
import mnm.helper.AuthHelper;
import mnm.helper.DialogHelper;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class LoginForm extends javax.swing.JFrame {

    private Animator animatorLogin;
    private Animator animatorBody;
    private boolean signIn;
    private final String senderEmail = "dinhtangductien26@gmail.com";
    private final String senderPass = "nkjmtfnbnxlffizn";
    private String random;
    private static Boolean send = false;
    private MigLayout layout;
    private Main main;
//    private MainForm main = new MainForm();
//    private Header header = new Header();

    /**
     * Constructor mặc định cho giao diện đăng nhập. Khởi tạo các thành phần và
     * cài đặt màu nền.
     */
    public LoginForm() {
        initComponents();
        getContentPane().setBackground(new Color(245, 245, 245));
        // ... (Cài đặt các sự kiện và thông số cho animatorLogin và animatorBody)
        TimingTarget targetLogin = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (signIn) {
                    background1.setAnimate(fraction);
                } else {
                    background1.setAnimate(1f - fraction);
                }
            }

            @Override
            public void end() {
                if (signIn) {
                    panelLogin.setVisible(false);
                    background1.setShowPaint(true);
                    panelBody.setAlpha(0);
                    panelBody.setVisible(true);
                    animatorBody.start();
                } else {
                    enableLogin(true);
                    txtUser.grabFocus();
                }
            }
        };
        TimingTarget targetBody = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (signIn) {
                    panelBody.setAlpha(fraction);
                } else {
                    panelBody.setAlpha(1f - fraction);
                }
            }

            @Override
            public void end() {
                if (signIn == false) {
                    panelBody.setVisible(false);
                    background1.setShowPaint(false);
                    background1.setAnimate(1);
                    panelLogin.setVisible(true);
                    animatorLogin.start();
                }
            }
        };
        animatorLogin = new Animator(1500, targetLogin);
        animatorBody = new Animator(500, targetBody);
        animatorLogin.setResolution(0);
        animatorBody.setResolution(0);
//        jScrollPane1.getViewport().setOpaque(false);
//        jScrollPane1.setViewportBorder(null);
    }

    /**
     * Gửi mã OTP thông qua email.
     *
     * @param senderEmail Địa chỉ email của người gửi.
     * @param senderPass Mật khẩu của người gửi.
     * @param email Địa chỉ email người nhận mã OTP.
     * @param random Mã OTP ngẫu nhiên được tạo.
     */
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
            message.setText("Để đăng nhập trên hệ thống bạn hãy nhập mã dưới đây vào!!\n"
                    + "Mã xác nhận 6 số của bạn là: " + random);

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã được gửi thành công!");
            System.out.println(random);
        } catch (MessagingException e) {
            System.out.println("Có lỗi xảy ra trong quá trình gửi email: " + e.getMessage());
            if (e.getMessage().contains("Could not connect to SMTP host")) {
                DialogHelper.alert(this, "Vui lòng kết nối internet để gửi email.");
            } else {
                DialogHelper.alert(this, "Error: " + e.getMessage());
            }
        }

    }

    /**
     * Kiểm tra sự khớp của mã OTP nhập vào.
     *
     * @return True nếu mã OTP khớp, ngược lại trả về False.
     */
    private boolean checkOTP() {
        String code = txtCode.getText().trim();

        // Check if the code matches the expected value (random)
        if (!code.equals(random)) {
            JOptionPane.showMessageDialog(this, "Mã xác nhận không chính xác. Vui lòng nhập lại mã xác nhận");
            txtCode.setText("");
            txtCode.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Tạo và trả về một số ngẫu nhiên có 6 chữ số.
     *
     * @return Một chuỗi đại diện cho số ngẫu nhiên có 6 chữ số.
     */
    public static String generateRandomNumber() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }

    /**
     * Khởi tạo giao diện quản lý sau khi đăng nhập thành công.
     *
     * @param id Mã nhân viên hoặc quản trị viên.
     */
    private void initAD(String id) {
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        bg.setLayout(new MigLayout("fill", "0[grow,fill]0", "0[grow,fill]0"));

        main = new Main(id);

        bg.add(main, "grow, push");
    }

    private void login() {
        if (!animatorLogin.isRunning()) {
            btnSignIn.setFocusPainted(false);
            signIn = true;
            String user = txtUser.getText().trim();
            String pass = String.valueOf(txtPass.getPassword());
            String otp = txtCode.getText().trim();
            boolean action = true;
            if (user.equals("") && pass.equals("") && otp.equals("")) {
                txtUser.setHelperText("Please input user name");
                txtPass.setHelperText("Please input password");
                txtCode.setHelperText("Please input OTP");
                txtUser.grabFocus();
                action = false;
            } else if (user.equals("")) {
                txtUser.setHelperText("Please input user name");
                txtUser.grabFocus();
                action = false;
            } else if (pass.equals("")) {
                txtPass.setHelperText("Please input password");
                txtUser.setHelperText("");

                if (action) {
                    txtPass.grabFocus();
                }
                action = false;
            } else if (otp.equals("")) {
                txtPass.setHelperText("");
                txtUser.setHelperText("");
                txtCode.setHelperText("Please input OTP");
            } else {
                txtUser.setHelperText(null);
                txtPass.setHelperText("");
                txtCode.setHelperText("");

                try {
                    String maNV = txtUser.getText();
                    String password = String.valueOf(txtPass.getPassword());
                    NhanVienDAO nvDAO = new NhanVienDAO();
                    if (maNV.charAt(0) == 'Q') { // check quản trị
                        boolean loginRe = nvDAO.checkLoginQT(maNV, password);
                        if (!loginRe) {
                            DialogHelper.alert(this, "Vui lòng kiểm tra lại tài khoản và mật khẩu");
                            return;
                        } else {
                            if (action) {
                                animatorLogin.start();
                                initAD(maNV);
                                enableLogin(false);
                            }
                        }

                    } else { //check cho nhân viên bình thường
                        boolean loginRs = nvDAO.checkLogin(maNV, password);

                        if (!loginRs) {
                            DialogHelper.alert(this, "Vui lòng kiểm tra lại tài khoản và mật khẩu");
                            return;
                        } else {
                            if (action) {
                                String vaiTro = AuthHelper.isManager(maNV);
                                if (vaiTro.equals("QUANLY")) {
                                    System.out.println("QUAN LY CUA HANG");
                                }
                                if (vaiTro.equals("KHO")) {
                                    System.out.println("NHAN VIEN KHO");
                                }
                                if (vaiTro.equals("SALE")) {
                                    System.out.println("NHAN VIEN SALE");
                                    this.dispose();
                                    MainSALE mainsale = new MainSALE();
                                    mainsale.setVisible(true);

                                }
                                animatorLogin.start();
                                enableLogin(false);

                            }

                        }
                    }

                } catch (Exception e) {
                }

            }

        }
    }

    /**
     * Gửi mã OTP thông qua email và hiển thị thông báo.
     *
     * @param email Địa chỉ email nhận mã OTP.
     */
    private void sendemail(String email) {
        random = generateRandomNumber();
        new Thread(new Runnable() {
            public void run() {
                sendOTP(senderEmail, senderPass, email, random);
            }
        }).start();
        DialogHelper.alert(this, "OTP đã được gửi qua email của bạn");
        send = true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new com.login.swing.Background();
        panelLogin = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnSignIn = new com.login.swing.Button();
        txtUser = new com.login.swing.TextField();
        txtPass = new com.login.swing.PasswordField();
        btnQMK = new javax.swing.JLabel();
        txtCode = new com.login.swing.TextField();
        btnCancel = new javax.swing.JLabel();
        panelBody = new com.login.swing.PanelTransparent();
        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        background1.setLayout(new java.awt.CardLayout());

        panelLogin.setOpaque(false);

        jPanel1.setOpaque(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/logoproject.png"))); // NOI18N

        btnSignIn.setBackground(new java.awt.Color(157, 153, 255));
        btnSignIn.setForeground(new java.awt.Color(255, 255, 255));
        btnSignIn.setText("Sign In");
        btnSignIn.setEffectColor(new java.awt.Color(199, 196, 255));
        btnSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignInActionPerformed(evt);
            }
        });

        txtUser.setBackground(new java.awt.Color(245, 245, 245));
        txtUser.setLabelText("User Name");
        txtUser.setLineColor(new java.awt.Color(131, 126, 253));
        txtUser.setSelectionColor(new java.awt.Color(157, 153, 255));
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserKeyPressed(evt);
            }
        });

        txtPass.setBackground(new java.awt.Color(245, 245, 245));
        txtPass.setLabelText("Password");
        txtPass.setLineColor(new java.awt.Color(131, 126, 253));
        txtPass.setSelectionColor(new java.awt.Color(157, 153, 255));
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });

        btnQMK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnQMK.setForeground(new java.awt.Color(153, 153, 153));
        btnQMK.setText("Fogot Password ?");
        btnQMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQMKMouseClicked(evt);
            }
        });

        txtCode.setBackground(new java.awt.Color(245, 245, 245));
        txtCode.setLabelText("OTP");
        txtCode.setLineColor(new java.awt.Color(131, 126, 253));
        txtCode.setSelectionColor(new java.awt.Color(157, 153, 255));
        txtCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtCodeMousePressed(evt);
            }
        });
        txtCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnQMK)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtPass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSignIn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addComponent(txtCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnQMK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(204, 204, 204));
        btnCancel.setText("X");
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCancelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addContainerGap(452, Short.MAX_VALUE)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                        .addComponent(btnCancel)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(403, 403, 403))))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancel)
                .addGap(110, 110, 110)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        background1.add(panelLogin, "card2");

        panelBody.setBackground(new java.awt.Color(249, 249, 249));

        bg.setMinimumSize(new java.awt.Dimension(1205, 789));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1205, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 789, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelBodyLayout = new javax.swing.GroupLayout(panelBody);
        panelBody.setLayout(panelBodyLayout);
        panelBodyLayout.setHorizontalGroup(
            panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBodyLayout.setVerticalGroup(
            panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        background1.add(panelBody, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignInActionPerformed

        login();

    }//GEN-LAST:event_btnSignInActionPerformed

    private void btnQMKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQMKMouseClicked
        // TODO add your handling code here:
        FogotPassword qmk = new FogotPassword();
        qmk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnQMKMouseClicked

    private void btnCancelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMousePressed
        // TODO add your handling code here:
        cancel();
    }//GEN-LAST:event_btnCancelMousePressed

    private void txtUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPass.requestFocus();
            /*if (!send) {
                NhanVienDAO nvDAO = new NhanVienDAO();
                String maNV = txtUser.getText();
                String email = nvDAO.selectEmailbyID(maNV);
                if (email == null) {
                    DialogHelper.alert(this, "Mã người dùng không tồn tại trong hệ thống.");
                    txtUser.setText("");
                    txtUser.requestFocus();
                } else {
                    random = generateRandomNumber();
                    sendOTP(senderEmail, senderPass, email, random);
                    DialogHelper.alert(this, "OTP đã được gửi qua email của bạn");
                    send = true;
                }

            }*/

        }
    }//GEN-LAST:event_txtUserKeyPressed

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCode.requestFocus();
            if (!send) {
                NhanVienDAO nvDAO = new NhanVienDAO();
                String maNV = txtUser.getText();
                String email = nvDAO.selectEmailbyID(maNV);
                if (email == null) {
                    String emailQT = nvDAO.selectEmailbyIDQT(maNV);
                    if (emailQT == null) {
                        DialogHelper.alert(this, "Mã người dùng không tồn tại trong hệ thống.");
                        txtUser.setText("");
                        txtUser.requestFocus();
                        txtPass.setText("");
                    } else {
                        sendemail(emailQT);
                    }

                } else {
                    sendemail(email);
                }

            }

        }
    }//GEN-LAST:event_txtPassKeyPressed

    private void txtCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtCode.getText().isEmpty()) {
                DialogHelper.alert(this, "Vui lòng nhập mã OTP được gửi qua mail của bạn");
            } else {
                if (checkOTP()) {
                    login();
                }

            }
        }
    }//GEN-LAST:event_txtCodeKeyPressed

    private void txtCodeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCodeMousePressed
        // TODO add your handling code here:
        if (!send) {
            NhanVienDAO nvDAO = new NhanVienDAO();
            String maNV = txtUser.getText();
            String email = nvDAO.selectEmailbyID(maNV);
            if (email == null) {
                DialogHelper.alert(this, "Mã người dùng không tồn tại trong hệ thống.");
                txtUser.setText("");
                txtUser.requestFocus();
                txtPass.setText("");
                txtCode.setText("");
            } else {
                sendemail(email);
            }
        }
    }//GEN-LAST:event_txtCodeMousePressed

    /*btnSignOUt{
    signIn = false;
        clearLogin();
        animatorBody.start();
    }*/
    /**
     * Xử lý sự kiện khi đăng xuất.
     */
    public void evenLogout() {
        signIn = false;
        panelBody.removeForm();
        clearLogin();
        animatorBody.start();
    }

    /**
     * Thực hiện hành động khi hủy bỏ đăng nhập.
     */
    private void cancel() {
        boolean can = DialogHelper.confirm(this, "Bạn có muốn thoát chương trình không ?");
        if (can) {
            System.exit(0);
        }
    }

    /**
     * Kích hoạt hoặc vô hiệu hóa trạng thái nhập liệu và nút đăng nhập.
     *
     * @param action True để kích hoạt, False để vô hiệu hóa.
     */
    private void enableLogin(boolean action) {
        txtUser.setEditable(action);
        txtPass.setEditable(action);
        btnSignIn.setEnabled(action);
    }

    /**
     * Xóa thông tin đăng nhập trên giao diện.
     */
    public void clearLogin() {
        txtUser.setText("");
        txtPass.setText("");
        txtUser.setHelperText("");
        txtPass.setHelperText("");
        txtCode.setText(null);
        txtCode.setHelperText(null);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.login.swing.Background background1;
    private javax.swing.JLayeredPane bg;
    private javax.swing.JLabel btnCancel;
    private javax.swing.JLabel btnQMK;
    private com.login.swing.Button btnSignIn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private com.login.swing.PanelTransparent panelBody;
    private javax.swing.JPanel panelLogin;
    private com.login.swing.TextField txtCode;
    private com.login.swing.PasswordField txtPass;
    private com.login.swing.TextField txtUser;
    // End of variables declaration//GEN-END:variables
}
