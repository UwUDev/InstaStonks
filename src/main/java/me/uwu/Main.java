package me.uwu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import me.uwu.utils.Actions;

import java.util.Scanner;

public class Main {

    public static String user = "";
    public static String pass = "";

    private static final WebDriver driver = new ChromeDriver();

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username : ");
        user = sc.nextLine();
        System.out.println("Enter password : ");
        pass = sc.nextLine();

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nStarting ...");

        String tag = null;
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

            state ++;

            switch (state) {
                case 1:
                    tag = "newyork";
                    break;
                case 2:
                    tag = "foodporn";
                    break;
                case 3:
                    tag = "summer";
                    break;
                case 4:
                    tag = "selfie";
                    break;
                case 5:
                    tag = "summermakeup";
                    break;
                case 6:
                    tag = "summerslam";
                    break;
                case 7:
                    tag = "fitness";
                    break;
                case 8:
                    tag = "food";
                    break;
                case 9:
                    tag = "cute";
                    break;
                case 10:
                    tag = "instagram";
                    state=0;
                    break;
            }

            System.out.println("Switching to #" + tag);
            driver.get("https://www.instagram.com/explore/tags/" + tag + "/");

            Thread.sleep(3000);

            action.openLastestPost();

            for (int i = 1; i <= 7; i++) {
                int errors = 0;
                for (int oof = 7; oof >= 1; oof--) {

                    System.out.print(oof + " ");

                    Thread.sleep(1000 + Actions.randomDelay(10, 300));

                    try {
                        if (!driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/article/div[3]/section[1]/span[1]/button")).getSize().equals(0)) {
                            action.likePost();
                        }
                    } catch (Exception e) {
                        errors++;
                    }

                    if(oof == 3) {
                        try {
                            action.subPost();
                            Thread.sleep(2000);
                        } catch (Exception e) {
                        }
                    }

                    Thread.sleep(2500 + Actions.randomDelay(10, 300));

                    try {
                        if (!driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/a[2]")).getSize().equals(0)) {
                            action.nextPost();
                        }
                    } catch (Exception e) {
                    }

                }

                if (errors >= 6) {
                    Thread.sleep(1000);
                    try {
                        action.nextPost();
                    } catch (Exception e) {
                    }
                }

                int pauseDelay = Actions.randomDelay(500, 1000) + 12000;
                System.out.println("Drinking a bit of coffe for " + pauseDelay + "ms ;)");

                Thread.sleep(pauseDelay);
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