package es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.dto.fileupload;

import es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.validation.FileNotEmpty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class SingleFileUploadDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull // Esto no sirve de nada, porque aunque no se seleccionen ficheros, el objeto MultipartFile existe.
    @FileNotEmpty
    private MultipartFile photo;
}
