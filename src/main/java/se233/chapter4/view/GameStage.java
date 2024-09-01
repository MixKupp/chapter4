package se233.chapter4.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import se233.chapter4.Launcher;
import se233.chapter4.model.GameCharacter;
import se233.chapter4.model.Keys;

public class GameStage extends Pane {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public static final int GROUND = 300;
    private Image gameStageImage;
    private GameCharacter gameCharacter;

    private GameCharacter nextGameCharacter;
    private Keys keys;
    public GameStage(){
        keys = new Keys();
        gameStageImage = new Image(Launcher.class.getResourceAsStream("assets/Background.png"));
        ImageView backgroundImg = new ImageView(gameStageImage);
        backgroundImg.setFitWidth(WIDTH);
        backgroundImg.setFitHeight(HEIGHT);
        gameCharacter = new GameCharacter(30, 35,0,0, KeyCode.A, KeyCode.D, KeyCode.W,"assets/MarioSheet.png",16,32,32,64,0,0);
        nextGameCharacter = new GameCharacter(30, 30,0,0, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP,"assets/rockman.png",541,512,64,64,10,10);
        getChildren().addAll(backgroundImg,gameCharacter,nextGameCharacter);
    }
    public GameCharacter getGameCharacter() {
        return gameCharacter;
    }
    public GameCharacter getNextGameCharacter() {
        return nextGameCharacter;
    }
    public Keys getKeys() {
        return keys;
    }

}
