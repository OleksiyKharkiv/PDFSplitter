package com.example.pdfsplitterbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.pdfsplitterbackend")
public class PdfSplitterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdfSplitterApplication.class, args);
    }
}