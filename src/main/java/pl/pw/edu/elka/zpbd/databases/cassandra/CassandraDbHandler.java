package pl.pw.edu.elka.zpbd.databases.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * Created by rkluz on 07.05.2017.
 */
public class CassandraDbHandler {
    private final String[] CONTACT_POINTS = {"127.0.0.1"};
    private final int PORT = 9042;

    private Cluster cluster;

    private Session session;


    public CassandraDbHandler() {
        this.connect(CONTACT_POINTS, PORT);
        this.createSchema(session);
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
