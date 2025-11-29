package com.smartzone.qlko.repository;

import com.smartzone.qlko.entity.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro, String> {
    // Không cần viết thêm gì, JpaRepository đã có sẵn save, findAll, deleteById...
}