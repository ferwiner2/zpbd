package pl.pw.edu.elka.zpbd.tests;

import java.util.Random;

/**
 * Created by rkluz on 11.06.2017.
 */
public abstract class DatabaseTest {


    public abstract void select(Integer i);

    public abstract void update(Integer i);

    public abstract void delete(Integer i);

    public abstract void init();

    public abstract void close();

    public abstract void runTimeTests();


}
