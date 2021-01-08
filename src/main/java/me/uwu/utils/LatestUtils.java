package me.uwu.utils;

import java.io.*;

public class LatestUtils {
    public static void saveLatest(String username) throws IOException {
        System.out.println("saving: " + username);
        FileWriter myWriter = new FileWriter("latest.l");
        myWriter.write(username);
        myWriter.close();
    }

    public static String getUsername() throws IOException {
        File latest = new File("latest.l");
        if(latest.exists()) {
            BufferedReader brTest = new BufferedReader(new FileReader("latest.l"));
            return brTest.readLine();
        } else {
            create();
            return "";
        }
    }

    private static void create() throws IOException {
            File myObj = new File("latest.l");
            myObj.createNewFile();
    }
}
