package me.uwu;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.users.UsersInfoRequest;
import me.uwu.threads.TagMethodThread;
import me.uwu.utils.Discord;
import me.uwu.utils.IGUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class IGStonks {

    private static final WebDriver chrome = new ChromeDriver();
    public static IGClient instagram;

    public static String user = "";
    public static String pass = "";

    public static boolean onlyLike = false;
    public static boolean safeMode = true;

    public static int[] delays = new int[]{1000, 2000, 3000, 2500, 12000, 0};

    public static void goStonks() throws InterruptedException, IOException {
        /*Controller controller = new Controller();
        controller.failed();*/

        Discord.update(-1, user);
        Thread.sleep(1000); //c'est pour que le chrome driver soit bien lancé
        IGUtils.login(user, pass);
        Thread.sleep(1500); //temps de login sinon ca se login sur le truc android et chrome en meme temps et ca nik tout C'EST COMPRIS ALORS CHANGE PAS !

        try {
            UsersInfoRequest req = new UsersInfoRequest(instagram.getSelfProfile().getPk());
            instagram.sendRequest(req).thenAccept(userResponse -> {
                int fol = userResponse.getUser().getFollower_count();
                Discord.update(fol, user);
                System.out.println(fol + " followers");
            });
        } catch (Exception e){
            System.out.println("Login failed...");
            cleanUp();
            return;
        }

        Runnable r = new TagMethodThread(chrome, Vars.config);
        new Thread(r).start();
    }


    public static void cleanUp(){
        chrome.manage().deleteAllCookies();
        chrome.close();
    }

}
