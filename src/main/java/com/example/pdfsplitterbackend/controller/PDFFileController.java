package com.example.pdfsplitterbackend.controller;

import com.example.pdfsplitterbackend.dto.PDFFileDTO;
import com.example.pdfsplitterbackend.service.PDFFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pdf")
public class PDFFileController {

    private final PDFFileService pdfFileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPDFFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileId = pdfFileService.uploadPDFFile(file);
        return ResponseEntity.ok(fileId);
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> downloadPDFFile(@PathVariable("id") String fileId) {
        try {
            byte[] fileContent = pdfFileService.getPDFFileContentById(fileId);
            PDFFileDTO pdfFile = pdfFileService.getPDFFileById(fileId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", pdfFile.getFileName());
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}