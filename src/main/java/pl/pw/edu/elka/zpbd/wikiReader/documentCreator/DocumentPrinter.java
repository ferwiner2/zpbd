package pl.pw.edu.elka.zpbd.wikiReader.documentCreator;

public class DocumentPrinter implements DocumentCreator {

    Parser parser;

    public DocumentPrinter() {
        parser = new Parser();
    }

    @Override
    public void generateAndInsertDocument(String page) {
        parser.loadPage(page);
        System.out.println("Id: " + parser.getId() + " Title: " + parser.getTitle());
        System.out.println(parser.getText());
    }
}
