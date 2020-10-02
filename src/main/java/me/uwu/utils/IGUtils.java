package me.uwu.utils;

import me.uwu.IGStonks;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;

import java.io.IOException;

public class IGUtils {

    public static boolean isValidAccount = false;

    public static void login (String user, String pass) throws IOException, InterruptedException {
        IGStonks.instagram = Instagram4j.builder().username(user).password(pass).build();
        IGStonks.instagram.setup();
        IGStonks.instagram.login();

        try {
            InstagramSearchUsernameResult selfUser = IGStonks.instagram.sendRequest(new InstagramSearchUsernameRequest(user));
            selfUser.getUser();
            isValidAccount = true;
        } catch (Exception e){
            isValidAccount = false;
            e.printStackTrace();
            IGStonks.cleanUp();
        }
    }


}
