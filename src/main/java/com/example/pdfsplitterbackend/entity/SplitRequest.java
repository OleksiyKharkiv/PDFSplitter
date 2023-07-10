package com.example.pdfsplitterbackend.entity;

import com.example.pdfsplitterbackend.enums.RequestStatus;
import com.example.pdfsplitterbackend.enums.SplitMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private String id;
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
}