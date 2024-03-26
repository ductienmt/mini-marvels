package TestUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mnm.DAO.NhanVienDAO;
import mnm.model.NhanVien;

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
	
	@Ignore
	@Test
	void testInsert_Success() {
		NhanVien nv = new NhanVien();
		nv.setMaNV("MNM007");
		nv.setHoTen("Nguyen Van A");
		nv.setGioiTinh("Nam");
		nv.setEmail("nv1@example.com");
		nv.setNgaySinh(Date.valueOf("1990-01-01"));
		nv.setMatKhau("123456");
		nv.setVaiTro("2");
		nv.setGhiChu("Test insert");
		nv.setHinh("avatar.jpg");
		nv.setMaCH("CN001");

		int result = nvDAO.insert(nv);
		assertEquals(1, result);
	}

	@Ignore
	@Test
	void testInsert_NullNhanVien() {
		int result = nvDAO.insert(null);
		assertEquals(-1, result);
	}
	
	@Test
    void testDelete_Success() {
       
        int result = nvDAO.delete("MNM007");
        assertEquals(1, result);
    }
	
	@Ignore
	@Test
    void testCheckLogin_ValidCredentials() {
        // Username và Password chính xác
        String maNV = "MNM0001";
        String password = "password123";

        boolean result = nvDAO.checkLogin(maNV, password);
        assertTrue(result);
    }

	@Ignore
    @Test
    void testCheckLogin_InvalidUsername() {
        // Username không hợp lệ
        String maNV = "";
        String password = "bzrdherher236235";

        boolean result = nvDAO.checkLogin(maNV, password);
        assertFalse(result);
    }

	@Ignore
    @Test
    void testCheckLogin_InvalidPassword() {
        // Password không hợp lệ
        String maNV = "nhanvien0001";
        String password = "";

        boolean result = nvDAO.checkLogin(maNV, password);
        assertFalse(result);
    }

	@Ignore
    @Test
    void testCheckLogin_NullCredentials() {
        // Username và Password là null
        String maNV = null;
        String password = null;

        boolean result = nvDAO.checkLogin(maNV, password);
        assertFalse(result);
    }
	
	@Test
    void testCheckLoginQT_ValidCredentials() {
        // Username và Password chính xác
        String maNV = "QuanTri";
        String password = "123456789";

        boolean result = nvDAO.checkLoginQT(maNV, password);
        assertTrue(result);
    }

    @Test
    void testCheckLoginQT_InvalidUsername() {
        // Username không hợp lệ
        String maNV = "";
        String password = "9876543";

        boolean result = nvDAO.checkLoginQT(maNV, password);
        assertFalse(result);
    }

    @Test
    void testCheckLoginQT_InvalidPassword() {
        // Password không hợp lệ
        String maNV = "quantrihethong000";
        String password = "";

        boolean result = nvDAO.checkLoginQT(maNV, password);
        assertFalse(result);
    }

    @Test
    void testCheckLoginQT_NullCredentials() {
        // Username và Password là null
        String maNV = null;
        String password = null;

        boolean result = nvDAO.checkLoginQT(maNV, password);
        assertFalse(result);
    }
	
	 
}
