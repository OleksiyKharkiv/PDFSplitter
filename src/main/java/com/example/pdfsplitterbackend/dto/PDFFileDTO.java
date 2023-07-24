package com.example.pdfsplitterbackend.dto;

import lombok.Data;

@Data
public class PDFFileDTO {
    private int id;
    private String title;
    private int sizeMb;
    private int numberOfPages;
    private byte[] fileContent;

    public PDFFileDTO(int id, String title, int sizeKb, int numberOfPages, byte[] fileContent) {
    }

    public String getFileName() {
        return title;
    }
    public void setFileName(String fileName) {
        this.title = fileName;
    }
}