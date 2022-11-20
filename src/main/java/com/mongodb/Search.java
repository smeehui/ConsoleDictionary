package com.mongodb;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.json.simple.JSONObject;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class Search {

    private String[] recentWords = new String[0];
    private String currentWord;

    static public String byWord(MongoCollection collection, String word) {
        List<Document> words = (List<Document>) collection.find(eq("word", word)).into(new ArrayList<>());
        int index = 0;
        StringBuilder wordsString = new StringBuilder();
        for (Document w : words) {
            index++;
            wordsString.append(index).append(". ").append(w.get("word")).append("\n\t").append(w.get("type")).append("\n").append(w.get("description")).append("\n");
        }
        if (words.isEmpty()) return "Not found";
        return wordsString.toString();
    }

    public String getCurrentWord() {
        return this.currentWord;
    }

    public void setCurrentWord(String currentWord, String description) {
        JSONObject word = new JSONObject();
        word.put(currentWord, description);
        this.currentWord = word.toString().substring(1, word.toJSONString().length() - 1);
    }



    public void setRecentWords(String word) {
        List<String> wordList = new ArrayList<>(Arrays.asList(this.recentWords));
        wordList.add(word);
        Set<String> setArr = new HashSet<>(wordList);
        this.recentWords = setArr.toArray(new String[0]);
    }
}