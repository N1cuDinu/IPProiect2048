package com.dinu.Main2048.GameObject;

import com.dinu.Main2048.Main;
import com.dinu.Main2048.Sprite.Renderer;
import com.dinu.Main2048.Sprite.Sprite;
import com.dinu.Main2048.game.Game;

import java.util.Random;

public class GameObject {
    public double x,y;
    public int width, height;
    public Sprite sprite;
    public int value, speed = 8;
    public boolean moving = false,remove= false,hasMoved=false;

    Random ran = new Random();
    public GameObject(double x, double y){
        this.x = x;
        this.y = y;
        this.value=(ran.nextBoolean() ? 2 : 4);
        createSprite();
        this.width = sprite.width;
        this.height = sprite.height;
    }
    public GameObject(GameObject gObject) {
        this.x = gObject.x;
        this.y = gObject.y;
        this.width = gObject.width;
        this.height = gObject.height;
        this.sprite = gObject.sprite;
        this.value = gObject.value;
        this.speed = gObject.speed;
        this.moving = gObject.moving;
        this.remove = gObject.remove;
        this.hasMoved = gObject.hasMoved;
    }

    public GameObject clone() {
        return new GameObject(this);
    }
    public static GameObject newInstance(GameObject gObject) {
        return new GameObject(gObject);
    }

    public void createSprite() {
        switch (this.value) {
            case 2:
                this.sprite = new Sprite(100,100,0xefe5db);
                break;
            case 4:
                this.sprite = new Sprite(100,100,0xece0c8);
                break;
            case 8:
                this.sprite = new Sprite(100,100,0xf1b078);
                break;
            case 16:
                this.sprite = new Sprite(100,100,0xEB8C52);
                break;
            case 32:
                this.sprite = new Sprite(100,100,0xF57C5F);
                break;
            case 64:
                this.sprite = new Sprite(100,100,0xEC563D);
                break;
            case 128:
                this.sprite = new Sprite(100,100,0xF2D86A);
                break;
            case 256:
                this.sprite = new Sprite(100,100,0xECC750);
                break;
            case 512:
                this.sprite = new Sprite(100,100,0xE5BF2D);
                break;
            case 1024:
                this.sprite = new Sprite(100,100,0xE2B913);
                break;
            case 2048:
                this.sprite = new Sprite(100,100,0xEDC22E);
                break;
            case 4096:
                this.sprite = new Sprite(100,100,0x5DDB92);
                break;
            case 8192:
                this.sprite = new Sprite(100,100,0xEC4D58);
                break;
        }
    }
    public boolean canMove(){
        if(x<0 || x + width > Main.WIDTH || y<0 || y+ height > Main.HEIGHT){
            return false;
        }
        for(int i=0; i< Game.objects.size();i++){
            GameObject o = Game.objects.get(i);
            if(this ==o)continue;
            if(x + width> o.x && x <o.x + o.width && y + height > o.y && y < o.y + o.height && value != o.value){
                return false;
            }

        }
        return true;
    }
    public void update() {
        if(Game.moving){
            if(!hasMoved){
                hasMoved = true;
            }
            if(canMove()){
                moving=true;
            }
            if(moving) {
                if(Game.dir == 0) x -= speed;
                if(Game.dir == 1) x += speed;
                if(Game.dir == 2) y -= speed;
                if(Game.dir == 3) y += speed;
            }
            if(!canMove()) {
                moving = false;
                x = Math.round(x / 100) * 100;
                y = Math.round(y / 100) * 100;
            }
        }
    }

    public void render(){
        Renderer.renderSprite(sprite, (int) x, (int) y);
    }

}
