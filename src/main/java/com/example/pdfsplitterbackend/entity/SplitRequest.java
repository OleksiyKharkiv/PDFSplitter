package com.example.pdfsplitterbackend.entity;

import com.example.pdfsplitterbackend.enums.RequestStatus;
import com.example.pdfsplitterbackend.enums.SplitMode;
import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "split_request")
@NoArgsConstructor
@AllArgsConstructor
public class SplitRequest {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "pdf_file_id", referencedColumnName = "id")
    private PDFFile pdfFile;
    @Column(name = "split_mode")
    @Enumerated(EnumType.ORDINAL)
    private SplitMode splitMode;
    @Column(name = "split_value")
    private int splitValue;
    @Column(name = "request_status")
    @Enumerated(EnumType.ORDINAL)
    private RequestStatus requestStatus;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SplitRequest that = (SplitRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(pdfFile, that.pdfFile) && // Используем поле 'originalFile', а не 'originalFileId'
                Objects.equals(splitMode, that.splitMode) &&
                Objects.equals(splitValue, that.splitValue) &&
                Objects.equals(requestStatus, that.requestStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pdfFile, splitMode, splitValue, requestStatus); // Включим 'originalFile' в вычисление hashCode
    }

    @Override
    public String toString() {
        return "SplitRequest{" +
                "id=" + id +
                ", pdfFile=" + pdfFile +
                ", splitMode=" + splitMode +
                ", splitValue=" + splitValue +
                ", requestStatus=" + requestStatus +
                '}';
    }
}