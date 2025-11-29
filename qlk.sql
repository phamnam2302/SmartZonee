-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quanlykhohang
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chitiet_donhang`
--

DROP TABLE IF EXISTS `chitiet_donhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitiet_donhang` (
  `maCTDH` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `maDonHang` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `maSanPham` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `soLuong` int NOT NULL,
  `donGiaBan` decimal(15,2) NOT NULL,
  `thanhTien` decimal(15,2) GENERATED ALWAYS AS ((`soLuong` * `donGiaBan`)) STORED,
  PRIMARY KEY (`maCTDH`),
  KEY `maDonHang` (`maDonHang`),
  KEY `maSanPham` (`maSanPham`),
  CONSTRAINT `chitiet_donhang_ibfk_1` FOREIGN KEY (`maDonHang`) REFERENCES `donhang` (`maDonHang`) ON DELETE CASCADE,
  CONSTRAINT `chitiet_donhang_ibfk_2` FOREIGN KEY (`maSanPham`) REFERENCES `sanpham` (`maSanPham`),
  CONSTRAINT `chitiet_donhang_chk_1` CHECK ((`soLuong` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiet_donhang`
--

LOCK TABLES `chitiet_donhang` WRITE;
/*!40000 ALTER TABLE `chitiet_donhang` DISABLE KEYS */;
INSERT INTO `chitiet_donhang` (`maCTDH`, `maDonHang`, `maSanPham`, `soLuong`, `donGiaBan`) VALUES ('DH001-01','DH001','I001',2,48500000.00),('DH001-02','DH001','L001',1,1990000.00),('DH002-01','DH002','MĐS001',2,4925000.00),('DH003-01','DH003','L001',1,1990000.00),('DH003-02','DH003','I001',1,48500000.00),('DH004-01','DH004','L001',3,1990000.00),('DH005-01','DH005','PKI002',1,1220000.00),('DH005-02','DH005','PKI006',1,6990000.00),('DH006-01','DH006','PKI004',1,699000.00),('DH006-02','DH006','KTM001',1,15990000.00),('DH006-03','DH006','L001',1,1990000.00),('DH007-01','DH007','RHB001',1,5290000.00),('DH008-01','DH008','I001',1,48500000.00),('DH008-02','DH008','L001',1,1990000.00),('DH008-03','DH008','PKI006',1,6990000.00),('DH009-01','DH009','KTM002',180,3590000.00),('DH010-01','DH010','PKI006',1,6990000.00),('DH011-01','DH011','RHB001',1,5290000.00),('DH012-01','DH012','PKI004',1,699000.00),('DH013-01','DH013','KTM002',12,3590000.00),('DH014-01','DH014','L001',140,1990000.00),('DH015-01','DH015','PKI004',1,699000.00),('DH016-01','DH016','PKI005',1,299000.00);
/*!40000 ALTER TABLE `chitiet_donhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitiet_phieunhap`
--

DROP TABLE IF EXISTS `chitiet_phieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitiet_phieunhap` (
  `maCTPN` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `maPhieuNhap` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `maSanPham` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `soLuong` int NOT NULL,
  `donGiaNhap` decimal(15,2) NOT NULL,
  `thanhTien` decimal(15,2) GENERATED ALWAYS AS ((`soLuong` * `donGiaNhap`)) STORED,
  PRIMARY KEY (`maCTPN`),
  KEY `maPhieuNhap` (`maPhieuNhap`),
  KEY `maSanPham` (`maSanPham`),
  CONSTRAINT `chitiet_phieunhap_ibfk_1` FOREIGN KEY (`maPhieuNhap`) REFERENCES `phieunhap` (`maPhieuNhap`) ON DELETE CASCADE,
  CONSTRAINT `chitiet_phieunhap_ibfk_2` FOREIGN KEY (`maSanPham`) REFERENCES `sanpham` (`maSanPham`),
  CONSTRAINT `chitiet_phieunhap_chk_1` CHECK ((`soLuong` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiet_phieunhap`
--

LOCK TABLES `chitiet_phieunhap` WRITE;
/*!40000 ALTER TABLE `chitiet_phieunhap` DISABLE KEYS */;
INSERT INTO `chitiet_phieunhap` (`maCTPN`, `maPhieuNhap`, `maSanPham`, `soLuong`, `donGiaNhap`) VALUES ('PN001-01','PN001','PKI005',250,200000.00),('PN001-02','PN001','PKI004',300,600000.00),('PN001-03','PN001','PKI003',120,95000.00),('PN001-04','PN001','PKI006',200,6000000.00),('PN002-01','PN002','PKI002',150,1000000.00),('PN002-02','PN002','PKI001',200,1110000.00),('PN003-01','PN003','RHB001',100,5000000.00),('PN004-01','PN004','KTM001',120,15000000.00),('PN004-02','PN004','KTM003',150,13990000.00),('PN004-03','PN004','KTM002',200,3190000.00),('PN005-01','PN005','L001',150,1500000.00),('PN006-01','PN006','I001',150,45000000.00);
/*!40000 ALTER TABLE `chitiet_phieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitiet_phieuxuat`
--

DROP TABLE IF EXISTS `chitiet_phieuxuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitiet_phieuxuat` (
  `maCTPX` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `maPhieuXuat` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `maSanPham` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `soLuong` int NOT NULL,
  `donGiaXuat` decimal(15,2) DEFAULT '0.00',
  `ghiChu` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`maCTPX`),
  KEY `maPhieuXuat` (`maPhieuXuat`),
  KEY `maSanPham` (`maSanPham`),
  CONSTRAINT `chitiet_phieuxuat_ibfk_1` FOREIGN KEY (`maPhieuXuat`) REFERENCES `phieuxuat` (`maPhieuXuat`) ON DELETE CASCADE,
  CONSTRAINT `chitiet_phieuxuat_ibfk_2` FOREIGN KEY (`maSanPham`) REFERENCES `sanpham` (`maSanPham`),
  CONSTRAINT `chitiet_phieuxuat_chk_1` CHECK ((`soLuong` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiet_phieuxuat`
--

LOCK TABLES `chitiet_phieuxuat` WRITE;
/*!40000 ALTER TABLE `chitiet_phieuxuat` DISABLE KEYS */;
INSERT INTO `chitiet_phieuxuat` VALUES ('PX001-01','PX001','L001',3,1990000.00,NULL),('PX002-01','PX002','I001',2,48500000.00,NULL),('PX002-02','PX002','L001',1,1990000.00,NULL),('PX003-01','PX003','PKI002',1,1220000.00,NULL),('PX003-02','PX003','PKI006',1,6990000.00,NULL),('PX004-01','PX004','KTM002',180,3590000.00,NULL),('PX005-01','PX005','KTM002',12,3590000.00,NULL),('PX006-01','PX006','L001',140,1990000.00,NULL),('PX007-01','PX007','PKI005',1,299000.00,NULL);
/*!40000 ALTER TABLE `chitiet_phieuxuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danhmuc`
--

DROP TABLE IF EXISTS `danhmuc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danhmuc` (
  `maDanhMuc` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenDanhMuc` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `hinhAnh` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `moTa` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `trangThai` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`maDanhMuc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danhmuc`
--

LOCK TABLES `danhmuc` WRITE;
/*!40000 ALTER TABLE `danhmuc` DISABLE KEYS */;
INSERT INTO `danhmuc` VALUES ('DM001','Máy đọc sách','1763544802465_maydocsach.jpg','Cung cấp các thiết bị chuyên dùng để đọc ebook với màn hình E-Ink chống chói, giúp đọc lâu mà không mỏi mắt. Thiết bị có thời lượng pin dài, thiết kế nhỏ gọn và hỗ trợ nhiều định dạng như EPUB, MOBI, PDF.','Hiển thị'),('DM002','Loa','1763544970736_loa.jpeg','Cung cấp đa dạng các dòng loa từ loa Bluetooth, loa mini, loa gia đình đến loa karaoke và loa vi tính','Hiển thị'),('DM003','Iphone','1763547139593_iphone.jpg','Cung cấp các dòng điện thoại Apple với thiết kế sang trọng, hiệu năng mạnh mẽ và hệ sinh thái iOS mượt mà.','Hiển thị'),('DM004','Kính thông minh','1763635621078_KinhThongMinh.jpg','Các thiết bị đeo tích hợp công nghệ, cho phép hiển thị thông tin ngay trước mắt người dùng, hỗ trợ chụp ảnh, quay video, dẫn đường, nghe gọi và kết nối với điện thoại','Hiển thị'),('DM005','Robot hút bụi','1763637126197_rbhbui.jpg','Thiết bị tự động làm sạch sàn nhà, có khả năng di chuyển thông minh, hút bụi và lau nhà theo lộ trình đã lập trình.','Hiển thị'),('DM006','Phụ kiện Iphone','1763638437303_pkip.jpeg','Phụ kiện kèm trợ các dòng iphone','Hiển thị');
/*!40000 ALTER TABLE `danhmuc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donhang`
--

DROP TABLE IF EXISTS `donhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donhang` (
  `maDonHang` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `maKhachHang` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `maNguoiLap` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngayLap` datetime DEFAULT CURRENT_TIMESTAMP,
  `tongTien` decimal(15,2) DEFAULT '0.00',
  `trangThai` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`maDonHang`),
  KEY `maKhachHang` (`maKhachHang`),
  KEY `maNguoiLap` (`maNguoiLap`),
  CONSTRAINT `donhang_ibfk_1` FOREIGN KEY (`maKhachHang`) REFERENCES `khachhang` (`maKhachHang`),
  CONSTRAINT `donhang_ibfk_2` FOREIGN KEY (`maNguoiLap`) REFERENCES `taikhoan` (`maTaiKhoan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donhang`
--

LOCK TABLES `donhang` WRITE;
/*!40000 ALTER TABLE `donhang` DISABLE KEYS */;
INSERT INTO `donhang` VALUES ('DH001','KH001','TK001','2025-11-19 17:06:00',98990000.00,'Hoàn thành'),('DH002','KH002','TK002','2025-11-19 17:06:00',9850000.00,'Chờ xử lý'),('DH003','KH001','TK001','2025-11-19 18:23:00',50490000.00,'Chờ xử lý'),('DH004','KH001','TK001','2025-11-19 18:45:00',5970000.00,'Hoàn thành'),('DH005','KH005','TK002','2025-11-20 13:09:00',8210000.00,'Hoàn thành'),('DH006','KH010','TK001','2025-11-20 13:17:00',18679000.00,'Chờ xử lý'),('DH007','KH007','TK001','2025-11-20 13:20:00',5290000.00,'Hoàn thành'),('DH008','KH011','TK001','2025-11-20 13:21:00',57480000.00,'Hoàn thành'),('DH009','KH006','TK001','2025-11-20 13:22:00',646200000.00,'Hoàn thành'),('DH010','KH003','TK001','2025-11-20 13:24:00',6990000.00,'Đang giao'),('DH011','KH002','TK001','2025-11-20 13:25:00',5290000.00,'Đang giao'),('DH012','KH005','TK001','2025-11-20 13:25:00',699000.00,'Đã hủy'),('DH013','KH003','TK002','2025-11-20 13:45:00',43080000.00,'Hoàn thành'),('DH014','KH005','TK001','2025-11-21 00:23:00',278600000.00,'Hoàn thành'),('DH015','KH003','TK005','2025-11-21 02:15:00',699000.00,'Hoàn thành'),('DH016','KH007','TK001','2025-11-27 05:01:00',299000.00,'Hoàn thành');
/*!40000 ALTER TABLE `donhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `maKhachHang` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenKhachHang` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sdt` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `diaChi` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `ngaySinh` datetime DEFAULT NULL,
  `gioiTinh` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `ngayTao` datetime DEFAULT CURRENT_TIMESTAMP,
  `ngaySua` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`maKhachHang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES ('KH001','Nguyễn Hồng Hà','0363900778','nguyenha@gmail.com','Số 38/132, Cầu Diễn, Minh Khai, Hà Nội','2002-02-05 00:00:00','Nữ','2025-11-19 10:30:24','2025-11-19 10:30:24'),('KH002','Lê Minh Đăng','0315600778','dangha@gmail.com','Bắc Đông Hưng, Hưng Yên','1998-02-07 00:00:00','Nam','2025-11-19 10:32:20','2025-11-19 10:32:20'),('KH003','Bùi Minh Anh','0267894561','minhanh@gmail.com','133, Hà Đông, Hà Nội','2000-02-10 00:00:00','Nữ','2025-11-20 13:01:25','2025-11-20 13:01:25'),('KH004','Hoàng Văn Hiệp','0123789546','hieph@gmail.com','38/134, đường CầuDiễn, Minh Khai, Hà Nội','2000-01-31 00:00:00','Nam','2025-11-20 13:02:27','2025-11-20 13:02:27'),('KH005','Phạm Ngọc Linh','0363900778','linh@gmail.com','Hoàng Mai, Hà Nội','2000-06-02 00:00:00','Nữ','2025-11-20 13:03:08','2025-11-20 13:03:08'),('KH006','Lê Minh Trí','0315600778','linhtri@gmail.com','Tôn Thất Thuyết, Mỹ Đình, Hà Nội','1998-02-05 00:00:00','Nam','2025-11-20 13:04:12','2025-11-20 13:04:12'),('KH007','Bùi Hà An','0315600778','an@gmail.com','Hoàng Mai, Hà Nội','1997-05-02 00:00:00','Nữ','2025-11-20 13:05:06','2025-11-20 13:05:06'),('KH008','Nguyễn Hoàng Trang','0363900778','trang24@gmail.com','Bắc Ninh','1985-05-24 00:00:00','Nữ','2025-11-20 13:05:53','2025-11-20 13:05:53'),('KH009','Đặng Mai Trang','0211600778','trang@gmail.com','Đông Phương, Đông Hưng, Thái Bình','2003-11-07 00:00:00','Nữ','2025-11-20 13:06:56','2025-11-20 13:06:56'),('KH010','Xuân Thế Thành','0443213845','thanh@gmail.com','Nghệ An','2002-01-07 00:00:00','Nam','2025-11-20 13:08:02','2025-11-20 13:08:02'),('KH011','Ngô Thành Nam','0363900778','nam@gmail.com','Nghĩa Hưng, Nam Định','2002-11-13 00:00:00','Nam','2025-11-20 13:09:14','2025-11-20 13:09:14');
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhacungcap`
--

DROP TABLE IF EXISTS `nhacungcap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhacungcap` (
  `maNCC` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenNCC` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `hinhAnh` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `diaChi` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `sdt` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngayTao` datetime DEFAULT CURRENT_TIMESTAMP,
  `ngaySua` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`maNCC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhacungcap`
--

LOCK TABLES `nhacungcap` WRITE;
/*!40000 ALTER TABLE `nhacungcap` DISABLE KEYS */;
INSERT INTO `nhacungcap` VALUES ('NCC001','Công ty cổ phần SamSung','1763542333466_1754033932381_OIP (1).jpg','Số 2, đường Hải Triều, Phường Bến Nghé, Quận 1, TP. Hồ Chí Minh','0123789546','samsungcty@gmail.com','2025-11-19 08:52:17','2025-11-19 08:52:17'),('NCC002','Công ty cổ phần LG','1763543324910_1754033986717_LG-Logo-2014-present.png','272 Võ Chí Công, Phường Phú Thượng, Quận Tây Hồ, Thành phố Hà Nội','0211600778','lg@gmail.com','2025-11-19 09:11:10','2025-11-19 09:11:10'),('NCC003','Công Ty TNHH Apple','1763638121634_ip.png','Số 33, đường Lê Duẩn, Phường Sài Gòn,\nThành Phố Hồ Chí Minh','0443213845','apple@gmail.com','2025-11-19 10:06:13','2025-11-20 11:28:43'),('NCC004','Panasonic VietNam','1763546801757_1754034004695_panasonic-logo-transparent-free-png.webp','117 Đường Trần Duy Hưng Quận Cầu Giấy','0215900778','panasonic@gmail.com','2025-11-19 10:07:52','2025-11-19 10:07:52'),('NCC005','RoboRox','1763637880949_Roborox.png','132, Cầu Giấy, Hà Nội','0373902778','roboroxx@gmail.com','2025-11-20 11:25:44','2025-11-20 11:25:44'),('NCC006','Mazer','1763638326894_mazer.png','Phường Trần Lãm, thành phố Thái Bình','0123789546','mophie12@gmail.com','2025-11-20 11:28:03','2025-11-20 11:32:11'),('NCC007','Meta','1763641850910_meta.png','716 - 718 Điện Biên Phủ, Phường 10, Quận 10, Thành phố Hồ Chí Minh, Việt Nam.','0215900778','meta@gmail.com','2025-11-20 12:30:54','2025-11-20 12:30:54'),('NCC008','Boox','1763642152485_boox.png','Phường 10, Quận 10, Thành phố Hồ Chí Minh, Việt Nam.','0443213845','boox@gmail.com','2025-11-20 12:35:54','2025-11-20 12:35:54');
/*!40000 ALTER TABLE `nhacungcap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieunhap`
--

DROP TABLE IF EXISTS `phieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieunhap` (
  `maPhieuNhap` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `maNCC` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `maNguoiNhap` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngayNhap` datetime DEFAULT CURRENT_TIMESTAMP,
  `tongTien` decimal(15,2) DEFAULT '0.00',
  `ghiChu` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`maPhieuNhap`),
  KEY `maNCC` (`maNCC`),
  KEY `maNguoiNhap` (`maNguoiNhap`),
  CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`maNCC`) REFERENCES `nhacungcap` (`maNCC`),
  CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`maNguoiNhap`) REFERENCES `taikhoan` (`maTaiKhoan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieunhap`
--

LOCK TABLES `phieunhap` WRITE;
/*!40000 ALTER TABLE `phieunhap` DISABLE KEYS */;
INSERT INTO `phieunhap` VALUES ('PN001','NCC003','TK001','2025-11-20 12:42:00',1441400000.00,'Nhập lô hàng phụ kiện Iphone'),('PN002','NCC006','TK002','2025-11-20 12:47:00',372000000.00,'Nhập lô hàng sạc'),('PN003','NCC005','TK001','2025-11-20 12:48:00',500000000.00,'Nhập máy hút bụi'),('PN004','NCC007','TK003','2025-11-20 12:49:00',4536500000.00,'Nhập lô hàng kính'),('PN005','NCC002','TK002','2025-11-20 12:52:00',225000000.00,'Nhập lô hàng loa'),('PN006','NCC003','TK003','2025-11-20 12:53:00',6750000000.00,'Nhập ip17');
/*!40000 ALTER TABLE `phieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieuxuat`
--

DROP TABLE IF EXISTS `phieuxuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieuxuat` (
  `maPhieuXuat` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `maNguoiXuat` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `maDonHang` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngayXuat` datetime DEFAULT CURRENT_TIMESTAMP,
  `lyDoXuat` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `ghiChu` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`maPhieuXuat`),
  KEY `maNguoiXuat` (`maNguoiXuat`),
  KEY `maDonHang` (`maDonHang`),
  CONSTRAINT `phieuxuat_ibfk_1` FOREIGN KEY (`maNguoiXuat`) REFERENCES `taikhoan` (`maTaiKhoan`),
  CONSTRAINT `phieuxuat_ibfk_2` FOREIGN KEY (`maDonHang`) REFERENCES `donhang` (`maDonHang`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieuxuat`
--

LOCK TABLES `phieuxuat` WRITE;
/*!40000 ALTER TABLE `phieuxuat` DISABLE KEYS */;
INSERT INTO `phieuxuat` VALUES ('PX001','TK001','DH004','2025-11-20 12:55:00','Xuất bán hàng','Khách quen'),('PX002','TK002','DH001','2025-11-20 12:56:00','Xuất bán hàng','Khách quen'),('PX003','TK001','DH005','2025-11-20 13:15:00','Xuất bán hàng','Khách quen'),('PX004','TK001','DH009','2025-11-20 13:30:00','Xuất bán hàng','Khách hàng lớn'),('PX005','TK002','DH013','2025-11-20 13:46:00','Xuất bán hàng','Khách quen'),('PX006','TK001','DH014','2025-11-21 00:23:00','Xuất bán hàng','Đã giao'),('PX007','TK001','DH016','2025-11-27 06:29:00','Xuất bán hàng','Bán lẻ');
/*!40000 ALTER TABLE `phieuxuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `maSanPham` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenSanPham` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `maDanhMuc` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `donViTinh` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `giaBan` decimal(15,2) DEFAULT '0.00',
  `hinhAnh` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `moTa` text COLLATE utf8mb4_unicode_ci,
  `trangThai` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngayTao` datetime DEFAULT CURRENT_TIMESTAMP,
  `ngaySua` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `mauSac` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `chatLieu` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `maNCC` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`maSanPham`),
  KEY `maDanhMuc` (`maDanhMuc`),
  KEY `fk_sanpham_ncc` (`maNCC`),
  CONSTRAINT `fk_sanpham_ncc` FOREIGN KEY (`maNCC`) REFERENCES `nhacungcap` (`maNCC`),
  CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`maDanhMuc`) REFERENCES `danhmuc` (`maDanhMuc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES ('I001','Iphone 17 Pro 1TB','DM003','Chiếc',48500000.00,'1763547330560_ip171TB.jpg','iPhone 17 Pro là chiếc điện thoại thông minh đỉnh cao nhất của Apple, đại diện cho một bước tiến nhảy vọt về thiết kế, hiệu năng và nhiếp ảnh. Với một loạt các cải tiến mang tính cách mạng, từ hệ thống camera Fusion Pro hoàn toàn mới đến chip A19 Pro mạnh mẽ, sản phẩm này được sinh ra để trở thành công cụ tối thượng cho các chuyên gia và những người đam mê công nghệ.\n\nThiết kế và Màn hình\nThiết kế mới lạ: iPhone 17 Pro từ bỏ khung viền titan và chuyển sang thiết kế nguyên khối (unibody) bằng nhôm, giúp tối ưu hóa khả năng tản nhiệt và mang lại cảm giác cầm nắm nhẹ hơn.\n\nMàn hình Super Retina XDR đỉnh cao: Màn hình 6.3 inch lớn hơn bao giờ hết, sử dụng tấm nền OLED tiên tiến với độ sáng tối đa ngoài trời lên tới 3.000 nits. Công nghệ ProMotion và màn hình Luôn Bật (Always-On) tiếp tục mang đến trải nghiệm hiển thị mượt mà và trực quan.\n\nHiệu năng và Pin\nChip A19 Pro mạnh mẽ: Sức mạnh của iPhone 17 Pro đến từ chip A19 Pro được sản xuất trên tiến trình 3nm, mang lại hiệu năng CPU và GPU vượt trội. Đây là bộ xử lý mạnh mẽ nhất trên một chiếc iPhone từ trước đến nay, đáp ứng mọi tác vụ từ chơi game đồ họa cao, chỉnh sửa video 4K cho đến xử lý các tính năng Apple Intelligence mượt mà.\n\nThời lượng pin đột phá: Pin dung lượng lớn hơn kết hợp với chip A19 Pro tiết kiệm năng lượng mang đến thời lượng sử dụng dài nhất từng có trên một chiếc iPhone.\n\nHệ thống Camera Chuyên nghiệp\nHệ thống 3 camera 48MP Fusion: Đây là nâng cấp đáng chú ý nhất. iPhone 17 Pro được trang bị ba camera sau đều có độ phân giải 48MP, bao gồm:\n\nCamera chính Fusion 48MP: Chụp ảnh siêu sắc nét với độ phân giải mặc định 24MP hoặc 48MP.\n\nCamera Ultra Wide Fusion 48MP: Lấy nét tự động và khả năng chụp macro.\n\nCamera Telephoto Fusion 48MP: Cung cấp zoom quang học lên tới 8x, mang lại khả năng chụp ảnh từ xa rõ nét chưa từng thấy.\n\nCamera trước Center Stage 18MP: Camera selfie được nâng cấp lên 18MP với tính năng Center Stage, tự động giữ bạn ở trung tâm khung hình trong các cuộc gọi video.\n\nCác tính năng khác\niOS 26 và Apple Intelligence: Chạy trên hệ điều hành iOS 26 mới nhất với các tính năng Apple Intelligence thông minh, mang lại trải nghiệm cá nhân hóa và tiện ích vượt trội.\n\nCổng USB-C: Hỗ trợ chuẩn USB 3, cho phép truyền dữ liệu nhanh hơn 20 lần so với các thế hệ trước.\n\nDung lượng: 3 mức dung lượng, gồm: 256GB, 512GB, 1TB','Còn hàng','2025-11-19 10:16:21','2025-11-19 10:16:21','Deep blue','Titan','NCC003'),('KTM001','Kính Apple Vison Pro','DM004','Chiếc',15990000.00,'1763637231869_Apple Vision Pro.jpg','Apple Vision Pro 2025 là thiết bị thực tế hỗn hợp (Mixed Reality) cao cấp nhất của Apple, kết hợp công nghệ thực tế ảo (VR) và thực tế tăng cường (AR) trong một thiết kế tinh tế, sang trọng.\nVới chip M5 mạnh mẽ, màn hình micro-OLED siêu nét và khả năng tương tác tự nhiên bằng mắt, tay, giọng nói, Vision Pro mở ra kỷ nguyên điện toán không gian (spatial computing) – nơi thế giới số và thế giới thật hòa làm một.\n\nThiết kế sang trọng – Trải nghiệm thoải mái\nApple Vision Pro 2025 được chế tác tỉ mỉ với khung hợp kim nhôm cao cấp, băng đeo Dual Knit Band êm ái, dễ điều chỉnh và Fit Dial giúp ôm khít khuôn mặt người dùng.\nPhần Light Seal gắn bằng nam châm giúp loại bỏ ánh sáng bên ngoài, mang lại trải nghiệm đắm chìm hoàn toàn.\nTùy chọn Solo Knit Band nhẹ và thoáng khí cũng được tích hợp để đáp ứng sở thích cá nhân.\n\nhttps://cdn.hstatic.net/files/200000785683/file/apple_vision_pro_4.mp4\nChip M5 siêu mạnh – Hiệu năng vượt trội\nApple trang bị cho Vision Pro chip M5, mang lại tốc độ xử lý nhanh hơn gấp đôi thế hệ trước, kết hợp cùng chip R1chuyên xử lý tín hiệu từ camera, cảm biến và micro.\nCặp đôi chip này giúp Vision Pro 2025 hiển thị hình ảnh và không gian thực tế với độ trễ cực thấp (12 ms), đảm bảo mọi chuyển động và tương tác diễn ra mượt mà, tự nhiên.','Còn hàng','2025-11-20 11:16:09','2025-11-20 12:34:33','Trắng','Titan','NCC007'),('KTM002','XReal eye','DM004','Chiếc',3590000.00,'1763637383269_ktmXRealEye.jpg','XREAL Eye là module camera nhỏ gọn dành cho kính AR XREAL One Series, mang đến khả năng theo dõi mắt (eye-tracking) và chuyển động 6DoF hiện đại. Sản phẩm giúp người dùng tương tác chân thực hơn, trải nghiệm AR/VR đắm chìm hơn và dễ dàng ghi lại những khoảnh khắc đáng nhớ.','Còn hàng','2025-11-20 11:17:43','2025-11-20 12:33:04','Đen','Nhựa','NCC007'),('KTM003','Kính Meta Ray-Ban Wayfarer Gen 2','DM004','Chiếc',14490000.00,'1763637517983_Kính Meta.jpg','Thiết kế & phong cách\nRay-Ban | Meta Wayfarer Gen 2 giữ nguyên vẻ ngoài Wayfarer biểu tượng với khung sang trọng, phù hợp nhiều phong cách. Tròng tự động điều chỉnh ánh sáng, bảo vệ mắt khi di chuyển trong nhà và ngoài trời. Hỗ trợ tròng Rx cho người dùng cần kính cận hoặc loạn.\n\nCamera & ghi hình\nKính được trang bị camera ultra-wide 12MP, quay video chất lượng Ultra HD 3K siêu sắc nét. Hỗ trợ nhiều chế độ ghi hình khác nhau, từ quay ngắn đến video hành trình. Sắp có bản cập nhật thêm Hyperlapse và Slow Motion, đáp ứng nhu cầu sáng tạo nội dung chuyên nghiệp.\n\nTrợ lý AI & dịch thuật\nTích hợp Meta AI, người dùng có thể ra lệnh bằng giọng nói để tìm kiếm, tra cứu, dịch ngôn ngữ hoặc đưa ra chỉ dẫn theo ngữ cảnh camera. Tính năng dịch hội thoại trực tiếp giúp giao tiếp dễ dàng hơn trong môi trường đa ngôn ngữ, kể cả khi offline nếu đã tải gói ngôn ngữ.\n\nÂm thanh & hội thoại\nHệ thống loa open-ear cho phép nghe nhạc, gọi điện hoặc nhận phản hồi từ AI mà vẫn giữ kết nối với môi trường xung quanh. Bộ micro đa hướng kết hợp tính năng Conversation Focus giúp lọc ồn, khuếch đại giọng nói đối diện – lý tưởng cho hội thoại nơi đông người.\n\nPin & hộp sạc\nThời lượng pin lên tới 8 giờ liên tục, gấp đôi thế hệ trước. Hộp sạc đi kèm cung cấp thêm 48 giờ sử dụng, tiện lợi khi di chuyển xa. Hỗ trợ sạc nhanh, chỉ 20 phút cho thời lượng sử dụng thêm nhiều giờ.\n\nKết nối & lưu trữ\nHỗ trợ Wi-Fi 6 và Bluetooth 5.3 cho truyền tải dữ liệu mượt mà, đồng bộ nhanh với điện thoại. Bộ nhớ trong 32GB đủ để lưu ảnh, video và dữ liệu AI, sẵn sàng cho mọi nhu cầu sáng tạo và lưu trữ hàng ngày.\n\nỨng dụng thực tế\nNgười sáng tạo nội dung: quay vlog, livestream rảnh tay, ghi lại khoảnh khắc tức thì.\n\nDoanh nhân: dịch hội thoại, ghi chú, tìm thông tin nhanh trong công việc.\n\nNgười du lịch: kính mát thời trang kiêm thiết bị lưu giữ hành trình.\n\nNgười dùng hàng ngày: trải nghiệm thời trang Ray-Ban cổ điển kết hợp công nghệ AI hiện đại.\nVì sao nên chọn Ray-Ban | Meta Wayfarer Gen 2?\nPhong cách Ray-Ban huyền thoại kết hợp công nghệ AI tiên tiến.\n\nCamera nâng cấp 3K, bắt trọn khoảnh khắc sống động hơn bao giờ hết.\n\nPin gấp đôi thế hệ trước, kèm hộp sạc tiện dụng.\n\nTính năng thông minh đa dạng: Meta AI, dịch hội thoại, Conversation Focus.\n\nPhù hợp mọi đối tượng: từ sáng tạo nội dung, kinh doanh, du lịch đến giải trí hàng ngày.','Còn hàng','2025-11-20 11:20:13','2025-11-20 12:32:11','Đen','Nhựa','NCC007'),('L001','Loa Vifa Mini','DM002','Chiếc',1990000.00,'1763545972384_loaVifaMini.jpg','Loa Bluetooth Vifa Mini là thành viên nhỏ tuổi nhất trong gia đình vifa, xét theo dòng chữ mô tả in trên hộp thì có nhiều điểm nổi bật, trong đó phải kể đến công nghệ âm thanh Vifa, âm thanh khuếch đại ngoài trời. chức năng, trường âm thanh vòm nổi 360° và hỗ trợ 2 thiết bị nối tiếp. Có thể thấy, Vifa đã dành rất nhiều ưu ái cho chiếc loa Bluetooth di động mới này, đồng thời nó còn có khả năng chống rơi từ độ cao 1,5 mét và hiệu suất chống nước, chống bụi IP67, ưu việt hơn so với các sản phẩm cạnh tranh cùng loại.\n\nLoa Bluetooth Vifa Mini có thiết kế kiểu dáng đơn giản, thời trang, bề mặt vỏ kim loại được bọc một lớp vải dệt đặc biệt, tay nghề và chất liệu rất đặc biệt. Vì được định vị là loa Bluetooth di động nên Vifa Mini có thân máy tương đối nhẹ, kích thước chỉ 105 x 93mm và nặng khoảng 0,45kg nên rất phù hợp khi sử dụng ngoài trời.','Còn hàng','2025-11-19 09:53:12','2025-11-19 10:11:16','Đen','Kim loại','NCC002'),('MĐS001','Máy đọc sách Boox Savi 6','DM001','Cái',4925000.00,'1763547951619_mdsBooxSavai6.png','Đọc sách ngon lành','Còn hàng','2025-11-19 10:26:05','2025-11-20 12:36:26','Trắng','Titan','NCC008'),('MĐS002','Máy đọc sách Savi 7','DM001','Chiếc',2500000.00,'1763691150371_maydocsach.jpg','hiện đại','Còn hàng','2025-11-21 02:12:38','2025-11-21 02:13:52','Đen','Nhựa','NCC008'),('PKI001','Pin sạc dự phòng Mazer MagAir14 Duo 10.000mAh','DM006','Cái',1590000.00,'1763638511478_pinAir14.jpeg','Pin sạc dự phòng Mazer MagAir14 Duo 10.000 mAh – Sạc không dây và có dây trong một thiết bị\nThiết kế nhỏ gọn và đa năng\nMazer MagAir14 Duo là pin dự phòng cao cấp mang đến sự linh hoạt tối đa cho người dùng Apple và thiết bị USB-C. Với dung lượng 10.000 mAh, nó đủ mạnh để sạc nhiều lần iPhone hoặc một vài thiết bị nhỏ, trong khi kích thước vẫn rất nhỏ gọn để dễ mang theo khi di chuyển.\n\nCác chế độ sạc linh hoạt\nSạc không dây MagSafe 15W\nPin hỗ trợ sạc từ tính MagSafe (Qi2) với công suất lên đến 15W, giúp kết nối nhanh và hiệu quả với iPhone 12 trở lên hoặc các thiết bị tương thích MagSafe mà không cần dùng cáp.','Còn hàng','2025-11-20 11:35:22','2025-11-20 11:35:22','Đen','Nhựa','NCC006'),('PKI002','Củ sạc MAZER Infinite Boost Super MINI GaN 66W','DM006','Chiếc',1220000.00,'1763638627163_củ sạc gaN 66w.jpg','Củ sạc MAZER Infinite Boost Super MINI GaN 66W – Nhỏ gọn, mạnh mẽ, sạc đa năng hiệu suất cao\nMAZER Infinite Boost Super MINI GaN 66W là củ sạc cao cấp mang lại trải nghiệm sạc nhanh, an toàn và tiện lợi cho người dùng hiện đại. Nhờ ứng dụng công nghệ GaN (Gallium Nitride) tiên tiến, sản phẩm sở hữu kích thước siêu nhỏ gọn nhưng vẫn cung cấp công suất lên tới 66W, đủ sức sạc đồng thời laptop, smartphone và các thiết bị di động khác mà không lo quá tải.\n\nThiết kế nhỏ gọn – Tiện dụng cho mọi chuyến đi\nCủ sạc MAZER Infinite Boost Super MINI GaN 66W được thiết kế tinh tế với chất liệu PC + ABS chịu nhiệt cao, mang lại cảm giác chắc chắn và bền bỉ. Chân cắm gập tiện lợi giúp dễ dàng mang theo trong balo hay túi xách mà không chiếm nhiều diện tích. Sản phẩm còn đi kèm bộ đầu chuyển đổi đa dạng (EU/UK/AU), tương thích điện áp 100–240V, phù hợp cho người thường xuyên du lịch hoặc công tác quốc tế.','Còn hàng','2025-11-20 11:37:23','2025-11-20 11:37:23','Đen','Nhựa','NCC006'),('PKI003','DEKEY Magic Cover Ultral Crystal case With Magsafe iPhone 17 Series','DM006','Chiếc',129000.00,'1763638756461_ốp lưng ip17.jpg','Giới thiệu sản phẩm\nKhi sở hữu iPhone 17 Series, ai cũng muốn giữ cho thiết bị luôn sáng bóng và an toàn. DEKEY Magic Cover Ultral Crystal Case With MagSafe được sinh ra cho mục đích đó – mang đến giải pháp bảo vệ iPhone cao cấp với thiết kế trong suốt, ôm khít, và tương thích hoàn hảo với hệ sinh thái MagSafe của Apple.\nSản phẩm không chỉ giúp bảo vệ iPhone 17, iPhone Air, iPhone 17 Pro và iPhone 17 Pro Max khỏi va đập, trầy xước, mà còn tôn lên vẻ đẹp nguyên bản của máy nhờ lớp vỏ trong suốt chống ố vàng hiệu quả.\n\nThiết kế trong suốt – Siêu mỏng, tinh tế, cao cấp\nChất liệu nhựa cao cấp (PC + TPU) chống ố vàng, bám bụi và trầy xước hiệu quả.\n\nThiết kế siêu mỏng nhẹ, chỉ vài mm, đảm bảo cảm giác cầm nắm tự nhiên, không làm tăng kích thước máy.\n\nCông nghệ Ultral Crystal trong suốt giúp phô diễn màu sắc và thiết kế nguyên bản của iPhone 17 Series, đặc biệt phù hợp với các phiên bản màu Titanium.\n\nKhung ốp được bo tròn mềm mại, tăng độ thoải mái khi cầm, đồng thời chống va chạm và rơi rớt hiệu quả.','Còn hàng','2025-11-20 11:39:39','2025-11-20 11:39:39','Trắng','Nhựa','NCC003'),('PKI004','Dây sạc','DM006','Chiếc',699000.00,'1763638997970_dây sạc.jpeg','Sạc nhanh','Còn hàng','2025-11-20 11:43:25','2025-11-20 11:43:25','Trắng','Nhựa','NCC003'),('PKI005','Cường lực Ip17','DM006','Chiếc',299000.00,'1763639378452_cuongluc.jpg','Giới thiệu sản phẩm\nVới sự ra mắt của dòng iPhone 17 Series, nhu cầu bảo vệ màn hình ngày càng cao. DEKEY Master Glass 3D Signaturera đời như giải pháp toàn diện cho những ai tìm kiếm miếng dán cường lực iPhone cao cấp, vừa bảo vệ hiệu quả vừa giữ nguyên độ tinh tế của thiết kế gốc.\nSản phẩm được sản xuất theo tiêu chuẩn công nghệ Nhật Bản, đảm bảo độ bền, độ trong suốt và độ nhạy cảm ứng vượt trội – lý tưởng để bảo vệ màn hình iPhone 17 Pro Max khỏi trầy xước và va đập hàng ngày.\n\nThiết kế cao cấp – Mỏng nhẹ nhưng bền chắc\nĐộ dày chỉ 0.29 mm, mỏng nhẹ và hầu như không ảnh hưởng đến cảm giác vuốt chạm.\n\nCấu trúc 3D Signature giúp ôm trọn viền màn hình iPhone 17, không bị hở cạnh hay bong góc sau thời gian dài sử dụng.\n\nCông nghệ True Edge to Edge bao phủ toàn bộ bề mặt hiển thị, giúp kính liền mạch, đẹp như nguyên bản.\n\nLớp phủ kháng khuẩn và chống bám vân tay giúp màn hình luôn sạch sẽ, sáng bóng, hạn chế trầy xước và bám bẩn.\n\nMỗi miếng dán cường lực iPhone 17 được gia công tỉ mỉ với kính cường lực 10H, chống lại trầy xước từ chìa khóa, tiền xu và các vật nhọn khác.\n\nHiệu năng bảo vệ vượt trội\nKhông chỉ là một miếng dán cường lực iPhone cao cấp, DEKEY Master Glass 3D Signature còn là lá chắn bảo vệ tối đa cho dòng iPhone mới nhất:\n\nChống trầy xước và chịu lực gấp 3 lần so với kính thường.\n\nGiữ nguyên độ nhạy cảm ứng, thao tác chạm, vuốt, chơi game mượt mà như màn hình gốc.\n\nChống nứt vỡ lan – khi chịu tác động mạnh, kính sẽ vỡ an toàn, không tạo mảnh sắc gây nguy hiểm.\n\nGiảm thiểu dấu vân tay, mồ hôi và dầu – giúp màn hình iPhone 17 luôn sáng rõ, dễ vệ sinh.\n\nVới khả năng bảo vệ toàn diện, đây là lựa chọn hàng đầu để bảo vệ màn hình iPhone 17 Pro Max khỏi mọi rủi ro trong quá trình sử dụng.\n\nLý do nên chọn DEKEY Master Glass 3D Signature\nThương hiệu DEKEY uy tín – nổi tiếng trong lĩnh vực phụ kiện bảo vệ iPhone.\n\nCông nghệ tiên tiến Nhật Bản – đảm bảo chất lượng kính trong suốt, mịn, và phản hồi cảm ứng chuẩn xác.\n\nLắp đặt dễ dàng, tương thích hoàn hảo với mọi dòng iPhone 17 Series.\n\nThiết kế tràn viền sang trọng, nâng tầm thẩm mỹ thiết bị mà không ảnh hưởng tới độ mỏng.\n\nNếu bạn đang tìm miếng dán cường lực iPhone cao cấp, vừa tinh tế vừa chắc chắn, thì DEKEY Master Glass 3D Signature là lựa chọn hoàn hảo.','Còn hàng','2025-11-20 11:51:01','2025-11-20 11:51:01','Đen','Nhựa','NCC003'),('PKI006','Airpod Pro 3','DM006','Chiếc',6990000.00,'1763639985132_airpods pro 3.jpg','Thông tin chi tiết\nChất lượng âm thanh và khử tiếng ồn đỉnh cao\nChủ động Khử Tiếng Ồn (ANC) gấp đôi: Apple tuyên bố ANC trên AirPods Pro 3 có khả năng khử tiếng ồn hiệu quả gấp 2 lần so với thế hệ trước. Nhờ cấu trúc âm thanh đa cổng mới và miếng đệm tai có đệm xốp, khả năng cách âm được tối ưu hóa, mang lại trải nghiệm nghe đắm chìm tuyệt đối.\n\nÂm thanh chất lượng cao: Cấu trúc âm thanh được thiết kế lại cho âm trầm sâu hơn, âm trường rộng hơn và giọng hát rõ ràng, sống động, cho phép bạn thưởng thức từng nốt nhạc với độ chi tiết cao nhất.\n\nChế độ Xuyên Âm (Transparency Mode): Giờ đây, chế độ Xuyên Âm trở nên tự nhiên hơn bao giờ hết, giúp bạn tương tác với thế giới xung quanh một cách an toàn và thoải mái.','Còn hàng','2025-11-20 12:00:18','2025-11-20 12:00:18','Trắng','Nhựa','NCC003'),('RHB001','Robot hút bụi lau nhà Roborock Q5 Pro','DM005','Chiếc',5290000.00,'1763637739354_rb Roborock.png','Tính năng nổi bật\nLực hút cực lớn 5.500 Pa, dễ dàng hút sạch bụi bẩn khỏi các loại sàn khác nhau\nThiết kế bàn chải con lăn kép xoay ngược chiều nhau chống tóc rối\nSử dụng sóng laser để quét và xác định vị trí vật thể\nDung lượng pin 5200mAh, dọn dẹp liên tục 240 phút, diện tích lên tới 350m2\nTự động điều hướng tránh các khu vực dễ mắc kẹt, tạo tường ảo thông qua ứng dụng Roborock\nKết nối với Roborock App để điều khiển, lưu bản đồ, phân vùng dọn dẹp từ xa, tạo tường ảo và vùng cấm\nTạo bản đồ nhanh gấp 6 lần trước khi dọn dẹp\nNhận dạng và lưu giữ đến 4 tầng khác nhau\nDung tích hộp rác lên đến 770ml, xử lý lông thú cưng','Còn hàng','2025-11-20 11:23:21','2025-11-20 11:29:01','Đen','Nhựa','NCC005');
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `maTaiKhoan` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenDangNhap` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `matKhau` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hoVaTen` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `gioiTinh` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `hinhAnh` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sdt` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `maVaiTro` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `trangThai` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngayTao` datetime DEFAULT CURRENT_TIMESTAMP,
  `ngaySua` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`maTaiKhoan`),
  UNIQUE KEY `tenDangNhap` (`tenDangNhap`),
  KEY `maVaiTro` (`maVaiTro`),
  CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`maVaiTro`) REFERENCES `vaitro` (`maVaiTro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES ('TK001','vananh','abc123','Nguyễn Vân Anh','Nữ','1763705704905_1754039071113_2.jfif','nguyenvananh@gmail.com','0363900778','VT04','Hoạt động','2025-11-19 05:38:29','2025-11-27 01:20:50'),('TK002','nhuhao','abc1234','Nguyễn Như Hào','Nam','1763531740523_1754039087810_5.jfif','hao@gmail.com','0315600778','VT02','Hoạt động','2025-11-19 05:56:14','2025-11-26 17:10:43'),('TK003','admin','abc123','Lê Admin','Nữ','1763541551876_1754039133109_7.jfif','admin1@gmail.com','0385874621','VT01','Tạm khóa','2025-11-19 08:39:24','2025-11-26 15:30:42'),('TK004','nam','abc123','Phạm Văn Nam','Nam','1763647340502_1754039060895_6.jfif','nampham@gmail.com','0315600778','VT01','Hoạt động','2025-11-20 14:02:23','2025-11-20 14:02:23'),('TK005','duong','abc123','Nguyễn Phúc Dương','Nam','1763647394826_1754039117173_3.jfif','duong@gmail.com','0363900778','VT03','Hoạt động','2025-11-20 14:03:17','2025-11-20 14:03:17'),('TK006','leader01','abc123','Nguyễn Thị Hà','Nữ','1764215746299_hinh-anh-nguoi-dep-an-tuong-1.jpg','hant@gmail.com','0443213845','VT03','Hoạt động','2025-11-27 03:55:51','2025-11-27 06:44:04'),('TK007','staff02','abc123','Trần Hoài An','Nữ','1764219800801_1753452515875_1.jfif','hoaian@gmail.com','0363900778','VT02','Hoạt động','2025-11-27 05:03:23','2025-11-27 05:03:23');
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tonkho`
--

DROP TABLE IF EXISTS `tonkho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tonkho` (
  `maTonKho` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `maSanPham` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `soLuongTon` int DEFAULT '0',
  `ngayCapNhatCuoi` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`maTonKho`),
  UNIQUE KEY `maSanPham` (`maSanPham`),
  CONSTRAINT `tonkho_ibfk_1` FOREIGN KEY (`maSanPham`) REFERENCES `sanpham` (`maSanPham`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tonkho`
--

LOCK TABLES `tonkho` WRITE;
/*!40000 ALTER TABLE `tonkho` DISABLE KEYS */;
INSERT INTO `tonkho` VALUES ('0c41a8b5-8f63-441c-8960-414686dc1d7d','I001',148,'2025-11-20 12:56:46'),('1697fa74-e5f0-4f6b-a3c0-e57a4551ca6a','KTM003',150,'2025-11-20 12:52:01'),('361938f2-21d8-48de-b5e9-fe8def63374b','PKI004',300,'2025-11-20 12:46:15'),('42d004d1-9be2-4116-ae72-d842a1e1bc27','L001',6,'2025-11-21 00:24:11'),('4ec84bbb-df79-448f-90a9-8fccb01c52f9','PKI005',249,'2025-11-27 06:29:26'),('545e3e7c-abd8-4fc5-ae0e-d4cde18bc738','PKI006',199,'2025-11-20 13:16:27'),('837be9c8-0dac-43b3-8ef4-4b17a1f23a85','RHB001',100,'2025-11-20 12:49:38'),('952093d9-14c4-4ee7-84fe-5230063ede7a','PKI001',200,'2025-11-20 12:48:47'),('9a6a6585-5b49-4e3d-8c15-7404923b8508','PKI002',149,'2025-11-20 13:16:27'),('9d51a31d-165e-4ec7-a346-031378837fc5','PKI003',120,'2025-11-20 12:46:15'),('a4716d49-4e4b-4910-9ba4-40541781653a','KTM002',8,'2025-11-20 13:46:43'),('b6b19f14-e577-436b-95ec-94c592c4f3f3','KTM001',120,'2025-11-20 12:52:01');
/*!40000 ALTER TABLE `tonkho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vaitro`
--

DROP TABLE IF EXISTS `vaitro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vaitro` (
  `maVaiTro` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenVaiTro` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`maVaiTro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vaitro`
--

LOCK TABLES `vaitro` WRITE;
/*!40000 ALTER TABLE `vaitro` DISABLE KEYS */;
INSERT INTO `vaitro` VALUES ('VT01','Admin'),('VT02','Staff'),('VT03','Leader'),('VT04','Boss');
/*!40000 ALTER TABLE `vaitro` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-28 22:34:23
