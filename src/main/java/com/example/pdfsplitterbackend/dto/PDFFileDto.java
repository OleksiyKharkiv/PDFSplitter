package com.example.pdfsplitterbackend.dto;

import org.springframework.web.multipart.MultipartFile;

public class PDFFileDto {
    String fileName;
    MultipartFile file;
    int fileSizeKb;
}