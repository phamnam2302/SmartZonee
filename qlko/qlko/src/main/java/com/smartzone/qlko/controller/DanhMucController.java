package com.smartzone.qlko.controller;



import com.smartzone.qlko.entity.DanhMuc;
import com.smartzone.qlko.repository.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/danhmuc")
@CrossOrigin(origins = "*")
public class DanhMucController {

    @Autowired
    private DanhMucRepository repo;

    @GetMapping
    public List<DanhMuc> getAll() {
        return repo.findAll();
    }

    // API Tự sinh mã DMxxx (Tăng dần dựa trên mã lớn nhất hiện có)
    @GetMapping("/next-id")
    public ResponseEntity<String> getNextId() {
        List<DanhMuc> list = repo.findAll();
        if (list.isEmpty()) return ResponseEntity.ok("DM001");

        // Tìm mã lớn nhất (Ví dụ DM005)
        String maxId = list.stream()
                .map(DanhMuc::getMaDanhMuc)
                .max(Comparator.naturalOrder())
                .orElse("DM000");
        try {
            int num = Integer.parseInt(maxId.substring(2));
            return ResponseEntity.ok(String.format("DM%03d", num + 1));
        } catch (Exception e) {
            return ResponseEntity.ok("DM001");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DanhMuc dm) {
        if (repo.existsById(dm.getMaDanhMuc())) {
            return ResponseEntity.badRequest().body("Mã danh mục đã tồn tại");
        }
        // Không cần setNgayTao nữa
        return ResponseEntity.ok(repo.save(dm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody DanhMuc dm) {
        Optional<DanhMuc> exist = repo.findById(id);
        if (exist.isPresent()) {
            DanhMuc current = exist.get();
            current.setTenDanhMuc(dm.getTenDanhMuc());
            current.setHinhAnh(dm.getHinhAnh());
            current.setMoTa(dm.getMoTa());
            current.setTrangThai(dm.getTrangThai());
            return ResponseEntity.ok(repo.save(current));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
