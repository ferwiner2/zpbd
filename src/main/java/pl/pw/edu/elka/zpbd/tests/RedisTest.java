package pl.pw.edu.elka.zpbd.tests;

import com.google.common.collect.ImmutableMap;
import io.redisearch.client.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.Map;

/**
 * Created by Jakub Lorenc on 11.06.17.
 */
public class RedisTest extends DatabaseTest {
    private Client client;
    private String indexName;
    private JedisPool pool;

    @Override
    public void select(final Integer i) {
        final Jedis conn = pool.getResource();
        conn.getClient().sendCommand(Protocol.Command.HGETALL, i.toString()).getOne();
        conn.close();
    }

    @Override
    public void update(final Integer i) {
        Map<String, Object> newValue = ImmutableMap.of(
                "title", "title",
                "text", "text"
        );
        client.replaceDocument(i.toString(), 1.0, newValue);
    }

    @Override
    public void delete(final Integer i) {
        client.deleteDocument(i.toString());
    }

    @Override
    public void init() {
        client = new Client("wiki", "localhost", 6379);
        JedisPoolConfig conf = new JedisPoolConfig();
        conf.setMaxTotal(100);
        conf.setTestOnBorrow(false);
        conf.setTestOnReturn(false);
        conf.setTestOnCreate(false);
        conf.setTestWhileIdle(false);
        conf.setMinEvictableIdleTimeMillis(60000L);
        conf.setTimeBetweenEvictionRunsMillis(30000L);
        conf.setNumTestsPerEvictionRun(-1);
        conf.setFairness(true);
        this.pool = new JedisPool(conf, "localhost", 6379, 500);
        this.indexName = "wiki";
    }

    @Override
    public void close() {
        //do nothing
    }
}
