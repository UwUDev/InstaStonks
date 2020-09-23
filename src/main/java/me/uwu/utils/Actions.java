package me.uwu.utils;

import me.uwu.IGStonks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Actions extends PageOBJs {

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"loginForm\"]/div/div[3]/button")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"react-root\"]/section/main/article/div[2]/div/div[1]/div[1]/a/div[1]")
    private WebElement lastPostButton;

    @FindBy(xpath = "/html/body/div[5]/div/div/div/div[3]/button[2]")
    private WebElement unsubCancelButton;

    @FindBy(xpath = "/html/body/div[4]/div[2]/div/article/div[3]/section[1]/span[1]/button")
    private WebElement likeButton;

    @FindBy(xpath = "/html/body/div[4]/div[1]/div/div/a[2]")
    private WebElement nextPostButton;

    @FindBy(xpath = "/html/body/div[4]/div[2]/div/article/header/div[2]/div[1]/div[2]/button")
    private WebElement subButton;

    public Actions(WebDriver driver) {
        super(driver);
    }

    public void pasteUsername(){
        this.usernameField.sendKeys(IGStonks.user);
    }

    public void pastePassword(){
        this.passwordField.sendKeys(IGStonks.pass);
    }

    public void typeUsername(int delay) throws InterruptedException {
        for (int i = 0; i < IGStonks.user.length(); i++) {
            this.usernameField.sendKeys(IGStonks.user.substring(i, i+1));
            Thread.sleep(delay);
        }
    }

    public void typePassword(int delay) throws InterruptedException {
        for (int i = 0; i < IGStonks.pass.length(); i++) {
            this.passwordField.sendKeys(IGStonks.pass.substring(i, i+1));
            Thread.sleep(delay);
        }
    }

    public void pressLoginButton(){
        this.loginButton.click();
    }

    public void openLastestPost(){
        this.lastPostButton.click();
    }

    public void likePost(){
        this.likeButton.click();
    }

    public void subPost(){
        this.subButton.click();
    }

    public void nextPost(){
        this.nextPostButton.click();
    }

    public void unsubCancel(){ try { this.unsubCancelButton.click(); }catch (Exception ignored){} }


    public static int randomDelay(double min, double max){
        int x = (int) ((Math.random()*((max-min)+1))+min);
        return x;
    }
}