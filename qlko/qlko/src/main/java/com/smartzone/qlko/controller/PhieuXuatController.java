package com.smartzone.qlko.controller;

import com.smartzone.qlko.entity.PhieuXuat;
import com.smartzone.qlko.service.PhieuXuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phieuxuat")
@CrossOrigin(origins = "*")
public class PhieuXuatController {

    @Autowired
    private PhieuXuatService service;

    @GetMapping
    public List<PhieuXuat> getAll() {
        return service.findAll();
    }

    @GetMapping("/next-id")
    public ResponseEntity<String> getNextId() {
        return ResponseEntity.ok(service.generateNextId());
    }

    // --- SỬA HÀM CREATE ---
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PhieuXuat px) {
        try {
            // 1. Xử lý MaDonHang: Nếu rỗng thì set thành NULL
            if (px.getMaDonHang() != null && px.getMaDonHang().trim().isEmpty()) {
                px.setMaDonHang(null);
            }

            // 2. Sinh mã nếu chưa có
            if (px.getMaPhieuXuat() == null || px.getMaPhieuXuat().isEmpty()) {
                px.setMaPhieuXuat(service.generateNextId());
            }

            return ResponseEntity.ok(service.save(px));
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console để debug
            return ResponseEntity.internalServerError().body("Lỗi Server: " + e.getMessage());
        }
    }

    // --- SỬA HÀM UPDATE ---
    @PutMapping
    public ResponseEntity<?> update(@RequestBody PhieuXuat px) {
        try {
            // Xử lý MaDonHang: Nếu rỗng thì set thành NULL
            if (px.getMaDonHang() != null && px.getMaDonHang().trim().isEmpty()) {
                px.setMaDonHang(null);
            }

            return ResponseEntity.ok(service.save(px));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi cập nhật: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}