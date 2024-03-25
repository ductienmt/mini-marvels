package com.admin.form;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import mnm.DAO.ChiTietSanPhamDAO;
import mnm.DAO.SanPhamDAO;
import mnm.helper.DateHelper;
import mnm.model.ChiTietSanPham;
import mnm.model.SanPham;

public class Form2 extends javax.swing.JPanel {

    private String imageName = null;
    private static String hinh;
    private SanPhamDAO spDAO = new SanPhamDAO();
    private ChiTietSanPhamDAO ctSpDAO = new ChiTietSanPhamDAO();
    private static Boolean editbtn = false;

    public Form2() {
        initComponents();
        setOpaque(false);
    }

    private static String[] splitAlphaNumeric(String input) {
        // Sử dụng biểu thức chính quy để tách phần số và phần chữ
        Pattern pattern = Pattern.compile("([a-zA-Z]+)([0-9]+)");
        Matcher matcher = pattern.matcher(input);

        // Khởi tạo mảng để lưu phần chữ và phần số
        String[] parts = new String[2];

        // Kiểm tra xem biểu thức chính quy có khớp với chuỗi không
        if (matcher.find()) {
            // Lấy phần chữ và phần số từ kết quả của biểu thức chính quy
            parts[0] = matcher.group(1); // Phần chữ
            parts[1] = matcher.group(2); // Phần số
        } else {
            // Trường hợp không có khớp, xử lý theo ý của bạn
            System.out.println("Không có khớp");
        }

        return parts;
    }

    private void selectImg() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            hinh = selectedFile.getAbsolutePath();
            ImageIcon icon = new ImageIcon(hinh);
            hinh = selectedFile.getName();
            pic.setIcon(icon);
            pic.repaint();

