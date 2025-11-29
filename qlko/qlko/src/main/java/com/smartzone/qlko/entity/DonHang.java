package com.smartzone.qlko.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "donhang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonHang {

    @Id
    @Column(name = "maDonHang", length = 50)
    private String maDonHang;

    // QUAN TRỌNG: Quan hệ với Khách Hàng
    @ManyToOne
    @JoinColumn(name = "maKhachHang", nullable = false)
    private KhachHang khachHang;

    // QUAN TRỌNG: Quan hệ với Tài Khoản (Người lập)
    @ManyToOne
    @JoinColumn(name = "maNguoiLap", nullable = false)
    private TaiKhoan nguoiLap;

    @Column(name = "ngayLap")
    private LocalDateTime ngayLap;

    @Column(name = "tongTien", precision = 15, scale = 2)
    private BigDecimal tongTien;

    @Column(name = "trangThai", length = 50)
    private String trangThai;

    // Quan hệ với Chi Tiết Đơn Hàng
    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Ngăn vòng lặp vô tận khi chuyển sang JSON
    private List<ChiTietDonHang> chiTietDonHangs = new ArrayList<>();

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public TaiKhoan getNguoiLap() {
        return nguoiLap;
    }

    public void setNguoiLap(TaiKhoan nguoiLap) {
        this.nguoiLap = nguoiLap;
    }

    public LocalDateTime getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDateTime ngayLap) {
        this.ngayLap = ngayLap;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public List<ChiTietDonHang> getChiTietDonHangs() {
        return chiTietDonHangs;
    }

    public void setChiTietDonHangs(List<ChiTietDonHang> chiTietDonHangs) {
        this.chiTietDonHangs = chiTietDonHangs;
    }
}