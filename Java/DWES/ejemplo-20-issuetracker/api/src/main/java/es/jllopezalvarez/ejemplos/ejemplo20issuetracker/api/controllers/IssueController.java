package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.api.controllers;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services.IssueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
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

}
