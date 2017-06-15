package pl.pw.edu.elka.zpbd.wikiReader;

import pl.pw.edu.elka.zpbd.tests.DatabaseTest;
import pl.pw.edu.elka.zpbd.tests.MongoTest;
import pl.pw.edu.elka.zpbd.tests.MySQLTest;
import pl.pw.edu.elka.zpbd.wikiReader.documentCreator.DocumentCreator;
import pl.pw.edu.elka.zpbd.wikiReader.documentCreator.MongoDocCreator;
import pl.pw.edu.elka.zpbd.wikiReader.documentCreator.MySQLDocCreator;
import pl.pw.edu.elka.zpbd.wikiReader.freader.FileReader;
import pl.pw.edu.elka.zpbd.wikiReader.freader.Runner;

import javax.xml.crypto.Data;
import java.awt.image.DataBuffer;
import java.io.IOException;


public class Main {


    public static void main(String[] args) throws Exception {
//        mongoInit();
        mySQLInit();
//        mongoTimeTest();
        mySQLTestTime();
    }

    private static void mySQLTestTime() throws InterruptedException {
        DatabaseTest mySQLTimeTest = new MySQLTest();
        mySQLTimeTest.init();
        mySQLTimeTest.runTimeTests();
        mySQLTimeTest.close();
    }


    private static void mySQLInit() throws IOException {
        DocumentCreator dp = new MySQLDocCreator();

        dp.init();
        Runner p = new Runner(dp, FileReader.Mode.PL);
        p.runLoop();
        dp.close();
    }

    private static void mongoTimeTest() throws InterruptedException {
        DatabaseTest mongoTimeTest = new MongoTest();
        mongoTimeTest.init();
        mongoTimeTest.runTimeTests();
        mongoTimeTest.close();
    }

    private static void mongoInit() throws IOException {
        DocumentCreator dp = new MongoDocCreator();

        dp.init();
        Runner p = new Runner(dp, FileReader.Mode.PL);

        p.runLoop();

        dp.close();
    }

}
