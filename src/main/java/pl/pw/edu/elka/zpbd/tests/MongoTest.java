package pl.pw.edu.elka.zpbd.tests;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import pl.pw.edu.elka.zpbd.wikiReader.config.Config;


public class MongoTest extends DatabaseTest {
    private MongoCollection<Document> collection;

    @Override
    public void init() {
        MongoClient mongo = new MongoClient();
        MongoDatabase database = mongo.getDatabase(Config.getMongoDB());
        this.collection = database.getCollection(Config.getMongoCollection());
    }

    @Override
    public void select(Integer i) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("id", i);
        collection.find(whereQuery);
    }

    @Override
    public void update(Integer i) {
        collection.findOneAndUpdate(new BasicDBObject("id:", i), new BasicDBObject("$inc", new BasicDBObject("text", "text updated")));
    }

    @Override
    public void delete(Integer i) {
        collection.findOneAndDelete(new BasicDBObject().append("id", i));
    }


    @Override
    public void close() {
    }

}
