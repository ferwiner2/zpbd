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


    @Override
    public void insertDocument(WikiPage page) {
        cassandraDb.getMapper().save(page);
    }


    @Override
    public void init() {
        cassandraDb = new CassandraDbHandler();
        timer.start();
    }

    @Override
    public void close() {
        cassandraDb.close();
        timer.stop();
    }
}
