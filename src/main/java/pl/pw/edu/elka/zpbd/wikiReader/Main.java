package pl.pw.edu.elka.zpbd.wikiReader;

import pl.pw.edu.elka.zpbd.wikiReader.documentCreator.DocumentCreator;
import pl.pw.edu.elka.zpbd.wikiReader.documentCreator.MongoDocCreator;
import pl.pw.edu.elka.zpbd.wikiReader.freader.FileReader;
import pl.pw.edu.elka.zpbd.wikiReader.freader.Runner;

public class Main {


    public static void main(String[] args) throws Exception {
        DocumentCreator dp = new MongoDocCreator();

        dp.init();
        Runner p = new Runner(dp, FileReader.Mode.PL);

        p.runLoop();

        dp.close();
        dp.timer.showTime();

    }
}
