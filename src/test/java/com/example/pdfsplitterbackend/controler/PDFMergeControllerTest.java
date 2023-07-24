package com.example.pdfsplitterbackend.controler;

import com.example.pdfsplitterbackend.controller.PDFFileController;
import com.example.pdfsplitterbackend.dto.PDFFileDTO;
import com.example.pdfsplitterbackend.service.PDFFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PDFFileController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PDFMergeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PDFFileService pdfFileService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testMergePDFFiles() throws Exception {
        List<String> fileIds = Arrays.asList("fileId1", "fileId2", "fileId3");
        String mergedFileId = "mergedFileId123";

        when(pdfFileService.mergePDFFiles(fileIds)).thenReturn(mergedFileId);

        mockMvc.perform(post("/api/pdf/merge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fileIds)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string(mergedFileId));
    }

    @Test
    public void testGetMergedPDFFile() throws Exception {
        String fileId = "mergedFileId123";
        byte[] fileContent = "pdf content".getBytes();
        String fileName = "merged.pdf";

        PDFFileDTO pdfFileDTO = new PDFFileDTO(fileId, fileName, fileContent.length, 10, fileContent);
        when(pdfFileService.getMergedPDFFileById(fileId)).thenReturn(pdfFileDTO);

        mockMvc.perform(get("/api/pdf/merge/{id}", fileId))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"merged.pdf\""))
                .andExpect((ResultMatcher) content().bytes(fileContent));
    }
}