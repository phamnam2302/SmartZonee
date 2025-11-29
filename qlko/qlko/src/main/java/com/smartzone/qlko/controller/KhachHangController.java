package com.smartzone.qlko.controller;


import com.smartzone.qlko.entity.KhachHang;
import com.smartzone.qlko.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/khachhang")
@CrossOrigin(origins = "*")
public class KhachHangController {

    @Autowired
    private KhachHangRepository repo;

    // Lấy danh sách
    @GetMapping
    public List<KhachHang> getAll() {
        return repo.findAll();
    }

    // API Sinh mã tự động KHxxx
    @GetMapping("/next-id")
    public ResponseEntity<String> getNextId() {
        List<KhachHang> list = repo.findAll();
        if (list.isEmpty()) return ResponseEntity.ok("KH001");

        String maxId = list.stream()
                .map(KhachHang::getMaKhachHang)
                .max(Comparator.naturalOrder())
                .orElse("KH000");
        try {
            int currentNum = Integer.parseInt(maxId.substring(2)); // Bỏ chữ "KH"
            return ResponseEntity.ok(String.format("KH%03d", currentNum + 1));
        } catch (Exception e) {
            return ResponseEntity.ok("KH001");
        }
    }

    // Thêm mới
    @PostMapping
    public ResponseEntity<?> create(@RequestBody KhachHang kh) {
        if (repo.existsById(kh.getMaKhachHang())) {
            return ResponseEntity.badRequest().body("Mã khách hàng đã tồn tại!");
        }
        kh.setNgayTao(new Date());
        kh.setNgaySua(new Date());
        return ResponseEntity.ok(repo.save(kh));
    }

    // Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody KhachHang kh) {
        Optional<KhachHang> exists = repo.findById(id);
        if (exists.isPresent()) {
            KhachHang current = exists.get();
            current.setTenKhachHang(kh.getTenKhachHang());
            current.setSdt(kh.getSdt());
            current.setEmail(kh.getEmail());
            current.setDiaChi(kh.getDiaChi());
            current.setNgaySinh(kh.getNgaySinh());
            current.setGioiTinh(kh.getGioiTinh());
            current.setNgaySua(new Date());
            return ResponseEntity.ok(repo.save(current));
        }
        return ResponseEntity.notFound().build();
    }

    // Xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}