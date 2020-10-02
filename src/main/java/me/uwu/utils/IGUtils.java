package me.uwu.utils;

import me.uwu.IGStonks;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.io.IOException;

public class IGUtils {

    public static boolean isValidAccount = false;

    public static void login (String user, String pass) throws IOException {
        try {
            IGStonks.instagram = Instagram4j.builder().username(user).password(pass).build();
            IGStonks.instagram.setup();
            IGStonks.instagram.login();
            isValidAccount = true;
        } catch (Exception ignored){
            isValidAccount = false;
        }
    }


}
