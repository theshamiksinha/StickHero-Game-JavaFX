package com.example.newgame;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

import static com.example.newgame.MainPage.mediaPlayer;

public class GamePlayController {

    @FXML
    private Pane GamePlayRoot;

    @FXML
    public ImageView backgroundImage;

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
    public Label xLabelCherry;

    @FXML
    public ImageView CherryPNG;

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
    public static boolean isFlipPossible = false;
    private MediaPlayer StickGrowSound;
    private int cherryCount;

    public static boolean isUp = true;

    public static double distanceXnextPillar;
    private double cherryX = 0;

    public Pane getGamePlayRoot() {
        return GamePlayRoot;
    }

    public double getCherryX() {
        return cherryX;
    }

    public void setCherryX(double cherryX) {
        this.cherryX = cherryX;
    }

    public int getCherryCount() {
        return cherryCount;
    }

    public void setCherryCount(int cherryCount) {
        this.cherryCount = cherryCount;
    }

    public void initialize() {

        this.scoreBoard = new ScoreBoard(this.scoreBox, this.scoreLabel, this.scoreLabelNum, this.cherryCountLabel, this.GamePlayRoot);
        this.cherryCount = 0;
        Platform = new Pillar(this.currentPillar);
        this.HeroCharacter = Hero.getHeroInstance(heroCharacter, GamePlayRoot, this.scoreBoard, this.cherryCount, this);
        this.stick = this.HeroCharacter.getHeroStick();
        this.isGrowthPossible = true;


        this.gamePlayScreen = new GamePlayScreen(scoreBoard, HeroCharacter, Platform, GamePlayRoot, this);
        this.HeroCharacter.setGamePlay(this.gamePlayScreen);

//        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/redCursor.png")));
//        this.GamePlayRoot.setCursor(new ImageCursor(image));

        EndGameController scoreDisplay = new EndGameController();
        scoreBoard.attachObserver(scoreDisplay);

        gamePlayScreen.spawnPillar();



    }

    @FXML
    public void gameEnd() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EndGame.fxml"));
        Parent endGameRoot = fxmlLoader.load();
        EndGameController endGameController = fxmlLoader.getController();

