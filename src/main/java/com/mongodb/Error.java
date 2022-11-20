package com.mongodb;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Error {
    private static final Scanner sc = new Scanner(System.in);
    public static void handleInput(Exception e){
        System.out.println(e.getMessage()+ " is not allow, press Enter");
        sc.nextLine();


    }

    public static void fileNotFound(FileNotFoundException e) {

        if (e.getLocalizedMessage().equals("D:\\favorites.json (The system cannot find the file specified)")){
            System.out.println("You have not added any favorites yet, press enter");
            sc.nextLine();
        }
    }
}
