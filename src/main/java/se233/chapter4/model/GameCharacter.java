package se233.chapter4.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import se233.chapter4.Launcher;
import se233.chapter4.view.GameStage;

public class GameCharacter extends Pane {
    private static final Logger logger = LogManager.getLogger(GameCharacter.class);
    private Image gameCharacterImage;
    private AnimatedSprite imageView;
    private int CHARACTER_HEIGHT;
    private int x;
    private int y;
    private int speed,gravity;
    private KeyCode leftKey;
    private KeyCode rightKey;
    private KeyCode upKey;
    int xVelocity = 0;
    int yVelocity = 0;
    boolean isFalling = true;
    boolean canJump = false;
    boolean isJumping = false;
    boolean isMoveLeft = false;
    boolean isMoveRight = false;
    int xAcceleration = 1;
    int yAcceleration = 1;
    int xMaxVelocity = 7;
    int yMaxVelocity = 17;

    public GameCharacter(int x, int y,int offsetX,int offsetY,KeyCode leftKey,KeyCode rightKey,KeyCode upKey,String img,int width,int height,int cWidth,int cHeight,int speed,int gravity) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.gravity = gravity;
        this.CHARACTER_HEIGHT = cHeight;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.gameCharacterImage = new Image(Launcher.class.getResourceAsStream(img));
        this.imageView = new AnimatedSprite(gameCharacterImage,4,4,1,offsetX,offsetY,width,height);
        this.imageView.setFitHeight(cHeight);
        this.imageView.setFitWidth(cWidth);
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.getChildren().add(this.imageView);
    }

    public void moveLeft(){
        setScaleX(-1);
        isMoveLeft = true;
        isMoveRight = false;
    }
    public void moveRight(){
        setScaleX(1);
        isMoveRight = true;
        isMoveLeft = false;
    }
    public void moveY(){
        setTranslateY(y);
        if(isFalling){
            yVelocity = yVelocity >= yMaxVelocity ? yMaxVelocity : yVelocity + yAcceleration;
            y = y + yVelocity + gravity;
        } else if (isJumping) {
            yVelocity = yVelocity <= 0 ? 0 : yVelocity - yAcceleration;
            y = y - yVelocity - gravity;
        }
    }
    public void moveX(){
        setTranslateX(x);
        if (isMoveRight){
            xVelocity = xVelocity >= xMaxVelocity ? xMaxVelocity : xVelocity + xAcceleration;
            x = x + xVelocity + speed;
        }
        if (isMoveLeft){
            xVelocity = xVelocity >= xMaxVelocity ? xMaxVelocity : xVelocity + xAcceleration;
            x = x - xVelocity - speed;
        }
    }
    public void checkReachGameWall(){
        if(x <= 0){
            x=0;
            logger.info("GAME WALL REACHED");
        } else if (x+getWidth() >= GameStage.WIDTH) {
            x = GameStage.WIDTH-(int)getWidth();
            logger.info("GAME WALL REACHED");
        }
    }
    public void jump(){
        if(canJump){
            yVelocity = yMaxVelocity;
            canJump = false;
            isJumping = true;
            isFalling = false;
        }
    }
    public void checkReachHighest(){
        if(isJumping && yVelocity <= 0){
            isJumping = false;
            isFalling = true;
            yVelocity =0;
        }
    }
    public void checkReachFloor(){
        if(isFalling && y >= GameStage.GROUND - CHARACTER_HEIGHT){
            isFalling = false;
            canJump = true;
            yVelocity = 0;
        }
    }
    public void repaint(){
        setTranslateX(x);
        setTranslateY(y);
        moveY();
        moveX();
    }
    public void stop(){
        isMoveLeft = false;
        isMoveRight = false;
    }
    public void trace(){
        logger.info(String.format("x:%d y:%d vx:%d vy%d",x,y,xVelocity,yVelocity));
    }
    public KeyCode getLeftKey() {
        return leftKey;
    }

    public KeyCode getRightKey() {
        return rightKey;
    }

    public KeyCode getUpKey() {
        return upKey;
    }

    public AnimatedSprite getImageView() {
        return imageView;
    }

}
