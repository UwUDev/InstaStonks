package me.uwu.utils;

import com.github.instagram4j.instagram4j.IGClient;
import me.uwu.IGStonks;

import java.io.IOException;

public class IGUtils {

    public static void login (String user, String pass) throws IOException {
        IGStonks.instagram = IGClient.builder()
                .username(user)
                .password(pass)
                .login();
    }
}
