package com.example.pdfsplitterbackend.entity;

import com.example.pdfsplitterbackend.enums.RequestStatus;
import com.example.pdfsplitterbackend.enums.SplitMode;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "split_request")
public class SplitRequest {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "split_mode")
    @Enumerated(EnumType.ORDINAL)
    private SplitMode splitMode;
    @Column(name = "split_value")
    private int splitValue;
    @Column(name = "request_status")
    @Enumerated(EnumType.ORDINAL)
    private RequestStatus requestStatus;

    @ManyToOne
    @JoinColumn(name = "pdf_file_id", referencedColumnName = "id")
    private PDFFile originalFileId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SplitRequest that = (SplitRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(originalFileId, that.originalFileId) && // Используем поле 'originalFile', а не 'originalFileId'
                Objects.equals(splitMode, that.splitMode) &&
                Objects.equals(splitValue, that.splitValue) &&
                Objects.equals(requestStatus, that.requestStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originalFileId, splitMode, splitValue, requestStatus); // Включим 'originalFile' в вычисление hashCode
    }

    @Override
    public String toString() {
        return "SplitRequest{" +
                "id=" + id +
                ", originalFile=" + originalFileId +
                ", splitMode=" + splitMode +
                ", splitValue=" + splitValue +
                ", requestStatus=" + requestStatus +
                '}';
    }
}