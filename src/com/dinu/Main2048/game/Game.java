package com.dinu.Main2048.game;

import com.dinu.Main2048.Command.ExitCommand;
import com.dinu.Main2048.Command.LastCommand;
import com.dinu.Main2048.GameObject.GameObject;
import com.dinu.Main2048.Iterator.Iterator;
import com.dinu.Main2048.Main;
import com.dinu.Main2048.Memento.Memento;
import com.dinu.Main2048.Memento.MementoHistory;
import com.dinu.Main2048.Sprite.Renderer;
import com.dinu.Main2048.Sprite.Sprite;
import com.dinu.Main2048.inputPackage.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    public static List<GameObject> objects;
    public static boolean moving = false, hasMoved = true, somethingIsMoving = false;
    public static int dir = 0 ;
    ExitCommand ext = new ExitCommand();
    LastCommand last = new LastCommand();
    private Random rand = new Random();
    MementoHistory history = new MementoHistory();
    Iterator iterator = history.createIterator();

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
        objects = new ArrayList<GameObject>();
        moving = false;
        hasMoved = true;
        somethingIsMoving = false;
        spawn();
    }



    public void update(){
        if(Keyboard.keyUP(KeyEvent.VK_R)){
            init();
        }
        if(Keyboard.keyUP(KeyEvent.VK_Q)){
            ext.execute();
        }
        for(int i=0; i< objects.size(); i++){
            objects.get(i).update();
        }
        checkForValueIncrease();
        movingLogic();
    }



    private void checkForValueIncrease() {
        for(int i=0; i<objects.size(); i++){
            for(int j=0; j< objects.size(); j++){
                if(i==j) continue;
                if(objects.get(i).x == objects.get(j).x && objects.get(i).y == objects.get(j).y && !objects.get(i).remove && !objects.get(j).remove){
                    objects.get(j).remove = true;
                    objects.get(i).value *= 2;
                    objects.get(i).createSprite();
                }

            }
        }
        for(int i=0; i<objects.size(); i++){
            if(objects.get(i).remove) objects.remove(i);
        }

    }

    public void render(){
        Renderer.renderBackground();
        for(int i=0; i<objects.size(); i++){
            objects.get(i).render();
        }
        for(int i=0; i< Main.pixels.length; i++){
            Main.pixels[i] = Renderer.pixels[i];
        }

    }
    private void spawn() {
        if(objects.size() == 16)return;

        boolean avalaible = false;
        int x =0, y=0;
        while(!avalaible){
            x=rand.nextInt(4);
            y=rand.nextInt(4);
            boolean isAvaialble = true;
            for(int i=0; i<objects.size();i++){
                if(objects.get(i).x / 100 == x && objects.get(i).y /100 == y){
                    isAvaialble = false;
                }
            }
            if(isAvaialble) avalaible = true;
        }
        objects.add(new GameObject(x*100, y*100));
    }
    private void movingLogic() {
        somethingIsMoving = false;
        for(int i=0; i<objects.size();i++){
            if(objects.get(i).moving){
                somethingIsMoving = true;
            }
        }
        if(!somethingIsMoving){
            moving=false;
            for(int i=0; i<objects.size();i++){
                objects.get(i).hasMoved=false;
            }
        }
        if(!moving&&hasMoved){
            spawn();
            hasMoved=false;
        }
        if(!moving&& !hasMoved){
            if(Keyboard.keyDown(KeyEvent.VK_A)){
                history.push(Game.createState());
                hasMoved=true;
                moving=true;
                dir=0;
            }else if(Keyboard.keyDown(KeyEvent.VK_D)){
                history.push(Game.createState());
                hasMoved=true;
                moving=true;
                dir=1;
            }else if(Keyboard.keyDown(KeyEvent.VK_W)){
                history.push(Game.createState());
                hasMoved=true;
                moving=true;
                dir=2;
            }else if(Keyboard.keyDown(KeyEvent.VK_S)){
                history.push(Game.createState());
                hasMoved=true;
                moving=true;
                dir=3;
            }else if(Keyboard.keyDown(KeyEvent.VK_U)) {
                last.getHistory(history);
                last.execute();
                render();
            } else if(Keyboard.keyDown(KeyEvent.VK_I)) {
                printIterator();
            }
        }
    }

    public void renderText(Graphics2D g){
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(new Font("Verdana", 0, 100));
        g.setColor(Color.black);

        for(int i=0; i<objects.size(); i++){
            GameObject o = objects.get(i);
            String s = o.value + " ";
            int sw = (int) (g.getFontMetrics().stringWidth(s) / 2 / Main.scale);
            g.drawString(s, (int) (o.x + o.width /2 - sw) * Main.scale, (int) (o.y + o.height /2 +18)*Main.scale);
        }
    }

    public static void setObjects(List<GameObject> objects) {
        Game.objects = List.copyOf(objects);
    }

    public static List<GameObject> getObjects() {
        return Game.objects;
    }

    public static Memento createState() {
        return new Memento(Game.objects);
    }

    public static void restore(Memento state) {
        Game.objects.clear();
        Game.objects.addAll(state.getObjects());
//		Game.objects = List.copyOf(state.getObjects());
    }

    public void printIterator() {

        while(iterator.hasNext()) {
            System.out.println(iterator.current().toString());
            iterator.next();
        }

    }

}
