package me.uwu.controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodTextRun;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import me.uwu.IGStonks;
import me.uwu.Main;
import me.uwu.config.ConfigUtils;

import javax.annotation.Resources;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static Controller instance;

    public TextField user;
    public PasswordField pass;

    public Label unTxt;
    public Label pwTxt;
    public Label failTxt;
    public Label plzSelect;

    public CheckBox sm;
    public CheckBox rf;

    public ComboBox configs;
    public ImageView imgStonks;

    public Controller(){instance = this;}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("loaded");
        try {
            imgStonks.setImage(Main.icon);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    protected void goStonks() throws IOException, InterruptedException {
        if(isValidUsername(user)){
            unTxt.setText("Valid username");
            unTxt.setTextFill(Color.web("#A3BE8C"));
        }else {
            unTxt.setText("Enter valid username...");
            unTxt.setTextFill(Color.web("#BF616A"));
        }

        if(isValidPassword(pass)){
            pwTxt.setText("Valid password");
            pwTxt.setTextFill(Color.web("#A3BE8C"));
        }else {
            pwTxt.setText("Enter valid password...");
            pwTxt.setTextFill(Color.web("#BF616A"));
        }

        System.out.println(configs.getValue());

        if (configs.getValue() == null) plzSelect.setVisible(true);
        else plzSelect.setVisible(false);


        if(isValidUsername(user) && isValidPassword(pass)){
            IGStonks.user = user.getText();
            IGStonks.pass = pass.getText();

            if(rf.isSelected()){
                IGStonks.onlyLike = false;
            } else {
                IGStonks.onlyLike = true;
            }

            if(sm.isSelected()){
                IGStonks.safeMode = true;
            } else {
                IGStonks.safeMode = false;
            }

            ConfigUtils.setConfig(configs.getValue().toString());
            IGStonks.goStonks();
        }
    }

      public void cleanUp() {
        IGStonks.cleanUp();
      }

      private static boolean isValidUsername(TextField user) {
         String u = user.getText();
            if(u.length() >= 3 && !u.contains("%") && !u.contains(",") && !u.contains("#") && !u.contains("µ") && !u.contains("²") && !u.contains("°"))
             return true;
             else return false;
      }

    private static boolean isValidPassword(TextField pass) {
        return pass.getText().length() >= 7;
    }

    public void failed(){
        failTxt.setText("Login failed");
    }

    public void reloadList(MouseEvent mouseEvent) {
        configs.getItems().clear();
        for (String config : ConfigUtils.getConfigsName()){
            configs.getItems().add(config);
        }
    }
}
