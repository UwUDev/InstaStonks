package me.uwu.threads;

import me.uwu.IGStonks;
import me.uwu.Vars;
import me.uwu.utils.Actions;
import me.uwu.utils.Discord;
import me.uwu.utils.Tags;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

public class TagMethodThread implements Runnable {
    public WebDriver chrome;
    private Thread thread;
    /*Runnable runnable = new MonTraitement();
    Thread thread = new Thread(runnable);
      thread.start();*/

    public TagMethodThread(WebDriver chrome){
        this.chrome = chrome;
    }

    @Override
    public void run() {
        /*if(IGStonks.safeMode){
            IGStonks.delay1 = 2500;
            IGStonks.delay2 = 4000;
            IGStonks.delay3 = 4000;
            IGStonks.delay4 = 4000;
            IGStonks.delay5 = 18000;
            IGStonks.delay6 = 600000;
        }*/

        List<String> tags = null;
        try {
            tags = Tags.getTags();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.setProperty("webdriver.chrome.driver", Vars.CHROME_DRIVER_LOCATION);

        chrome.get("https://www.instagram.com/");
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

            for (String tag : tags) {

                System.out.println("Switching to #" + tag);

                chrome.get("https://www.instagram.com/explore/tags/" + tag);

                try {Thread.sleep(IGStonks.delays[2]);} catch (InterruptedException e) {e.printStackTrace();}

                action.openLastestPost();

                for (int i = 1; i <= 7; i++) {
                    int errors = 0;
                    for (int oof = 7; oof >= 1; oof--) {

                        System.out.print(oof + " ");

                        try {Thread.sleep(IGStonks.delays[0]);} catch (InterruptedException e) {e.printStackTrace();}

                        try {
                            if (!chrome.findElement(By.xpath("/html/body/div[5]/div[2]/div/article/div[3]/section[1]/span[1]/button")).getSize().equals(0)) {
                                action.likePost();
                            }
                        } catch (Exception e) {
                            errors++;
                        }

                        if (oof == 3 && !IGStonks.onlyLike) {
                            try {
                                action.subPost();
                                Thread.sleep(IGStonks.delays[1]);
                                action.unsubCancel();
                                Thread.sleep(250);
                            } catch (Exception e) {
                            }
                        }

                        try {Thread.sleep(IGStonks.delays[4]);} catch (InterruptedException e) {e.printStackTrace();}

                        try {
                            if (!chrome.findElement(By.xpath("/html/body/div[5]/div[1]/div/div/a[2]")).getSize().equals(0)) {
                                action.nextPost();
                            }
                        } catch (Exception e) {e.printStackTrace();
                        }

                    }

                    if (errors >= 6) {
                        try {Thread.sleep(IGStonks.delays[0]);} catch (InterruptedException e) {e.printStackTrace();}
                        try {action.nextPost();
                        } catch (Exception ignored) {ignored.printStackTrace();}
                    }

                    InstagramSearchUsernameResult selfUser2 = null;
                    try {
                        selfUser2 = IGStonks.instagram.sendRequest(new InstagramSearchUsernameRequest(IGStonks.user));
                        Discord.update(selfUser2.getUser().getFollower_count(), IGStonks.user);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    int pauseDelay = Actions.randomDelay(500, 1000) + IGStonks.delays[4];
                    System.out.println("Drinking a bit of coffee for " + pauseDelay + "ms ;)");

                    try {Thread.sleep(pauseDelay);} catch (InterruptedException e) {e.printStackTrace();}
                }

                    try {Thread.sleep(IGStonks.delays[5]);} catch (InterruptedException e) {e.printStackTrace();}

            }
        }
    }

    public void kill() {
        thread.interrupt();
    }
}
