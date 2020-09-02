package me.uwu.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageOBJs {
    protected WebDriver driver;

    public PageOBJs(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}