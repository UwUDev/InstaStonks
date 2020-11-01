package me.uwu.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ConfigUtils {

    public static void setupConfigs() throws IOException {
        Path path = Paths.get("config");

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println("Config directory is created!");

            } catch (IOException e) {
                System.err.println("Failed to create directory!" + e.getMessage());
            }
        }

        File file = new File("config/Base.OwO");
        Files.delete(file.toPath());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ConfigObj cfg = getBaseConfig();
        String json = gson.toJson(cfg);

        try (FileWriter writer = new FileWriter("config/Base.OwO")) {
            gson.toJson(cfg, writer);
        } catch (IOException e) {
            e.printStackTrace();
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

    private static ConfigObj getBaseConfig() {

        ConfigObj cfg = new ConfigObj();

        cfg.always_working = true;
        cfg.auto_commments = false;
        cfg.auto_follows = true;
        cfg.auto_likes = true;
        cfg.randomize_tags = true;
        cfg.comments = new String[]{"nope", "nope"};
        cfg.max_comments_per_day = 99999;
        cfg.max_likes_per_day = 99999;
        cfg.max_subs_per_day = 99999;
        cfg.method_delays = new int[]{2500, 4000, 4000, 4000, 18000, 600000};
        cfg.method_type = 1;
        cfg.min_max_subs = new int[]{50,250};
        cfg.start_stop_time = new String[]{"00:00", "00:00"};
        cfg.tags = new String[]{"nature", "art", "instagram", "newyrok", "nail", "cat", "explore", "funny", "food", "dog", "selfie", "fitness", "summer", "cute"};
        cfg.tags_to_avoid = new String[]{"nude", "porn"};
        cfg.whitelist = new String[]{"github", "jetbrains"};

        return cfg;

    }

}
