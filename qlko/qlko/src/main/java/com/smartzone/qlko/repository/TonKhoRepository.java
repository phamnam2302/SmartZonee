package com.smartzone.qlko.repository;

import com.smartzone.qlko.entity.TonKho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TonKhoRepository extends JpaRepository<TonKho, String> {
    // Lấy danh sách sắp xếp theo số lượng tồn giảm dần (Top cao nhất)
    List<TonKho> findAllByOrderBySoLuongTonDesc();

    // Lấy danh sách sắp xếp theo số lượng tồn tăng dần (Top thấp nhất/Hết hàng)
    List<TonKho> findAllByOrderBySoLuongTonAsc();
    // Tìm kiếm tồn kho theo mã sản phẩm
    Optional<TonKho> findBySanPham_MaSanPham(String maSanPham);
}