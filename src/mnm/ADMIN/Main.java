/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package mnm.ADMIN;

import com.admin.component.Menu;
import com.admin.form.Form1;
import com.admin.form.Form2;
import com.admin.model.ModelMenu;
import com.login.main.LoginForm;
import com.admin.event.EventMenuSelectedForm;
import com.admin.form.Form3;
import com.admin.form.Form4;
import com.admin.form.Form5;
import com.admin.form.Form6;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import mnm.helper.DialogHelper;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author Duc Tien
 */
public class Main extends javax.swing.JPanel {

    private Menu menu;
    private JPanel main = new JPanel();
    private MigLayout layout;
    private Animator animator;
    private boolean menuShow;
    private LoginForm loginform;

    public Main(String id) {
        initComponents();
        setBackground(new Color(0, 0, 0, 0)); // xoa background
        menu = new Menu(id);
        init();
    }

    /**
     * Hủy bỏ chương trình sau khi xác nhận từ người dùng.
     */
    private void cancel() {
        boolean can = DialogHelper.confirm(this, "Bạn có muốn thoát chương trình không ?");
        if (can) {
            System.exit(0);
        }
    }

    /**
     * Khởi tạo giao diện chính với bố cục và các sự kiện cần thiết.
     */
    private void init() {
        // Khởi tạo bố cục MigLayout cho body
        layout = new MigLayout("fill", "0[]10[]5", "0[fill]0");
        body.setLayout(layout);

        // Cài đặt thuộc tính cho main
        main.setOpaque(false);
        main.setLayout(new BorderLayout());

        // Thêm sự kiện cho nút Logout trong menu
        menu.addEventLogout(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cancel();
            }
        });

        // Thêm sự kiện cho nút Menu trong menu
        menu.addEventMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });

        // Thiết lập sự kiện khi chọn một mục trong menu
        menu.setEvent(new EventMenuSelectedForm() {
            @Override
            public void selected(int index) {
                // Hiển thị form tương ứng với mục được chọn
                switch (index) {
                    case 0:
                        showForm(new Form1());
                        break;
                    case 1:
                        showForm(new Form2());
                        break;
                    case 2:
                        showForm(new Form3());
                        break;
                    case 3:
                        showForm(new Form4());
                        break;
                    case 4:
                        showForm(new Form5());
                        break;
                    case 5:
                        showForm(new Form6());
                        break;
                    default:
                        // Thêm xử lý cho các trường hợp khác nếu cần
                        break;
                }
            }
        });

        // Thêm các mục menu với tên và biểu tượng tương ứng
        menu.addMenu(new ModelMenu("Bảng Điều Khiển", new ImageIcon(getClass().getResource("/com/admin/icon/dashboard_1.png"))));
        menu.addMenu(new ModelMenu("Quản Lý Sản Phẩm", new ImageIcon(getClass().getResource("/com/admin/icon/products.png"))));
        menu.addMenu(new ModelMenu("Quản Lý Cửa Hàng", new ImageIcon(getClass().getResource("/com/admin/icon/store.png"))));
        menu.addMenu(new ModelMenu("Quản Lý Hệ Thống", new ImageIcon(getClass().getResource("/com/admin/icon/system.png"))));
        menu.addMenu(new ModelMenu("Quản Lý Tài Khoản", new ImageIcon(getClass().getResource("/com/admin/icon/user_1.png"))));
        menu.addMenu(new ModelMenu("Dịch Vụ", new ImageIcon(getClass().getResource("/com/admin/icon/service.png"))));

        // Thêm các thành phần vào body
        body.add(menu, "w 50!");
        body.add(main, "w 100%");

        // Khởi tạo đối tượng TimingTarget để điều chỉnh hiệu ứng menu
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

        // Khởi tạo đối tượng Animator để thực hiện hiệu ứng
        animator = new Animator(400, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);

        // Hiển thị Form1 khi khởi động ứng dụng
        showForm(new Form1());
    }

    /**
     * Hiển thị một thành phần trên giao diện chính.
     *
     * @param com Thành phần cần hiển thị.
     */
    private void showForm(Component com) {
        main.removeAll();
        main.add(com);
        main.repaint();
        main.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new com.admin.swing.Background();

        body.setBackground(new java.awt.Color(249, 249, 249));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1211, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.admin.swing.Background body;
    // End of variables declaration//GEN-END:variables
}
