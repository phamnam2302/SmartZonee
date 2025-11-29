package com.smartzone.qlko.repository;

import com.smartzone.qlko.entity.PhieuNhap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuNhapRepository extends JpaRepository<PhieuNhap, String> {
    @Query(value = "SELECT maPhieuNhap FROM phieunhap ORDER BY maPhieuNhap DESC LIMIT 1", nativeQuery = true)
    String findLastId();
}