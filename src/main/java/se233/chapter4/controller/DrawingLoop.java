package se233.chapter4.controller;

import se233.chapter4.model.GameCharacter;
import se233.chapter4.view.GameStage;

public class DrawingLoop implements Runnable{
    private GameStage gameStage;
    private int frameRate;
    private float interval;
    private boolean running;
    public DrawingLoop(GameStage gameStage){
        this.gameStage = gameStage;
        frameRate = 60;
        interval = 1000.0f/ frameRate;
        running = true;
    }
    private void checkDrawCollisions(GameCharacter gameCharacter,GameCharacter otherCharacter){
        gameCharacter.checkReachHighest();
        gameCharacter.checkReachGameWall();
        gameCharacter.checkReachFloor();
        otherCharacter.checkReachHighest();
        otherCharacter.checkReachGameWall();
        otherCharacter.checkReachFloor();
    }
    private void paint(GameCharacter gameCharacter,GameCharacter otherCharacter){
        gameCharacter.repaint();
        otherCharacter.repaint();
    }
    @Override
    public void run(){
        while (running){
            float time = System.currentTimeMillis();
            checkDrawCollisions(gameStage.getGameCharacter(),gameStage.getNextGameCharacter());
            paint(gameStage.getGameCharacter(),gameStage.getNextGameCharacter());
            time = System.currentTimeMillis() - time;
            if (time < interval){
                try{
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException e ){
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep((long) (interval - (interval % time)));
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
