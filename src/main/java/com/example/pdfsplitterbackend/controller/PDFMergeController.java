package com.example.pdfsplitterbackend.controller;

import com.example.pdfsplitterbackend.dto.PDFFileDTO;
import com.example.pdfsplitterbackend.service.PDFFileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/pdf/merge")
public class PDFMergeController {
    private final PDFFileService pdfFileService;

    public PDFMergeController(PDFFileService pdfFileService) {
        this.pdfFileService = pdfFileService;
    }

    @PostMapping("/files")
    public ResponseEntity<String> mergePDFFiles(@RequestParam("fileIds") String[] fileIds) {
        try {
            // Логика объединения PDF-файлов и получения идентификатора объединенного файла
            String mergedFileId = pdfFileService.mergePDFFiles(List.of(fileIds));
            return ResponseEntity.ok(mergedFileId);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to merge PDF files.");
        }
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> downloadMergedPDFFile(@PathVariable("id") String fileId) {
        try {
            // Логика получения объединенного файла по идентификатору
            byte[] mergedFileContent = pdfFileService.getMergedPDFFileContentById(fileId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", "merged.pdf");
            return new ResponseEntity<>(mergedFileContent, headers, HttpStatus.OK);
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Другие методы контроллера для обработки объединения PDF-файлов
}