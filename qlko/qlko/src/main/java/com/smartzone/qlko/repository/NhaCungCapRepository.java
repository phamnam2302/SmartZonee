package com.smartzone.qlko.repository;


import com.smartzone.qlko.entity.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, String> {
    // JPA có sẵn hàm findAll, save, deleteById rồi nên không cần viết thêm
}