package pl.pw.edu.elka.zpbd.wikiReader.documentCreator;

import pl.pw.edu.elka.zpbd.wikiReader.WikiPage;

public class DocumentPrinter extends DocumentCreator {

    public DocumentPrinter() {
        super();
    }

    @Override
    public void insertDocument(WikiPage page) {
        System.out.println("Id: " + page.getId() + " Title: " + page.getTitle());
        System.out.println(page.getText());
    }

    @Override
    public void init() {

    }

    @Override
    public void close() {

    }
}
