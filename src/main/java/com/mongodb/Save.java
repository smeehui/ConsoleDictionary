package com.mongodb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Save {
    public static String[] jsonRecentWordsList = new String[0];
    public static String[] jsonFavoritesList = new String[0];
    public static String jsonFavoritesString;
    private static String jsonRecentWordsString;

    public static void main(String[] args) {
    }
    public static void recentWords(String searchWord) {
        List<String> recentWordsList = new ArrayList<>(Arrays.asList(jsonRecentWordsList));
        recentWordsList.add(searchWord);
        Set<String> setArr = new HashSet<>(recentWordsList);
        jsonRecentWordsList = setArr.toArray(new String[0]);
        try {
            String filePath = new File("").getAbsolutePath();
            filePath.concat("/src/main/save/recentWords.json");
            FileWriter file = new FileWriter("D:/recent_words.json");
            StringBuilder wordListToString = new StringBuilder();
            wordListToString.append("{");
            for (String wordString : jsonRecentWordsList) {
                wordListToString.append(wordString).append(",");
            }
            wordListToString.deleteCharAt(wordListToString.length() - 1);
            wordListToString.append("}");
            jsonRecentWordsString = wordListToString.toString();
            file.write(jsonRecentWordsString);
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void favorites(String word) {
        List<String> favWordsList = new ArrayList<>(Arrays.asList(jsonFavoritesList));
        favWordsList.add(word);
        Set<String> setArr = new HashSet<>(favWordsList);
        jsonFavoritesList = setArr.toArray(new String[0]);
        try {
            String filePath = new File("").getAbsolutePath();
            filePath.concat("/src/main/save/recentWords.json");
            FileWriter file = new FileWriter("D:/favorites.json");
            StringBuilder wordListToString = new StringBuilder();
            wordListToString.append("{");
            for (String wordString : jsonFavoritesList) {
                wordListToString.append(wordString).append(",");
            }
            wordListToString.deleteCharAt(wordListToString.length() - 1);
            wordListToString.append("}");
            jsonFavoritesString = wordListToString.toString();
            file.write(jsonFavoritesString);
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
