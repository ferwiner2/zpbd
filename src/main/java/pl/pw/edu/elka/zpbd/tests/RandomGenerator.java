package pl.pw.edu.elka.zpbd.tests;

import java.util.Random;

/**
 * Created by rkluz on 11.06.2017.
 */
public class RandomGenerator {
    private Random randomGenerator = new Random();


    public int getRandom(){
       return randomGenerator.nextInt(100000);

    }


}
