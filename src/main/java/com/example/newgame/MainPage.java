package com.example.newgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class MainPage extends Application {

    public static MediaPlayer mediaPlayer;
    public static Database currentd;

    public static ArrayList<Integer> scoreList  = new ArrayList<Integer>(Arrays.asList(0));

    @Override
    public void start(Stage primaryStage) throws Exception {

        addMusic();

        FXMLLoader fxmlLoader = new FXMLLoader(MainPage.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);

//
//        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/redCursor.png")));
//        scene.setCursor(new ImageCursor(image));

        primaryStage.setTitle("Stick Hero");

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/StickHeroCharacter.png")));
        primaryStage.getIcons().add(icon);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void addMusic(){

        Media sound = new Media(getClass().getResource("/sounds/calmBackground.mp3").toString());

        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setStartTime(Duration.seconds(0));
        mediaPlayer.play();

    }

    public static void main(String[] args) {
        launch();
    }



    public static void serialize() throws IOException {
        ObjectOutputStream out = null;
        FileOutputStream outf = null;
        try {
            outf = new FileOutputStream("database.txt");
            out = new ObjectOutputStream (outf);
            out.writeObject(currentd);
        }
        finally {

            if(out != null){
                out.close();
            }
            if(out != null){
                outf.close();
            }
        }
    }
    public static void deserialize() throws ClassNotFoundException, FileNotFoundException, IOException{
        ObjectInputStream in = null;
        try {
            in=new ObjectInputStream (new FileInputStream("database.txt"));
            currentd=(Database) in.readObject();
            in.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        finally {
            if(in != null){
                in.close();
            }
        }
    }

}