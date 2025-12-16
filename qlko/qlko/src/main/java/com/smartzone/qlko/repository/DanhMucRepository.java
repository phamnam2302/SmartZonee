package com.smartzone.qlko.repository;



import com.smartzone.qlko.entity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMuc, String> {
    // Hàm tìm kiếm theo Tên hoặc Mã (cho chức năng tìm kiếm Server-side nếu cần)
    @Query("SELECT d FROM DanhMuc d WHERE d.tenDanhMuc LIKE %?1% OR d.maDanhMuc LIKE %?1%")
    List<DanhMuc> search(String keyword);
}