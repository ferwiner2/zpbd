package pl.pw.edu.elka.zpbd.tests;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import pl.pw.edu.elka.zpbd.wikiReader.config.Config;

import java.util.logging.Level;
import java.util.logging.Logger;


public class MongoTest extends DatabaseTest {
    private MongoCollection<Document> collection;

    @Override
    public void init() {
        MongoClient mongo = new MongoClient();
        MongoDatabase database = mongo.getDatabase(Config.getMongoDB());
        this.collection = database.getCollection(Config.getMongoCollection());
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.OFF);
    }

    @Override
    public void select(Integer i) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("id", i);
        Document doc = collection.find(whereQuery).first();
    }

    @Override
    public void update(Integer i) {
        Bson filter = Filters.eq("id", i);
        Bson updates = Updates.set("text", "text updated vol 2");
        collection.findOneAndUpdate(filter, updates);
    }

    @Override
    public void delete(Integer i) {
        collection.findOneAndDelete(new BasicDBObject().append("id", i));
    }


    @Override
    public void close() {
    }

}
