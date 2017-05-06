package pl.pw.edu.elka.zpbd.wikiReader;

import pl.pw.edu.elka.zpbd.wikiReader.documentCreator.DocumentCreator;
import pl.pw.edu.elka.zpbd.wikiReader.documentCreator.DocumentPrinter;
import pl.pw.edu.elka.zpbd.wikiReader.freader.FileReader;
import pl.pw.edu.elka.zpbd.wikiReader.freader.Runner;

public class Main {


    public static void main(String[] args) throws Exception {
        DocumentCreator dp = new DocumentPrinter();
        Runner p = new Runner(dp, FileReader.Mode.JV);

        p.runLoop(20);

    }
}
