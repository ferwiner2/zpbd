package pl.pw.edu.elka.zpbd.wikiReader.documentCreator;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import pl.pw.edu.elka.zpbd.wikiReader.WikiPage;
import pl.pw.edu.elka.zpbd.wikiReader.config.Config;


public class MongoDocCreator extends DocumentCreator {
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MongoDocCreator() {
        super();

        //by default IP = localhost and port = 27017
        MongoClient mongo = new MongoClient();

        this.database = mongo.getDatabase(Config.getMongoDB());
    }

    @Override
    public void insertDocument(WikiPage page) {
        Document doc = new Document();
        doc.putAll(page.toMap());
        this.collection.insertOne(doc);
    }

    @Override
    public void init() {
        createCollection();
        turnOffDebug();
        timer.start();
    }

    @Override
    public void close() {
        timer.stop();
        timer.showTime();
//        long startTime = System.currentTimeMillis();
//        createIndexes();
//        System.out.println("Indexing time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private void turnOffDebug() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
    }

    private void createCollection() {
        MongoCollection collection = this.database.getCollection(Config.getMongoCollection());
        collection.drop();
        this.database.createCollection(Config.getMongoCollection());
        this.collection = this.database.getCollection(Config.getMongoCollection());
    }

    private void createIndexes() {
        this.collection.createIndex(new BasicDBObject("id", 1));

        BasicDBObject index = new BasicDBObject();
        index.put("title", "text");
        index.put("text", "text");
        BasicDBObject weights = new BasicDBObject("title", 10).append("text", 5);
        IndexOptions options = new IndexOptions();
        options.weights(weights);
        collection.createIndex(index, options);
    }
}
