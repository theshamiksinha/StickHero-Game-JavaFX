package com.example.newgame;

import javafx.animation.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.newgame.GamePlayController.*;
import static com.example.newgame.MainPage.mediaPlayer;


public class Hero extends GameElements {

    private static Map<String, Hero> heroInstances = new HashMap<>();
    private static AtomicInteger counter = new AtomicInteger(0);
    private int high_score = 0;
    private final ImageView heroCharacter;
    private boolean HeroAlive = true;
    private Stick heroStick;
    private GamePlayController gamePlayScreen;
    private GamePlayScreen gamePlay;
    private final ScoreBoard scoreBoard;
    private final Pane pane;
    private int cherryCount;
    private boolean isHeroMoving;
    private boolean ateCherry = false;

    public static Hero getHeroInstance(ImageView him, Pane pane, ScoreBoard scoreboard, int CherryCount, GamePlayController g) {

        int uniqueId = counter.getAndIncrement();
        String key = "Hero_" + uniqueId;

        if (!heroInstances.containsKey(key)) {
            Hero newHero = new Hero(him, pane, scoreboard, CherryCount, g);
            heroInstances.put(key, newHero);
        }
        return heroInstances.get(key);
    }

    private Hero(ImageView him, Pane pane, ScoreBoard scoreboard, int CherryCount, GamePlayController g) {
        this.heroCharacter = him;
        this.pane = pane;
        this.scoreBoard = scoreboard;
        this.cherryCount = CherryCount;
        this.gamePlayScreen = g;
        this.heroStick = new Stick(this, pane);
    }


    public int getCherryCount() {
        return cherryCount;
    }

    public void setCherryCount(int cherryCount) {
        this.cherryCount = cherryCount;
    }

    public GamePlayController getGamePlayScreen() {
        return gamePlayScreen;
    }

    public void setGamePlayScreen(GamePlayController gamePlayScreen) {
        this.gamePlayScreen = gamePlayScreen;
    }

    public GamePlayScreen getGamePlay() {
        return this.gamePlay;
    }

    public void setGamePlay(GamePlayScreen gamePlay) {
        this.gamePlay = gamePlay;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }



    public ImageView getHeroCharacter() {
        return heroCharacter;
    }



    public Stick getHeroStick() {
        return heroStick;
    }

    public void setNewHeroStick() {
        this.heroStick = new Stick(this, this.pane, 4);
    }
    public int getHigh_score() {
        return high_score;
    }

    public void setHigh_score(int high_score) {
        this.high_score = high_score;
    }

    public boolean isHeroAlive() {
        return HeroAlive;
    }

    public void setHeroAlive(boolean heroAlive) {
        HeroAlive = heroAlive;
    }

    @Override
    public double getPositionX() {
        return heroCharacter.getLayoutX();
    }

    @Override
    public double getPositionY() {
        return heroCharacter.getLayoutY();
    }

    public boolean isHeroMoving() {
        return isHeroMoving;
    }

    public void setHeroMoving(boolean heroMoving) {
        isHeroMoving = heroMoving;
    }

