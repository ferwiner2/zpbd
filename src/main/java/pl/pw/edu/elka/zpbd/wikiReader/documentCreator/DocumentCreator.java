package pl.pw.edu.elka.zpbd.wikiReader.documentCreator;

import pl.pw.edu.elka.zpbd.wikiReader.WikiPage;

public abstract class DocumentCreator  {
     private Parser parser;

     DocumentCreator() {
          parser = new Parser();
     }

     public void generateAndInsertDocument(String page){
          WikiPage wikiPage = generateDocument(page);
          insertDocument(wikiPage);
     }

     private WikiPage generateDocument(String page){
          parser.loadPage(page);
          return parser.getWikiPage();
     }

     public abstract void insertDocument(WikiPage page);
}
