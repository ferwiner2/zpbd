package pl.pw.edu.elka.zpbd.tests;

import pl.pw.edu.elka.zpbd.wikiReader.config.RandId;

import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by rkluz on 11.06.2017.
 */
public abstract class DatabaseTest {

    public abstract void select(Integer i);

    public abstract void update(Integer i);

    public abstract void delete(Integer i);

    public abstract void init();

    public abstract void close();

    public void runTimeTests() {
        RandId ids = new RandId();

        long startTime = System.currentTimeMillis();
        for(int id: ids){
            select(id);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("executed " + ids.size() + " selects in " + (endTime - startTime));


        startTime = System.currentTimeMillis();
        for(int id: ids){
            update(id);
        }
        endTime = System.currentTimeMillis();
        System.out.println("executed " + ids.size() + " updates in " + (endTime - startTime));


        startTime = System.currentTimeMillis();
        for(int id: ids){
            delete(id);
        }
        endTime = System.currentTimeMillis();

        System.out.println("executed " + ids.size() + " deletes in " + (endTime - startTime));
    }


}