    public void moveHero(double targetX) {

//        System.out.println("Cherry is at" + cherryX);

        isHeroMoving = true;
        isFlipPossible = true;
        System.out.println("Player is moving ahead");

        Timeline heroTimeline = new Timeline();

//         Add a KeyFrame to the Timeline
        KeyFrame HeroKeyFrame = new KeyFrame(Duration.seconds(2),
                new KeyValue(this.getHeroCharacter().layoutXProperty(), this.getHeroCharacter().getLayoutX() + targetX ));

        heroTimeline.getKeyFrames().add(HeroKeyFrame);

        heroTimeline.play();

        Timeline positionCheckTimeline = new Timeline();
        double checkInterval = 0.01;

        KeyFrame checkKeyFrame = new KeyFrame(Duration.seconds(checkInterval), event -> {

            double heroX = this.getHeroCharacter().getLayoutX();
//            System.out.println(heroX);

            if (!isUp && heroX <= distanceXnextPillar && heroX>(distanceXnextPillar-this.heroCharacter.getFitWidth())) {

                positionCheckTimeline.stop();
                heroTimeline.stop();
                System.out.println("died here");
                moveHeroToDeath();
            }

            if(!isUp && ((heroX >= gamePlayScreen.getCherryX()-this.getHeroCharacter().getFitWidth()) && heroX < gamePlayScreen.getCherryX()+2) && gamePlay.nextCherryImage.isVisible()){
                System.out.println("Heros position is: " +heroX);
                System.out.println("cherry is at : "+ gamePlayScreen.getCherryX());
                gamePlay.nextCherryImage.setVisible(false);
                playSoundEffectCherryCollected();
                cherryCount++;
                gamePlayScreen.setCherryCount(cherryCount);
                this.getScoreBoard().increaseCherryCount(1);
                this.getScoreBoard().increaseScore(3);

                System.out.println("Cherry collected");
            }
        });

        positionCheckTimeline.getKeyFrames().add(checkKeyFrame);
        positionCheckTimeline.setCycleCount(Timeline.INDEFINITE);
        positionCheckTimeline.play();

        heroTimeline.setOnFinished(event -> {
            if (HeroAlive){
                isHeroMoving = false;
                isFlipPossible = false;
                System.out.println("Player movement completed");

                gamePlayScreen.shiftElementsForNextPillar();

                this.getScoreBoard().increaseScore(1);

            }else{
                moveHeroToDeath();
            }
        });

        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.UP, false, false, false, false);

        gamePlayScreen.getGamePlayRoot().fireEvent(keyEvent);

    }

    public void moveHeroToDeath(){

        System.out.println("hero is dead");

        Timeline heroTimeline = new Timeline();

        // Add a KeyFrame to the Timeline
        KeyFrame HeroKeyFrame = new KeyFrame(Duration.seconds(5),
                new KeyValue(this.getHeroCharacter().layoutYProperty(), this.getHeroCharacter().getLayoutY() +1000));

        heroTimeline.getKeyFrames().add(HeroKeyFrame);

        heroTimeline.play();

        this.getHeroStick().getStick().setVisible(true);

        Rotate existingRotate = (Rotate) this.getHeroStick().getStick().getTransforms().get(0);
        double currentAngle = existingRotate.getAngle();

        // Set the pivot point to the bottom center of the stick
        double pivotX = this.getHeroStick().getStick().getX() + this.getHeroStick().getStick().getWidth() / 2;
        double pivotY = this.getHeroStick().getStick().getY() + this.getHeroStick().getStick().getHeight();

        // Rotate the stick further by adding 90 degrees to the current angle
        Rotate rotate = new Rotate(currentAngle + 90, pivotX, pivotY);
        this.getHeroStick().getStick().getTransforms().clear();
        this.getHeroStick().getStick().getTransforms().add(rotate);

        Duration duration = Duration.seconds(1);
        double finalAngle = currentAngle + 90;

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), currentAngle)),
                new KeyFrame(duration, new KeyValue(rotate.angleProperty(), finalAngle))
        );
        mediaPlayer.stop();
        mediaPlayer.dispose();
        playSoundEffectDeath();

        timeline.play();

        timeline.setOnFinished(event -> {
            System.out.println("Death Timeline completed");// this will only execute when timeline animation are over
            // hence adding player moment animation here
            try {
                gamePlayScreen.gameEnd();


            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });



    }

    private void playSoundEffectCherryCollected() {

        // create a new media player and play the sound file
        Media media = new Media(getClass().getResource("/sounds/cherryCollected.mp3").toString());
        MediaPlayer cherryCollected = new MediaPlayer(media);
        cherryCollected.setAutoPlay(true);
        cherryCollected.setCycleCount(1);
        cherryCollected.setStartTime(Duration.seconds(0));

        cherryCollected.play();
//        if (cherryCollected != null && cherryCollected.getStatus() == MediaPlayer.Status.PLAYING) {
//            cherryCollected.stop();
//            cherryCollected.dispose();
//        }

    }

    private void playSoundEffectDeath() {

        // create a new media player and play the sound file
        Media media = new Media(getClass().getResource("/sounds/DeathHeroScream.mp3").toString());
        MediaPlayer death = new MediaPlayer(media);
        death.setAutoPlay(true);
        death.setCycleCount(1);
        death.setStartTime(Duration.seconds(0));

        death.play();

//        if (death != null && death.getStatus() == MediaPlayer.Status.PLAYING) {
//            death.stop();
//            death.dispose();
//        }
    }
}
