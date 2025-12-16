package com.smartzone.qlko.entity;

import jakarta.persistence.Column; // <--- 1. NHỚ IMPORT DÒNG NÀY
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vaitro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaiTro {

    @Id
    @Column(name = "maVaiTro") // <--- 2. SỬA DÒNG NÀY: Khớp chính xác tên cột trong SQL
    private String maVaiTro;

    @Column(name = "tenVaiTro") // <--- 3. SỬA DÒNG NÀY
    private String tenVaiTro;

    public String getMaVaiTro() {
        return maVaiTro;
    }

    public void setMaVaiTro(String maVaiTro) {
        this.maVaiTro = maVaiTro;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }
}