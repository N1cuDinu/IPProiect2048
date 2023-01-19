package com.dinu.Main2048.game;

import com.dinu.Main2048.Main;
import com.dinu.Main2048.Sprite.Renderer;
import com.dinu.Main2048.Sprite.Sprite;
import com.dinu.Main2048.inputPackage.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Game {
    //Singleton ->
    private static Game instance;
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
    public Game(){
        init();
    }
    public void init(){

    }
    public void update(){
        if(Keyboard.keyDown(KeyEvent.VK_A)){
            System.out.println("Down a");
        }
        if(Keyboard.key(KeyEvent.VK_A)){
            System.out.println("a");
        }
        if(Keyboard.keyUP(KeyEvent.VK_A)){
            System.out.println("Up a");
        }
    }
    public void render(){

    }
    public void renderText(Graphics2D g){
        Renderer.renderBackground();
        Renderer.renderSprite(new Sprite(100,100,0xffffff00), 100, 100);
        for(int i=0; i< Main.pixels.length; i++){
            Main.pixels[i] = Renderer.pixels[i];
        }
    }
}
