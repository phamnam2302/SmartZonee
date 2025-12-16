package com.smartzone.qlko.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "phieuxuat")
@Data
public class PhieuXuat {
    @Id
    @Column(name = "maPhieuXuat", length = 50)
    private String maPhieuXuat;

    // Khóa ngoại sang TaiKhoan
    @ManyToOne
    @JoinColumn(name = "maNguoiXuat", nullable = false)
    private TaiKhoan nguoiXuat;

    @Column(name = "maDonHang", length = 50)
    private String maDonHang; // Để dạng String để linh hoạt nhập liệu

    @Column(name = "ngayXuat")
    private LocalDateTime ngayXuat;

    @Column(name = "lyDoXuat", length = 200)
    private String lyDoXuat;

    @Column(name = "ghiChu", columnDefinition = "TEXT")
    private String ghiChu;

    @OneToMany(mappedBy = "phieuXuat", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ChiTietPhieuXuat> chiTietPhieuXuats;

    // Getter ảo để tính tổng tiền hiển thị ra JSON (nếu cần)
    public BigDecimal getTongTien() {
        if (chiTietPhieuXuats == null) return BigDecimal.ZERO;
        return chiTietPhieuXuats.stream()
                .map(ChiTietPhieuXuat::getThanhTien)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getMaPhieuXuat() {
        return maPhieuXuat;
    }

    public void setMaPhieuXuat(String maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
    }

    public TaiKhoan getNguoiXuat() {
        return nguoiXuat;
    }

    public void setNguoiXuat(TaiKhoan nguoiXuat) {
        this.nguoiXuat = nguoiXuat;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public LocalDateTime getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(LocalDateTime ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public String getLyDoXuat() {
        return lyDoXuat;
    }

    public void setLyDoXuat(String lyDoXuat) {
        this.lyDoXuat = lyDoXuat;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public List<ChiTietPhieuXuat> getChiTietPhieuXuats() {
        return chiTietPhieuXuats;
    }

    public void setChiTietPhieuXuats(List<ChiTietPhieuXuat> chiTietPhieuXuats) {
        this.chiTietPhieuXuats = chiTietPhieuXuats;
    }
}