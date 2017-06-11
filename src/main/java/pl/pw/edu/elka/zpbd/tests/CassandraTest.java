package pl.pw.edu.elka.zpbd.tests;

import pl.pw.edu.elka.zpbd.databases.cassandra.CassandraDbHandler;
import pl.pw.edu.elka.zpbd.databases.cassandra.CassandraSearchEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rkluz on 11.06.2017.
 */
public class CassandraTest extends DatabaseTest {
    CassandraDbHandler db;


    @Override
    public void select(Integer i) {
        db.getSession().execute("select * from wiki_pages.pl6 where id=" + i.toString() + ";");
    }

    @Override
    public void update(Integer i) {
        db.getSession().execute("update wiki_pages.pl6 set content= \'content updated\' where id=" + i.toString() + ";");
    }

    @Override
    public void delete(Integer i) {
        db.getSession().execute("delete from wiki_pages.pl6 where id=" + i.toString() + ";");
    }

    @Override
    public void init() {
        db = new CassandraDbHandler();
    }

    @Override
    public void close() {
        db.close();
    }

    @Override
    public void runTimeTests() {
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

        System.out.println("select " + rowsNumber.toString() + " " + operationTime.toString());
        startTime = System.currentTimeMillis();

        for (int i = 0; i < rowsNumber; ++i) {
            ct.update(i);
        }

        endTime = System.currentTimeMillis();
        operationTime = endTime - startTime;

        System.out.println("update " + rowsNumber.toString() + " " + operationTime.toString());
        startTime = System.currentTimeMillis();

        for (int i = 0; i < rowsNumber; ++i) {
            ct.delete(i);
        }

        endTime = System.currentTimeMillis();
        operationTime = endTime - startTime;
        System.out.println("delete " + rowsNumber.toString() + " " + operationTime.toString());

        ct.close();
    }

}
