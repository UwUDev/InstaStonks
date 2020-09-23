package me.uwu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodTextRun;
import javafx.scene.paint.Color;
import me.uwu.IGStonks;
import java.io.IOException;

public class Controller {

    public TextField user;
    public PasswordField pass;

    public Label unTxt;
    public Label pwTxt;

    public CheckBox sm;
    public CheckBox rf;

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

            IGStonks.goStonks();
        }
    }

      public void exit() {
        IGStonks.cleanUp();
        System.exit(-1);
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

    }
