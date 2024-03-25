package mnm.ADMIN;

import com.admin.component.Menu;
import com.admin.form.Form1;
import com.admin.form.Form2;
import com.admin.form.Form3;
import com.admin.form.Form4;
import com.admin.form.Form5;
import com.admin.form.Form6;
import com.admin.model.ModelMenu;
import com.login.main.LoginForm;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import com.admin.event.EventMenuSelectedForm;
import com.admin.form.FormDanhSachSanPham;
import com.admin.form.FormThemChiTietSanPham;
import com.sale.swing.icon.GoogleMaterialDesignIcons;
import com.sale.swing.icon.IconFontSwing;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import mnm.helper.DialogHelper;
import mnm.model.SanPham;

public class MainADMIN extends javax.swing.JFrame {

    private Menu menu;
    private JPanel main = new JPanel();
    private MigLayout layout;
    private Animator animator;
    private boolean menuShow;
    private LoginForm loginform;

    public MainADMIN() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0)); // xoa background
        menu = new Menu("QuanTri");
        init();
    }

    private void cancel() {
        boolean can = DialogHelper.confirm(this, "Bạn có muốn thoát chương trình không ?");
        if (can) {
            System.exit(0);
        }
    }

    private void init() {
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        layout = new MigLayout("fill", "0[]10[]5", "0[fill]0");
        body.setLayout(layout);
        main.setOpaque(false);
        main.setLayout(new BorderLayout());
        Form1 form1 = new Form1();
        Form2 form2 = new Form2();

        FormThemChiTietSanPham formaddct = new FormThemChiTietSanPham();
        FormDanhSachSanPham formdssp = new FormDanhSachSanPham();

        menu.addEventLogout(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cancel();
            }
        });
        menu.addEventMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
        menu.setEvent(new EventMenuSelectedForm() {
            @Override
            public void selected(int index) {
                System.out.println(index);
                if (index == 0) {

                    showForm(form1);
                    form1.initTableData();
                    form1.initTableDataSP();
                }
                if (index == 1) {
                    showForm(form2);
                }
                if (index == 2) {
                    showForm(new Form3());
                }
                if (index == 3) {
                    showForm(new Form4());
                }
                if (index == 4) {
                    showForm(new Form5());
                }
                if (index == 5) {
                    showForm(new Form6());
                }
            }
        });
        menu.addMenu(new ModelMenu("Bảng Điều Khiển", new ImageIcon(getClass().getResource("/com/admin/icon/dashboard_1.png"))));
        menu.addMenu(new ModelMenu("Quản Lý Sản Phẩm", new ImageIcon(getClass().getResource("/com/admin/icon/products.png"))));
        menu.addMenu(new ModelMenu("Quản Lý Cửa Hàng", new ImageIcon(getClass().getResource("/com/admin/icon/store.png"))));
        menu.addMenu(new ModelMenu("Quản Lý Hệ Thống", new ImageIcon(getClass().getResource("/com/admin/icon/system.png"))));
        menu.addMenu(new ModelMenu("Quản Lý Tài Khoản", new ImageIcon(getClass().getResource("/com/admin/icon/user_1.png"))));
        menu.addMenu(new ModelMenu("Dịch Vụ", new ImageIcon(getClass().getResource("/com/admin/icon/service.png"))));
        body.add(menu, "w 50!");
        body.add(main, "w 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menuShow) {
                    width = 50 + (150 * (1f - fraction));
                    menu.setAlpha(1f - fraction);
                } else {
                    width = 50 + (150 * fraction);
                    menu.setAlpha(fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!");
                body.revalidate();
            }

            @Override
            public void end() {
                menuShow = !menuShow;
            }
        };
        animator = new Animator(400, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);

        showForm(form1);
        form1.initTableData();
        form1.initTableDataSP();

        form2.addEvenList(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showForm(formdssp);
                formdssp.fillToTable();
            }
        });
        form2.addEventAddCT(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String masp = form2.getMasp();
                if (masp.isEmpty()) {
                    DialogHelper.alert(body, "Vui lòng chọn sản phẩm để thêm chi tiết");
                } else {
                    formaddct.clear();
                    showForm(formaddct);
                    formaddct.getData(masp);
//                    formaddct.fillToTable();
                }

            }
        });
        formdssp.addEvenList(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showForm(form2);
            }
        });
        // Thêm sự kiện cho bảng tblSanPham trong FormDanhSachSanPham
        formdssp.addEventTable(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Lấy thông tin sản phẩm từ FormDanhSachSanPham
                    SanPham selectedProduct = formdssp.getSelectedProductInfo();

                    // Nếu có thông tin sản phẩm, truyền cho Form2 và hiển thị nó
                    if (selectedProduct != null) {
                        form2.setLblInfo(selectedProduct);
                        showForm(form2);
                    }
                }
            }
        });

        formaddct.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showForm(form2);
                
            }
        });

        /*menu.addEventMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });*/
    }

    private void showForm(Component com) {
        main.removeAll();
        main.add(com);
        main.repaint();
        main.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new com.admin.swing.Background();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        body.setBackground(new java.awt.Color(249, 249, 249));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1131, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MainADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainADMIN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.admin.swing.Background body;
    // End of variables declaration//GEN-END:variables
}
