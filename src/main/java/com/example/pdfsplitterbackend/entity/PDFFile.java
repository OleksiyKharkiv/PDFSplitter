package com.example.pdfsplitterbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pdf_file")

public class PDFFile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "size_Kb")
    private int sizeKb;
    @Column(name = "number_of_pages")
    private int numberOfPages;
    @Column(name = "file_content")
    private byte[] fileContent;
    @OneToMany(mappedBy = "pdfFile")
    private List<PDFPage> pdfPages;

    @OneToOne(mappedBy = "originalFile")
    private ResultFile resultFile;

    @OneToMany(mappedBy = "originalFile")
    private List<SplitRequest> splitRequests;

//    public PDFFile(String mergedFileId, String mergedFileTitle, int mergedFileSize, int mergedFileNumberOfPages, byte[] mergedFileContent) {
//    }
}