import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PDFFileMapper {
    PDFFileMapper INSTANCE = Mappers.getMapper(PDFFileMapper.class);

    @Mapping(target = "numberOfPages", ignore = true)
    PDFFileDTO toDTO(PDFFile pdfFile);

    PDFFile fromDTO(PDFFileDTO pdfFileDTO);
}