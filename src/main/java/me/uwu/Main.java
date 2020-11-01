package me.uwu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.uwu.config.ConfigUtils;
import me.uwu.utils.Discord;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UwU.fxml"));
        primaryStage.setTitle("InstaStonks by UwU#0001");
        primaryStage.setScene(new Scene(root, 1270, 710));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        primaryStage.show();
        primaryStage.resizableProperty().set(false);
    }

    public static void main(String[] args) {
        ConfigUtils.setupConfigs();
        Discord.startup();
        Discord.update(-1, null);
        launch(args);
    }
}