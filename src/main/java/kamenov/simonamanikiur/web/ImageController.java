package kamenov.simonamanikiur.web;

import io.jsonwebtoken.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/treatments")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {

    private final String uploadDir = new File("C:\\Users\\Plamen Kamenov\\Desktop\\MyProjectsPictures\"").getAbsolutePath();

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Създаване на директория, ако не съществува
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }
            String originalFilename = file.getOriginalFilename();
            // Може да добавите уникален префикс/суфикс за избягване на конфликти
            String filePath = uploadDir + File.separator + originalFilename;
            File dest = new File(filePath);
            file.transferTo(dest);
            // Предполагаме, че файловете се сервират като статични ресурси (вижте следващата стъпка)
            String fileUrl = "http://localhost:8888/uploads/" + originalFilename;
            return ResponseEntity.ok(fileUrl);
        } catch (IOException | java.io.IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading file: " + e.getMessage());
        }
    }
}