            // Gọi phương thức copyImageToDirectory khi đã chọn ảnh
            copyImageToDirectory(selectedFile);
        }
    }

    private void copyImageToDirectory(File selectedFile) {
        Path sourcePath = selectedFile.toPath();
        String imageName = selectedFile.getName();
        System.out.println(sourcePath);
        System.out.println(imageName);

        // Lấy đường dẫn thư mục dự án
        String projectDirectory = System.getProperty("user.dir");

        // Tạo đường dẫn tương đối đến thư mục "src/mnm/images"
        Path relativePath = Paths.get("src", "mnm", "images", imageName);

        // Tạo đường dẫn đầy đủ đến thư mục đích
        Path destinationPath = Paths.get(projectDirectory).resolve(relativePath);

        // Kiểm tra sự tồn tại của file nguồn
        if (!Files.exists(sourcePath)) {
            System.err.println("Source file does not exist: " + sourcePath);
            return;
        }

        try {
            // Copy file
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Image copied to: " + destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý lỗi khi sao chép file
        }
    }

    public void addEvenList(ActionListener event) {
        btnList.addActionListener(event);
    }

    public void addEventAddCT(ActionListener event) {
        btnAddCT.addActionListener(event);
    }

    public String getMasp() {
        System.out.println(txtMasp.getText());
        return txtMasp.getText();
    }

    public void setLblInfo(SanPham model) {
        // Lấy đường dẫn thư mục dự án
        String projectDirectory = System.getProperty("user.dir");

        // Tạo đường dẫn tương đối đến thư mục "src/mnm/images"
        String imagePath = "src/mnm/images/" + model.getHinh();

        // Tạo đường dẫn đầy đủ đến hình ảnh
        String fullPath = Paths.get(projectDirectory, imagePath).toString();

        // Hiển thị hình ảnh
        pic.setIcon(new ImageIcon(fullPath));

        // Các dòng code khác ở đây
        txtMaLoai.setText(model.getMaLoai());
        txtMasp.setText(model.getMaSP());
        txtGia.setText(String.valueOf(model.getDonGia()));
        txtMota.setText(model.getMoTa());
        txtName.setText(model.getTenSP());
        txtNgaysanxuat.setText(DateHelper.convertDateToString(model.getNgaySx()));
        txtNguongoc.setText(model.getNguonGoc());
        txtNpp.setText(model.getNsx());
        txtSoluong.setText(String.valueOf(model.getSoLuongTon()));
    }

    private void addSanPham() {
        if (validateForm()) {
            // Lấy dữ liệu từ các trường nhập liệu
            String maSP = txtMasp.getText();
            String tenSP = txtName.getText();
            float donGia = Float.parseFloat(txtGia.getText());
            String maLoai = txtMaLoai.getText();
            String nsx = txtNpp.getText();  // Ghi chú: Trong code DAO, nsx là NhaSanXuat, bạn có thể sửa lại tùy thuộc vào thiết kế của bạn.
            String nguonGoc = txtNguongoc.getText();
            String moTa = txtMota.getText();
            int soLuongTon = Integer.parseInt(txtSoluong.getText());
            String dateString = txtNgaysanxuat.getText(); // Lấy ngày từ TextField
            Date ngaySx = DateHelper.convertStringToDate(dateString);

            // Tạo một đối tượng SanPham
            // String maSP, String tenSP, String maLoai, String nsx, String nguonGoc, String moTa, float donGia, int soLuongTon, Date ngaySx, String hinh
            SanPham sanPham = new SanPham(maSP, tenSP, maLoai, nsx, nguonGoc, moTa, donGia, soLuongTon, ngaySx, hinh);

            // Thực hiện thêm sản phẩm bằng cách gọi phương thức insertSanPham từ DAO
            int resultSP = spDAO.insertSanPham(sanPham);
            // Kiểm tra kết quả và thông báo cho người dùng
            if (resultSP > 0) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                clear();
                // Tùy chỉnh các tác vụ khác sau khi thêm sản phẩm thành công (nếu cần)
            } else {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại");
            }
        }
    }

    private void updateSanPham() {
        if (validateForm()) {
            // Lấy thông tin từ giao diện người dùng (ví dụ: thông tin được nhập vào các JTextField)
            String maSP = txtMasp.getText();
            String tenSP = txtName.getText();
            float donGia = Float.parseFloat(txtGia.getText());
            String maLoai = txtMaLoai.getText();
            String nsx = txtNpp.getText();  // Ghi chú: Trong code DAO, nsx là NhaSanXuat, bạn có thể sửa lại tùy thuộc vào thiết kế của bạn.
            String nguonGoc = txtNguongoc.getText();
            String moTa = txtMota.getText();
            int soLuongTon = Integer.parseInt(txtSoluong.getText());
            String dateString = txtNgaysanxuat.getText(); // Lấy ngày từ TextField
            Date ngaySx = DateHelper.convertStringToDate(dateString);
            // Lấy các giá trị khác từ giao diện người dùng

            // Tạo đối tượng SanPham với thông tin mới
            SanPham sanPham = new SanPham();
            sanPham.setMaSP(maSP);
            sanPham.setTenSP(tenSP);
            sanPham.setDonGia(donGia);
            sanPham.setMaLoai(maLoai);
            sanPham.setNguonGoc(nguonGoc);
            sanPham.setNsx(nsx);
            sanPham.setMoTa(moTa);
            sanPham.setSoLuongTon(soLuongTon);
            sanPham.setNgaySx(ngaySx);
            sanPham.setHinh(hinh);

            // Đặt các giá trị khác cho đối tượng SanPham
            // Gọi phương thức updateSanPham từ DAO để cập nhật thông tin sản phẩm
            SanPhamDAO sanPhamDAO = new SanPhamDAO(); // Giả sử bạn có một đối tượng DAO
            int result = sanPhamDAO.updateSanPham(sanPham);

            if (result > 0) {
                // Nếu cập nhật thành công, thông báo cho người dùng
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                clear();
            } else {
                // Nếu cập nhật thất bại, thông báo cho người dùng
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
            }
        }
    }

    public void clear() {
        pic.setIcon(new ImageIcon());
        txtGia.setText(null);
        txtMaLoai.setText(null);
        txtMasp.setText(null);
        txtMota.setText(null);
        txtName.setText(null);
        txtNgaysanxuat.setText(null);
        txtNguongoc.setText(null);
        txtNpp.setText(null);
        txtSoluong.setText(null);
    }

    private boolean isFutureDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate currentDate = LocalDate.now();
        LocalDate productionDate = LocalDate.parse(dateString, formatter);

        return productionDate.isAfter(currentDate);
    }

    private boolean isValidDateString(String dateString) {
        // Kiểm tra chuỗi ngày theo định dạng "dd-MM-yyyy"
        String regex = "\\d{2}-\\d{2}-\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateString);

        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Ngày không hợp lệ. Nhập theo định dạng dd-MM-yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra xem ngày có tồn tại không
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false); // Không cho phép chấp nhận các giá trị không hợp lệ (ví dụ: 32-01-2023)
        try {
            java.util.Date utilDate = sdf.parse(dateString);
            Date sqlDate = new Date(utilDate.getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Ngày không tồn tại. Nhập theo định dạng dd-MM-yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean validateForm() {
        // Lấy dữ liệu từ các trường nhập liệu
        String maSP = txtMasp.getText();
        String tenSP = txtName.getText();
        String giaText = txtGia.getText();
        String maLoai = txtMaLoai.getText();
        String nsx = txtNpp.getText();
        String nguonGoc = txtNguongoc.getText();
        String moTa = txtMota.getText();
        String soLuongText = txtSoluong.getText();
        String dateString = txtNgaysanxuat.getText();

        // Kiểm tra tính hợp lệ của dữ liệu
        if (maSP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (tenSP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (giaText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (maLoai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã loại sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (nsx.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập nhà sản xuất", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (nguonGoc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập nguồn gốc", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (moTa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mô tả sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (soLuongText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng tồn", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (dateString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sản xuất", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!isValidDateString(dateString)) {
            return false;
        }

        if (!isFutureDate(dateString)) {
            JOptionPane.showMessageDialog(this, "Ngày sản xuất phải là ngày trong tương lai", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        float donGia;
        int soLuongTon;

        try {
            donGia = Float.parseFloat(giaText);
            soLuongTon = Integer.parseInt(soLuongText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá và số lượng phải là số", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra các điều kiện khác nếu cần
        // ...
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMaLoai = new com.login.swing.TextField();
        txtMasp = new com.login.swing.TextField();
        txtName = new com.login.swing.TextField();
        txtGia = new com.login.swing.TextField();
        txtSoluong = new com.login.swing.TextField();
        txtNguongoc = new com.login.swing.TextField();
        txtNgaysanxuat = new com.login.swing.TextField();
        txtNpp = new com.login.swing.TextField();
        btnAdd = new com.login.swing.Button();
        btnEdit = new com.login.swing.Button();
        btnNew = new com.login.swing.Button();
        btnAddCT = new com.login.swing.Button();
        txtMota = new javax.swing.JTextArea();
        btnList = new com.login.swing.Button();
        jPanel1 = new javax.swing.JPanel();
        pic = new com.admin.swing.ImageSP();

        setBackground(new java.awt.Color(249, 249, 249));

        txtMaLoai.setBackground(new java.awt.Color(249, 249, 249));
        txtMaLoai.setLabelText("Mã Loại");

        txtMasp.setBackground(new java.awt.Color(249, 249, 249));
        txtMasp.setLabelText("Mã sản phẩm");
        txtMasp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaspActionPerformed(evt);
            }
        });

        txtName.setBackground(new java.awt.Color(249, 249, 249));
        txtName.setLabelText("Tên sản phẩm");

        txtGia.setBackground(new java.awt.Color(249, 249, 249));
        txtGia.setLabelText("Đơn giá");

        txtSoluong.setBackground(new java.awt.Color(249, 249, 249));
        txtSoluong.setLabelText("Số lượng");

        txtNguongoc.setBackground(new java.awt.Color(249, 249, 249));
        txtNguongoc.setLabelText("Nguồn gốc");

        txtNgaysanxuat.setBackground(new java.awt.Color(249, 249, 249));
        txtNgaysanxuat.setLabelText("Ngày sản xuất");

        txtNpp.setBackground(new java.awt.Color(249, 249, 249));
        txtNpp.setLabelText("Nhà phân phối");

        btnAdd.setBackground(new java.awt.Color(157, 153, 255));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm");
        btnAdd.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(157, 153, 255));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Sửa");
        btnEdit.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(157, 153, 255));
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setText("Làm mới");
        btnNew.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnAddCT.setBackground(new java.awt.Color(157, 153, 255));
        btnAddCT.setForeground(new java.awt.Color(255, 255, 255));
        btnAddCT.setText("Thêm chi tiết sản phẩm");
        btnAddCT.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnAddCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCTActionPerformed(evt);
            }
        });

        txtMota.setBackground(new java.awt.Color(249, 249, 249));
        txtMota.setColumns(20);
        txtMota.setRows(5);
        txtMota.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mô tả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(150, 150, 150))); // NOI18N

        btnList.setBackground(new java.awt.Color(157, 153, 255));
        btnList.setForeground(new java.awt.Color(255, 255, 255));
        btnList.setText("Danh sách");
        btnList.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(249, 249, 249));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hình ảnh", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(150, 150, 150))); // NOI18N

        pic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                picMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
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
                        .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addGap(115, 115, 115)
                        .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                        .addGap(244, 244, 244)
                        .addComponent(btnAddCT, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnList, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNpp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtMaLoai, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMasp, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNguongoc, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(txtGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoluong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNgaysanxuat, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addComponent(txtMota, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtMota, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNgaysanxuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNguongoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(txtNpp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddCT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnList, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        addSanPham();

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        updateSanPham();

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnAddCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddCTActionPerformed

    private void btnListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnListActionPerformed

    private void txtMaspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaspActionPerformed

    private void picMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_picMousePressed
        // TODO add your handling code here:
        selectImg();
    }//GEN-LAST:event_picMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.login.swing.Button btnAdd;
    private com.login.swing.Button btnAddCT;
    private com.login.swing.Button btnEdit;
    private com.login.swing.Button btnList;
    private com.login.swing.Button btnNew;
    private javax.swing.JPanel jPanel1;
    private com.admin.swing.ImageSP pic;
    private com.login.swing.TextField txtGia;
    private com.login.swing.TextField txtMaLoai;
    private com.login.swing.TextField txtMasp;
    private javax.swing.JTextArea txtMota;
    private com.login.swing.TextField txtName;
    private com.login.swing.TextField txtNgaysanxuat;
    private com.login.swing.TextField txtNguongoc;
    private com.login.swing.TextField txtNpp;
    private com.login.swing.TextField txtSoluong;
    // End of variables declaration//GEN-END:variables
}
