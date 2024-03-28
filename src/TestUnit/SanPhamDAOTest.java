package TestUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import mnm.DAO.SanPhamDAO;
import mnm.model.SanPham;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SanPhamDAOTest {
	public SanPhamDAO spDAO;

	@BeforeEach
	void setUp() throws Exception {
		spDAO = new SanPhamDAO();
	}

	@AfterEach
	void tearDown() throws Exception {
		spDAO = null;
	}

	@Test
	@Order(1)
	void testInsertSanPham_Success() {
		// Dữ liệu sản phẩm mới
		SanPham sanPham = new SanPham();
		sanPham.setMaSP("SP064");
		sanPham.setTenSP("Mô hình Rồng Chibi 2024");
		sanPham.setDonGia(99000);
		sanPham.setMaLoai("MH001");
		sanPham.setNsx("XYZ Toy Factory");
		sanPham.setNguonGoc("TrungQuoc");
		sanPham.setMoTa("Mô hình rồng chibi cho các anh chị em đam mê nghệ thuật đỉnh cao từ chế tác ạ");
		sanPham.setSoLuongTon(130);
		sanPham.setNgaySx(Date.valueOf("2024-03-25"));
		sanPham.setHinh("mohinhrong.png");
		sanPham.setTrangThai(true);

		int result = spDAO.insertSanPham(sanPham);
		assertEquals(1, result);
	}
	
	@Test
	@Order(2)
    void testInsertSanPham_FailureDuplicateMaSP() {
        // Dữ liệu sản phẩm mới trùng mã SP
        SanPham sanPham = new SanPham();
        sanPham.setMaSP("SP064");
        sanPham.setTenSP("Mô hình Rồng Chibi 2024");
        // Điều chỉnh giá trị khác để không trùng lặp hoàn toàn với test trước
        sanPham.setDonGia(100000);
        sanPham.setMaLoai("MH001");
        sanPham.setNsx("XYZ Toy Factory");
        sanPham.setNguonGoc("TrungQuoc");
        sanPham.setMoTa("Mô hình rồng chibi cho các anh chị em đam mê nghệ thuật đỉnh cao từ chế tác ạ");
        sanPham.setSoLuongTon(130);
        sanPham.setNgaySx(Date.valueOf("2024-03-25"));
        sanPham.setHinh("mohinhrong.png");
        sanPham.setTrangThai(true);

        int result = spDAO.insertSanPham(sanPham);
        assertEquals(-1, result);
    }

	@Test
	@Order(3)
    void testInsertSanPham_EmptyData() {
        // Dữ liệu sản phẩm mới rỗng
        SanPham sanPham = new SanPham();
        sanPham.setMaSP("");
        sanPham.setTenSP("");
        // Điều chỉnh giá trị khác để không trùng lặp hoàn toàn với test trước
        sanPham.setDonGia(100000);
        sanPham.setMaLoai("MH001");
        sanPham.setNsx("XYZ Toy Factory");
        sanPham.setNguonGoc("");
        sanPham.setMoTa("");
        sanPham.setSoLuongTon(0);
        sanPham.setNgaySx(null);
        sanPham.setHinh("");
        sanPham.setTrangThai(false);

        int result = spDAO.insertSanPham(sanPham);
        assertEquals(-1, result);
    }
	
	 @Test
	 @Order(4)
	    void testUpdateSanPham_Success() {
	        SanPham sanPham = new SanPham();
	        sanPham.setMaSP("SP064");
	        // Cập nhật giá sản phẩm từ 99000 thành 145000
	        sanPham.setDonGia(145000);
	        
	        sanPham.setTenSP("Mô hình Rồng Chibi 2024");
	        // Điều chỉnh giá trị khác để không trùng lặp hoàn toàn với test trước
	        sanPham.setMaLoai("MH001");
	        sanPham.setNsx("XYZ Toy Factory");
	        sanPham.setNguonGoc("TrungQuoc");
	        sanPham.setMoTa("Mô hình rồng chibi cho các anh chị em đam mê nghệ thuật đỉnh cao từ chế tác ạ");
	        sanPham.setSoLuongTon(130);
	        sanPham.setNgaySx(Date.valueOf("2024-03-25"));
	        sanPham.setHinh("mohinhrong.png");
	        sanPham.setTrangThai(true);

	        int result = spDAO.updateSanPham(sanPham);
	        assertEquals(1, result);
	    }

	    @Test
	    @Order(5)
	    void testUpdateSanPham_EmptyData() {
	        // Cập nhật dữ liệu rỗng
	        SanPham sanPham = new SanPham();
	        sanPham.setMaSP("SP064");
	        sanPham.setTenSP("");
	        sanPham.setDonGia(0); 
	        sanPham.setMaLoai("");
	        sanPham.setNsx("");
	        sanPham.setNguonGoc("");
	        sanPham.setMoTa("");
	        sanPham.setSoLuongTon(0);
	        sanPham.setNgaySx(null);
	        sanPham.setHinh("");
	        sanPham.setTrangThai(false); // Không cần cập nhật trạng thái

	        int result = spDAO.updateSanPham(sanPham);
	        assertEquals(-1, result);
	    }
	
	@Test
	@Order(6)
	void testHideSanPham_Success() {
		// Ẩn sản phẩm
		String maSP = "SP064";

		int result = spDAO.hideSanPham(maSP);
		assertEquals(1, result);
	}

	@Test
	@Order(7)
	void testHideSanPham_NonexistentMaSP() {
		// Ẩn sản phẩm không tồn tại
		String maSP = "dsfbdgfhg";

		int result = spDAO.hideSanPham(maSP);
		assertEquals(0, result);
	}

	@Test
	@Order(8)
	void testFindById_Success() {
		// Tìm kiếm sản phẩm theo MaSP đã thêm trước đó
		String maSP = "SP064";

		SanPham result = spDAO.findById(maSP);
		assertNotNull(result);
	}

	@Test
	@Order(9)
	void testFindById_NonexistentMaSP() {
		// Tìm kiếm sản phẩm với MaSP không tồn tại
		String maSP = "SP067864";

		SanPham result = spDAO.findById(maSP);
		assertNull(result);
	}

}
