package me.uwu.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tags {

    public static String getTagsString() throws IOException {
        BufferedReader brTest = new BufferedReader(new FileReader("../tags.txt"));
        return brTest.readLine();
    }

    public static List<String> getTags() throws IOException {
        String all = getTagsString().replace("#", "");
        String[] str = all.split(" ");

        List<String> tags = Arrays.asList(str);

        System.out.println("Tags loaded :");
        for(String s: tags){
            System.out.println(s);
        }

        System.out.println("Total : " + getQuantity());

        return tags;
    }

    public static int getQuantity() throws IOException {
        String input = getTagsString();

        int index = input.indexOf("#");
        int count = 0;
        while (index != -1) {
            count++;
            input = input.substring(index + 1);
            index = input.indexOf("#");
        }
       return count;
    }

}
