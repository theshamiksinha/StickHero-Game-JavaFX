package com.example.newgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameMenuController {
    @FXML
    private Pane GamePlayRoot;

    @FXML
    private AnchorPane GameMenuRoot;

    @FXML
    private ImageView returnToMainMenuButton;

    @FXML
    private ImageView pauseGameButton;

    @FXML
    private ImageView restartGameButton;


    private static int cherryCount;

    @FXML
    public void initData(Pane gamePlayRoot, int cheCount){

        this.GamePlayRoot=gamePlayRoot;
        cherryCount=cheCount;
    }

//    @FXML
//    void restartGame(MouseEvent event) throws IOException {
//        GamePlayController.gameStatus = false;
//        GamePlayController.endAnimations();
//        //System.out.println("restart called");
//        Stage stage = (Stage) restartGameButton.getScene().getWindow();
//        stage.close();
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamePlay.fxml"));
//        AnchorPane game=fxmlLoader.load();
//        GamePlayController controller = fxmlLoader.<GamePlayController>getController();
//        data=new DataTable(levelNumber);
//        controller.initData(levelNumber,data);
//
//        GamePlayRoot.getChildren().setAll(game);
//    }
//
//    @FXML
//    void pauseGame(MouseEvent event) throws IOException {
//        GamePlayController.gameStatus = false;
//        GameMenuMessage.setText("Game Saved!");
//        data.update(levelNumber,sunCount, allPlants,allZombies,allMowers, time, zombieList1, zombieList2, LevelMenuController.status);
//        Main.getDatabase().removeData(data);
//        data.saveGame();
//        Main.getDatabase().setMaxLevel(levelNumber);
//
//    }
//
//    @FXML
//    void showMainMenu(MouseEvent event) throws IOException {
//        GamePlayController.gameStatus = false;
//        GamePlayController.endAnimations();
//        AnchorPane pane= FXMLLoader.load(getClass().getResource("MainPage.fxml"));
//        GamePlayRoot.getChildren().setAll(pane);
//        Stage stage = (Stage) restartGameButton.getScene().getWindow();
//        stage.close();
//
//    }

}
