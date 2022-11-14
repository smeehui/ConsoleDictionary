package com.mongodb;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Read {
    public static Object[] wordsList;
    public static void favoritesList() throws IOException, ParseException {
        FileReader favoritesFile = new FileReader("D:/favorites.json");
        JSONParser parser = new JSONParser();
        JSONObject wordsObj = (JSONObject) parser.parse(favoritesFile);
        Object[] wordNames = wordsObj.keySet().toArray();
        for (int i = 0; i < wordNames.length; i++) {
            System.out.println((i + 1) + ". " + wordNames[i].toString());
        }
    }

    public static String favoriteWordByIndex(int index) throws IOException, ParseException {
        FileReader favoritesFile = new FileReader("D:/favorites.json");
        JSONParser parser = new JSONParser();
        JSONObject wordsObj = (JSONObject) parser.parse(favoritesFile);
        Object[] wordNames = wordsObj.keySet().toArray();
        for (int i = 0; i < wordNames.length; i++) {
            if (i == index - 1) return wordsObj.get(wordNames[i]).toString();
        }
        return "Index not found";
    }
    public static void recentWordsList() throws IOException, ParseException {
        FileReader recentWordsFile = new FileReader("D:/recent_words.json");
        JSONParser parser = new JSONParser();
        JSONObject wordsObj = (JSONObject) parser.parse(recentWordsFile);
        Object[] wordNames = wordsObj.keySet().toArray();
        wordsList = wordNames;
        for (int i = 0; i < wordNames.length; i++) {
            System.out.println((i + 1) + ". " + wordNames[i].toString());
        }
    }

    public static String recentWordsListByIndex(int index) throws IOException, ParseException {
        FileReader recentWordsFile = new FileReader("D:/recent_words.json");
        JSONParser parser = new JSONParser();
        JSONObject wordsObj = (JSONObject) parser.parse(recentWordsFile);
        Object[] wordNames = wordsObj.keySet().toArray();
        for (int i = 0; i < wordNames.length; i++) {
            if (i == index - 1) return wordsObj.get(wordNames[i]).toString();
        }
        return "Index not found";
    }

}
