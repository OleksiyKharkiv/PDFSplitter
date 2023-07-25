package com.example.pdfsplitterbackend.mapper;

import com.example.pdfsplitterbackend.dto.PDFFileDTO;
import com.example.pdfsplitterbackend.entity.PDFFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import static org.mapstruct.factory.Mappers.getMapper;
@Service
@Primary
@Mapper(componentModel = "spring")
public interface PDFFileMapper {
    PDFFileMapper INSTANCE = getMapper(PDFFileMapper.class);

    // Методы маппинга для преобразования между DTO и сущностью PDFFile
    @Mapping(target = "title", source = "title")
    PDFFileDTO toDTO(PDFFile pdfFile);

    @Mapping(target = "title", source = "fileName")
    PDFFile toEntity(PDFFileDTO pdfFileDTO);
}