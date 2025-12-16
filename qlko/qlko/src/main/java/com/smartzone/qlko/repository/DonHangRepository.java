package com.smartzone.qlko.repository;

import com.smartzone.qlko.entity.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, String> {
    @Query(value = "SELECT maDonHang FROM donhang ORDER BY maDonHang DESC LIMIT 1", nativeQuery = true)
    String findLastId();
}