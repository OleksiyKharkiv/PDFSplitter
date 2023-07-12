package com.example.pdfsplitterbackend.service;

import com.example.pdfsplitterbackend.dto.PDFFileDTO;
import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PDFFileService {
    String uploadPDFFile(MultipartFile file);
    Resource downloadPDFFile(String fileId);
    List<PDFFileDTO> getAllPDFFiles();
    PDFFileDTO getPDFFileById(String fileId);
    void deletePDFFile(String fileId);

    byte[] getPDFFileContentById(String fileId) throws IOException;
    // Другие методы сервиса
}
