package me.uwu;

import me.uwu.utils.Actions;
import me.uwu.utils.Discord;
import me.uwu.utils.Tags;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IGStonks {

    private static final WebDriver driver = new ChromeDriver();

    public static String user = "";
    public static String pass = "";

    public static boolean onlyLike = false;
    public static boolean safeMode = true;

    private static int delay1 = 1000;
    private static int delay2 = 2000;
    private static int delay3 = 3000;
    private static int delay4 = 2500;
    private static int delay5 = 12000;

    public static void goStonks()  throws InterruptedException, IOException {

        Discord.update(-1, user);

        if(safeMode){
            delay1 = 2500;
            delay2 = 4000;
            delay3 = 4000;
            delay4 = 4000;
            delay5 = 18000;
        }

        List<String> tags = Tags.getTags();

        System.setProperty("webdriver.chrome.driver", Vars.CHROME_DRIVER_LOCATION);

        driver.get("https://www.instagram.com/");
        Actions action = new Actions(driver);

        System.out.println("Starting ...");

        //cleanUp();

        //c'est pour que ca ai le temps de bien charger la page  (oui j'ai une co de merde :c)
        Thread.sleep(2000);

        action.typeUsername(100);
        action.typePassword(100);

        Thread.sleep(1200);

        action.pressLoginButton();

        //histoire que ca ai le temp de se co
        Thread.sleep(5000);

        while (true) {

            for (String tag : tags) {

                System.out.println("Switching to #" + tag);

                driver.get("https://www.instagram.com/explore/tags/" + tag);

                Thread.sleep(delay3);

                action.openLastestPost();

                for (int i = 1; i <= 7; i++) {
                    int errors = 0;
                    for (int oof = 7; oof >= 1; oof--) {

                        System.out.print(oof + " ");

                        Thread.sleep(delay1 + Actions.randomDelay(10, 300));

                        try {
                            if (!driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/article/div[3]/section[1]/span[1]/button")).getSize().equals(0)) {
                                action.likePost();
                            }
                        } catch (Exception e) {
                            errors++;
                        }

                        if (oof == 3 && !onlyLike) {
                            try {
                                action.subPost();
                                Thread.sleep(delay2);
                                action.unsubCancel();
                                Thread.sleep(250);
                            } catch (Exception e) {
                            }
                        }

                        Thread.sleep(delay4 + Actions.randomDelay(10, 300));

                        try {
                            if (!driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/a[2]")).getSize().equals(0)) {
                                action.nextPost();
                            }
                        } catch (Exception e) {
                        }

                    }

                    if (errors >= 6) {
                        Thread.sleep(delay1);
                        try {action.nextPost();
                        } catch (Exception ignored) {}
                    }

                    int pauseDelay = Actions.randomDelay(500, 1000) + delay5;
                    System.out.println("Drinking a bit of coffee for " + pauseDelay + "ms ;)");

                    Thread.sleep(pauseDelay);
                }

                if (safeMode) {
                    System.out.println("Safe mode !\nSleeping for 10min...");
                    Thread.sleep(600000);
                }

            }
        }


        //Thread.sleep(3000);
        //cleanUp();
    }


    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
