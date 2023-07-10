package com.example.pdfsplitterbackend.repository;

import com.example.pdfsplitterbackend.entity.PDFFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PDFFileRepository extends JpaRepository<PDFFile, String> {
}