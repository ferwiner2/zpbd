package pl.pw.edu.elka.zpbd.databases.dummy;

import org.springframework.stereotype.Component;
import pl.pw.edu.elka.zpbd.databases.SearchEngineInterface;
import pl.pw.edu.elka.zpbd.databases.SearchEngines;
import pl.pw.edu.elka.zpbd.databases.SearchResult;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jakub Lorenc on 08.04.17.
 */
@Component
public class DummySearchEngine implements SearchEngineInterface {
    @Override
    public List<SearchResult> search(String query) {
        return Arrays.asList(
                new SearchResult("AAAA"),
                new SearchResult("bbbb")
        );
    }

    @Override
    public String getKey() {
        return "dummy";
    }

    @Override
    public String getName() {
        return "Dummy engine";
    }
}
