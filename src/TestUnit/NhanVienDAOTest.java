package TestUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import mnm.DAO.NhanVienDAO;
import mnm.model.NhanVien;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NhanVienDAOTest {
	public NhanVienDAO nvDAO;

	@BeforeEach
	void setUp() throws Exception {
		nvDAO = new NhanVienDAO();
	}

	@AfterEach
	void tearDown() throws Exception {
		nvDAO = null;
	}
	
//	@Ignore
//	@Test
//	void testInsert_Success() {
//		NhanVien nv = new NhanVien();
//		nv.setMaNV("MNM007");
//		nv.setHoTen("Nguyen Van A");
//		nv.setGioiTinh("Nam");
//		nv.setEmail("nv1@example.com");
//		nv.setNgaySinh(Date.valueOf("1990-01-01"));
//		nv.setMatKhau("123456");
//		nv.setVaiTro("2");
//		nv.setGhiChu("Test insert");
//		nv.setHinh("avatar.jpg");
//		nv.setMaCH("CN001");
//
//		int result = nvDAO.insert(nv);
//		assertEquals(1, result);
//	}
//
//	@Ignore
//	@Test
//	void testInsert_NullNhanVien() {
//		int result = nvDAO.insert(null);
//		assertEquals(-1, result);
//	}
//	
//	@Test
//    void testDelete_Success() {
//       
//        int result = nvDAO.delete("MNM007");
//        assertEquals(1, result);
//    }
//	
	
	@Test
    void testCheckLogin_ValidCredentials() {
        // Username và Password chính xác
        String maNV = "MNM0001";
        String password = "password";

        boolean result = nvDAO.checkLogin(maNV, password);
        assertTrue(result);
    }

	
//    @Test
//    void testCheckLogin_InvalidUsername() {
//        // Username không hợp lệ
//        String maNV = "";
//        String password = "bzrdherher236235";
//
//        boolean result = nvDAO.checkLogin(maNV, password);
//        assertFalse(result);
//    }
//
//	
//    @Test
//    void testCheckLogin_InvalidPassword() {
//        // Password không hợp lệ
//        String maNV = "nhanvien0001";
//        String password = "";
//
//        boolean result = nvDAO.checkLogin(maNV, password);
//        assertFalse(result);
//    }
//
//	
//    @Test
//    void testCheckLogin_NullCredentials() {
//        // Username và Password là null
//        String maNV = null;
//        String password = null;
//
//        boolean result = nvDAO.checkLogin(maNV, password);
//        assertFalse(result);
//    }
//	
//	@Test
//    void testCheckLoginQT_ValidCredentials() {
//        // Username và Password chính xác
//        String maNV = "QuanTri";
//        String password = "123456789";
//
//        boolean result = nvDAO.checkLoginQT(maNV, password);
//        assertTrue(result);
//    }
//
//    @Test
//    void testCheckLoginQT_InvalidUsername() {
//        // Username không hợp lệ
//        String maNV = "";
//        String password = "9876543";
//
//        boolean result = nvDAO.checkLoginQT(maNV, password);
//        assertFalse(result);
//    }
//
//    @Test
//    void testCheckLoginQT_InvalidPassword() {
//        // Password không hợp lệ
//        String maNV = "quantrihethong000";
//        String password = "";
//
//        boolean result = nvDAO.checkLoginQT(maNV, password);
//        assertFalse(result);
//    }
//
//    @Test
//    void testCheckLoginQT_NullCredentials() {
//        // Username và Password là null
//        String maNV = null;
//        String password = null;
//
//        boolean result = nvDAO.checkLoginQT(maNV, password);
//        assertFalse(result);
//    }
	
//	@Test
//	@Order(1)
//	void testInsert_NhanVien_Success() {
//	    // Thêm nhân viên mới thành công
//	    NhanVien nhanVien = new NhanVien();
//	    nhanVien.setMaNV("MNM0007");
//	    nhanVien.setHoTen("Nguyễn Văn Tèo");
//	    nhanVien.setGioiTinh("Nam");
//	    nhanVien.setEmail("nguyenvanteo.nvt@gmail.com");
//	    nhanVien.setVaiTro("2");
//	    nhanVien.setNgaySinh(Date.valueOf("1992-05-20"));
//	    nhanVien.setGhiChu("Nhân viên mới trong năm 2024 tháng 3");
//	    nhanVien.setHinh("nvt.png");
//	    nhanVien.setMaCH("CN002");
//	    nhanVien.setMatKhau("nvt19920520");
//
//	    int result = nvDAO.insert(nhanVien);
//	    assertEquals(1, result);
//	}
//
//	@Test
//	@Order(2)
//	void testInsert_NhanVien_Failure_EmptyData() {
//	    // Thêm nhân viên với dữ liệu rỗng
//	    NhanVien nhanVien = new NhanVien();
//
//	    int result = nvDAO.insert(nhanVien);
//	    assertEquals(-1, result);
//	}
//
//	@Test
//	@Order(3)
//	void testUpdate_NhanVien_Success() {
//	    // Cập nhật email của nhân viên thành công
//	    NhanVien nhanVien = new NhanVien();
//	    nhanVien.setMaNV("MNM0007");
//	    nhanVien.setEmail("nvt20051992@gmail.com");
//	    nhanVien.setHoTen("Nguyễn Văn Tèo");
//	    nhanVien.setGioiTinh("Nam");
//	    nhanVien.setVaiTro("2");
//	    nhanVien.setNgaySinh(Date.valueOf("1992-05-20"));
//	    nhanVien.setGhiChu("Nhân viên mới trong năm 2024 tháng 3");
//	    nhanVien.setHinh("nvt.png");
//	    nhanVien.setMaCH("CN002");
//	    nhanVien.setMatKhau("nvt19920520");
//
//	    int result = nvDAO.update(nhanVien);
//	    assertEquals(1, result);
//	}
//
//	@Test
//	@Order(4)
//	void testUpdate_NhanVien_Failure_EmptyMaNV() {
//	    // Cập nhật email của nhân viên với MaNV rỗng
//	    NhanVien nhanVien = new NhanVien();
//	    nhanVien.setEmail("nvt20051992@gmail.com");
//
//	    int result = nvDAO.update(nhanVien);
//	    assertEquals(-1, result);
//	}
//
//	@Test
//	@Order(5)
//	void testDelete_NhanVien_Success() {
//	    // Xóa nhân viên thành công
//	    String maNV = "MNM0007";
//
//	    int result = nvDAO.delete(maNV);
//	    assertEquals(1, result);
//	}
//
//	@Test
//	@Order(6)
//	void testDelete_NhanVien_NonexistentMaNV() {
//	    // Xóa nhân viên với MaNV không tồn tại
//	    String maNV = "MNM0008";
//
//	    int result = nvDAO.delete(maNV);
//	    assertEquals(-1, result);
//	}
//
//	@Test
//	@Order(7)
//	void testFindByEmail_Success() {
//	    // Tìm kiếm nhân viên theo email
//	    String email = "ductien.mt26@gmail.com";
//
//	    Boolean result = nvDAO.findEmail(email);
//	    assertTrue(result);
//	}
//
//	@Test
//	@Order(8)
//	void testFindByEmail_NonexistentEmail() {
//	    // Tìm kiếm nhân viên với email không tồn tại
//	    String email = "ductien.mt26";
//
//	    Boolean result = nvDAO.findEmail(email);
//	    assertFalse(result);
//	}

    	
	 
}
