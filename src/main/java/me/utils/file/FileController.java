package me.utils.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@Slf4j
public class FileController {

    // 파일 업로드 구현
    @PostMapping("/api/uploadFile")
    public ResponseEntity<String> uploadFile(MultipartFile file) throws IllegalStateException, IOException {

        if (!file.isEmpty()) {
            log.debug("file org name = {}", file.getOriginalFilename());
            log.debug("file content type = {}", file.getContentType());
            file.transferTo(new File(file.getOriginalFilename()));
        }

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
