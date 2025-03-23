package com.aseda.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class FileStorageService {

	private static final String UPLOAD_DIR = System.getProperty("user.home") + "/uploads/";
    private static final long MAX_FILE_SIZE = 1_048_576; // 1MB in bytes
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(".jpg", ".png", ".jpeg");

    public List<String> storeFiles(List<MultipartFile> files) throws IOException {
        
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; // Skip empty files
            }

            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null) {
                throw new IllegalArgumentException("File name cannot be null");
            }

            // Check file size
            if (file.getSize() > MAX_FILE_SIZE) {
                throw new IllegalArgumentException("File '" + originalFileName + "' exceeds maximum size of 1MB");
            }

            // Check file extension
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
            if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
                throw new IllegalArgumentException("File '" + originalFileName + "' must be a .jpg, .png, or .jpeg");
            }
            
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
            String filePath = UPLOAD_DIR + uniqueFileName;
            File dest = new File(filePath);
            file.transferTo(dest);
            fileNames.add(uniqueFileName);            
        }
        return fileNames;
    }
    
    public static boolean deleteImage(String fileName) {
        File file = new File(UPLOAD_DIR + fileName);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }
}
