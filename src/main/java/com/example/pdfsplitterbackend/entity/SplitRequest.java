package com.example.pdfsplitterbackend.entity;

import com.example.pdfsplitterbackend.enums.RequestStatus;
import com.example.pdfsplitterbackend.enums.SplitMode;
import lombok.Data;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Data
@Entity
@Table(name = "split_request")
public class SplitRequest {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "original_file_id")
    private int originalFileId;
    @Column(name = "split_mode")
    @Enumerated(EnumType.ORDINAL)
    private SplitMode splitMode;
    @Column(name = "split_value")
    private int splitValue;
    @Column(name = "request_status")
    @Enumerated(EnumType.ORDINAL)
    private RequestStatus requestStatus;

    @ManyToOne
    @JoinColumn(name = "original_file_id", referencedColumnName = "id")
    private PDFFile originalFile;

    private ResultFile resultFileName;
    private ResultFile resultFileSizeKb;
    private ResultFile numberOfPages;
    private PDFFile fileContent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultFile that = (ResultFile) o;
        return Objects.equals(id, that.getId()) &&
                Objects.equals(originalFileId, that.getOriginalFileId()) &&
                Objects.equals(resultFileName, that.getResultFileName()) &&
                Objects.equals(resultFileSizeKb, that.getResultFileSizeKb()) &&
                Objects.equals(numberOfPages, that.getNumberOfPages()) &&
                Arrays.equals(fileContent.getFileContent(), that.getFileContent());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, originalFileId, resultFileName, resultFileSizeKb, numberOfPages);
        result = 31 * result + Arrays.hashCode(fileContent.getFileContent());
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