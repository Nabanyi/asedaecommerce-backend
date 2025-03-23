package com.aseda.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.aseda.demo.dto.ImageDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileUploadController {

    private static final String UPLOAD_DIR = System.getProperty("user.home") + "/uploads/";
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<?> handleFileUpload(
            @RequestPart("purpose") String purpose,
            @RequestPart("name") String name,
            @RequestPart("images") List<MultipartFile> images) {

        if (images.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No images uploaded");
        }
        
        List<String> uploadedFiles = new ArrayList<>();

        try {
            // Create the upload directory if it doesn't exist
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Save each image
            for (MultipartFile image : images) {
            	if (!image.getContentType().startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("File " + image.getOriginalFilename() + " is not an image.");
                }

                // Validate file size
                if (image.getSize() > MAX_FILE_SIZE) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("File " + image.getOriginalFilename() + " exceeds the 5MB size limit.");
                }
                
                String filePath = UPLOAD_DIR + image.getOriginalFilename();
                File dest = new File(filePath);
                image.transferTo(dest);
                uploadedFiles.add(image.getOriginalFilename()); // Add file name to the list
                System.out.println("Saved file: " + dest.getAbsolutePath());
            }

            // Process additional fields
            System.out.println("Purpose: " + purpose);
            System.out.println("Name: " + name);

            return ResponseEntity.ok(uploadedFiles);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload files: " + e.getMessage());
        }
    }

    @PostMapping(value = "/uploadto", consumes = "multipart/form-data")
    public ResponseEntity<?> handleFiles(ImageDTO imageDTO){
    	List<MultipartFile> images = imageDTO.getImages();
    	String purpose = imageDTO.getPurpose();
    	String name = imageDTO.getName();
    	
    	if (images.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No images uploaded");
        }
        
        List<String> uploadedFiles = new ArrayList<>();

        try {
            // Create the upload directory if it doesn't exist
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Save each image
            for (MultipartFile image : images) {
            	if (!image.getContentType().startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("File " + image.getOriginalFilename() + " is not an image.");
                }

                // Validate file size
                if (image.getSize() > MAX_FILE_SIZE) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("File " + image.getOriginalFilename() + " exceeds the 5MB size limit.");
                }
                
                String filePath = UPLOAD_DIR + image.getOriginalFilename();
                File dest = new File(filePath);
                image.transferTo(dest);
                uploadedFiles.add(image.getOriginalFilename()); // Add file name to the list
                System.out.println("Saved file: " + dest.getAbsolutePath());
            }

            // Process additional fields
            System.out.println("Purpose: " + purpose);
            System.out.println("Name: " + name);

            return ResponseEntity.ok(uploadedFiles);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload files: " + e.getMessage());
        }
    }
}