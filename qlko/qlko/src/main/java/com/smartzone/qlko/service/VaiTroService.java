package com.smartzone.qlko.service;

import com.smartzone.qlko.entity.VaiTro;
import com.smartzone.qlko.repository.VaiTroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VaiTroService {

    @Autowired
    private VaiTroRepository vaiTroRepository;

    public List<VaiTro> getAll() {
        return vaiTroRepository.findAll();
    }

    // Thêm mới: Phải check trùng mã
    public VaiTro create(VaiTro vaiTro) {
        if (vaiTroRepository.existsById(vaiTro.getMaVaiTro())) {
            throw new RuntimeException("Mã vai trò '" + vaiTro.getMaVaiTro() + "' đã tồn tại!");
        }
        return vaiTroRepository.save(vaiTro);
    }

    // Cập nhật: Phải check tồn tại
    public VaiTro update(VaiTro vaiTro) {
        if (!vaiTroRepository.existsById(vaiTro.getMaVaiTro())) {
            throw new RuntimeException("Không tìm thấy vai trò có mã: " + vaiTro.getMaVaiTro());
        }
        return vaiTroRepository.save(vaiTro);
    }

    // Xóa
    public void delete(String id) {
        if (!vaiTroRepository.existsById(id)) {
            throw new RuntimeException("Vai trò không tồn tại để xóa!");
        }
        try {
            vaiTroRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Không thể xóa! Có thể vai trò này đang được gán cho nhân viên.");
        }
    }
}