        endGameController.updateScore(gamePlayScreen.getScore().getScoreValue());
        endGameController.setScoreLabel(gamePlayScreen.getScore().getScoreValue());
        endGameController.setCherryScore(gamePlayScreen.getHero().getCherryCount());
        endGameController.setCherryScoreValue(gamePlayScreen.getHero().getCherryCount());
        endGameController.setBestScore();
        endGameController.setBestScoreLabel(endGameController.setBestScore());
        GamePlayRoot.getChildren().setAll(endGameRoot);
    }

    @FXML
    public void spacePressed (KeyEvent event) throws Exception{

        if (event.getCode() == KeyCode.SPACE && isGrowthPossible && gamePlayScreen.getHero().isHeroAlive()) {
            System.out.println("Space Bar is pressed");
            gamePlayScreen.getHero().getHeroStick().growStick();

            playSoundEffectgrow();

        }

    }
    @FXML
    public void spaceReleased (KeyEvent event) throws Exception{

        if (event.getCode() == KeyCode.SPACE) {
            System.out.println("Space Bar is released");
            isGrowthPossible = false;

            double stickLength = gamePlayScreen.getHero().getHeroStick().getStick_length();
            double pillarDistanceMax = getXNextPillar() - getXCurrentPillar() - gamePlayScreen.getCurrentPillar().getPillar().getWidth()+gamePlayScreen.getNextPillar().getPillar().getWidth();
            double pillarDistanceMin = getXNextPillar() - getXCurrentPillar() - gamePlayScreen.getCurrentPillar().getPillar().getWidth();
            distanceXnextPillar = getXNextPillar();

            System.out.println("stick length " + stickLength);
            System.out.println("pillar distance min " + pillarDistanceMin);

            System.out.println("pillar distance max " + pillarDistanceMax);

            //            double targetX = gamePlayScreen.getHero().getHeroStick().getStick_length() + gamePlayScreen.getHero().getHeroCharacter().getLayoutX() - gamePlayScreen.getHero().getHeroCharacter().getFitWidth();

            double targetX = getXNextPillar() - getXCurrentPillar() - gamePlayScreen.getCurrentPillar().getPillar().getWidth() + gamePlayScreen.getNextPillar().getPillar().getWidth();
            System.out.println(targetX + " this is targetX");

            if((stickLength<=pillarDistanceMin) || (stickLength > pillarDistanceMax)){

                if((stickLength<=pillarDistanceMin)){
                    gamePlayScreen.getHero().setHeroAlive(false);
                    targetX = stickLength;
                }else{
                    gamePlayScreen.getHero().setHeroAlive(false);
                    targetX = stickLength;
                }

            }
//            if(!isUp){
//                targetX = -(gamePlayScreen.getNextPillar().getPillar().getLayoutX()-gamePlayScreen.getCurrentPillar().getPillar().getLayoutX()-gamePlayScreen.getCurrentPillar().getPillar().getWidth()) ;
//            }

            isGrowthPossible = gamePlayScreen.getHero().getHeroStick().stopGrowStick(targetX); // passing the distance hero needs to move after stick falls

        }

    }

    @FXML
    public void downPressed (KeyEvent event) throws Exception{

        if(event.getCode()==KeyCode.DOWN && gamePlayScreen.getHero().isHeroMoving() && isFlipPossible ){

            playSoundEffectWhoosh();

            System.out.println("Down is pressed");

            gamePlayScreen.getHero().getHeroCharacter().setScaleY(-1);
            gamePlayScreen.getHero().getHeroCharacter().setLayoutY(gamePlayScreen.getHero().getHeroCharacter().getLayoutY()+(gamePlayScreen.getHero().getHeroCharacter().getFitHeight()));
            isUp = false;

        }
    }

    @FXML
    public void upPressed (KeyEvent event) throws Exception{

        if(event.getCode()==KeyCode.UP  && gamePlayScreen.getHero().isHeroMoving()  ){

            playSoundEffectWhoosh();

            System.out.println("Up is pressed");

            gamePlayScreen.getHero().getHeroCharacter().setScaleY(1);
            gamePlayScreen.getHero().getHeroCharacter().setLayoutY(gamePlayScreen.getHero().getHeroCharacter().getLayoutY()-(gamePlayScreen.getHero().getHeroCharacter().getFitHeight()));
            isUp= true;
        }
    }

    private double getXNextPillar(){

        Rectangle nextPillar = gamePlayScreen.getNextPillar().getPillar();
        Bounds boundsInScene = nextPillar.localToScene(nextPillar.getBoundsInLocal());

        return boundsInScene.getMinX();
    }

    private double getXCurrentPillar(){

        Rectangle currentPillar = gamePlayScreen.getCurrentPillar().getPillar();
        Bounds boundsInScene = currentPillar.localToScene(currentPillar.getBoundsInLocal());

        return boundsInScene.getMinX();
    }

    public void shiftElementsForNextPillar() {

        isGrowthPossible = false;
        Timeline gameShiftTimeline = new Timeline();

        double currentX = getXNextPillar();

        KeyFrame HeroKeyFrame = new KeyFrame(Duration.seconds(1),
                new KeyValue(heroCharacter.layoutXProperty(),  gamePlayScreen.getNextPillar().getPillar().getWidth() - gamePlayScreen.getHero().getHeroCharacter().getFitWidth()));

        gameShiftTimeline.getKeyFrames().add(HeroKeyFrame);

        /////////////////////////////////////////////////////////////////////////////////////////////////

        KeyFrame currentPillarKeyFrame = new KeyFrame(Duration.seconds(1),
                new KeyValue(gamePlayScreen.getCurrentPillar().getPillar().layoutXProperty(), gamePlayScreen.getCurrentPillar().getPillar().getLayoutX() - 1000));

        gameShiftTimeline.getKeyFrames().add(currentPillarKeyFrame);

        /////////////////////////////////////////////////////////////////////////////////////////////////

        KeyFrame newPillarKeyFrame = new KeyFrame(Duration.seconds(1),
                new KeyValue(gamePlayScreen.getNextPillar().getPillar().layoutXProperty(), -currentX));

        gameShiftTimeline.getKeyFrames().add(newPillarKeyFrame);

        //////////////////////////////////////////////////////////////////////////////////////////////////

        KeyFrame stickKeyFrame = new KeyFrame(Duration.seconds(1),
                new KeyValue(gamePlayScreen.getHero().getHeroStick().getStick().layoutXProperty(), -1000));

        gameShiftTimeline.getKeyFrames().add(stickKeyFrame);

        /////////////////////////////////////////////////////////////////////////////////////////////////

        if(gamePlayScreen.getNextCherry() != null){

            KeyFrame cherryKeyFrame = new KeyFrame(Duration.seconds(1),
                    new KeyValue(gamePlayScreen.getNextCherry().getCherry().layoutXProperty(), -1000));

            gameShiftTimeline.getKeyFrames().add(cherryKeyFrame);

            gamePlayScreen.getNextCherry().getCherry().setVisible(false);

        }else{

        }

        //////////////////////////////////////////////////////////////////////////////////////////////////

        gameShiftTimeline.play();

        gamePlayScreen.setCurrentPillar(gamePlayScreen.getNextPillar());

        gameShiftTimeline.setOnFinished(event -> {
            System.out.println("screen shift for next pillar timeline completed");// this will only execute when timeline animation are over

            gamePlayScreen.getHero().setNewHeroStick();
            isGrowthPossible = true;
        });

        gamePlayScreen.spawnPillar();
        gamePlayScreen.spawnCherry();
    }


    public void shiftPlatformAndPlayer() {

        Timeline heroTimeline = new Timeline();

        KeyFrame heroKeyFrame = new KeyFrame(Duration.seconds(2),
                new KeyValue(heroCharacter.layoutXProperty(), heroCharacter.getLayoutX() - 220));

        heroTimeline.getKeyFrames().add(heroKeyFrame);


        Timeline pillarTimeline = new Timeline();

        double n = currentPillar.getLayoutX() - 220;

        KeyFrame pillarKeyFrame = new KeyFrame(Duration.seconds(2),
                new KeyValue(currentPillar.layoutXProperty(), currentPillar.getLayoutX() - 220));

        pillarTimeline.getKeyFrames().add(pillarKeyFrame);


        heroTimeline.play();
        pillarTimeline.play();
    }

    public void restartPositionSet() {

        double heroNewX = heroCharacter.getLayoutX() - 220;
        double pillarNewX = currentPillar.getLayoutX() - 220;

        heroCharacter.setLayoutX(heroNewX);
        currentPillar.setLayoutX(pillarNewX);
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

    public void shiftCherryCount(){

        TranslateTransition cherryLabelNumTransition = new TranslateTransition(Duration.seconds(2), this.cherryCountLabel);
        cherryLabelNumTransition.setToX(720.0 - this.cherryCountLabel.getLayoutX());
        cherryLabelNumTransition.setToY(18.0 - this.cherryCountLabel.getLayoutY());

        TranslateTransition cherryPNGTransition = new TranslateTransition(Duration.seconds(2), this.CherryPNG);
        cherryPNGTransition.setToX(633 - this.CherryPNG.getLayoutX());
        cherryPNGTransition.setToY(36.0 - this.CherryPNG.getLayoutY());

        TranslateTransition cherryXTransition = new TranslateTransition(Duration.seconds(2), this.xLabelCherry);
        cherryXTransition.setToX(695.0 - this.xLabelCherry.getLayoutX());
        cherryXTransition.setToY(30.0 - this.xLabelCherry.getLayoutY());

        cherryLabelNumTransition.play();
        cherryPNGTransition.play();
        cherryXTransition.play();

    }

    private void playSoundEffectgrow() {

        // create a new media player and play the sound file
        Media media = new Media(getClass().getResource("/sounds/blipGrow.mp3").toString());
        this.StickGrowSound = new MediaPlayer(media);
        this.StickGrowSound.setAutoPlay(true);
        this.StickGrowSound.setCycleCount(1);
        this.StickGrowSound.setStartTime(Duration.millis(0));

        this.StickGrowSound.play();

    }

    public void setScoreOnRevive(int score, int cherries, EndGameController e){

        this.scoreLabelNum.setText(String.valueOf(score));
        this.cherryCountLabel.setText(String.valueOf(cherries));
        this.scoreBoard.setScoreValue(score);
        this.scoreBoard.setCherryCount(cherries);
        this.cherryCount = cherries;
        e.setScore(score);
        e.setCherryScoreValue(cherries);
        gamePlayScreen.getHero().setCherryCount(cherries);
    }

    public void playSoundOnRestart(){

        Media media = new Media(getClass().getResource("/sounds/calmBackground.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setStartTime(Duration.millis(0));


//        mediaPlayer.setVolume(0.1f);

        mediaPlayer.play();
    }

    private void playSoundEffectWhoosh() {

        // create a new media player and play the sound file
        Media media = new Media(getClass().getResource("/sounds/heroWhoosh.mp3").toString());
        MediaPlayer whoosh = new MediaPlayer(media);
        whoosh.setAutoPlay(true);
        whoosh.setCycleCount(1);
        whoosh.setStartTime(Duration.seconds(0));

        whoosh.play();

    }

}