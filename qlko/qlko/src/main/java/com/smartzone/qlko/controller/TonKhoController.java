package com.smartzone.qlko.controller;

import com.smartzone.qlko.entity.TonKho;
import com.smartzone.qlko.service.TonKhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tonkho")
@CrossOrigin(origins = "*")
public class TonKhoController {
    @Autowired
    private TonKhoService service;

    @GetMapping
    public ResponseEntity<List<TonKho>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
}