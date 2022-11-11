package com.mongodb;

import java.util.*;

public class Favorites {
    private String[] favorites = new String[0];

    public void setFavorites(String word) {
        List<String> wordList = new ArrayList<>(Arrays.asList(this.favorites));
        wordList.add(word);
        Set<String> setArr = new HashSet<>(wordList);
        this.favorites = setArr.toArray(new String[0]);
    }

    public String[] getFavorites() {
        return this.favorites;
    }
    public String getFavoriteByIndex(int index) {
        for (int i = 0; i < this.favorites.length; i++) {
            if(i==index) return this.getFavorites()[i];
        }
        return "Not found";
    }
    public String favoritesView() {
        if(this.favorites.length==0) return "You haven't added any favorite word yet!";
        StringBuilder view = new StringBuilder();
        for (int i = 0; i < this.getFavorites().length; i++) {
            view.append(i + 1).append(". ").append(this.getFavorites()[i]).append("\n");
        }
        return view.toString();
    }
    public void deleteWord(int index){
        List<String> wordList = new ArrayList<>(Arrays.asList(this.favorites));
        wordList.remove(index-1);
        this.favorites = wordList.toArray(new String[this.getFavorites().length-1]);
    }
}
