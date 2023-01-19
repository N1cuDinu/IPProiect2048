package com.dinu.Main2048;

import com.dinu.Main2048.game.Game;
import com.dinu.Main2048.inputPackage.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Main extends Canvas implements Runnable {

    public static final int WIDTH = 400, HEIGHT = 400;
    public static final float scale = 2.0f;
    public Keyboard key;
    public JFrame frame;
    public Thread thread;
    public Game game;
    public boolean running = false;
    public static final BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    public static int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Main(){
        setPreferredSize(new Dimension((int) (WIDTH*scale), (int) (HEIGHT*scale)));
        frame = new JFrame();
        //Implementare Singleton ->
        game = Game.getInstance();
        key = new Keyboard();
        addKeyListener(key);
    }
    public void start(){
        running = true;
        thread = new Thread(this, "loopThread");
        thread.start();
    }
    public void stop(){
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void run(){
        long nanoTime = System.nanoTime();
        long  timer = System.currentTimeMillis();
        double nanoSecondsPerUpdate = 1000000000 /60.0;
        double updateToPerform = 0.0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while(running) {
            long currentTimeInNanoSeconds = System.nanoTime();
            updateToPerform += (currentTimeInNanoSeconds - nanoTime) /nanoSecondsPerUpdate;
            if(updateToPerform >= 1){
                update();
                updates++;
                updateToPerform--;
            }
            if(System.currentTimeMillis() - timer > 1000) {
                updates = 0;
                frames = 0;
                timer += 1000;
            }
            nanoTime = currentTimeInNanoSeconds;
            render();
            frames++;
        }
    }
    public void update(){
        game.update();
        key.update();
    }
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3);
            return;
        }
        game.render();
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.drawImage(image, 0 ,0, (int) (WIDTH * scale) , (int) (HEIGHT*scale), null);
        game.renderText(g);
        g.dispose();
        bs.show();
    }




    public static void main(String[] args) {

        Main m = new Main();
        m.frame.setResizable(false);
        m.frame.setTitle("2048 made by Nicu");
        m.frame.add(m);
        m.frame.pack();
        m.frame.setVisible(true);
        m.frame.setLocationRelativeTo(null);
        m.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.frame.setAlwaysOnTop(true);
        m.start();
    }
}
