package pl.pw.edu.elka.zpbd.wikiReader;

import pl.pw.edu.elka.zpbd.databases.SearchResult;
import pl.pw.edu.elka.zpbd.databases.cassandra.CassandraSearchEngine;
import pl.pw.edu.elka.zpbd.wikiReader.documentCreator.CassandraDocCreator;
import pl.pw.edu.elka.zpbd.wikiReader.documentCreator.DocumentCreator;
//import pl.pw.edu.elka.zpbd.wikiReader.documentCreator.RedisDocCreator;
import pl.pw.edu.elka.zpbd.wikiReader.freader.FileReader;
import pl.pw.edu.elka.zpbd.wikiReader.freader.Runner;

import java.util.List;

public class Main {


    public static void main(String[] args) throws Exception {
//        DocumentCreator dp = new CassandraDocCreator();
//
//        dp.init();
//        Runner p = new Runner(dp, FileReader.Mode.PL);
//
//        p.runLoop();
//
//        dp.close();
//        dp.timer.showTime();
        String[] arguments = {"War%", "Wa%", "W%", "%war%", "%wa%", "%w%","A%","li%","%li%","ele%", "%ele%", "do%" ,"%do%","d%","%d%"};
        CassandraSearchEngine cse = new CassandraSearchEngine();
        try {
            for (int i = 0; i < arguments.length; ++i) {

                long startTime = System.currentTimeMillis();
                List<SearchResult> sr = cse.search("select * from wiki_pages.pl where title like \'" + arguments[i] + "\';");
                long endTime = System.currentTimeMillis();
                long time = (endTime - startTime);
                System.out.print(arguments[i]);
                System.out.print(" ");
                System.out.print(sr.size());
                System.out.print(" ");
                System.out.println(time);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


        cse.getCassandraDb().close();
    }
}
