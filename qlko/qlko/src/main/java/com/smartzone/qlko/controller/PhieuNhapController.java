package com.smartzone.qlko.controller;

import com.smartzone.qlko.entity.PhieuNhap;
import com.smartzone.qlko.service.PhieuNhapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phieunhap")
@CrossOrigin(origins = "*")
public class PhieuNhapController {

    @Autowired
    private PhieuNhapService phieuNhapService;

    @GetMapping
    public List<PhieuNhap> getAll() {
        return phieuNhapService.findAll();
    }

    @GetMapping("/next-id")
    public ResponseEntity<String> getNextId() {
        return ResponseEntity.ok(phieuNhapService.generateNextId());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PhieuNhap phieuNhap) {
        try {
            if (phieuNhap.getMaPhieuNhap() == null || phieuNhap.getMaPhieuNhap().isEmpty()) {
                phieuNhap.setMaPhieuNhap(phieuNhapService.generateNextId());
            }
            return ResponseEntity.ok(phieuNhapService.save(phieuNhap));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Lỗi Server: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PhieuNhap phieuNhap) {
        try {
            return ResponseEntity.ok(phieuNhapService.save(phieuNhap));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi cập nhật: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        phieuNhapService.delete(id);
        return ResponseEntity.ok().build();
    }
}