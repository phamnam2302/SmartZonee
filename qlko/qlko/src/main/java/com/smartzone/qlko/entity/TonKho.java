package com.smartzone.qlko.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "tonkho")
@Data
public class TonKho {
    @Id
    @Column(name = "maTonKho", length = 50)
    private String maTonKho;

    // Quan hệ với Sản phẩm (Lấy tên, ảnh, danh mục)
    @OneToOne
    @JoinColumn(name = "maSanPham", nullable = false)
    private SanPham sanPham;

    @Column(name = "soLuongTon")
    private Integer soLuongTon;

    @Column(name = "ngayCapNhatCuoi")
    private LocalDateTime ngayCapNhatCuoi;

    public String getMaTonKho() {
        return maTonKho;
    }

    public void setMaTonKho(String maTonKho) {
        this.maTonKho = maTonKho;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public Integer getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(Integer soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public LocalDateTime getNgayCapNhatCuoi() {
        return ngayCapNhatCuoi;
    }

    public void setNgayCapNhatCuoi(LocalDateTime ngayCapNhatCuoi) {
        this.ngayCapNhatCuoi = ngayCapNhatCuoi;
    }
}