package gameClient;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Xevious20 extends Game {

    static int FRAMERATE = 60;
    static int SCREENWIDTH = 800;
    static int SCREENHEIGHT = 600;
    
    final int ENEMY = 40;
    final int BULLET_SPEED = 5;
    final int BULLET_SPEED_BOSS = 10;
    final double ACCELERATION = 0.05;
    final double SHIPROTATION = 5.0;
    
    final int STATE_NORMAL = 0;
    final int STATE_COLLIDED = 1;
    final int STATE_EXPLODING = 2;
    
    final int SPRITE_SHIP = 1;
    final int SPRITE_BOSS = 2;
    final int SPRITE_ENEMY = 20;
    final int SPRITE_BULLET = 100;
    final int SPRITE_BULLETEVIL = 150;
    final int SPRITE_EXPLOSION = 200;
    final int SPRITE_POWERUP_enemyHealth = 2000;
    
    final int GAME_MENU = 0;
    final int GAME_RUNNING = 1;
    final int GAME_OVER = 2;
    final int GAME_WIN = 3;


    ImageEntity background;
    ImageEntity bulletImage;
    ImageEntity bulletImageEvil;
    ImageEntity enemy;
    ImageEntity explosions;
    ImageEntity shipImage;
    ImageEntity[] barImage = new ImageEntity[2];
    ImageEntity bossImage;
    ImageEntity barFrame;
    ImageEntity backgroundA = new ImageEntity(this);
    ImageEntity backgroundB = new ImageEntity(this);
    ImageEntity backgroundC = new ImageEntity(this);
    static int xToBG = 0;

    int health = 20;
    int enemyHealth = 20;
    int score = 0;
    int highscore = 0;
    int gameState = GAME_MENU;

    Random rand = new Random();

    long collisionTimer = 0;
    long startTime = 0;

    boolean keyLeft, keyRight, keyUp, keyDown, keyFire, keyB, keyC, keyenemyHealth;

    MidiSequence music = new MidiSequence();
    SoundClip shoot = new SoundClip();
    SoundClip explosion = new SoundClip();

    public Xevious20() {
        super(FRAMERATE, SCREENWIDTH, SCREENHEIGHT);
    }

    void gameStartup() {

        music.load("ninja3.mid");
        shoot.load("shoot.au");
        explosion.load("explode.au");
        setSize(800, 408);

        backgroundA.setX(0);
        backgroundA.load("051Big.png");
        backgroundA.setGraphics(this.graphics());
        backgroundB.setX(0);
        backgroundB.load("051Big.png");
        backgroundB.setGraphics(this.graphics());
        backgroundC.setX(0);
        backgroundC.load("051Big.png");
        backgroundC.setGraphics(this.graphics());
        xToBG = backgroundA.width();

        barFrame = new ImageEntity(this);
        barFrame.load("barframe.png");
        barImage[0] = new ImageEntity(this);
        barImage[0].load("marco_health.png");
        barImage[1] = new ImageEntity(this);
        barImage[1].load("enemy_health.png");

        shipImage = new ImageEntity(this);
        shipImage.load("marco.gif");

        AnimatedSprite ship = new AnimatedSprite(this, graphics());
        ship.setSpriteType(SPRITE_SHIP);
        ship.setImage(shipImage.getImage());
        ship.setFrameWidth(ship.imageWidth());
        ship.setFrameHeight(ship.imageHeight());
        ship.setPosition(new Point2D(SCREENWIDTH / 2, SCREENHEIGHT / 2));
        ship.setAlive(true);

        ship.setState(STATE_EXPLODING);
        collisionTimer = System.currentTimeMillis();
        sprites().add(ship);

        bossImage = new ImageEntity(this);
        bossImage.load("boss3.gif");

        bulletImage = new ImageEntity(this);
        bulletImage.load("marcoBullet.png");

        bulletImageEvil = new ImageEntity(this);
        bulletImageEvil.load("proyectilEvil3.png");
        
        explosions = new ImageEntity(this);
        explosions.load("explosion.png");

        enemy = new ImageEntity(this);
        enemy.load("enemy4.gif");
        pauseGame();
        startTime = System.currentTimeMillis();
    }

    private void updateBackground() {

        if (xToBG % backgroundA.width() == 1) {
            xToBG = backgroundA.width();
            backgroundA.setX(0);
        } else {
            xToBG--;
        }
        backgroundA.loadBackground("051Big.png", xToBG - backgroundA.width());
        backgroundB.loadBackground("051Big.png", xToBG - 1);
        backgroundC.loadBackground("051Big.png", xToBG + backgroundB.width() - 2);
    }

    public void drawBackground() {
        backgroundA.draw();
        backgroundB.draw();
        backgroundC.draw();
    }

    private void resetGame() {

        music.setLooping(true);
        music.play();

        AnimatedSprite ship = (AnimatedSprite) sprites().get(0);

        sprites().clear();

        ship.setPosition(new Point2D(SCREENWIDTH / 2, SCREENHEIGHT / 2));
        ship.setAlive(true);
        ship.setState(STATE_EXPLODING);
        collisionTimer = System.currentTimeMillis();
        ship.setVelocity(new Point2D(0, 0));
        sprites().add(ship);

        for (int n = 0; n < ENEMY; n++) {
            createEnemy();
        }

        health = 20;
        enemyHealth = 20;
        score = 0;
        createdBoss = false;
        startTime = System.currentTimeMillis();
    }
    long lastBossBullet = 0;

    void gameTimedUpdate() {
        checkInput();
        updateBackground();

        if (!gamePaused() && sprites().size() == 1) {
            resetGame();
            gameState = GAME_OVER;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis() - startTime);
        
        if (calendar.getTimeInMillis() > 15000 && !createdBoss) {
            createBoss();
        }

        if (createdBoss && (System.currentTimeMillis() - lastBossBullet) > 300) {
            fireBulletEvil();
            lastBossBullet = System.currentTimeMillis();
        }
    }

    void gameRefreshScreen() {
        Graphics2D g2d = graphics();
        drawBackground();

        if (gameState == GAME_MENU) {

            g2d.setFont(new Font("Verdana", Font.BOLD, 36));
            g2d.setColor(Color.BLACK);
            g2d.drawString("XEVIUOS 2.0", 252, 202);
            g2d.setColor(new Color(200, 30, 30));
            g2d.drawString("XEVIUOS 2.0", 250, 200);

            int x = 270, y = 10;
            g2d.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 20));
            g2d.setColor(Color.BLUE);
            g2d.drawString("CONTROLES:", x, ++y * 20);
            g2d.drawString("ARRIBA - Flecha arriba", x + 20, ++y * 20);
            g2d.drawString("ABAJO - Flecha abajo", x + 20, ++y * 20);
            g2d.drawString("DERECHA - Flecha derecha", x + 20, ++y * 20);
            g2d.drawString("IZQUIERDA - Flecha izquierda", x + 20, ++y * 20);
            g2d.drawString("DISPARO - Ctrl ", x + 20, ++y * 20);
            g2d.setFont(new Font("Ariel", Font.BOLD, 24));
            g2d.setColor(Color.RED);
            g2d.drawString("Presiona TAB para ver los resultados", 270, 345);
            g2d.setColor(Color.ORANGE);
            g2d.drawString("Presiona ENTER para iniciar", 280, 370);
        } else if (gameState == GAME_RUNNING) {

            g2d.drawImage(barFrame.getImage(), SCREENWIDTH - 132, 18, this);
            for (int n = 0; n < health; n++) {
                int dx = SCREENWIDTH - 130 + n * 5;
                g2d.drawImage(barImage[0].getImage(), dx, 20, this);
            }
            g2d.drawImage(barFrame.getImage(), SCREENWIDTH - 132, 33, this);
            for (int n = 0; n < enemyHealth; n++) {
                int dx = SCREENWIDTH - 130 + n * 5;
                g2d.drawImage(barImage[1].getImage(), dx, 35, this);
            }
            g2d.setFont(new Font("Verdana", Font.BOLD, 24));
            g2d.setColor(Color.WHITE);
            g2d.drawString("" + score, 20, 40);
            g2d.setColor(Color.DARK_GRAY);
            g2d.drawString("" + highscore, 350, 40);
        } else if (gameState == GAME_OVER) {
            g2d.setFont(new Font("Verdana", Font.BOLD, 36));
            g2d.setColor(new Color(200, 30, 30));
            g2d.drawString("GAME OVER", 270, 200);

            g2d.setFont(new Font("Arial", Font.CENTER_BASELINE, 24));
            g2d.setColor(Color.ORANGE);
            g2d.drawString("Presiona ENTER para reiniciar", 240, 300);
        } else if (gameState == GAME_WIN) {
            g2d.setFont(new Font("Verdana", Font.BOLD, 36));
            g2d.setColor(new Color(200, 30, 30));
            g2d.drawString("HAS GANADO!!!", 270, 200);

            g2d.setFont(new Font("Arial", Font.CENTER_BASELINE, 24));
            g2d.setColor(Color.ORANGE);
            g2d.drawString("Presiona ENTER para reiniciar", 240, 300);
        }
    }

    void gameShutdown() {
        music.stop();
        shoot.stop();
        explosion.stop();
    }

    public void spriteUpdate(AnimatedSprite sprite) {
        switch (sprite.spriteType()) {
            case SPRITE_SHIP:
                warp(sprite);
                break;

            case SPRITE_BULLET:
                warp(sprite);
                break;

            case SPRITE_BULLETEVIL:
                warp(sprite);
                break;
            case SPRITE_EXPLOSION:
                if (sprite.currentFrame() == sprite.totalFrames() - 1) {
                    sprite.setAlive(false);
                }
                break;

            case SPRITE_ENEMY:
                warp(sprite);
                break;
        }
    }

    public void spriteDraw(AnimatedSprite sprite) {
    }

    public void spriteDying(AnimatedSprite sprite) {
    }

    public void spriteCollision(AnimatedSprite spr1, AnimatedSprite spr2) {

        switch (spr1.spriteType()) {
            case SPRITE_BULLET:
                
                if (isEnemy(spr2.spriteType())) {
                    incScore(5);
                    spr1.setAlive(false);
                    spr2.setAlive(false);
                    killEnemy(spr2);
                    createEnemy();
                } else if (isBoss(spr2.spriteType())) {
                    if (gameState != GAME_OVER)
                    {
                    enemyHealth -= 1;
                    if (enemyHealth <= 0) {
                        spr2.setAlive(false);
                        killEnemy(spr2);
                        gameState = GAME_WIN;
                    }
                    spr1.setAlive(false);
                    incScore(5);
                }
                }
                break;
            case SPRITE_BULLETEVIL:
               
                if (isShip(spr2.spriteType())) {
                    if (gameState != GAME_WIN) {
                        spr1.setAlive(false);
                        collisionTimer = System.currentTimeMillis();
                        spr1.setVelocity(new Point2D(0, 0));
                        double x = spr1.position().X() - 10;
                        double y = spr1.position().Y() - 10;
                        startBigExplosion(new Point2D(x, y));
                        spr1.setState(STATE_EXPLODING);
                        
                        health -= 4; 
                        if (health <= 0) {
                            gameState = GAME_OVER;
                        }
                    }
                }
                break;
            case SPRITE_SHIP:
                if (gameState != GAME_WIN){
                if (isEnemy(spr2.spriteType())) {
                    if (spr1.state() == STATE_NORMAL) {
                        if (keyenemyHealth) { 
                            enemyHealth -= 1;
                        } else { 
                            collisionTimer = System.currentTimeMillis();
                            spr1.setVelocity(new Point2D(0, 0));
                            double x = spr1.position().X() - 10;
                            double y = spr1.position().Y() - 10;
                            startBigExplosion(new Point2D(x, y));
                            spr1.setState(STATE_EXPLODING);
                            
                            health -= 1; 
                            if (health <= 0) {
                                gameState = GAME_OVER;
                            }
                        }
                        spr2.setAlive(false);
                        killEnemy(spr2);

                    } 
                    else if (spr1.state() == STATE_EXPLODING) {
                        if (collisionTimer + 800
                                < System.currentTimeMillis()) {
                            spr1.setState(STATE_NORMAL);
                        }
                    }
                }
                break;
            }
        }
    }

    public void gameKeyDown(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                keyLeft = true;
                break;
            case KeyEvent.VK_RIGHT:
                keyRight = true;
                break;
            case KeyEvent.VK_UP:
                keyUp = true;
                break;
            case KeyEvent.VK_CONTROL:
                keyFire = true;
                break;
            case KeyEvent.VK_DOWN:
                keyDown = true;
                break;
            case KeyEvent.VK_ENTER:
                if (gameState == GAME_MENU) {
                    resetGame();
                    resumeGame();
                    gameState = GAME_RUNNING;
                } else if (gameState == GAME_OVER|| gameState == GAME_WIN) {
                    resetGame();
                    resumeGame();
                    gameState = GAME_RUNNING;
                }
                break;
        }
    }

    public void gameKeyUp(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                keyLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                keyRight = false;
                break;
            case KeyEvent.VK_UP:
                keyUp = false;
                break;
            case KeyEvent.VK_DOWN:
                keyDown = false;
                break;
            case KeyEvent.VK_CONTROL:
                keyFire = false;
                fireBullet();
                break;
        }
    }

    private void killEnemy(AnimatedSprite sprite) {
        switch (sprite.spriteType()) {
            case SPRITE_ENEMY:
            case SPRITE_BOSS:
                spawnEnemy(sprite);
                spawnEnemy(sprite);
                spawnEnemy(sprite);
                startBigExplosion(sprite.position());
                break;
        }
    }

    private void spawnEnemy(AnimatedSprite sprite) {
        AnimatedSprite ene = new AnimatedSprite(this, graphics());
        ene.setAlive(true);
        int w = sprite.getBounds().width;
        int h = sprite.getBounds().height;
        double x = sprite.position().X() + w / 2 + rand.nextInt(20) - 40;
        double y = sprite.position().Y() + h / 2 + rand.nextInt(20) - 40;
        ene.setPosition(new Point2D(x, y));

        ene.setFaceAngle(rand.nextInt(360));
        ene.setMoveAngle(rand.nextInt(360));
        ene.setRotationRate(rand.nextDouble());

        double ang = ene.moveAngle() - 90;
        double velx = calcAngleMoveX(ang);
        double vely = calcAngleMoveY(ang);
        ene.setVelocity(new Point2D(velx, vely));
    }

    public void createEnemy() {
        AnimatedSprite ene = new AnimatedSprite(this, graphics());
        ene.setAlive(true);
        ene.setSpriteType(SPRITE_ENEMY);

        ene.setImage(enemy.getImage());
        ene.setFrameWidth(enemy.width());
        ene.setFrameHeight(enemy.height());
        int x = rand.nextInt(SCREENWIDTH + 1000);
        int y = rand.nextInt(SCREENHEIGHT - 128);
        ene.setPosition(new Point2D(x, y));

        ene.setFaceAngle(0);
        ene.setMoveAngle(270);
        ene.setRotationRate(0);

        double ang = ene.moveAngle() - 90;
        double velx = calcAngleMoveX(ang);
        double vely = calcAngleMoveY(ang);
        ene.setVelocity(new Point2D(velx, vely));

        sprites().add(ene);
    }
    
    boolean createdBoss = false;

    public void createBoss() {
        createdBoss = true;

        AnimatedSprite boss = new AnimatedSprite(this, graphics());
        boss.setAlive(true);
        boss.setSpriteType(SPRITE_BOSS);

        boss.setImage(bossImage.getImage());
        boss.setFrameWidth(bossImage.width());
        boss.setFrameHeight(bossImage.height());

        int x = SCREENWIDTH - 128;
        int y = SCREENHEIGHT / 2 - 128;
        boss.setPosition(new Point2D(x, y));

        boss.setFaceAngle(0);
        boss.setMoveAngle(270);
        boss.setRotationRate(0);

        double velx = 0;
        double vely = 0;
        boss.setVelocity(new Point2D(velx, vely));
        sprites().add(boss);
    }

    private boolean isEnemy(int spriteType) {
        switch (spriteType) {
            case SPRITE_ENEMY:
                return true;
            default:
                return false;
        }
    }

    private boolean isBoss(int spriteType) {
        switch (spriteType) {
            case SPRITE_BOSS:
                return true;
            default:
                return false;
        }
    }

    private boolean isShip(int spriteType) {
        switch (spriteType) {
            case SPRITE_SHIP:
                return true;
            default:
                return false;
        }
    }

    public void checkInput() {
        if (gameState != GAME_RUNNING) {
            return;
        }
        AnimatedSprite ship = (AnimatedSprite) sprites().get(0);
        if (keyLeft) {
         
            ship.position().setX(ship.pos.X() - 10);
        } else if (keyRight) {
         
            ship.position().setX(ship.pos.X() + 10);
        } else if (keyUp) {
         
            ship.position().setY(ship.pos.Y() - 10);
        } else if (keyDown) {
         
            ship.position().setY(ship.pos.Y() + 10);
        } 
    }

    public void fireBullet() {
        
        AnimatedSprite[] bullets = new AnimatedSprite[6];
        bullets[0] = stockBullet();
        sprites().add(bullets[0]);
        shoot.play();
    }

    public void fireBulletEvil() {
        
        AnimatedSprite[] bulletsEvil = new AnimatedSprite[20];
        bulletsEvil[0] = stockBulletEvil();
        if (bulletsEvil[0] != null) {
            sprites().add(bulletsEvil[0]);
        }
    }

    private AnimatedSprite stockBullet() {
        AnimatedSprite ship = (AnimatedSprite) sprites().get(0);

        AnimatedSprite bul = new AnimatedSprite(this, graphics());
        bul.setAlive(true);
        bul.setImage(bulletImage.getImage());
        bul.setFrameWidth(bulletImage.width());
        bul.setFrameHeight(bulletImage.height());
        bul.setSpriteType(SPRITE_BULLET);

        bul.setFaceAngle(90);
        bul.setMoveAngle(0);
        
        double angle = bul.moveAngle();
        double svx = calcAngleMoveX(angle) * BULLET_SPEED;
        double svy = calcAngleMoveY(angle) * BULLET_SPEED;

        bul.setVelocity(new Point2D(svx, svy));

        double x = ship.center().X() - bul.imageWidth() / 2;
        double y = ship.center().Y() - bul.imageHeight() / 2;
        int xx = (int) (x);
        bul.setLifespan((SCREENWIDTH - xx) / 5);
        bul.setPosition(new Point2D(x, y));

        return bul;
    }

    private AnimatedSprite stockBulletEvil() {

        AnimatedSprite boss = null;
        for (int i = 0; i < sprites().size(); i++) {
            AnimatedSprite sprite = (AnimatedSprite) sprites().get(i);
            if (sprite.spriteType() == SPRITE_BOSS) {
                boss = sprite;
                break;
            }
        }

        if (boss != null) {
            
            AnimatedSprite bulEvil = new AnimatedSprite(this, graphics());
            bulEvil.setAlive(true);
            bulEvil.setImage(bulletImageEvil.getImage());
            bulEvil.setFrameWidth(bulletImageEvil.width());
            bulEvil.setFrameHeight(bulletImageEvil.height());
            bulEvil.setSpriteType(SPRITE_BULLETEVIL);

            bulEvil.setFaceAngle(rand.nextInt(360));
            bulEvil.setMoveAngle(220 - rand.nextInt(70));
            bulEvil.setRotationRate(rand.nextDouble());

            double angle = bulEvil.moveAngle();
            double svx = calcAngleMoveX(angle) * BULLET_SPEED_BOSS;
            double svy = calcAngleMoveY(angle) * BULLET_SPEED_BOSS;

            bulEvil.setVelocity(new Point2D(svx, svy));

            double x = boss.center().X() - bulEvil.imageWidth() / 2;
            double y = boss.center().Y() - bulEvil.imageHeight() / 2;
            bulEvil.setLifespan(150);
            bulEvil.setPosition(new Point2D(x, y));

            return bulEvil;
        }
        return null;
    }

    public void startBigExplosion(Point2D point) {

        AnimatedSprite expl = new AnimatedSprite(this, graphics());
        expl.setSpriteType(SPRITE_EXPLOSION);
        expl.setAlive(true);
        expl.setAnimImage(explosions.getImage());
        expl.setTotalFrames(16);
        expl.setColumns(4);
        expl.setFrameWidth(96);
        expl.setFrameHeight(96);
        expl.setFrameDelay(2);
        expl.setPosition(point);

        sprites().add(expl);
        explosion.play();
    }

    public void warp(AnimatedSprite spr) {

        int w = spr.frameWidth() - 1;
        int h = spr.frameHeight() - 1;

        if (spr.position().X() < 0 - w) {
            spr.position().setX(SCREENWIDTH);
        } else if (spr.position().X() > SCREENWIDTH) {
            spr.position().setX(0 - w);
        }
        if (spr.position().Y() < 0 - h) {
            spr.position().setY(SCREENHEIGHT);
        } else if (spr.position().Y() > SCREENHEIGHT) {
            spr.position().setY(0 - h);
        }
    }

    public void incScore(int howmuch) {
        score += howmuch;
        if (score > highscore) {
            highscore = score;
        }
    }
}
