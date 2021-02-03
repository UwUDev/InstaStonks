package me.uwu.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        try (FileWriter writer = new FileWriter("config/Base.OwO")) {
            gson.toJson(cfg, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getConfigsName(){
        ArrayList<String> result = new ArrayList<>();
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

    public static void setConfig(String name){
        String path = "config/" + name +".OwO";
        String content = null;

        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            content = lines.collect(Collectors.joining(System.lineSeparator()));
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject obj = new JSONObject(content);
        JSONArray jsonArray = obj.getJSONArray("method_delays");
        ArrayList<Integer> i = new ArrayList<>();

        int oof = 0;
        for (Object ignored : jsonArray){
            i.add(jsonArray.getInt(oof));
        }
        //IGStonks.delays = i.toArray().;
    }

    private static ConfigObj getBaseConfig() {
        ConfigObj cfg = new ConfigObj();

        cfg.always_working = true;
        cfg.auto_commments = false;
        cfg.auto_sub = true;
        cfg.auto_follows = true;
        cfg.auto_likes = true;
        cfg.randomize_tags = true;
        cfg.comments = new String[]{"nope", "nope"};
        cfg.max_comments_per_day = 99999;
        cfg.max_likes_per_day = 99999;
        cfg.max_subs_per_day = 99999;
        cfg.delays = new int[]{2500, 4000, 4000, 4000, 18000, 600000};
        cfg.method_type = 1;
        cfg.min_max_subs = new int[]{50,250};
        cfg.start_stop_time = new String[]{"00:00", "23:59"};
        cfg.tags = new String[]{"nature", "art", "instagram", "newyork", "nail", "cat", "explore", "funny", "food", "dog", "selfie", "fitness", "summer", "cute"};
        cfg.tags_to_avoid = new String[]{"nude", "porn"};
        cfg.whitelist = new String[]{"github", "jetbrains"};

        return cfg;
    }

    public static ConfigObj getConfig(String configName){
        ConfigObj cfg = new ConfigObj();

        String path = "config/" + configName +".OwO";
        String content = null;

        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            content = lines.collect(Collectors.joining(System.lineSeparator()));
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject obj = new JSONObject(content);
        JSONArray jsonArray = obj.getJSONArray("delays");
        ArrayList<Integer> i = new ArrayList<>();

        int oof = 0;
        for (Object ignored : jsonArray){
            i.add(jsonArray.getInt(oof));
            oof++;
        }
        int[] delays = {i.get(0), i.get(1), i.get(2), i.get(3), i.get(4), i.get(5)};

        JSONArray jsonArrayComments = obj.getJSONArray("comments");
        ArrayList<String> i2 = new ArrayList<>();
        int oof2 = 0;
        for (Object ignored : jsonArrayComments){
            i2.add(jsonArrayComments.getString(oof2));
            oof2++;
        }
        String[] comments = i2.toArray(new String[0]);

        JSONArray jsonArray3 = obj.getJSONArray("min_max_subs");
        ArrayList<Integer> i3 = new ArrayList<>();
        int oof3 = 0;
        for (Object ignored : jsonArray3){
            i3.add(jsonArray3.getInt(oof3));
            oof3++;
        }
        int[] min_max_subs = {i3.get(0), i3.get(1)};

        JSONArray jsonArrayTags = obj.getJSONArray("tags");
        ArrayList<String> i5 = new ArrayList<>();
        int oof5 = 0;
        for (Object ignored : jsonArrayTags){
            i5.add(jsonArrayTags.getString(oof5));
            oof5++;
        }
        String[] tags = i5.toArray(new String[0]);

        JSONArray jsonArray4 = obj.getJSONArray("start_stop_time");
        ArrayList<String> i4 = new ArrayList<>();
        int oof4 = 0;
        for (Object ignored : jsonArray4){
            i4.add(jsonArray4.getString(oof4));
            oof4++;
        }
        String[] start_stop_time = {i4.get(0), i4.get(1)};

        JSONArray jsonArrayTagsToAvoid = obj.getJSONArray("tags_to_avoid");
        ArrayList<String> i6 = new ArrayList<>();
        int oof6 = 0;
        for (Object ignored : jsonArrayTagsToAvoid){
            i6.add(jsonArrayTagsToAvoid.getString(oof6));
            oof6++;
        }
        String[] tags_to_avoid = i6.toArray(new String[0]);

        JSONArray jsonArrayWhiteList = obj.getJSONArray("whitelist");
        ArrayList<String> i7 = new ArrayList<>();
        int oof7 = 0;
        for (Object ignored : jsonArrayWhiteList){
            i7.add(jsonArrayWhiteList.getString(oof7));
            oof7++;
        }
        String[] whitelist = i7.toArray(new String[0]);


        cfg.always_working = obj.getBoolean("always_working");
        cfg.auto_commments = obj.getBoolean("auto_commments");
        cfg.auto_follows = obj.getBoolean("auto_follows");
        cfg.auto_likes = obj.getBoolean("auto_likes");
        cfg.auto_sub = obj.getBoolean("auto_sub");
        cfg.randomize_tags = obj.getBoolean("randomize_tags");
        cfg.comments = comments;
        cfg.max_comments_per_day = obj.getInt("max_comments_per_day");
        cfg.max_likes_per_day = obj.getInt("max_likes_per_day");
        cfg.max_subs_per_day = obj.getInt("max_subs_per_day");
        cfg.delays = delays;
        cfg.method_type = obj.getInt("method_type");
        cfg.min_max_subs = min_max_subs;
        cfg.start_stop_time = start_stop_time;
        cfg.tags = tags;
        cfg.tags_to_avoid = tags_to_avoid;
        cfg.whitelist = whitelist;

        return cfg;
    }

}
