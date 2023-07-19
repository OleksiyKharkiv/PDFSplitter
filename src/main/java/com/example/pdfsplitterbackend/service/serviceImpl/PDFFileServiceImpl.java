package com.example.pdfsplitterbackend.service.serviceImpl;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.example.pdfsplitterbackend.dto.PDFFileDTO;
import com.example.pdfsplitterbackend.entity.PDFFile;
import com.example.pdfsplitterbackend.repository.PDFFileRepository;
import com.example.pdfsplitterbackend.service.PDFFileService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Service
@RequiredArgsConstructor
public class PDFFileServiceImpl implements PDFFileService {

    private final PDFFileRepository pdfFileRepository;

    @Override
    public String uploadPDFFile(MultipartFile file) throws IOException {
        // Реализация загрузки файла и сохранения его в базе данных
        // вернуть идентификатор загруженного файла
        String fileId = UUID.randomUUID().toString();
        byte[] fileContent = file.getBytes();
        PDFFile pdfFile = new PDFFile();
        pdfFileRepository.save(pdfFile);
        return fileId;
    }

    @Override
    public Resource downloadPDFFile(String fileId) throws FileNotFoundException {
        // Реализация загрузки файла из базы данных и создание объекта Resource для скачивания
        PDFFile pdfFile = pdfFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found"));
        byte[] fileContent = pdfFile.getFileContent();
        return new ByteArrayResource(fileContent);
    }

    @Override
    public List<PDFFileDTO> getAllPDFFiles() {
        // Реализация получения списка всех PDF-файлов из базы данных и преобразование их в DTO
        List<PDFFile> pdfFiles = pdfFileRepository.findAll();
        return pdfFiles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PDFFileDTO getPDFFileById(String fileId) throws FileNotFoundException {
        // Реализация получения конкретного PDF-файла из базы данных по его идентификатору и преобразование его в DTO
        PDFFile pdfFile = pdfFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found"));
        return convertToDTO(pdfFile);
    }

    @Override
    public void deletePDFFile(String fileId) {
        // Реализация удаления PDF-файла из базы данных
        pdfFileRepository.deleteById(fileId);
    }

    @Override
    public byte[] getPDFFileContentById(String fileId) throws IOException {
        // Логика получения файла по идентификатору и возврат его содержимого в виде массива байтов
        PDFFile pdfFile = pdfFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found"));
        return pdfFile.getFileContent();
    }

    @Override
    public PDFFileDTO getMergedPDFFileById(String fileId) throws IOException {
        return null;
    }

    @Override
    public String mergePDFFiles(List<String> fileIds) {
        try {
            PDFMergerUtility merger = new PDFMergerUtility();

            for (String fileId : fileIds) {
                PDFFile pdfFile = pdfFileRepository.findById(fileId)
                        .orElseThrow(() -> new FileNotFoundException("File not found"));
                byte[] fileContent = pdfFile.getFileContent();

                try (PDDocument document = PDDocument.load(new ByteArrayInputStream(fileContent))) {
                    merger.appendDocument(document, document);
                } catch (IOException e) {
                    // Обработка ошибки
                    e.printStackTrace();
                }
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            merger.save(outputStream);
            merger.reset();

            byte[] mergedFileContent = outputStream.toByteArray();

            // Сохранение объединенного файла в базе данных
            String mergedFileId = UUID.randomUUID().toString();
            String mergedFileTitle = "Merged File";
            int mergedFileSize = mergedFileContent.length;
            int mergedFileNumberOfPages = calculateNumberOfPages(mergedFileContent); // Здесь нужно определить логику для определения количества страниц
            PDFFile mergedPDFFile = new PDFFile(mergedFileId, mergedFileTitle, mergedFileSize, mergedFileNumberOfPages, mergedFileContent);
            pdfFileRepository.save(mergedPDFFile);

            // Вернуть идентификатор объединенного файла
            return mergedFileId;
        } catch (IOException e) {
            // Обработка ошибки
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int calculateNumberOfPages(byte[] fileContent) {
        return 0;
    }

    private PDFFileDTO convertToDTO(PDFFile pdfFile) {
        // Конвертирование PDFFile в PDFFileDTO
        // Вернуть DTO
        return new PDFFileDTO(
                pdfFile.getId(),
                pdfFile.getTitle(),
                pdfFile.getSizeKb(),
                pdfFile.getNumberOfPages(),
                pdfFile.getFileContent()
        );
    }
}