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
@Table(name = "phieunhap")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhieuNhap {

    @Id
    @Column(name = "maPhieuNhap", length = 50)
    private String maPhieuNhap;

    // Quan hệ với Nhà Cung Cấp
    @ManyToOne
    @JoinColumn(name = "maNCC", nullable = false)
    private NhaCungCap ncc;

    // Quan hệ với Tài khoản (Người nhập)
    @ManyToOne
    @JoinColumn(name = "maNguoiNhap", nullable = false)
    private TaiKhoan nguoiNhap;

    @Column(name = "ngayNhap")
    private LocalDateTime ngayNhap;

    @Column(name = "tongTien", precision = 15, scale = 2)
    private BigDecimal tongTien;

    @Column(name = "ghiChu", columnDefinition = "TEXT")
    private String ghiChu;

    // Quan hệ Chi tiết
    @OneToMany(mappedBy = "phieuNhap", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ChiTietPhieuNhap> chiTietPhieuNhaps = new ArrayList<>();

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public NhaCungCap getNcc() {
        return ncc;
    }

    public void setNcc(NhaCungCap ncc) {
        this.ncc = ncc;
    }

    public TaiKhoan getNguoiNhap() {
        return nguoiNhap;
    }

    public void setNguoiNhap(TaiKhoan nguoiNhap) {
        this.nguoiNhap = nguoiNhap;
    }

    public LocalDateTime getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(LocalDateTime ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public List<ChiTietPhieuNhap> getChiTietPhieuNhaps() {
        return chiTietPhieuNhaps;
    }

    public void setChiTietPhieuNhaps(List<ChiTietPhieuNhap> chiTietPhieuNhaps) {
        this.chiTietPhieuNhaps = chiTietPhieuNhaps;
    }
}