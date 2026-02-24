package es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.dto.fileupload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
public class MultipleFileUploadDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull // Esto no sirve de nada, porque aunque no se seleccionen ficheros, el objeto MultipartFile existe.
    // @FileNotEmpty No sirve para varios, habría que hacer una validación tipo @FileNotEmpty.List

    private List<MultipartFile> photos;
}
