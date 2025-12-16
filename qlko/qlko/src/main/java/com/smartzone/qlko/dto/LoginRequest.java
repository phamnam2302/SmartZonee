package com.smartzone.qlko.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String taiKhoan; // Chấp nhận cả Tên đăng nhập hoặc Email
    private String matKhau;

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}