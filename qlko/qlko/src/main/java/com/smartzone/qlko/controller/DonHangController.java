package com.smartzone.qlko.controller;

import com.smartzone.qlko.entity.DonHang;
import com.smartzone.qlko.service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donhang")
@CrossOrigin(origins = "*")
public class DonHangController {

    @Autowired
    private DonHangService donHangService;

    @GetMapping
    public List<DonHang> getAll() {
        return donHangService.findAll();
    }

    @GetMapping("/next-id")
    public ResponseEntity<String> getNextId() {
        return ResponseEntity.ok(donHangService.generateNextId());
    }

    // --- SỬA ĐOẠN NÀY: Thêm try-catch để bắt lỗi 500 ---
    @PostMapping
    public ResponseEntity<?> create(@RequestBody DonHang donHang) {
        try {
            // Kiểm tra mã đơn hàng
            if (donHang.getMaDonHang() == null || donHang.getMaDonHang().isEmpty()) {
                donHang.setMaDonHang(donHangService.generateNextId());
            }

            // Gọi service lưu
            DonHang savedOrder = donHangService.save(donHang);
            return ResponseEntity.ok(savedOrder);

        } catch (Exception e) {
            // 1. In lỗi chi tiết ra màn hình Console của IntelliJ/Eclipse để bạn xem
            e.printStackTrace();

            // 2. Trả về thông báo lỗi cho Frontend hiển thị alert
            return ResponseEntity.internalServerError()
                    .body("Lỗi Server: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody DonHang donHang) {
        try {
            return ResponseEntity.ok(donHangService.save(donHang));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Lỗi cập nhật: " + e.getMessage());
        }
    }
    // ----------------------------------------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        donHangService.delete(id);
        return ResponseEntity.ok().build();
    }
}