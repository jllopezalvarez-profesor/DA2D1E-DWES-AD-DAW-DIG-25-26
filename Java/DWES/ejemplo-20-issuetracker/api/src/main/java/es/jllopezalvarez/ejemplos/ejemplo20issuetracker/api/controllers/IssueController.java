package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.api.controllers;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.dto.api.IssueDto;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Issue;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.mappers.IssueMapper;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services.IssueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {

    private final IssueService issueService;
    private final IssueMapper issueMapper;

    public IssueController(IssueService issueService, IssueMapper issueMapper) {
        this.issueService = issueService;
        this.issueMapper = issueMapper;
    }

    @GetMapping
    public ResponseEntity<List<IssueDto>> findAll() {
        return ResponseEntity.ok(issueMapper.map(issueService.findAll()));
    }

    @GetMapping(value = "/export", produces = "text/xml")
    public ResponseEntity<String> export() throws
            ParserConfigurationException,
            TransformerException,
            IOException {
        Document issuesDocument = issueService.exportAll();

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        DOMSource source = new DOMSource(issuesDocument);
        try (StringWriter writer = new StringWriter()) {
            StreamResult result = new StreamResult(writer);

            transformer.setOutputProperty("indent", "4");

            transformer.transform(source, result);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Disposition", "attachement; datos.xml")
//                    .contentType(MediaType.TEXT_XML)
                    .body(writer.toString());

        }
    }

    @GetMapping(value = "/export-stax", produces = "text/xml")
    public ResponseEntity<String> exportStax() throws XMLStreamException {
        String issuesDocument = issueService.exportAllStax();

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Disposition", "attachement; datos.xml")
//                    .contentType(MediaType.TEXT_XML)
                .body(issuesDocument);

    }


    @PostMapping("/import")
    public ResponseEntity<String> importFile(@RequestParam("file") MultipartFile multipartFile) throws IOException, ParserConfigurationException, SAXException {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.badRequest().body("El fichero no se ha recibido");
        }
        if (multipartFile.getSize() == 0) {
            return ResponseEntity.badRequest().body("El fichero recibido está vacío");
        }

        System.out.printf("Tamaño del fichero recibido: %s\n", multipartFile.getSize());
        System.out.printf("Nombre original del fichero recibido: %s\n", multipartFile.getOriginalFilename());

        issueService.importIssues(multipartFile.getInputStream());

        return ResponseEntity.ok("Fichero recibido correctamente");
    }

    @PostMapping("/import-stax")
    public ResponseEntity<String> importFileStax(@RequestParam("file") MultipartFile multipartFile) throws XMLStreamException, IOException {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.badRequest().body("El fichero no se ha recibido");
        }
        if (multipartFile.getSize() == 0) {
            return ResponseEntity.badRequest().body("El fichero recibido está vacío");
        }

        System.out.printf("Tamaño del fichero recibido: %s\n", multipartFile.getSize());
        System.out.printf("Nombre original del fichero recibido: %s\n", multipartFile.getOriginalFilename());

        issueService.importIssuesStax(multipartFile.getInputStream());

        return ResponseEntity.ok("Fichero recibido correctamente");
    }

}
