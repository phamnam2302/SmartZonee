package com.smartzone.qlko.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "*")
public class UploadController {

    // Thư mục gốc
    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
                                        @RequestParam(value = "folder", defaultValue = "others") String folderType) {
        try {
            // 1. Kiểm tra tên thư mục hợp lệ (bảo mật, tránh người dùng nhập linh tinh)
            if (!folderType.equals("sanpham") && !folderType.equals("danhmuc") && !folderType.equals("nhacungcap") && !folderType.equals("taikhoan")) {
                folderType = "others";
            }

            // 2. Tạo đường dẫn: uploads/sanpham/
            String uploadPath = UPLOAD_DIR + folderType + "/";
            File directory = new File(uploadPath);
            if (!directory.exists()) {
                directory.mkdirs(); // Tạo thư mục nếu chưa có
            }

            // 3. Lưu file
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadPath + fileName);
            Files.write(filePath, file.getBytes());

            // 4. Trả về kết quả (chỉ trả về tên file, không cần đường dẫn vì frontend sẽ tự ghép)
            Map<String, String> response = new HashMap<>();
            response.put("fileName", fileName);
            response.put("folder", folderType);
            response.put("url", "http://localhost:8080/photos/" + folderType + "/" + fileName);

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Lỗi upload: " + e.getMessage());
        }
    }
}