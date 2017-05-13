package pl.pw.edu.elka.zpbd.databases.cassandra;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import pl.pw.edu.elka.zpbd.databases.SearchEngineInterface;
import pl.pw.edu.elka.zpbd.databases.SearchResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rkluz on 13.05.2017.
 */
public class CassandraSearchEngine implements SearchEngineInterface {
    private final CassandraDbHandler cassandraDb = new CassandraDbHandler();
    @Override
    public List<SearchResult> search(String query) {
        ResultSet rs = cassandraDb.getSession().execute(query);
        List<SearchResult> sr = new ArrayList<>();

        for (Row row : rs) {
            sr.add(new SearchResult(Integer.toString(row.getInt("id")),row.getString("title"),row.getString("content")));
        }

        return sr;
    }

    @Override
    public String getKey() {
        return "cassandra";
    }

    @Override
    public String getName() {
        return "Cassandra";
    }
}
