package pl.pw.edu.elka.zpbd.wikiReader;

import pl.pw.edu.elka.zpbd.tests.CassandraTest;
import pl.pw.edu.elka.zpbd.tests.DatabaseTest;
import pl.pw.edu.elka.zpbd.tests.RandomGenerator;
//import pl.pw.edu.elka.zpbd.wikiReader.documentCreator.RedisDocCreator;


public class Main {


    public static void main(String[] args) throws Exception {


        DatabaseTest ct = new CassandraTest();
        ct.init();
        RandomGenerator r = new RandomGenerator();
        Integer rowsNumber = r.getRandom();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < rowsNumber; ++i) {
            ct.select(i);
        }

        long endTime = System.currentTimeMillis();

        Long operationTime = endTime - startTime;

        System.out.println("seelct " + rowsNumber.toString() + " " + operationTime.toString() );
        startTime = System.currentTimeMillis();

        for (int i = 0; i < rowsNumber; ++i) {
            ct.update(i);
        }

        endTime = System.currentTimeMillis();
        operationTime = endTime - startTime;

        System.out.println("update " + rowsNumber.toString() + " " + operationTime.toString() );
        startTime = System.currentTimeMillis();

        for (int i = 0; i < rowsNumber; ++i) {
            ct.delete(i);
        }

        endTime = System.currentTimeMillis();
        operationTime = endTime - startTime;
        System.out.println("delete " + rowsNumber.toString() + " " + operationTime.toString() );

        ct.close();
    }
}
