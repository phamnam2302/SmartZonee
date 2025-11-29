package com.smartzone.qlko.repository;



import com.smartzone.qlko.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, String> {
    // Tìm các mã sản phẩm bắt đầu bằng prefix (ví dụ 'L%')
    @Query("SELECT s.maSanPham FROM SanPham s WHERE s.maSanPham LIKE ?1%")
    List<String> findByMaSanPhamStartingWith(String prefix);
}