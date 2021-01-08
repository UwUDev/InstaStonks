package me.uwu;

import me.uwu.config.ConfigObj;
import me.uwu.config.ConfigUtils;

import java.util.Arrays;

public class TestingShit {
    public static void main(String[] args) {
        ConfigObj cfg = ConfigUtils.getConfig("Base");
        System.out.println(Arrays.toString(cfg.delays));
        System.out.println(cfg.always_working);
        System.out.println(Arrays.toString(cfg.comments));
        System.out.println(cfg.max_comments_per_day);
        System.out.println(Arrays.toString(cfg.min_max_subs));
        System.out.println(Arrays.toString(cfg.start_stop_time));
        System.out.println(Arrays.toString(cfg.tags));
        System.out.println(Arrays.toString(cfg.tags_to_avoid));
        System.out.println(Arrays.toString(cfg.whitelist));
    }
}
