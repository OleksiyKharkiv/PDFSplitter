package com.example.pdfsplitterbackend.entity;

import com.example.pdfsplitterbackend.enums.RequestStatus;
import com.example.pdfsplitterbackend.enums.SplitMode;
import lombok.*;

import javax.persistence.*;

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
    @JoinColumn(name = "original_file_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PDFFile originalFile;
}