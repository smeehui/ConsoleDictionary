package com.mongodb;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class Database {
    private MongoCollection<Document> collection;


    public Database (MongoCollection<Document> collection) {
            this.setDb();
            this.setCollection(collection);
    }

    public void setDb() {
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public void setCollection(MongoCollection collection) {
        this.collection = collection;
    }


}