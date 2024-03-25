create database MiniMarvels

use MiniMarvels

create table NhanVienQuanTri(
	ID nvarchar(10) primary key,
	HoTen nvarchar(50) not null,
	Email nvarchar(100) not null,
	Sdt nvarchar(13) not null,
	MatKhau nvarchar(100) not null,
	Hinh nvarchar(500),
);

INSERT INTO NhanVienQuanTri (ID, HoTen, Email, Sdt, MatKhau, Hinh)
VALUES ('QuanTri', N'Đức Tiến', 'ductien.ky@gmail.com', '0911201827', '123456789', 'abchabwfd.jpg');

create table CuaHang (
	MaCH nvarchar(10) primary key,
	TenCH nvarchar(100) not null,
	DiaChi nvarchar(500) not null,
        Hinh nvarchar(500)
);

create table LoaiSanPham(
	MaLoai nvarchar(10) primary key,
	TenLoai nvarchar(50) not null,
	MaCH nvarchar(10) not null,
	foreign key (MaCH) references CuaHang(MaCH)
);

create table SanPham(
	MaSP nvarchar(10) primary key,
	TenSP nvarchar(50) not null,
	DonGia float not null,
	MaLoai nvarchar(10) not null,
	NhaSanXuat nvarchar(100) not null,
	NguonGoc nvarchar(50) not null,
	MoTa nvarchar(500) not null,
	SoLuongTon int not null,
	NgaySanXuat date not null,
        Hinh nvarchar(500),
	foreign key (MaLoai) references LoaiSanPham(MaLoai)
);

create table ChiTietSanPham(
	MaCTTSP nvarchar(10) primary key,
	MaSP nvarchar(10) not null,
	ColorSanPham nvarchar(10) not null,
	ChatLieu nvarchar(10) not null,
	NgayNhap date not null,
	TiLeSP nvarchar(10) not null,
	PhienBan nvarchar(10) not null
	foreign key (MaSP) references SanPham(MaSP)
);

create table NhanVien(
	MaNV nvarchar(10) primary key,
	HoTen nvarchar(100) not null,
	GioiTinh nvarchar(4) not null,
	Email nvarchar(255) not null,
	NgaySinh date not null,
	MatKhau nvarchar(255) not null,
	VaiTro nvarchar(2) not null,
	GhiChu nvarchar(500),
	Hinh nvarchar(500),
	MaCH nvarchar(10) not null,
	foreign key (MaCH) references CuaHang(MaCH)
);


create table KhachHang(
	SoDienThoai nvarchar(13) primary key,
	HoTen nvarchar(100) not null,
	GioiTinh nvarchar(4) not null,
	Email nvarchar(255),
	NgaySinh date,
	DiaChi nvarchar(500)
);

create table NhaPhanPhoi(
	MaNPP nvarchar(10) primary key,
	TenNhaPhanPhoi nvarchar(50) not null,
	QuocGia nvarchar(50) not null
);

create table HoaDonNhap(
	MaHDN nvarchar(10) primary key,
	MaNV nvarchar(10) not null,
	MaCH nvarchar(10) not null,
	MaNPP nvarchar(10) not null,
	NgayNhap date not null,
	GhiChu nvarchar(500),
	TongTien float not null,
	foreign key (MaNPP) references NhaPhanPhoi(MaNPP)
);

create table ChiTietHoaDonNhap(
	MaCTTHDN nvarchar(10) primary key,
	MaSP nvarchar(10) not null,
	MaHDN nvarchar(10) not null,
	DonGia float not null,
	SoLuong int not null,
	TongTien float not null,
	foreign key (MaSP) references SanPham(MaSP),
	foreign key (MaHDN) references HoaDonNhap(MaHDN)
);

create table DichVu(
	MaDV nvarchar(10) primary key,
	TenDichVu nvarchar(50) not null,
	DonGia float not null
);

create table HoaDonBan(
	MaHDB nvarchar(10) primary key,
	MaNV nvarchar(10) not null,
	MaKH nvarchar(13) not null,
	MaCH nvarchar(10) not null,
	MaDV nvarchar(10),
	NgayLap date not null,
	TongTien float not null,
	GhiChu nvarchar(500)
	foreign key (MaNV) references NhanVien(MaNV),
	foreign key (MaKH) references KhachHang(SoDienThoai),
	foreign key (MaCH) references CuaHang(MaCH),
	foreign key (MaDV) references DichVu(MaDV)
);

create table ChiTietHoaDonBan(
	MaCTTHDB nvarchar(10) primary key,
	MaSP nvarchar(10) not null,
	MaHDB nvarchar(10) not null,
	SoLuong int not null,
	TongTien float not null,
	foreign key (MaSP) references SanPham(MaSP),
	foreign key (MaHDB) references HoaDonBan(MaHDB)
);

-- Insert dữ liệu vào bảng NhanVien
INSERT INTO NhanVien (MaNV, HoTen, GioiTinh, Email, NgaySinh, MatKhau, VaiTro, GhiChu, Hinh, MaCH)
VALUES 
('MNM0001', N'Nguyễn Văn A', N'Nam', 'ductien.mt26@gmail.com', '2004-09-26', 'password123', '1', null, null, 'CN001'),
('MNM0002', N'Huỳnh Hạo Nam', N'Nam', 'tt.b@example.com', '1992-05-20', 'pass123', '2', null, null, 'CN001'),
('MNM0003', N'Lê Thị A', N'Nữ', 'lt.a@example.com', '1988-09-10', 'qwerty', '1', null, null, 'CN002'),
('MNM0004', N'Nguyễn Đức Thắng', N'Nam', 'pt.d@example.com', '1995-11-25', 'abc123', '2', null, null, 'CN002'),
('MNM0005', N'Nguyễn Văn B', N'Nam', 'nv.b@example.com', '1992-05-20', 'pass123', '3', null, null, 'CN001'),
('MNM0006', N'Lê Văn C', N'Nam', 'lv.c@example.com', '1992-05-20', 'pass123', '3', null, null, 'CN002');

INSERT INTO CuaHang (MaCH, TenCH, DiaChi)
values
('CN001',N'MiniMavels Premium',N'Thủ Đức, Hồ Chí Minh'),
('CN002',N'MiniMavels Gò Vấp',N'Gò Vấp, Hồ Chí Minh')

create or alter trigger Trg_CheckAge
on NhanVien
after insert
as
begin
    declare @MinAge int
    declare @MaxAge int
    declare @CurrentAge int
    declare @NgaySinh date

    select @MinAge = 18, @MaxAge = 30

    -- Kiểm tra từng bản ghi được chèn vào bảng
    select @NgaySinh = NgaySinh from INSERTED

    -- Tính toán tuổi của nhân viên
    set @CurrentAge = datediff(year, @NgaySinh, getdate())

    -- Nếu tuổi không nằm trong khoảng từ 18 đến 30, hủy thao tác chèn dữ liệu
    if (@CurrentAge < @MinAge OR @CurrentAge > @MaxAge)
    begin
        print(N'Tuổi của nhân viên phải từ 18 đến 30')
        rollback
    end
end


