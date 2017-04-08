package pl.pw.edu.elka.zpbd.databases.dummy;

import org.springframework.stereotype.Component;
import pl.pw.edu.elka.zpbd.databases.SearchEngineInterface;
import pl.pw.edu.elka.zpbd.databases.SearchResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jakub Lorenc on 08.04.17.
 */
@Component
public class AnotherDummySearchEngine implements SearchEngineInterface {
    @Override
    public List<SearchResult> search(String query) {
        return Collections.emptyList();
    }

    @Override
    public String getKey() {
        return "dummy2";
    }

    @Override
    public String getName() {
        return "Dummy engine2";
    }
}
