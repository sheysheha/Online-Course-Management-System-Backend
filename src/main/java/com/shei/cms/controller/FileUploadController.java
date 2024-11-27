package com.shei.cms.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        // Save file locally or to AWS S3 and return URL.
        return "File uploaded successfully!";
    }
}