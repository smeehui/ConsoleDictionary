package com.mongodb;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Word {
    private String word, type, description;
    Word(String word, String type, String description) {
        this.setWord(word);
        this.setType(type);
        this.setDescription(description);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Document toDocument() {
        Document word = new Document("_id", new ObjectId());
        word.append("word", this.getWord()).append("type", "(" + this.getType() + ")").append("description", this.getDescription());
        return word;
    }
}
