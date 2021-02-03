package me.uwu.threads;

import com.github.instagram4j.instagram4j.requests.users.UsersInfoRequest;
import me.uwu.IGStonks;
import me.uwu.Vars;
import me.uwu.config.ConfigObj;
import me.uwu.config.ConfigUtils;
import me.uwu.utils.Actions;
import me.uwu.utils.Discord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class TagMethodThread implements Runnable {
    public WebDriver chrome;
    private Thread thread;
    private int[] delays;
    private ConfigObj cfg;

    public TagMethodThread(WebDriver chrome, String config){
        this.chrome = chrome;
        this.cfg = ConfigUtils.getConfig(config);
    }

    @Override
    public void run() {

        System.setProperty("webdriver.chrome.driver", Vars.CHROME_DRIVER_LOCATION);

        chrome.get(Vars.BASE_URL);
        Actions action = new Actions(chrome);

        System.out.println("Starting ...");

        //cleanUp();

        //c'est pour que ca ai le temps de bien charger la page  (oui j'ai une co de merde :c)
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            action.typeUsername(100);
            action.typePassword(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        action.pressLoginButton();

        //histoire que ca ai le temp de se co
        try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}

        while (true) {

            for (String tag : cfg.tags) {

                System.out.println("Switching to #" + tag);

                chrome.get("https://www.instagram.com/explore/tags/" + tag);

                try {Thread.sleep(cfg.delays[2]);} catch (InterruptedException e) {e.printStackTrace();}

                action.openLastestPost();

                for (int i = 1; i <= 7; i++) {
                    int errors = 0;
                    for (int oof = 7; oof >= 1; oof--) {

                        System.out.print(oof + " ");

                        try {Thread.sleep(cfg.delays[0]);} catch (InterruptedException e) {e.printStackTrace();}

                        if(cfg.auto_likes)
                        try {
                            if (!chrome.findElement(By.xpath("/html/body/div[5]/div[2]/div/article/div[3]/section[1]/span[1]/button")).getSize().equals(0)) {
                                action.likePost();
                            }
                        } catch (Exception e) {
                            errors++;
                        }

                        if (oof == 3 && !cfg.auto_sub)
                            try {
                                action.subPost();
                                Thread.sleep(cfg.delays[1]);
                                action.unsubCancel();
                                Thread.sleep(250);
                            } catch (Exception ignored) {}

                        try {Thread.sleep(cfg.delays[4]);} catch (InterruptedException e) {e.printStackTrace();}

                        try {
                            if (!chrome.findElement(By.xpath("/html/body/div[5]/div[1]/div/div/a[2]")).getSize().equals(0)) {
                                action.nextPost();
                            }
                        } catch (Exception e) {e.printStackTrace();
                        }

                    }

                    if (errors >= 6) {
                        try {Thread.sleep(cfg.delays[0]);} catch (InterruptedException e) {e.printStackTrace();}
                        try {action.nextPost();
                        } catch (Exception ignored) {ignored.printStackTrace();}
                    }

                    UsersInfoRequest req = new UsersInfoRequest(IGStonks.instagram.getSelfProfile().getPk());
                    IGStonks.instagram.sendRequest(req).thenAccept(userResponse -> {
                        Discord.update(userResponse.getUser().getFollower_count(), IGStonks.user);
                    });

                    int pauseDelay = Actions.randomDelay(500, 1000) + cfg.delays[4];
                    System.out.println("Drinking a bit of coffee for " + pauseDelay + "ms ;)");

                    try {Thread.sleep(pauseDelay);} catch (InterruptedException e) {e.printStackTrace();}
                }

                    try {Thread.sleep(cfg.delays[5]);} catch (InterruptedException e) {e.printStackTrace();}

            }
        }
    }

    public void kill() {
        thread.interrupt();
    }
}
