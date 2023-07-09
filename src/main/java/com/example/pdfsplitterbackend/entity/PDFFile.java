package com.example.pdfsplitterbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pdffile")
@NoArgsConstructor
@AllArgsConstructor

public class PDFFile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "title")

    private String title;
    private int sizeKb;
    private int numberOfPages;
    private byte[] fileContent;
}
