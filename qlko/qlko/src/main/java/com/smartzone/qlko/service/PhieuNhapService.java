package com.smartzone.qlko.service;

import com.smartzone.qlko.entity.ChiTietPhieuNhap;
import com.smartzone.qlko.entity.PhieuNhap;
import com.smartzone.qlko.repository.PhieuNhapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PhieuNhapService {

    @Autowired
    private PhieuNhapRepository phieuNhapRepository;

    @Autowired
    private TonKhoService tonKhoService;

    public List<PhieuNhap> findAll() {
        return phieuNhapRepository.findAll(Sort.by(Sort.Direction.DESC, "ngayNhap"));
    }

    public String generateNextId() {
        String lastId = phieuNhapRepository.findLastId();
        if (lastId == null) return "PN001";
        try {
            int num = Integer.parseInt(lastId.substring(2));
            return String.format("PN%03d", num + 1);
        } catch (Exception e) { return "PN" + System.currentTimeMillis(); }
    }

    @Transactional
    public PhieuNhap save(PhieuNhap phieuNhap) {
        // --- XỬ LÝ SỬA (UPDATE) ---
        // Nếu phiếu nhập đã có ID và tồn tại trong DB -> Đây là hành động Sửa
        if (phieuNhap.getMaPhieuNhap() != null && phieuNhapRepository.existsById(phieuNhap.getMaPhieuNhap())) {
            // Lấy dữ liệu cũ trong DB ra
            PhieuNhap phieuNhapCu = phieuNhapRepository.findById(phieuNhap.getMaPhieuNhap()).orElse(null);

            if (phieuNhapCu != null && phieuNhapCu.getChiTietPhieuNhaps() != null) {
                // HOÀN TÁC KHO: Trừ đi số lượng đã nhập lần trước
                for (ChiTietPhieuNhap ctCu : phieuNhapCu.getChiTietPhieuNhaps()) {
                    // Truyền số ÂM để trừ lại tồn kho
                    tonKhoService.capNhatTonKho(ctCu.getSanPham().getMaSanPham(), -ctCu.getSoLuong());
                }
            }
        }

        // --- XỬ LÝ MỚI (HOẶC ÁP DỤNG DỮ LIỆU MỚI KHI SỬA) ---
        if (phieuNhap.getChiTietPhieuNhaps() != null) {
            for (ChiTietPhieuNhap ct : phieuNhap.getChiTietPhieuNhaps()) {
                ct.setPhieuNhap(phieuNhap);
                // CẬP NHẬT TỒN KHO: Cộng số lượng (Mới hoặc Đã sửa) vào kho
                tonKhoService.capNhatTonKho(ct.getSanPham().getMaSanPham(), ct.getSoLuong());
            }
        }

        return phieuNhapRepository.save(phieuNhap);
    }

    @Transactional
    public void delete(String id) {
        // Khi xóa phiếu nhập, phải trừ lại tồn kho
        Optional<PhieuNhap> pnOpt = phieuNhapRepository.findById(id);
        if (pnOpt.isPresent()) {
            PhieuNhap pn = pnOpt.get();
            for (ChiTietPhieuNhap ct : pn.getChiTietPhieuNhaps()) {
                // Trừ kho đi vì xóa phiếu nhập
                tonKhoService.capNhatTonKho(ct.getSanPham().getMaSanPham(), -ct.getSoLuong());
            }
            phieuNhapRepository.deleteById(id);
        }
    }
}