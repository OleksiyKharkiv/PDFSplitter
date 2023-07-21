package com.example.pdfsplitterbackend.controler;

@RunWith(SpringRunner.class)
@WebMvcTest(PDFMergeController.class)
public class PDFMergeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PDFFileService pdfFileService;

    @Test
    public void testMergePDFFiles() throws Exception {
        List<String> fileIds = Arrays.asList("fileId1", "fileId2", "fileId3");
        String mergedFileId = "mergedFileId123";

        when(pdfFileService.mergePDFFiles(fileIds)).thenReturn(mergedFileId);

        mockMvc.perform(post("/api/pdf/merge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(fileIds)))
                .andExpect(status().isOk())
                .andExpect(content().string(mergedFileId));
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
                .andExpect(content().bytes(fileContent));
    }
}