package com.shei.cms.controller;

import com.shei.cms.entity.CourseMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.shei.cms.repository.CourseMaterialRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @PostMapping("")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        // Save file locally or to AWS S3 and return URL.
        return "File uploaded successfully!";
    }
    @GetMapping("")
    public List<CourseMaterial> getAllFiles() {
        return courseMaterialRepository.findAll(); // Fetches all course materials
    }

}