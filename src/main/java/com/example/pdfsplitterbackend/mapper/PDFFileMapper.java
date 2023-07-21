package com.example.pdfsplitterbackend.mapper;

import com.example.pdfsplitterbackend.dto.PDFFileDTO;
import com.example.pdfsplitterbackend.entity.PDFFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import static org.mapstruct.factory.Mappers.getMapper;

@Component
@Mapper(componentModel = "spring")
public interface PDFFileMapper {
    PDFFileMapper INSTANCE = getMapper(PDFFileMapper.class);

    // Методы маппинга для преобразования между DTO и сущностью PDFFile
    @Mapping(target = "fileName", source = "title")
    PDFFileDTO toDTO(PDFFile pdfFile);

    @Mapping(target = "title", source = "fileName")
    PDFFile toEntity(PDFFileDTO pdfFileDTO);
}