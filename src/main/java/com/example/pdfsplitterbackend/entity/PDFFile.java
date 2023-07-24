package com.example.pdfsplitterbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pdf_file")

public class PDFFile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "size_Kb")
    private int sizeKb;
    @Column(name = "number_of_pages")
    private int numberOfPages;
    @Column(name = "file_content")
    private byte[] fileContent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pdfFile")
    private List<PDFPage> pdfPages;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "originalFile")
    private ResultFile resultFile;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "originalFile")
    private List<SplitRequest> splitRequests;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PDFFile pdfFile = (PDFFile) o;
        return Objects.equals(id, pdfFile.id) &&
                Objects.equals(title, pdfFile.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "PDFFile{" +
                "id=" + id +
                ", title='" + title + '\'' +
                // Другие поля класса
                '}';
    }
    public PDFFile(int id, String title, int sizeKb, int numberOfPages, byte[] fileContent) {
        this.id = id;
        this.title = title;
        this.sizeKb = sizeKb;
        this.numberOfPages = numberOfPages;
        this.fileContent = fileContent;
    }
}