package com.example.pdfsplitterbackend.controller;

import com.example.pdfsplitterbackend.dto.PDFFileDTO;
import com.example.pdfsplitterbackend.mapper.PDFFileMapper;
import com.example.pdfsplitterbackend.service.PDFFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pdf")
public class PDFFileController {
    private final PDFFileMapper pdfFileMapper;
    private final PDFFileService pdfFileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPDFFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileId = pdfFileService.uploadPDFFile(file);
        return ResponseEntity.ok(fileId);
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<Resource> downloadPDFFile(@PathVariable("id") int fileId) {
        try {
            byte[] fileContent = pdfFileService.getPDFFileContentById(fileId);
            PDFFileDTO pdfFile = pdfFileService.getPDFFileById(fileId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", pdfFile.getFileName());
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(new ByteArrayResource(fileContent), headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Метод для получения списка всех PDF-файлов
    @GetMapping("/all")
    public ResponseEntity<List<PDFFileDTO>> getAllPDFFiles() {
        List<PDFFileDTO> pdfFiles = pdfFileService.getAllPDFFiles();
        return ResponseEntity.ok(pdfFiles);
    }

    public PDFFileMapper getPdfFileMapper() {
        return pdfFileMapper;
    }
}