package com.example.pdfsplitterbackend.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "pdf_page")
@NoArgsConstructor
@AllArgsConstructor
public class PDFPage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "pdf_file_id", referencedColumnName = "id")
    private PDFFile pdfFile;
    @Column(name = "page_number")
    private int pageNumber;
    @Column(name = "size_Kb")
    private int sizeKb;
    @Column(name = "number_of_pages")
    private int numberOfPages;
    @Column(name = "file_content")
    private byte[] fileContent;

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
                ", pdfFile=" + pdfFile +
                ", pageNumber=" + pageNumber +
                ", sizeKb=" + sizeKb +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}