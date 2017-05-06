package pl.pw.edu.elka.zpbd.wikiReader.documentCreator;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import org.bson.Document;
import org.bson.UuidRepresentation;
import org.bson.codecs.UuidCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
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

        createCollection();
    }

    private void createCollection() {
        MongoCollection collection = this.database.getCollection(Config.getMongoCollection());
        collection.drop();
        this.database.createCollection(Config.getMongoCollection(), new CreateCollectionOptions().autoIndex(true));
        this.collection = this.database.getCollection(Config.getMongoCollection());
        registerCodecClass();
    }

    private void registerCodecClass() {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(CodecRegistries.fromCodecs(new UuidCodec(UuidRepresentation.STANDARD)),
                MongoClient.getDefaultCodecRegistry());

    }

    @Override
    public void insertDocument(WikiPage page) {
        Document doc = new Document();
        doc.putAll(page.toMap());
        this.collection.insertOne(doc);
    }
}
