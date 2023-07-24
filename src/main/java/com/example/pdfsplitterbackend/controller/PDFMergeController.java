package com.example.pdfsplitterbackend.controller;

import com.example.pdfsplitterbackend.dto.PDFFileDTO;
import com.example.pdfsplitterbackend.service.PDFFileService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/pdf/merge")
@RequiredArgsConstructor
public class PDFMergeController {

    private final PDFFileService pdfFileService;

    @PostMapping
    public ResponseEntity<String> mergePDFFiles(@RequestBody List<String> fileIds) {
        try {
            String mergedFileId = pdfFileService.mergePDFFiles(fileIds);
            return ResponseEntity.ok(mergedFileId);
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to merge PDF files.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getMergedPDFFile(@PathVariable("id") String fileId) {
        try {
            Resource mergedFileResource = (Resource) pdfFileService.getMergedPDFFileById(fileId);
            ResponseEntity.BodyBuilder ok = ResponseEntity.ok();
            ok.header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + mergedFileResource.name() + "\"");
            return ok.body(mergedFileResource);
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/file/{id}")
    public ResponseEntity<Resource> downloadPDFFile(@PathVariable("id") String fileId) {
        try {
            PDFFileDTO fileResource = pdfFileService.getPDFFileById(fileId);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResource.getFileName() + "\"")
                    .body((Resource) fileResource);
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PDFFileDTO>> getAllPDFFiles() {
        List<PDFFileDTO> pdfFiles = pdfFileService.getAllPDFFiles();
        return ResponseEntity.ok(pdfFiles);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePDFFile(@PathVariable("id") String fileId) {
        try {
            pdfFileService.deletePDFFile(fileId);
            return ResponseEntity.ok("PDF file deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete PDF file.");
        }
    }
}