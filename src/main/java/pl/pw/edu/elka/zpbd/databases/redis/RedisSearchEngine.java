package pl.pw.edu.elka.zpbd.databases.redis;

import io.redisearch.Document;
import io.redisearch.Query;
import io.redisearch.client.Client;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.pw.edu.elka.zpbd.databases.SearchEngineInterface;
import pl.pw.edu.elka.zpbd.databases.SearchResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jakub Lorenc on 10.05.17.
 */
@Component
public class RedisSearchEngine implements SearchEngineInterface {
    private final Client client = new Client("wiki", "localhost", 6379);

    @Override
    public List<SearchResult> search(String query) {
        final String asciiQuery = StringUtils.stripAccents(query);
        final Query redisQuery = new Query(asciiQuery).limit(0,10).setWithScores();
        final io.redisearch.SearchResult result = RedisIterator.iterativeSearch(() -> client.search(redisQuery));
        return result.docs.stream()
                .map(this::asSearchResult)
                .collect(Collectors.toList());
    }

    private SearchResult asSearchResult(Document document){
        return new SearchResult(
                document.getId(),
                document.get("title").toString(),
                document.get("text").toString()
        );
    }

    @Override
    public String getKey() {
        return "redis";
    }

    @Override
    public String getName() {
        return "Redis";
    }
}
