package gameClient;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

abstract class Game extends Applet implements Runnable, KeyListener{

    private Thread gameloop;

    private LinkedList _sprites;
    public LinkedList sprites() { return _sprites; }

    private BufferedImage backbuffer;
    private Graphics2D g2d;
    private int screenWidth, screenHeight;

    private int _frameCount = 0;
    private int _frameRate = 0;
    private int desiredRate;
    private long startTime = System.currentTimeMillis();

    public Applet applet() { return this; }

    private boolean _gamePaused = false;
    public boolean gamePaused() { return _gamePaused; }
    public void pauseGame() { _gamePaused = true; }
    public void resumeGame() { _gamePaused = false; }

    abstract void gameStartup();
    abstract void gameTimedUpdate();
    abstract void gameRefreshScreen();
    abstract void gameShutdown();
    abstract void gameKeyDown(int keyCode);
    abstract void gameKeyUp(int keyCode);
    abstract void spriteUpdate(AnimatedSprite sprite);
    abstract void spriteDraw(AnimatedSprite sprite);
    abstract void spriteDying(AnimatedSprite sprite);
    abstract void spriteCollision(AnimatedSprite spr1, AnimatedSprite spr2);

    public Game(int frameRate, int width, int height) {
        desiredRate = frameRate;
        screenWidth = width;
        screenHeight = height;
    }

    public Graphics2D graphics() { return g2d; }

    public int frameRate() { return _frameRate; }

    public void init() {

        backbuffer = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
        g2d = backbuffer.createGraphics();

        _sprites = new LinkedList<AnimatedSprite>();

        addKeyListener(this);
        gameStartup();
    }

    public void update(Graphics g) {

        _frameCount++;
        if (System.currentTimeMillis() > startTime + 1000) {
            startTime = System.currentTimeMillis();
            _frameRate = _frameCount;
            _frameCount = 0;

            purgeSprites();
        }

        gameRefreshScreen();

        if (!gamePaused()) {
            drawSprites();
        }

        paint(g);
    }

     public void paint(Graphics g) {
         g.drawImage(backbuffer, 0, 0, this);
     }

     public void start() {
         gameloop = new Thread(this);
         gameloop.start();
     }

     public void run() {
         Thread t = Thread.currentThread();
         while (t == gameloop) {
             try {
                 Thread.sleep(1000 / desiredRate);
             }
             catch(InterruptedException e) {
                 e.printStackTrace();
             }
             if (!gamePaused()) {
                 updateSprites();
                 testCollisions();
             }
             gameTimedUpdate();
             repaint();
         }
     }

     public void stop() {
         gameloop = null;
         gameShutdown();
     }

     public void keyTyped(KeyEvent k) { }
     public void keyPressed(KeyEvent k) {
         gameKeyDown(k.getKeyCode());
     }
     public void keyReleased(KeyEvent k) {
         gameKeyUp(k.getKeyCode());
     }
   
     protected double calcAngleMoveX(double angle) {
         return (double)(Math.cos(angle * Math.PI / 180));
     }
     protected double calcAngleMoveY(double angle) {
         return (double) (Math.sin(angle * Math.PI / 180));
     }

     protected void updateSprites() {
         for (int n=0; n < _sprites.size(); n++) {
             AnimatedSprite spr = (AnimatedSprite) _sprites.get(n);
             if (spr.alive()) {
                 spr.updatePosition();
                 spr.updateRotation();
                 spr.updateAnimation();
                 spriteUpdate(spr);
                 spr.updateLifetime();
                 if (!spr.alive()) {
                     spriteDying(spr);
                 }
             }
         }
     }

     protected void testCollisions() {
         for (int first=0; first < _sprites.size(); first++) {
             AnimatedSprite spr1 = (AnimatedSprite) _sprites.get(first);
             if (spr1.alive()) {
                 for (int second = 0; second < _sprites.size(); second++) {
                     if (first != second) {
                         AnimatedSprite spr2 = (AnimatedSprite)
                                               _sprites.get(second);
                         if (spr2.alive()) {
                             if (spr2.collidesWith(spr1)) {
                                 spriteCollision(spr1, spr2);
                                 break;
                             }
                             else
                                spr1.setCollided(false);
                         }
                     }
                 }
             }
         }
     }

     protected void drawSprites() {
         for (int n=0; n<_sprites.size(); n++) {
             AnimatedSprite spr = (AnimatedSprite) _sprites.get(n);
             if (spr.alive()) {
                 spr.updateFrame();
                 spr.transform();
                 spr.draw();
                 spriteDraw(spr);
             }
         }
     }

     private void purgeSprites() {
         for (int n=0; n < _sprites.size(); n++) {
             AnimatedSprite spr = (AnimatedSprite) _sprites.get(n);
             if (!spr.alive()) {
                 _sprites.remove(n);
             }
         }
     }
}
