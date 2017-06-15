package pl.pw.edu.elka.zpbd.wikiReader;

import pl.pw.edu.elka.zpbd.tests.DatabaseTest;
import pl.pw.edu.elka.zpbd.tests.RedisTest;


public class RedisMain {


    public static void main(String[] args) throws Exception {
        redisTimeTest();
    }

    private static void redisTimeTest() throws InterruptedException {
        DatabaseTest redisTimeTest = new RedisTest();
        redisTimeTest.init();
        redisTimeTest.runTimeTests();
        redisTimeTest.close();
    }

}
