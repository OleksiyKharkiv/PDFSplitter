package com.example.pdfsplitterbackend.dto;

import lombok.Data;

@Data
public class PDFFileDTO {
    private String id;
    private String title;
    private int sizeMb;
    private int numberOfPages;
    private byte[] fileContent;

    public PDFFileDTO(String id, String title, int sizeKb, int numberOfPages, byte[] fileContent) {
    }

    public String getFileName() {
        return title;
    }
}