package me.uwu;

import me.uwu.utils.Tags;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import me.uwu.utils.Actions;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static String user = "";
    public static String pass = "";

    private static int delay1 = 1000;
    private static int delay2 = 2000;
    private static int delay3 = 3000;
    private static int delay4 = 2500;
    private static int delay5 = 12000;

    private static final WebDriver driver = new ChromeDriver();

    public static void main(String[] args) throws InterruptedException, IOException {

        Scanner sc = new Scanner(System.in);

        /*if(args[0].equalsIgnoreCase("--username") || args[0].equalsIgnoreCase("--mail")){
            user = args[1].toString();
        }else{*/
            System.out.println("Enter username : ");
            user = sc.nextLine();
       // }

        /*if(args[2].equalsIgnoreCase("--password") || args[2].equalsIgnoreCase("--pass")){
            pass = args[3].toString();
        }else{*/
            System.out.println("Enter password : ");
            pass = sc.nextLine();
        //}

        //bruh bruh bruh...
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nStarting ...");

        System.out.println("Ultra safe mode ? (y/n) : ");
        String yn = sc.nextLine();

        if(yn.equalsIgnoreCase("y")){
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

        int state = 0;

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

                        if (oof == 3) {
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
                        try {
                            action.nextPost();
                        } catch (Exception e) {
                        }
                    }

                    int pauseDelay = Actions.randomDelay(500, 1000) + delay5;
                    System.out.println("Drinking a bit of coffe for " + pauseDelay + "ms ;)");

                    Thread.sleep(pauseDelay);
                }

                if (yn.equalsIgnoreCase("y")) {
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