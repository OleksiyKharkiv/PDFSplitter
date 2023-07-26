package com.example.pdfsplitterbackend.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "result_file")
@NoArgsConstructor
@AllArgsConstructor
public class ResultFile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne()
    @JoinColumn(name = "pdf_file_id", referencedColumnName = "id")
    private PDFFile pdfFile;
    @Column(name = "result_file_name")
    private String resultFileName;
    @Column(name = "result_file_size")
    private int resultFileSizeKb;
    @Column(name = "number_of_pages")
    private int numberOfPages;
    @Column(name = "file_content")
    private byte[] fileContent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultFile that = (ResultFile) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(pdfFile, that.pdfFile) &&
                Objects.equals(resultFileName, that.resultFileName) &&
                Objects.equals(resultFileSizeKb, that.resultFileSizeKb) &&
                Objects.equals(numberOfPages, that.numberOfPages) &&
                Arrays.equals(fileContent, that.fileContent);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, pdfFile, resultFileName, resultFileSizeKb, numberOfPages);
        result = 31 * result + Arrays.hashCode(fileContent);
        return result;
    }

    @Override
    public String toString() {
        return "ResultFile{" +
                "id=" + id +
                ", pdfFile=" + pdfFile +
                ", resultFileName='" + resultFileName + '\'' +
                ", resultFileSizeKb=" + resultFileSizeKb +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}