package com.smartzone.qlko.service;

import com.smartzone.qlko.entity.TaiKhoan;
import com.smartzone.qlko.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // <--- Import mới
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder; // <--- Inject PasswordEncoder

    public List<TaiKhoan> search(String maVaiTro, String gioiTinh) {
        return repository.searchTaiKhoan(maVaiTro, gioiTinh);
    }

    public List<TaiKhoan> getAll() {
        return repository.findAll();
    }

    // 2. Thêm mới (Cần mã hóa mật khẩu trước khi lưu)
    public TaiKhoan create(TaiKhoan tk) {
        if (repository.existsById(tk.getMaTaiKhoan())) {
            throw new RuntimeException("Mã tài khoản " + tk.getMaTaiKhoan() + " đã tồn tại!");
        }
        if (repository.existsByTenDangNhap(tk.getTenDangNhap())) {
            throw new RuntimeException("Tên đăng nhập đã được sử dụng!");
        }

        // --- MỚI: Mã hóa mật khẩu ---
        tk.setMatKhau(passwordEncoder.encode(tk.getMatKhau()));

        return repository.save(tk);
    }

    // 3. Cập nhật
    public TaiKhoan update(TaiKhoan tkRequest) {
        TaiKhoan existingTK = repository.findById(tkRequest.getMaTaiKhoan())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản!"));

        existingTK.setHoVaTen(tkRequest.getHoVaTen());
        existingTK.setEmail(tkRequest.getEmail());
        existingTK.setSdt(tkRequest.getSdt());
        existingTK.setGioiTinh(tkRequest.getGioiTinh());
        existingTK.setTrangThai(tkRequest.getTrangThai());
        existingTK.setHinhAnh(tkRequest.getHinhAnh());
        existingTK.setVaiTro(tkRequest.getVaiTro());

        // LOGIC MẬT KHẨU: Nếu có nhập mật khẩu mới thì mã hóa rồi lưu
        if (tkRequest.getMatKhau() != null && !tkRequest.getMatKhau().isEmpty()) {
            // --- MỚI: Mã hóa mật khẩu mới ---
            existingTK.setMatKhau(passwordEncoder.encode(tkRequest.getMatKhau()));
        }

        return repository.save(existingTK);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public String getNextMaTaiKhoan() {
        // ... (Giữ nguyên logic cũ của bạn)
        String maxId = repository.findMaxMaTaiKhoan();
        if (maxId == null) return "TK001";
        try {
            String numberPart = maxId.substring(2);
            int nextNumber = Integer.parseInt(numberPart) + 1;
            return String.format("TK%03d", nextNumber);
        } catch (NumberFormatException e) {
            return "TK001";
        }
    }

    // 4. Đăng nhập (So sánh mật khẩu đã mã hóa)
    public TaiKhoan login(String input, String password) {
        Optional<TaiKhoan> userOpt = repository.findByTenDangNhapOrEmail(input);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Tài khoản không tồn tại!");
        }

        TaiKhoan user = userOpt.get();

        // --- MỚI: Dùng passwordEncoder.matches(mk_nhập, mk_trong_db) ---
        // Không dùng .equals() nữa
        if (!passwordEncoder.matches(password, user.getMatKhau())) {
            throw new RuntimeException("Mật khẩu không chính xác!");
        }

        if ("Tạm khóa".equals(user.getTrangThai()) || "Đã khóa".equals(user.getTrangThai())) {
            throw new RuntimeException("Tài khoản đã bị khóa. Vui lòng liên hệ Admin!");
        }

        return user;
    }
}