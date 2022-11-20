package com.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ParseException {
//        Connect to Mongo Database
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {
            MongoDatabase db = mongoClient.getDatabase("dictionary");
            MongoCollection<Document> clt = db.getCollection("dict");
            Database dictionaryDb = new Database(clt);
            Search search = new Search();
            Favorites fav = new Favorites();
            int selection = 0;
//            Catch Input Exception.
            boolean hasErrors;
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
                                if (searchInp.equals("1")) handleAddToFavorites(search);
                                else {
                                    String word = searchInp.substring(0, 1).toUpperCase() + searchInp.substring(1);
                                    String searchString = Search.byWord(dictionaryDb.getCollection(), word);
                                    handleShowResult(searchString, search, word);
                                }

                            } while (true);
                            break;
//                            Show search words
                        case 2:
                            do {
                                View.recentWordView();
                                int choice ;
                                choice = Integer.parseInt(sc.nextLine());
                                if (choice == 0) break;
//                                HandleInput
//                                handleRecentSearch(dictionaryDb.getCollection(), choice, search, fav);
                                handleRecentInput(search, choice);
                            } while (true);
                            break;
//                            Show favorites
                        case 3:
                            do {
                                View.favoritesView();
                                String choice = sc.nextLine();
                                if (choice.equals("0")) break;
                                if (choice.equals("*")) handleRemoveFavorite(fav);
                                else {
                                    View.clear();
                                    handleFavShow(Integer.parseInt(choice));
                                }
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
                } catch (FileNotFoundException e) {
                    hasErrors = true;
                    Error.fileNotFound(e);
                } catch (InputMismatchException | NumberFormatException e) {
                    hasErrors = true;
                    Error.handleInput(e);
                }
            }
//            Reselect menuView
            while (selection != 0 || hasErrors);
        }

    }

    //            Add to favorites
    private static void handleAddToFavorites(Search search) {
        Save.favorites(search.getCurrentWord());
        System.out.println("Added to favorites, press enter!");
        sc.nextLine();

    }

    //            Display favorites word
    private static void handleFavShow(int choice) throws ParseException, IOException {
        System.out.println(Read.favoriteWordByIndex(choice));
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
    private static void handleShowResult(String searchString, Search search, String word) {
        if (searchString.equals("Not found")) System.out.println(searchString);
        else {
            System.out.println(searchString);
            System.out.println("1. Add to favorite\n0. Return");
            search.setCurrentWord(word, searchString);
            search.setRecentWords(word);
            Save.recentWords(search.getCurrentWord());
        }
    }

    //            Display recent words
    private static void handleRecentInput(Search search, int choice) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        int selection;
        do {
            System.out.println(Read.recentWordsListByIndex(choice));
            System.out.println("1. Add to favorite\n0. Return");
            selection = sc.nextInt();
            if (selection == 1) {
                search.setCurrentWord(Read.wordsList[choice - 1].toString(), Read.recentWordsListByIndex(choice));
                Save.favorites(search.getCurrentWord());
                System.out.println("Added "+Read.wordsList[choice - 1].toString() +" to favorites!");
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