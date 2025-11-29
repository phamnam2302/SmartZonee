package com.smartzone.qlko.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "chitiet_phieuxuat")
@Data
public class ChiTietPhieuXuat {
    @Id
    @Column(name = "maCTPX", length = 50)
    private String maCTPX;

    @ManyToOne
    @JoinColumn(name = "maPhieuXuat", nullable = false)
    @JsonBackReference
    private PhieuXuat phieuXuat;

    @ManyToOne
    @JoinColumn(name = "maSanPham", nullable = false)
    private SanPham sanPham;

    @Column(name = "soLuong")
    private Integer soLuong;

    @Column(name = "donGiaXuat", precision = 15, scale = 2)
    private BigDecimal donGiaXuat;

    @Column(name = "ghiChu", columnDefinition = "TEXT")
    private String ghiChu;

    // Tính toán thành tiền (Không lưu DB nhưng trả về API)
    public BigDecimal getThanhTien() {
        if (soLuong == null || donGiaXuat == null) return BigDecimal.ZERO;
        return donGiaXuat.multiply(new BigDecimal(soLuong));
    }

    public String getMaCTPX() {
        return maCTPX;
    }

    public void setMaCTPX(String maCTPX) {
        this.maCTPX = maCTPX;
    }

    public PhieuXuat getPhieuXuat() {
        return phieuXuat;
    }

    public void setPhieuXuat(PhieuXuat phieuXuat) {
        this.phieuXuat = phieuXuat;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGiaXuat() {
        return donGiaXuat;
    }

    public void setDonGiaXuat(BigDecimal donGiaXuat) {
        this.donGiaXuat = donGiaXuat;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}