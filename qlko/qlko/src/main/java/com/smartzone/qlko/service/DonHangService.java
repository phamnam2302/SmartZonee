package com.smartzone.qlko.service;



import com.smartzone.qlko.entity.ChiTietDonHang;
import com.smartzone.qlko.entity.DonHang;
import com.smartzone.qlko.repository.DonHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DonHangService {

    @Autowired
    private DonHangRepository donHangRepository;

    public List<DonHang> findAll() {
        return donHangRepository.findAll(Sort.by(Sort.Direction.DESC, "ngayLap"));
    }

    // Logic sinh mã tự động DH001, DH002...
    public String generateNextId() {
        String lastId = donHangRepository.findLastId(); // Cần viết query này trong Repository
        if (lastId == null) return "DH001";
        try {
            int num = Integer.parseInt(lastId.substring(2));
            return String.format("DH%03d", num + 1);
        } catch (Exception e) {
            return "DH" + System.currentTimeMillis();
        }
    }

    @Transactional
    public DonHang save(DonHang donHang) {
        // Gán quan hệ cha-con để JPA hiểu và lưu đúng khóa ngoại vào bảng chitiet
        if (donHang.getChiTietDonHangs() != null) {
            for (ChiTietDonHang ct : donHang.getChiTietDonHangs()) {
                ct.setDonHang(donHang);
            }
        }
        return donHangRepository.save(donHang);
    }

    public void delete(String id) {
        donHangRepository.deleteById(id);
    }
}