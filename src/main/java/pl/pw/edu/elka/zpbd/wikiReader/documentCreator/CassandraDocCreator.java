package pl.pw.edu.elka.zpbd.wikiReader.documentCreator;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import pl.pw.edu.elka.zpbd.databases.cassandra.CassandraDbHandler;
import pl.pw.edu.elka.zpbd.wikiReader.WikiPage;

/**
 * Created by rkluz on 07.05.2017.
 */
public class CassandraDocCreator extends DocumentCreator {
    private CassandraDbHandler cassandraDb;
    private MappingManager manager;
    private Mapper<WikiPage> mapper;

    public CassandraDocCreator() {
        cassandraDb = new CassandraDbHandler();
    }

    @Override
    public void insertDocument(WikiPage page) {
        mapper.save(page);
    }

    public WikiPage selectDocument(int id){
        return mapper.get(id);
    }

    public void deleteDocument(int id){
        mapper.delete(id);
    }

    @Override
    public void init() {
        manager = new MappingManager(cassandraDb.getSession());
        mapper = manager.mapper(WikiPage.class);
        timer.start();
    }

    @Override
    public void close() {
        cassandraDb.close();
        timer.stop();
    }
}
