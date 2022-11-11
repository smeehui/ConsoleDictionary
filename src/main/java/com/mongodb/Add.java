package com.mongodb;

import org.bson.Document;

public class Add {
    static public void newWord(Database db, Document word){
        db.getCollection().insertOne(word);
        System.out.println("Added new word: "+ word.get("word")+" successfully!");
    }
}
