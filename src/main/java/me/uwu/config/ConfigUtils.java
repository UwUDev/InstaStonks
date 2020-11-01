package me.uwu.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

}
