package pl.pw.edu.elka.zpbd.wikiReader;

import pl.pw.edu.elka.zpbd.tests.CassandraTest;
import pl.pw.edu.elka.zpbd.tests.DatabaseTest;
import pl.pw.edu.elka.zpbd.tests.RandomGenerator;
import pl.pw.edu.elka.zpbd.wikiReader.documentCreator.DocumentCreator;
import pl.pw.edu.elka.zpbd.wikiReader.documentCreator.MongoDocCreator;
import pl.pw.edu.elka.zpbd.wikiReader.freader.FileReader;
import pl.pw.edu.elka.zpbd.wikiReader.freader.Runner;

import java.io.IOException;


public class Main {


    public static void main(String[] args) throws Exception {
        mongo();
    }

    private static void mongo() throws IOException {
        DocumentCreator dp = new MongoDocCreator();

        dp.init();
        Runner p = new Runner(dp, FileReader.Mode.PL);

        p.runLoop();

        dp.close();
        dp.timer.showTime();
    }

}
