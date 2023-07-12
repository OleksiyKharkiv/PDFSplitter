package com.example.pdfsplitterbackend.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pdf_page")
public class PDFPage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "page_number")
    private String title;
    @Column(name = "size_Kb")
    private int sizeKb;
    @Column(name = "number_of_pages")
    private int numberOfPages;
    @Column(name = "file_content")
    private byte[] fileContent;
}