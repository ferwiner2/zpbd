package pl.pw.edu.elka.zpbd.databases.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import pl.pw.edu.elka.zpbd.databases.SearchEngineInterface;
import pl.pw.edu.elka.zpbd.databases.SearchResult;
import pl.pw.edu.elka.zpbd.wikiReader.config.Config;

import java.util.ArrayList;
import java.util.List;

public class MongoSearchEngine implements SearchEngineInterface {
    private MongoCollection<Document> collection;

    public MongoSearchEngine() {
        MongoClient mongo = new MongoClient();
        MongoDatabase database = mongo.getDatabase(Config.getMongoDB());
        this.collection = database.getCollection(Config.getMongoCollection());
    }

    @Override
    public List<SearchResult> search(String query) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("title", query);
        FindIterable<Document> docs = collection.find(whereQuery);

        List<SearchResult> sr = new ArrayList<>();

        for (Document doc : docs) {
            sr.add(new SearchResult(Integer.toString(doc.getInteger("id")), doc.getString("title"), doc.getString("text")));
        }

        return sr;
    }

    @Override
    public String getKey() {
        return "mongo";
    }

    @Override
    public String getName() {
        return "MongoDB";
    }
}
