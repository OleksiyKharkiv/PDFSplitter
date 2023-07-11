package com.example.pdfsplitterbackend.repository;

import com.example.pdfsplitterbackend.entity.SplitRequest;
import com.example.pdfsplitterbackend.entity.SplitResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SplitRequestRepository extends JpaRepository<SplitRequest, Long> {
}