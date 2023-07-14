package com.example.pdfsplitterbackend.service.serviceImpl;

import com.example.pdfsplitterbackend.dto.PDFFileDTO;
import com.example.pdfsplitterbackend.entity.PDFFile;
import com.example.pdfsplitterbackend.repository.PDFFileRepository;
import com.example.pdfsplitterbackend.service.PDFFileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PDFFileServiceImpl implements PDFFileService {

    private final PDFFileRepository pdfFileRepository;

    public PDFFileServiceImpl(PDFFileRepository pdfFileRepository) {
        this.pdfFileRepository = pdfFileRepository;
    }

    @Override
    public String uploadPDFFile(MultipartFile file) throws IOException {
        // Реализация загрузки файла и сохранения его в базе данных
        // Вернуть идентификатор загруженного файла
        return "fileId";
    }

    @Override
    public Resource downloadPDFFile(String fileId) throws FileNotFoundException {
        // Реализация загрузки файла из базы данных и создание объекта Resource для скачивания
        // Вернуть объект Resource
        byte[] fileContent = getFileContent(fileId);
        return new ByteArrayResource(fileContent);
    }

    @Override
    public List<PDFFileDTO> getAllPDFFiles() {
        // Реализация получения списка всех PDF-файлов из базы данных и преобразование их в DTO
        // Вернуть список DTO
        List<PDFFile> pdfFiles = pdfFileRepository.findAll();
        return pdfFiles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PDFFileDTO getPDFFileById(String fileId) throws FileNotFoundException {
        // Реализация получения конкретного PDF-файла из базы данных по его идентификатору и преобразование его в DTO
        // Вернуть DTO
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
    public String uploadPDFFile(byte[] fileContent) {
        // Реализация загрузки и сохранения файла в базе данных
    }

    @Override
    public Resource getMergedPDFFileById(String fileId) throws IOException {
        // Логика получения объединенного файла по идентификатору
        PDFFile mergedFile = pdfFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found"));

        return new ByteArrayResource(mergedFile.getFileContent());
    }

    @Override
    public byte[] mergePDFFiles(List<String> fileIds) throws IOException {
        // Логика объединения PDF-файлов
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfMerger merger = new PdfMerger(new PdfDocument(new PdfWriter(outputStream)));

        for (String fileId : fileIds) {
            PDFFileDTO pdfFile = pdfFileRepository.findById(fileId)
                    .orElseThrow(() -> new FileNotFoundException("File not found"));

            byte[] fileContent = pdfFile.getFileContent();
            PdfReader reader = new PdfReader(new ByteArrayInputStream(fileContent));

            merger.merge(reader, 1, reader.getNumberOfPages());

            reader.close();
        }

        merger.close();

        return outputStream.toByteArray();
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

    private byte[] getFileContent(String fileId) throws FileNotFoundException {
        PDFFile pdfFile = pdfFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found"));
        return pdfFile.getFileContent();
    }
}