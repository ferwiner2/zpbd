package pl.pw.edu.elka.zpbd.wikiReader;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.util.HashMap;

//mapowanie dla cassandrowego orm'a
@Table(keyspace = "wiki_pages", name = "pl",
        readConsistency = "QUORUM",
        writeConsistency = "QUORUM",
        caseSensitiveKeyspace = false,
        caseSensitiveTable = false)
public class WikiPage {
    @PartitionKey
    private int id;
    private String title;
    @Column(name = "content")
    private String text;

    //wymagane przez cassandowy orm
    public  WikiPage(){}

    public WikiPage(int id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HashMap<String, Object> toMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        map.put("text", text);
        return map;
    }
}
