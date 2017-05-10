package pl.pw.edu.elka.zpbd.wikiReader.documentCreator;

import com.google.common.collect.ImmutableMap;
import io.redisearch.Schema;
import io.redisearch.client.Client;
import pl.pw.edu.elka.zpbd.databases.redis.RedisIterator;
import pl.pw.edu.elka.zpbd.wikiReader.WikiPage;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.Map;

/**
 * Created by Jakub Lorenc on 09.05.17.
 */
public class RedisDocCreator extends DocumentCreator {
    Client client;

    @Override
    public void insertDocument(WikiPage page) {
        final Map<String, Object> document = ImmutableMap.<String, Object>builder()
                .put("title", page.getTitle())
                .put("text", page.getText())
                .build();
        try {
            RedisIterator.iterativeSearch(() -> client.addDocument(Integer.toString(page.getId()), document));
        }
        catch(JedisConnectionException e){
            System.err.println("zapis strony " + page.getId() + " nie powiódł się");
        }
    }

    @Override
    public void init() {
        client = new Client("wiki", "localhost", 6379);
        if(isIndexAlreadyCreated()){
            client.dropIndex();
        }
        final Schema schema = new Schema()
                .addTextField("title", 5.0)
                .addTextField("text", 1.0);
        client.createIndex(schema, Client.IndexOptions.Default());
    }

    private boolean isIndexAlreadyCreated() {
        try {
            client.getInfo();
        }
        catch (JedisDataException e){
            return false;
        }
        return true;
    }

    @Override
    public void close() {
        //do nothing
    }
}
