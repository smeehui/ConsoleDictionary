package com.mongodb;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Database {
    private MongoDatabase db;
    private MongoCollection<Document> collection;


    public Database (MongoDatabase db, MongoCollection<Document> collection) {
            this.setDb(db);
            this.setCollection(collection);
    }

    public void setDb(MongoDatabase db) {
        this.db = db;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public void setCollection(MongoCollection collection) {
        this.collection = collection;
    }


}