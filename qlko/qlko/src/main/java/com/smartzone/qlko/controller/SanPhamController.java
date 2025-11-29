package com.smartzone.qlko.controller;


import com.smartzone.qlko.entity.DanhMuc;
import com.smartzone.qlko.entity.SanPham;
import com.smartzone.qlko.repository.DanhMucRepository;
import com.smartzone.qlko.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sanpham")
@CrossOrigin(origins = "*")
public class SanPhamController {

    @Autowired
    private SanPhamRepository spRepo;

    @Autowired
    private DanhMucRepository dmRepo;

    @GetMapping
    public List<SanPham> getAll() {
        return spRepo.findAll();
    }

    // API Sinh mã tự động: Lấy chữ cái đầu của Tên Danh Mục + Số tăng dần
    @GetMapping("/next-id")
    public ResponseEntity<String> generateId(@RequestParam String maDanhMuc) {
        Optional<DanhMuc> dmOpt = dmRepo.findById(maDanhMuc);
        if (dmOpt.isEmpty()) return ResponseEntity.badRequest().body("Danh mục không tồn tại");

        String tenDM = dmOpt.get().getTenDanhMuc();
        String prefix = getAcronym(tenDM); // Ví dụ: "Loa" -> "L", "Máy Tính" -> "MT"

        List<String> existingIds = spRepo.findByMaSanPhamStartingWith(prefix);

        int maxNum = 0;
        for (String id : existingIds) {
            try {
                String numPart = id.substring(prefix.length());
                int num = Integer.parseInt(numPart);
                if (num > maxNum) maxNum = num;
            } catch (Exception e) {}
        }

        String nextId = prefix + String.format("%03d", maxNum + 1);
        return ResponseEntity.ok(nextId);
    }

    private String getAcronym(String str) {
        if (str == null || str.isEmpty()) return "SP";
        String[] words = str.trim().split("\\s+");
        StringBuilder acronym = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) acronym.append(word.charAt(0));
        }
        return acronym.toString().toUpperCase();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SanPham sp) {
        if (spRepo.existsById(sp.getMaSanPham())) {
            return ResponseEntity.badRequest().body("Mã sản phẩm đã tồn tại!");
        }
        sp.setNgayTao(new Date());
        sp.setNgaySua(new Date());
        return ResponseEntity.ok(spRepo.save(sp));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody SanPham sp) {
        Optional<SanPham> exists = spRepo.findById(id);
        if (exists.isPresent()) {
            SanPham current = exists.get();
            current.setTenSanPham(sp.getTenSanPham());
            current.setDanhMuc(sp.getDanhMuc());
            current.setNhaCungCap(sp.getNhaCungCap()); // Cập nhật NCC
            current.setDonViTinh(sp.getDonViTinh());
            current.setGiaBan(sp.getGiaBan());
            current.setHinhAnh(sp.getHinhAnh());
            current.setMoTa(sp.getMoTa());
            current.setTrangThai(sp.getTrangThai());
            current.setMauSac(sp.getMauSac());
            current.setChatLieu(sp.getChatLieu());
            current.setNgaySua(new Date());
            return ResponseEntity.ok(spRepo.save(current));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (spRepo.existsById(id)) {
            spRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}