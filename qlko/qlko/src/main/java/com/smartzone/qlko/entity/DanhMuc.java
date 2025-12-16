package com.smartzone.qlko.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "danhmuc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DanhMuc {
    @Id
    @Column(name = "maDanhMuc", length = 50)
    private String maDanhMuc;

    @Column(name = "tenDanhMuc", length = 100)
    private String tenDanhMuc;

    @Column(name = "hinhAnh", length = 500)
    private String hinhAnh;

    @Column(name = "moTa", length = 500)
    private String moTa;

    @Column(name = "trangThai", length = 50)
    private String trangThai; // "Hiển thị" hoặc "Đang ẩn"

    public String getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}