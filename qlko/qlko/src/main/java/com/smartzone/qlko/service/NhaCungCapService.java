package com.smartzone.qlko.service;

import com.smartzone.qlko.entity.NhaCungCap;
import com.smartzone.qlko.repository.NhaCungCapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhaCungCapService {

    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;

    public List<NhaCungCap> findAll() {
        return nhaCungCapRepository.findAll();
    }

    // Thêm các hàm save, delete nếu cần quản lý CRUD nhà cung cấp sau này
}