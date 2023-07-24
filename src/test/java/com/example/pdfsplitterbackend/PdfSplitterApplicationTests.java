package com.example.pdfsplitterbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {
        "jdbc.url=jdbc:mysql://localhost:3306/pdf_splitter",
        "jdbc.username=${MY_USERNAME}",
        "jdbc.password=${MY_PASSWORD}"
})

@SpringBootTest
public class PdfSplitterApplicationTests {

    @Test
    void contextLoads() {
    }
}