package pl.pw.edu.elka.zpbd.databases.redis;

import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Created by Jakub Lorenc on 10.05.17.
 */
public class RedisIterator {
    private static int iterationCounter = 5;

    public static <R> R iterativeSearch(RedisSupplier<R> operation){
        JedisConnectionException exception = null;
        for(int i = 0; i<5; ++i){
            try{
                return operation.get();
            }
            catch (JedisConnectionException e){
                exception = e;
            }
        }
        throw exception;
    }

    @FunctionalInterface
    public static interface RedisSupplier<R>
    {
        R get() throws JedisConnectionException;
    }
}
