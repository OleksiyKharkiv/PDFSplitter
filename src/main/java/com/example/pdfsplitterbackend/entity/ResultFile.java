package com.example.pdfsplitterbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "processed_file")
@NoArgsConstructor
@AllArgsConstructor

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