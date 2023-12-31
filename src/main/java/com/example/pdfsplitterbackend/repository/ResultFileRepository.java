package com.example.pdfsplitterbackend.repository;

import com.example.pdfsplitterbackend.entity.ResultFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultFileRepository extends JpaRepository<
        ResultFile, Long>{
}