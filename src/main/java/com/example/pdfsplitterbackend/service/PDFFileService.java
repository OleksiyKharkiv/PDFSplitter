package com.example.pdfsplitterbackend.service;

import com.example.pdfsplitterbackend.dto.PDFFileDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface PDFFileService {
    String uploadPDFFile(MultipartFile file) throws IOException;

    Resource downloadPDFFile(String fileId) throws FileNotFoundException;

    List<PDFFileDTO> getAllPDFFiles();

    PDFFileDTO getPDFFileById(String fileId) throws FileNotFoundException;

    void deletePDFFile(String fileId);

    byte[] getPDFFileContentById(String fileId) throws IOException;

    Resource getMergedPDFFileById(String fileId) throws IOException;

    String mergePDFFiles(List<String> fileIds) throws IOException;
    // Другие методы сервиса
}
