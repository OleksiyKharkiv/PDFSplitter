package com.example.pdfsplitterbackend.dto;

import com.example.pdfsplitterbackend.enums.SplitMode;
import lombok.Value;

import java.util.List;

@Value
public class PDFSplitRequestDTO {
    String pdfFileId;
    SplitMode splitMode;
    int pageNumberFrom;
    int pageQuantityGroup;
    int splitSizeKb;
    List<Integer> splitPagesList;
}