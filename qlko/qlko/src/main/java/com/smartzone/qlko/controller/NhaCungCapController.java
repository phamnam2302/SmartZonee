package com.smartzone.qlko.controller;


import com.smartzone.qlko.entity.NhaCungCap;
import com.smartzone.qlko.repository.NhaCungCapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nhacungcap")
@CrossOrigin(origins = "*") // Cho phép Frontend gọi API
public class NhaCungCapController {

    @Autowired
    private NhaCungCapRepository repo;

    // 1. Lấy danh sách (Mặc định sắp xếp backend hoặc để frontend lo)
    @GetMapping
    public List<NhaCungCap> getAll() {
        return repo.findAll();
    }

    // 2. Thêm mới
    @PostMapping
    public ResponseEntity<?> create(@RequestBody NhaCungCap ncc) {
        if (repo.existsById(ncc.getMaNCC())) {
            return ResponseEntity.badRequest().body("Mã nhà cung cấp đã tồn tại!");
        }
        ncc.setNgayTao(new Date());
        ncc.setNgaySua(new Date());
        return ResponseEntity.ok(repo.save(ncc));
    }

    // 3. Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody NhaCungCap ncc) {
        Optional<NhaCungCap> exists = repo.findById(id);
        if (exists.isPresent()) {
            NhaCungCap current = exists.get();
            current.setTenNCC(ncc.getTenNCC());
            current.setSdt(ncc.getSdt());
            current.setEmail(ncc.getEmail());
            current.setDiaChi(ncc.getDiaChi());
            current.setHinhAnh(ncc.getHinhAnh());
            current.setNgaySua(new Date()); // Cập nhật ngày sửa
            return ResponseEntity.ok(repo.save(current));
        }
        return ResponseEntity.notFound().build();
    }

    // 4. Xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/next-id")
    public ResponseEntity<String> getNextId() {
        List<NhaCungCap> list = repo.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.ok("NCC001");
        }

        // Tìm mã lớn nhất hiện tại (Logic này giả định mã luôn là NCC + 3 số)
        String maxId = list.stream()
                .map(NhaCungCap::getMaNCC)
                .max(Comparator.naturalOrder())
                .orElse("NCC000");

        try {
            // Tách phần số: "NCC005" -> lấy "005" -> parse thành 5
            int currentNum = Integer.parseInt(maxId.substring(3));
            // Tăng lên 1 và format lại thành 3 chữ số (006)
            String nextId = String.format("NCC%03d", currentNum + 1);
            return ResponseEntity.ok(nextId);
        } catch (NumberFormatException e) {
            // Phòng trường hợp dữ liệu cũ không đúng định dạng
            return ResponseEntity.ok("NCC001");
        }
    }
}