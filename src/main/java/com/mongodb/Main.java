package com.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
//        Connect to Mongo Database
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {
            MongoDatabase db = mongoClient.getDatabase("dictionary");
            MongoCollection<Document> clt = db.getCollection("dict");
            Database dictionaryDb = new Database(db, clt);
            Search recent = new Search();
            Favorites fav = new Favorites();
            int selection = 0;
//            Catch Input Exception.
            boolean hasErrors = true;
            do {
                hasErrors = false;
                try {
//                    Main Menu.
                    View.clear();
                    View.menuView();
                    selection = Integer.parseInt(sc.nextLine());
                    switch (selection) {
//                        Search by word
                        case 1:
                            do {
                                View.searchByWordView();
                                String searchInp = sc.nextLine();
//                                Exit
                                if (searchInp.equals("0"))
                                    break;
//                                Handle add to favorites
                                if (searchInp.equals("1"))handleAddToFavorites(fav,dictionaryDb,recent);
                                else {
                                    String word = searchInp.substring(0, 1).toUpperCase() + searchInp.substring(1);
                                    String searchString = Search.byWord(dictionaryDb.getCollection(), word);
                                    handleShowResult(searchString, recent, word);
                                }

                            } while (true);
                            break;
//                            Show recent words
                        case 2:
                            do {
                                View.clear();
                                View.recentWordView(recent);
                                int choice = 0;
                                choice = Integer.parseInt(sc.nextLine());
                                if (choice == 0) break;
//                                HandleInput
                                handleRecentSearch(dictionaryDb.getCollection(), choice, recent,fav);
                            } while (true);
                            break;
//                            Show favorites
                        case 3:
                            do {
                                View.favoritesView(fav);
                                String choice = sc.nextLine();
                                if (choice.equals("0")) break;
                                if (choice.equals("*")) handleRemoveFavorite(fav);
                                else
                                    handleFavSearch(dictionaryDb.getCollection(), Integer.parseInt(choice),fav);
                            } while (true);
                            break;
                        case 5:
                            do {
                                View.clear();
                                View.managementView();
                                int input = Integer.parseInt(sc.nextLine());
                                if (input == 0) break;
                                switch (input) {
                                    case 1:
                                        handleAddNewWord(dictionaryDb);
                                        break;
                                }
                            } while (true);
                            break;
                    }
                } catch (Exception e) {
                    hasErrors = true;
//                    Display the error message
                    Error.handleInput(e);
                }
            }
//            Reselect menuView
            while (selection != 0 || hasErrors);
        }

    }
//            Add to favorites
    private static void handleAddToFavorites(Favorites fav, Database dictionaryDb, Search recent) {
            fav.setFavorites(recent.getCurentWord());
            System.out.println("Added to favorites, press enter!");
            sc.nextLine();
    }
//            Display favorites word
    private static void handleFavSearch(MongoCollection<Document> collection, int choice, Favorites fav) {
        System.out.println(Search.byWord(collection, fav.getFavoriteByIndex(choice)));
    }
//            Remove favorites word
    private static void handleRemoveFavorite(Favorites fav) {
        System.out.println("Enter index of favorite word");
        Scanner sc = new Scanner(System.in);
        int index = Integer.parseInt(sc.nextLine());
        boolean isConfirm = false;
        System.out.println("Are you sure to delete " + fav.getFavoriteByIndex(index));
        System.out.println("1. Yes" + "\n2. No");
        int confirm = Integer.parseInt(sc.nextLine());
        if (confirm == 1) isConfirm = true;
        if (isConfirm) fav.deleteWord(index);
    }

//             Display search result
    private static void handleShowResult(String searchString, Search recent, String word) {
        if (searchString.equals("Not found")) System.out.println(searchString);
        else {
            System.out.println(searchString);
            System.out.println("1. Add to favorite\n0. Return");
            recent.setCurentWord(word);
            recent.setRecentWords(word);
            Save.recentWords(recent, searchString);
        }
    }
//            Display recent words
    private static void handleRecentSearch(MongoCollection<Document> collection, int choice, Search recent, Favorites fav) {
        Scanner sc = new Scanner(System.in);
        int selection;
        do {
            System.out.println(Search.byWord(collection, recent.reviewWord(choice)));
            System.out.println("1. Add to faovrite\n0. Return");
            selection = sc.nextInt();
            if (selection == 0) break;
            if (selection == 1) {
                View.clear();
                fav.setFavorites(recent.reviewWord(choice));
                System.out.println("Added to favorites");
                sc.nextLine();
                break;
            }

        } while (true);

    }
//            Add new words to database
    private static void handleAddNewWord(Database dictionaryDb) {
        System.out.println("Enter word:");
        String input = sc.nextLine();
        String wordStr = input.substring(0, 1).toUpperCase() + input.substring(1);
        System.out.println("Enter type of word:");
        String type = sc.nextLine();
        System.out.println("Enter description:");
        String description = sc.nextLine();
        Word newWord = new Word(wordStr, type, description);
        Add.newWord(dictionaryDb, newWord.toDocument());
    }
}