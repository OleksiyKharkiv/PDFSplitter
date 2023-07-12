package com.example.pdfsplitterbackend.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PDFFileDTO {
    private String id;
    private String title;
    private int sizeMb;
    private int numberOfPages;
    private byte[] fileContent;
}