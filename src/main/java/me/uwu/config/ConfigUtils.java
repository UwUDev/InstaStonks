package me.uwu.config;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ConfigUtils {

    public static void setupConfigs(){
        Path path = Paths.get("config");

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println("Config directory is created!");

            } catch (IOException e) {
                System.err.println("Failed to create directory!" + e.getMessage());
            }
        }
    }

    public static ArrayList<String> getConfigsName(){
        ArrayList<String> result = new ArrayList<String>();
        try {
            File f = new File("config");

            FilenameFilter filter = (f1, name) -> name.endsWith(".OwO");

            File[] files = f.listFiles(filter);

            for (File file : files) {
                result.add(file.getName().replaceFirst("[.][^.]+$", ""));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        for (String oof : result ){
            System.out.println(oof);
        }
        return result;
    }

}
