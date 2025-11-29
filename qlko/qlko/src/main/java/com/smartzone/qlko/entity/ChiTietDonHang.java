package com.smartzone.qlko.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "chitiet_donhang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietDonHang {

    @Id
    @Column(name = "maCTDH", length = 50)
    private String maCTDH;

    @ManyToOne
    @JoinColumn(name = "maDonHang", nullable = false)
    @JsonBackReference
    private DonHang donHang;

    @ManyToOne
    @JoinColumn(name = "maSanPham", nullable = false)
    private SanPham sanPham;

    @Column(name = "soLuong")
    private Integer soLuong;

    @Column(name = "donGiaBan", precision = 15, scale = 2)
    private BigDecimal donGiaBan;

    // --- SỬA ĐOẠN NÀY ---
    // Thêm insertable = false, updatable = false
    // Để Hibernate bỏ qua cột này khi Insert/Update
    @Column(name = "thanhTien", precision = 15, scale = 2, insertable = false, updatable = false)
    private BigDecimal thanhTien;

    public String getMaCTDH() {
        return maCTDH;
    }

    public void setMaCTDH(String maCTDH) {
        this.maCTDH = maCTDH;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
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

    public BigDecimal getDonGiaBan() {
        return donGiaBan;
    }

    public void setDonGiaBan(BigDecimal donGiaBan) {
        this.donGiaBan = donGiaBan;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }
}