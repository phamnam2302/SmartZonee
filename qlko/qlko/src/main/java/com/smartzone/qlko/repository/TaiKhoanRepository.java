package com.smartzone.qlko.repository;

import com.smartzone.qlko.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, String> {

    boolean existsByTenDangNhap(String tenDangNhap);

    // Lọc dữ liệu (code cũ)
    @Query("SELECT t FROM TaiKhoan t WHERE " +
            "(:maVaiTro IS NULL OR :maVaiTro = '' OR t.vaiTro.maVaiTro = :maVaiTro) AND " +
            "(:gioiTinh IS NULL OR :gioiTinh = '' OR :gioiTinh = 'Tất cả' OR t.gioiTinh = :gioiTinh)")
    List<TaiKhoan> searchTaiKhoan(@Param("maVaiTro") String maVaiTro, @Param("gioiTinh") String gioiTinh);

    // --- MỚI: Lấy mã tài khoản lớn nhất hiện tại để tính mã tiếp theo ---
    @Query("SELECT MAX(t.maTaiKhoan) FROM TaiKhoan t WHERE t.maTaiKhoan LIKE 'TK%'")
    String findMaxMaTaiKhoan();
    // ...
    @Query("SELECT t FROM TaiKhoan t WHERE t.tenDangNhap = :input OR t.email = :input")
    Optional<TaiKhoan> findByTenDangNhapOrEmail(@Param("input") String input);
// ...
}