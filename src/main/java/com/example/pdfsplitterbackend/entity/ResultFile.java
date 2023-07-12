package com.example.pdfsplitterbackend.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "processed_file")
public class ResultFile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "original_file_id")
    private int originalFileId;
    @Column(name = "processed_file_name")
    private String processedFileName;
    @Column(name = "processed_file_size")
    private int processedFileSizeKb;
    @Column(name = "number_of_pages")
    private int numberOfPages;
    @Column(name = "file_content")
    private byte[] fileContent;
}