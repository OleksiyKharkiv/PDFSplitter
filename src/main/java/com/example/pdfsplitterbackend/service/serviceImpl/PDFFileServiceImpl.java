package com.example.pdfsplitterbackend.service.serviceImpl;

import com.example.pdfsplitterbackend.dto.PDFFileDTO;
import com.example.pdfsplitterbackend.entity.PDFFile;
import com.example.pdfsplitterbackend.mapper.PDFFileMapper;
import com.example.pdfsplitterbackend.repository.PDFFileRepository;
import com.example.pdfsplitterbackend.service.PDFFileService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Service
@RequiredArgsConstructor
public class PDFFileServiceImpl implements PDFFileService {

    private final PDFFileRepository pdfFileRepository;
    @Qualifier("PDFFileMapper")
    private final PDFFileMapper pdfFileMapper;

    @Override
    public String uploadPDFFile(MultipartFile file) {
        // Реализация загрузки файла и сохранения его в базе данных
        // возвращается идентификатор загруженного файла
        String fileId = UUID.randomUUID().toString();
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
        List<PDFFile> pdfFiles = pdfFileRepository.findAll();
        return pdfFiles.stream()
                .map(pdfFileMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PDFFileDTO getPDFFileById(int fileId) throws FileNotFoundException {
        PDFFile pdfFile = pdfFileRepository.findById(String.valueOf(fileId))
                .orElseThrow(() -> new FileNotFoundException("File not found"));
        return pdfFileMapper.toDTO(pdfFile);
    }


    @Override
    public void deletePDFFile(String fileId) {
        // Реализация удаления PDF-файла из базы данных
        pdfFileRepository.deleteById(fileId);
    }

    @Override
    public byte[] getPDFFileContentById(int fileId) throws IOException {
        // Логика получения файла по идентификатору и возврат его содержимого в виде массива байтов
        PDFFile pdfFile = pdfFileRepository.findById(String.valueOf(fileId))
                .orElseThrow(() -> new FileNotFoundException("File not found"));
        return pdfFile.getFileContent();
    }

    @Override
    public PDFFileDTO getMergedPDFFileById(int fileId) throws IOException {
        PDFFile mergedPDFFile = mergePDFFiles();
        return pdfFileMapper.toDTO(mergedPDFFile);
    }

    private PDFFile mergePDFFiles() throws IOException {
        try {
            PDFMergerUtility merger = new PDFMergerUtility();

            // ... (ваш код для объединения файлов)

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            merger.setDestinationFileName(outputStream.toString());
            merger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
            byte[] mergedFileContent = outputStream.toByteArray();

            PDFFile mergedPDFFile = new PDFFile();
            mergedPDFFile.setId(Integer.parseInt(UUID.randomUUID().toString()));
            mergedPDFFile.setTitle("Merged File");
            mergedPDFFile.setSizeKb(mergedFileContent.length);
            mergedPDFFile.setNumberOfPages(calculateNumberOfPages(mergedFileContent));
            mergedPDFFile.setFileContent(mergedFileContent);

            return pdfFileRepository.save(mergedPDFFile);
        } catch (IOException e) {
            // Обработка ошибки
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int mergePDFFiles(List<String> fileIds) {
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
            merger.setDestinationFileName(outputStream.toString());
            merger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
            byte[] mergedFileContent = outputStream.toByteArray();

            // Сохранение объединенного файла в базе данных
            int mergedFileId = Integer.parseInt(UUID.randomUUID().toString());
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
            return -1;
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