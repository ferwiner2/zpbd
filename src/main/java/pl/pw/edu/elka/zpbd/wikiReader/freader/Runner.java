package pl.pw.edu.elka.zpbd.wikiReader.freader;

import pl.pw.edu.elka.zpbd.wikiReader.config.Config;
import pl.pw.edu.elka.zpbd.wikiReader.documentCreator.DocumentCreator;

import java.io.IOException;

public class Runner {
    private DocumentCreator documentCreator = null;
    FileReader.Mode mode;

    public Runner(DocumentCreator documentCreator, FileReader.Mode mode) {
        this.documentCreator = documentCreator;
        this.mode = mode;
    }

    public void runLoop(int limit) throws IOException {
        FileReader freader = new FileReader(mode);
        long start = System.currentTimeMillis();
        int i = 0;
        try {
            while (limit < 0 || (limit > 0 && limit > i)) {
                if (++i % Config.getLogResulotion() == 0) {
                    double pagesPerSecond = (i * 1000.0) / (System.currentTimeMillis() - start);
                    System.out.printf(i + " pages parsed. %.2f p per sec \n", pagesPerSecond);
                }
                String page = freader.getNextPage();
                documentCreator.generateAndInsertDocument(page);
            }
        } catch (EndOfFileException ignored) {
        }
        System.out.println(i + " pages parsed");
        freader.close();
    }

    public void runLoop() throws IOException{
        runLoop(-1);
    }
}
