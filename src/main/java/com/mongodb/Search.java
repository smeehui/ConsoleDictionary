package com.mongodb;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class Search {

    private String[] recentWords = new String[0];
    private String curentWord;

    static public String byWord(MongoCollection collection, String word) {
        List<Document> words = (List<Document>) collection.find(eq("word", word)).into(new ArrayList<>());
        int index = 0;
        StringBuilder wordsString = new StringBuilder();
        for (Document w : words) {
            index++;
            wordsString.append(index).append(". ").append(w.get("word")).append("\n\t").append(w.get("type")).append("\n").append(w.get("description")).append("\n");
        }
        if(words.isEmpty()) return "Not found";
        return wordsString.toString();
    }

    public String getCurentWord() {
        return this.curentWord;
    }

    public void setCurentWord(String curentWord) {
        this.curentWord = curentWord;
    }

    public String[] getRecentWords() {
        return this.recentWords;
    }

    public void setRecentWords(String word) {
        List<String> wordList = new ArrayList<>(Arrays.asList(this.recentWords));
        wordList.add(word);
        Set<String> setArr = new HashSet<>(wordList);
        this.recentWords = setArr.toArray(new String[setArr.size()]);
    }

    public String reviewWord(int choice) {
        for (int i = 0; i <= this.getRecentWords().length; i++) {
            if (i == choice - 1) return this.getRecentWords()[i];
        }
        return "Not found";
    }

    public String recentWordsView() {
        if(this.recentWords.length==0) return "You haven't seached any word yet!";
        StringBuilder view = new StringBuilder();
        for (int i = 0; i < this.getRecentWords().length; i++) {
            view.append(i + 1).append(". ").append(this.getRecentWords()[i]).append("\n");
        }
        return view.toString();
    }
}