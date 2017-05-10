package pl.pw.edu.elka.zpbd.databases;

/**
 * Created by Jakub Lorenc on 09.04.17.
 */
public class SearchResult {
    private final String id;
    private final String name;
    private final String text;

    public SearchResult(final String id, final String name, final String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getText() {
        return text;
    }
}
