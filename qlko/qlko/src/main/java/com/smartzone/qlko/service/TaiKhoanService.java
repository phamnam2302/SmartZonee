package com.smartzone.qlko.service;

import com.smartzone.qlko.entity.TaiKhoan;
import com.smartzone.qlko.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepository repository;

    // 1. Tìm kiếm (Lọc)
    public List<TaiKhoan> search(String maVaiTro, String gioiTinh) {
        return repository.searchTaiKhoan(maVaiTro, gioiTinh);
    }

    public List<TaiKhoan> getAll() {
        return repository.findAll();
    }

    // 2. Thêm mới
    public TaiKhoan create(TaiKhoan tk) {
        if (repository.existsById(tk.getMaTaiKhoan())) {
            throw new RuntimeException("Mã tài khoản " + tk.getMaTaiKhoan() + " đã tồn tại!");
        }
        if (repository.existsByTenDangNhap(tk.getTenDangNhap())) {
            throw new RuntimeException("Tên đăng nhập đã được sử dụng!");
        }
        return repository.save(tk);
    }

    // 3. Cập nhật (Logic quan trọng)
    public TaiKhoan update(TaiKhoan tkRequest) {
        // Lấy tài khoản cũ từ DB
        TaiKhoan existingTK = repository.findById(tkRequest.getMaTaiKhoan())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản!"));

        // Cập nhật thông tin chung
        existingTK.setHoVaTen(tkRequest.getHoVaTen());
        existingTK.setEmail(tkRequest.getEmail());
        existingTK.setSdt(tkRequest.getSdt());
        existingTK.setGioiTinh(tkRequest.getGioiTinh());
        existingTK.setTrangThai(tkRequest.getTrangThai());
        existingTK.setHinhAnh(tkRequest.getHinhAnh());
        existingTK.setVaiTro(tkRequest.getVaiTro());

        // LOGIC MẬT KHẨU: Chỉ đổi nếu người dùng nhập chuỗi không rỗng
        if (tkRequest.getMatKhau() != null && !tkRequest.getMatKhau().isEmpty()) {
            existingTK.setMatKhau(tkRequest.getMatKhau());
        }

        return repository.save(existingTK);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
    //đưa gợi ý mã TK
    public String getNextMaTaiKhoan() {
        String maxId = repository.findMaxMaTaiKhoan();
        if (maxId == null) {
            return "TK001"; // Nếu chưa có ai, trả về số đầu tiên
        }

        // Logic: Cắt bỏ chữ "TK", lấy phần số, cộng 1, rồi ghép lại
        try {
            String numberPart = maxId.substring(2); // Bỏ 2 ký tự đầu "TK"
            int nextNumber = Integer.parseInt(numberPart) + 1;
            // Format thành chuỗi 5 ký tự: "TK" + 3 số (ví dụ 002)
            return String.format("TK%03d", nextNumber);
        } catch (NumberFormatException e) {
            return "TK001"; // Fallback nếu mã trong DB không đúng định dạng
        }
    }
    // ...
    public TaiKhoan login(String input, String password) {
        // 1. Tìm tài khoản theo tên đăng nhập hoặc email
        Optional<TaiKhoan> userOpt = repository.findByTenDangNhapOrEmail(input);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Tài khoản không tồn tại!");
        }

        TaiKhoan user = userOpt.get();

        // 2. Kiểm tra mật khẩu (So sánh chuỗi thường, thực tế nên mã hóa)
        if (!user.getMatKhau().equals(password)) {
            throw new RuntimeException("Mật khẩu không chính xác!");
        }

        // 3. Kiểm tra trạng thái
        if ("Tạm khóa".equals(user.getTrangThai()) || "Đã khóa".equals(user.getTrangThai())) {
            throw new RuntimeException("Tài khoản đã bị khóa. Vui lòng liên hệ Admin!");
        }

        return user;
    }
// ...
}