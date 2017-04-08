package pl.pw.edu.elka.zpbd.databases;

import java.util.List;

/**
 * Created by Jakub Lorenc on 08.04.17.
 */
public interface SearchEngineInterface {
    List<SearchResult> search(String query);
    String getKey();
    String getName();
}
