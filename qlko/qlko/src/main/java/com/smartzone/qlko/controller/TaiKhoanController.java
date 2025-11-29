package com.smartzone.qlko.controller;

import com.smartzone.qlko.dto.LoginRequest;
import com.smartzone.qlko.entity.TaiKhoan;
import com.smartzone.qlko.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/taikhoan")
@CrossOrigin("*")
public class TaiKhoanController {

    @Autowired
    private TaiKhoanService service;

    @GetMapping
    public List<TaiKhoan> getAll() {
        return service.getAll();
    }

    // API TÌM KIẾM / LỌC
    @GetMapping("/search")
    public List<TaiKhoan> search(
            @RequestParam(required = false) String maVaiTro,
            @RequestParam(required = false) String gioiTinh) {
        return service.search(maVaiTro, gioiTinh);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TaiKhoan tk) {
        try {
            return ResponseEntity.ok(service.create(tk));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody TaiKhoan tk) {
        try {
            tk.setMaTaiKhoan(id);
            return ResponseEntity.ok(service.update(tk));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Không thể xóa: " + e.getMessage());
        }
    }
    // --- MỚI: API lấy mã gợi ý ---
    @GetMapping("/next-id")
    public ResponseEntity<String> getNextId() {
        return ResponseEntity.ok(service.getNextMaTaiKhoan());
    }
    // ... import LoginRequest ...

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            TaiKhoan user = service.login(loginRequest.getTaiKhoan(), loginRequest.getMatKhau());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
// ...
}