package com.example.pdfsplitterbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Data
@Entity
@Table(name = "pdf_page")
public class PDFPage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "page_number")
    private int pageNumber;
    @Column(name = "size_Mb")
    private int sizeKb;
    @Column(name = "number_of_pages")
    private int numberOfPages;
    @Column(name = "file_content")
    private byte[] fileContent;
    @ManyToOne
    @JoinColumn(name = "pdf_file_id", referencedColumnName = "id")
    private PDFFile pdfFile;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PDFPage pdfPage = (PDFPage) o;
        return Objects.equals(id, pdfPage.id) &&
                Objects.equals(pageNumber, pdfPage.pageNumber) &&
                Objects.equals(sizeKb, pdfPage.sizeKb) &&
                Objects.equals(numberOfPages, pdfPage.numberOfPages) &&
                Arrays.equals(fileContent, pdfPage.fileContent);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, pageNumber, sizeKb, numberOfPages);
        result = 31 * result + Arrays.hashCode(fileContent);
        return result;
    }

    @Override
    public String toString() {
        return "PDFPage{" +
                "id=" + id +
                ", pageNumber=" + pageNumber +
                ", sizeKb=" + sizeKb +
                ", numberOfPages=" + numberOfPages +
                // Другие поля класса
                '}';
    }
}