package com.smartzone.qlko.controller;

import com.smartzone.qlko.entity.VaiTro;
import com.smartzone.qlko.service.VaiTroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaitro")
@CrossOrigin("*") // Cho phép HTML gọi API mà không bị chặn
public class VaiTroController {

    @Autowired
    private VaiTroService vaiTroService;

    @GetMapping
    public List<VaiTro> getAll() {
        return vaiTroService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VaiTro vaiTro) {
        try {
            return ResponseEntity.ok(vaiTroService.create(vaiTro));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody VaiTro vaiTro) {
        try {
            vaiTro.setMaVaiTro(id); // Đảm bảo ID trong object khớp với URL
            return ResponseEntity.ok(vaiTroService.update(vaiTro));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            vaiTroService.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}