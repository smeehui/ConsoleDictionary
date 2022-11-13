package com.mongodb;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONObject;
public class Save {
    public static String[] jsonRecentWordsList =new String[0];
    public static void main(String args[]) {
    }

    public static void recentWords(Search recent, String searchString) {
        JSONObject jsonRecentWords = new JSONObject();
        jsonRecentWords.put(recent.getCurentWord(), searchString);
        List<String> recentWordsList = new ArrayList<>(Arrays.asList(jsonRecentWordsList));
        recentWordsList.add(jsonRecentWords.toJSONString());
        Set<String> setArr = new HashSet<>(recentWordsList);
        jsonRecentWordsList = setArr.toArray(new String[0]);
        try {
            String filePath = new File("").getAbsolutePath();
            filePath.concat("/src/main/save/recentWords.json");
            System.out.println(filePath);
            FileWriter file = new FileWriter(filePath);
            file.write(Arrays.toString(jsonRecentWordsList));
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("JSON file created: "+jsonRecentWords);
    }
}
