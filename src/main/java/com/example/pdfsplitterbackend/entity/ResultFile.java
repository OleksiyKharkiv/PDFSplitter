package com.example.pdfsplitterbackend.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "result_file")
public class ResultFile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "original_file_id")
    private int originalFileId;
    @Column(name = "result_file_name")
    private String resultFileName;
    @Column(name = "result_file_size")
    private int resultFileSizeKb;
    @Column(name = "number_of_pages")
    private int numberOfPages;
    @Column(name = "file_content")
    private byte[] fileContent;
    @OneToOne
    @JoinColumn(name = "original_file_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PDFFile originalFile;

}