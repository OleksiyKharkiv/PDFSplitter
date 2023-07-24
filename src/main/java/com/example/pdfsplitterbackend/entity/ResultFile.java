package com.example.pdfsplitterbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

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
//    @OneToOne
//    @JoinColumn(name = "original_file_id", referencedColumnName = "id", insertable = false, updatable = false)
//    private PDFFile originalFile;
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ResultFile that = (ResultFile) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(originalFileId, that.originalFileId) &&
            Objects.equals(resultFileName, that.resultFileName) &&
            Objects.equals(resultFileSizeKb, that.resultFileSizeKb) &&
            Objects.equals(numberOfPages, that.numberOfPages) &&
            Arrays.equals(fileContent, that.fileContent);
}

    @Override
    public int hashCode() {
        int result = Objects.hash(id, originalFileId, resultFileName, resultFileSizeKb, numberOfPages);
        result = 31 * result + Arrays.hashCode(fileContent);
        return result;
    }

    @Override
    public String toString() {
        return "ResultFile{" +
                "id=" + id +
                ", originalFileId=" + originalFileId +
                ", resultFileName='" + resultFileName + '\'' +
                ", resultFileSizeKb=" + resultFileSizeKb +
                ", numberOfPages=" + numberOfPages +
                // Другие поля класса
                '}';
    }
}