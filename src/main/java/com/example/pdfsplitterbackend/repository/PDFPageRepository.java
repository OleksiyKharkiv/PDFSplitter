package com.example.pdfsplitterbackend.repository;

import com.example.pdfsplitterbackend.entity.PDFPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PDFPageRepository extends JpaRepository<PDFPage, Long> {
}