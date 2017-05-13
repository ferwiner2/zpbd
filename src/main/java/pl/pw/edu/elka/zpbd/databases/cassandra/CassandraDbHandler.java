package pl.pw.edu.elka.zpbd.databases.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import pl.pw.edu.elka.zpbd.wikiReader.WikiPage;

/**
 * Created by rkluz on 07.05.2017.
 */
public class CassandraDbHandler {
    private final String[] CONTACT_POINTS = {"127.0.0.1"};
    private final int PORT = 9042;

    private Cluster cluster;

    private Session session;
    private MappingManager manager;

    public MappingManager getManager() {
        return manager;
    }

    public Mapper<WikiPage> getMapper() {
        return mapper;
    }

    private Mapper<WikiPage> mapper;


    public CassandraDbHandler() {
        this.connect(CONTACT_POINTS, PORT);
        this.createSchema(session);
        this.manager = new MappingManager(session);
        this.mapper = this.manager.mapper(WikiPage.class);

    }

    private void connect(String[] contactPoints, int port) {
        cluster = Cluster.builder()
                .addContactPoints(contactPoints).withPort(port)
                .build();

        session = cluster.connect();
    }

    private void createSchema(Session session) {
        session.execute("CREATE KEYSPACE IF NOT EXISTS wiki_pages WITH replication " +
                "= {'class':'SimpleStrategy', 'replication_factor':1};");

        session.execute(
                "CREATE TABLE IF NOT EXISTS wiki_pages.pl (" +
                        "id int PRIMARY KEY," +
                        "title text," +
                        "content text" +
                        ");");
    }

    public void close() {
        session.close();
        cluster.close();
    }

    public Session getSession() {
        return session;
    }
}
