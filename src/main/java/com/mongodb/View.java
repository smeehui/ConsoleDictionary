package com.mongodb;
public class View {
    static void searchByWordView() {
        System.out.println("Search by Word");
        System.out.print("Enter word (0. Return)");
    }
    public static void clear(){
        for(int clear = 0; clear < 1000; clear++) {
            System.out.println("\b") ;
        }
    }
    static void menuView() {
        System.out.println("Menu selection:");
        System.out.println("1. Search by word:");
        System.out.println("2. Recent words");
        System.out.println("3. Favorites");
        System.out.println("5. Dictionary management:");
        System.out.println("0. Exit");
    }
    public static void managementView() {
        System.out.println("Management");
        System.out.println("1. Add new word:");
        System.out.println("2. Edit word");
        System.out.println("3. Add to favorite.");
        System.out.println("0. Return");
    }
    public static void recentWordView(Search recent) {
        System.out.println("Recently searched words:");
        System.out.println(recent.recentWordsView());
        System.out.println("Enter number to review word \n0. Return");
    }

    public static void favoritesView(Favorites fav) {
        System.out.println("Favorites words:");
        System.out.println(fav.favoritesView());
        System.out.println("Enter number to review word \n 0. Return\n*. Delete by index:");
    }
}
