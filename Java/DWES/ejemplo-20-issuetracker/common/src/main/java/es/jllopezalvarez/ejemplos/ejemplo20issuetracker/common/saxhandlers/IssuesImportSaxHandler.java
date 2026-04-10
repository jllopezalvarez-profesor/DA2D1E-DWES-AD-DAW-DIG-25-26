package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.saxhandlers;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Issue;
import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IssuesImportSaxHandler extends DefaultHandler {

    @Getter
    private List<Issue> issues = new ArrayList<>();
    private Issue currentIssue;

    private StringBuilder currentTextBuilder = new StringBuilder();

    private boolean inIssue = false;
    private boolean inTitle = false;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.printf("Inicio de elemento: %s\n", qName);
        switch (qName) {
            case "issue":
                this.inIssue = true;
                this.currentIssue = new Issue();
                // si quisieramos coger un atributo del elemento, sería aquí;
                break;
            case "title":
                this.inTitle = true;
                this.currentTextBuilder.setLength(0);
                break;
            default:
                System.out.printf("Se ha encontrado un elemento que no nos interesa: %s\n", qName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.printf("Fin de elemento: %s\n", qName);
        switch (qName) {
            case "issue":
                this.inIssue = false;
                this.issues.add(currentIssue);
//                this.currentIssue = null;
            case "title":
                this.inTitle = false;
                // guardar el título en el objeto currentIssue
                this.currentIssue.setTitle(currentTextBuilder.toString());
                break;
            default:
                System.out.printf("Se ha encontrado un fin de elemento que no nos interesa: %s\n", qName);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.printf("Texto recibido: %s\n", new String(ch, start, length));
        this.currentTextBuilder.append(ch, start, length);
    }
}
