package com.smartzone.qlko.repository;

import com.smartzone.qlko.entity.PhieuXuat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuXuatRepository extends JpaRepository<PhieuXuat, String> {
    @Query(value = "SELECT maPhieuXuat FROM phieuxuat ORDER BY maPhieuXuat DESC LIMIT 1", nativeQuery = true)
    String findLastId();
}