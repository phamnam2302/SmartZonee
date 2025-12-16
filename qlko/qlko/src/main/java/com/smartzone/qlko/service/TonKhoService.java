package com.smartzone.qlko.service;

import com.smartzone.qlko.entity.SanPham;
import com.smartzone.qlko.entity.TonKho;
import com.smartzone.qlko.repository.SanPhamRepository;
import com.smartzone.qlko.repository.TonKhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TonKhoService {

    @Autowired
    private TonKhoRepository tonKhoRepo;

    @Autowired
    private SanPhamRepository sanPhamRepo;

    public List<TonKho> findAll() {
        return tonKhoRepo.findAllByOrderBySoLuongTonDesc();
    }

    /**
     * HÀM TÍNH TOÁN TỒN KHO
     * @param maSanPham: Mã sản phẩm cần chỉnh sửa
     * @param soLuongThayDoi:
     * - Nếu là số DƯƠNG (+) => Nhập hàng (Cộng thêm)
     * - Nếu là số ÂM (-) => Xuất hàng (Trừ đi)
     */
    @Transactional
    public void capNhatTonKho(String maSanPham, int soLuongThayDoi) {
        Optional<TonKho> optTonKho = tonKhoRepo.findBySanPham_MaSanPham(maSanPham);

        if (optTonKho.isPresent()) {
            // TRƯỜNG HỢP 1: Sản phẩm đã có trong kho -> Cập nhật
            TonKho tonKho = optTonKho.get();
            int soLuongCu = tonKho.getSoLuongTon();
            int soLuongMoi = soLuongCu + soLuongThayDoi;

            // Kiểm tra logic: Không được xuất quá số lượng tồn
            if (soLuongMoi < 0) {
                throw new RuntimeException("Lỗi: Không đủ hàng trong kho để xuất! " +
                        "(Mã SP: " + maSanPham + ", Tồn: " + soLuongCu + ", Yêu cầu xuất: " + Math.abs(soLuongThayDoi) + ")");
            }

            tonKho.setSoLuongTon(soLuongMoi);
            tonKho.setNgayCapNhatCuoi(LocalDateTime.now());
            tonKhoRepo.save(tonKho);

        } else {
            // TRƯỜNG HỢP 2: Sản phẩm chưa có trong kho (Lần đầu nhập)
            if (soLuongThayDoi < 0) {
                throw new RuntimeException("Lỗi: Sản phẩm " + maSanPham + " chưa có trong kho, không thể xuất!");
            }

            TonKho tonKhoMoi = new TonKho();
            tonKhoMoi.setMaTonKho(UUID.randomUUID().toString()); // Tự sinh ID

            // Tìm thông tin sản phẩm để gán vào
            SanPham sp = sanPhamRepo.findById(maSanPham)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Mã SP: " + maSanPham));

            tonKhoMoi.setSanPham(sp);
            tonKhoMoi.setSoLuongTon(soLuongThayDoi);
            tonKhoMoi.setNgayCapNhatCuoi(LocalDateTime.now());
            tonKhoRepo.save(tonKhoMoi);
        }
    }
}