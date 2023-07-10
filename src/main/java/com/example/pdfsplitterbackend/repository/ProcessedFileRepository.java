package com.example.pdfsplitterbackend.repository;

import com.example.pdfsplitterbackend.entity.ProcessedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedFileRepository extends JpaRepository<
ProcessedFile, Long>{
}