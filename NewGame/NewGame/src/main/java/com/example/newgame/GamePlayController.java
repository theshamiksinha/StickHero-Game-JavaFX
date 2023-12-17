package com.example.newgame;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public class GamePlayController {



    @FXML
    private Pane GamePlayRoot;

    @FXML
    public Rectangle currentPillar;

    @FXML
    public ImageView heroCharacter;

    @FXML
    public Rectangle scoreBox;

    @FXML
    public Label scoreLabelNum;

    @FXML
    public Label scoreLabel;

    @FXML
    private Label cherryCountLabel;

    @FXML
    private ImageView GameMenuLoaderButton;

    @FXML
    private int pillarNumber;

    private ScoreBoard scoreBoard;
    private Stick stick;
    private static Pillar Platform;
    private Hero HeroCharacter;
    private GamePlayScreen gamePlayScreen;
    private boolean isGrowthPossible = false;


    public void initialize() {

        this.scoreBoard = new ScoreBoard(this.scoreBox, this.scoreLabel, this.scoreLabelNum);
        Platform = new Pillar(this.currentPillar);
        this.HeroCharacter = new Hero(heroCharacter, GamePlayRoot);
        this.stick = this.HeroCharacter.getHeroStick();
        this.isGrowthPossible = true;

        this.gamePlayScreen = new GamePlayScreen(scoreBoard, HeroCharacter, Platform, GamePlayRoot);

        gamePlayScreen.spawnPillar();

    }
    @FXML
    public void spacePressed (KeyEvent event) throws Exception{

        if (event.getCode() == KeyCode.SPACE && isGrowthPossible && gamePlayScreen.getHero().isHeroAlive()) {
            System.out.println("Space Bar is pressed");
            gamePlayScreen.getHero().getHeroStick().growStick();

        }
    }

    public void spaceReleased (KeyEvent event) throws Exception{

        if (event.getCode() == KeyCode.SPACE) {
            System.out.println("Space Bar is released");
            isGrowthPossible = false;
            isGrowthPossible = gamePlayScreen.getHero().getHeroStick().stopGrowStick();

            gamePlayScreen.moveHero();

            if(gamePlayScreen.getHero().isHeroAlive()){
                gamePlayScreen.spawnPillar();
            }
            else{
                // end game.... load end game menu
            }

        }

    }

//    @FXML
//    void loadGameMenu(MouseEvent event) throws IOException {
////        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameMenu.fxml"));
////        Parent gameMenu = (Parent) fxmlLoader.load();
////        Stage stage = new Stage();
////        stage.setScene(new Scene(gameMenu));
////        GameMenuController controller = fxmlLoader.<GameMenuController>getController();
////        controller.initData(GamePlayRoot, levelNumber,d,sunCount,allPlants, allZombies, allMowers, timeElapsed, l.getZombieList1(), l.getZombieList2());
////        stage.show();
//    }

//    public void gameEnd() throws IOException{
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EndGame.fxml"));
//        AnchorPane pane=fxmlLoader.load();
////        EndGameController controller = fxmlLoader.<EndGameController>getController();
////        controller.initData(levelNumber, true,d);
//        GamePlayRoot.getChildren().setAll(pane);
//
//    }



    public void shiftPlatformAndPlayer() {

        TranslateTransition platformTransition = new TranslateTransition(Duration.seconds(2), currentPillar);
        platformTransition.setToX(56.0 - currentPillar.getLayoutX());
        platformTransition.setToY(386.0 - currentPillar.getLayoutY());

        TranslateTransition characterTransition = new TranslateTransition(Duration.seconds(2), heroCharacter);
        characterTransition.setToX(83.0 - heroCharacter.getLayoutX());
        characterTransition.setToY(292.0 - heroCharacter.getLayoutY());

        platformTransition.play();
        characterTransition.play();
    }

    public void shiftScoreBoard() {

        TranslateTransition scoreBoxTransition = new TranslateTransition(Duration.seconds(2), this.scoreBox);
        scoreBoxTransition.setToX(300.0 - this.scoreBox.getLayoutX());
        scoreBoxTransition.setToY(27.0 - this.scoreBox.getLayoutY());

        TranslateTransition scoreLabelTransition = new TranslateTransition(Duration.seconds(2), this.scoreLabel);
        scoreLabelTransition.setToX(330.0 - this.scoreLabel.getLayoutX());
        scoreLabelTransition.setToY(27.0 - this.scoreLabel.getLayoutY());

        TranslateTransition scoreLabelNumTransition = new TranslateTransition(Duration.seconds(2), this.scoreLabelNum);
        scoreLabelNumTransition.setToX(432.0 - this.scoreLabelNum.getLayoutX());
        scoreLabelNumTransition.setToY(27.0 - this.scoreLabelNum.getLayoutY());

        scoreBoxTransition.play();
        scoreLabelTransition.play();
        scoreLabelNumTransition.play();

    }

}