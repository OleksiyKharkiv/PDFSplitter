package com.example.pdfsplitterbackend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PDFFileDTO {
    private int id;
    private String title;
    private int sizeMb;
    private int numberOfPages;
    private byte[] fileContent;

    public PDFFileDTO(int id, String title, int sizeMb, int numberOfPages, byte[] fileContent) {
        this.id = id;
        this.title = title;
        this.sizeMb = sizeMb;
        this.numberOfPages = numberOfPages;
        this.fileContent = fileContent;
    }

    public String getFileName() {
        return title;
    }
}