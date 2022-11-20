package com.mongodb;
import java.util.*;

public class Favorites {
    private String[] favorites = new String[0];

    public String[] getFavorites() {
        return this.favorites;
    }
    public String getFavoriteByIndex(int index) {
        for (int i = 0; i < this.favorites.length; i++) {
            if(i==index-1) return this.getFavorites()[i];
        }
        return "Not found";
    }

    public void deleteWord(int index){
        List<String> wordList = new ArrayList<>(Arrays.asList(this.favorites));
        wordList.remove(index-1);
        this.favorites = wordList.toArray(new String[this.getFavorites().length-1]);
    }
}
