package com.example.pdfsplitterbackend.controler;

import com.example.pdfsplitterbackend.controller.PDFFileController;
import com.example.pdfsplitterbackend.dto.PDFFileDTO;
import com.example.pdfsplitterbackend.service.PDFFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(PDFFileController.class)
public class PDFFileControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PDFFileService pdfFileService;

    @Test
    public void testUploadPDFFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.pdf", "application/pdf", "pdf content".getBytes());

        when(pdfFileService.uploadPDFFile(any(MultipartFile.class))).thenReturn("fileId123");

        mockMvc.perform(multipart("/api/pdf/upload").file(file))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("fileId123"));
    }

    @Test
    public void testDownloadPDFFile() throws Exception {
        String fileId = "fileId123";
        byte[] fileContent = "pdf content".getBytes();
        String fileName = "test.pdf";

        PDFFileDTO pdfFileDTO = new PDFFileDTO(fileId, fileName, fileContent.length, 1, fileContent);
        when(pdfFileService.getPDFFileById(fileId)).thenReturn(pdfFileDTO);
        when(pdfFileService.getPDFFileContentById(fileId)).thenReturn(fileContent);

        mockMvc.perform(get("/api/pdf/file/{id}", fileId))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"test.pdf\""))
                .andExpect((ResultMatcher) content().bytes(fileContent));
    }
}