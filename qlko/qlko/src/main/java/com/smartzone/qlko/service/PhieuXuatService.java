package com.smartzone.qlko.service;

import com.smartzone.qlko.entity.ChiTietPhieuXuat;
import com.smartzone.qlko.entity.PhieuXuat;
import com.smartzone.qlko.repository.PhieuXuatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PhieuXuatService {

    @Autowired
    private PhieuXuatRepository phieuXuatRepo;

    @Autowired
    private TonKhoService tonKhoService;

    public List<PhieuXuat> findAll() {
        return phieuXuatRepo.findAll(Sort.by(Sort.Direction.DESC, "ngayXuat"));
    }

    public String generateNextId() {
        String lastId = phieuXuatRepo.findLastId();
        if (lastId == null) return "PX001";
        try {
            int num = Integer.parseInt(lastId.substring(2));
            return String.format("PX%03d", num + 1);
        } catch (Exception e) { return "PX" + System.currentTimeMillis(); }
    }

    @Transactional
    public PhieuXuat save(PhieuXuat phieuXuat) {
        // --- XỬ LÝ SỬA (UPDATE) ---
        // Nếu phiếu xuất đã có ID và tồn tại trong DB
        if (phieuXuat.getMaPhieuXuat() != null && phieuXuatRepo.existsById(phieuXuat.getMaPhieuXuat())) {
            PhieuXuat phieuXuatCu = phieuXuatRepo.findById(phieuXuat.getMaPhieuXuat()).orElse(null);

            if (phieuXuatCu != null && phieuXuatCu.getChiTietPhieuXuats() != null) {
                // HOÀN TÁC KHO: Cộng trả lại số lượng đã xuất (coi như chưa xuất)
                for (ChiTietPhieuXuat ctCu : phieuXuatCu.getChiTietPhieuXuats()) {
                    // Truyền số DƯƠNG để cộng trả lại kho
                    tonKhoService.capNhatTonKho(ctCu.getSanPham().getMaSanPham(), ctCu.getSoLuong());
                }
            }
        }

        // --- XỬ LÝ MỚI (HOẶC ÁP DỤNG DỮ LIỆU MỚI) ---
        if (phieuXuat.getChiTietPhieuXuats() != null) {
            for (ChiTietPhieuXuat ct : phieuXuat.getChiTietPhieuXuats()) {
                ct.setPhieuXuat(phieuXuat);
                // TRỪ TỒN KHO: Trừ số lượng thực tế cần xuất
                tonKhoService.capNhatTonKho(ct.getSanPham().getMaSanPham(), -ct.getSoLuong());
            }
        }

        return phieuXuatRepo.save(phieuXuat);
    }

    @Transactional
    public void delete(String id) {
        // Khi xóa phiếu xuất, phải cộng trả lại hàng vào kho
        Optional<PhieuXuat> pxOpt = phieuXuatRepo.findById(id);
        if (pxOpt.isPresent()) {
            PhieuXuat px = pxOpt.get();
            for (ChiTietPhieuXuat ct : px.getChiTietPhieuXuats()) {
                // Cộng lại kho vì hủy phiếu xuất
                tonKhoService.capNhatTonKho(ct.getSanPham().getMaSanPham(), ct.getSoLuong());
            }
            phieuXuatRepo.deleteById(id);
        }
    }
}