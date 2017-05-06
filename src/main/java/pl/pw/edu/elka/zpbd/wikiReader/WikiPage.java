package pl.pw.edu.elka.zpbd.wikiReader;

import java.util.HashMap;

public class WikiPage {
    private int id;
    private String title;
    private String text;

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
