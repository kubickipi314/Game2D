package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        solidArea = new Rectangle();
        solidArea.x = gp.tileSize/4;
        solidArea.y = gp.tileSize/2;
        solidArea.width = gp.tileSize/2;
        solidArea.height = gp.tileSize/2;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 19;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/character_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/character_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/character_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/character_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/character_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/character_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/character_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/character_right_2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        boolean keyPressed = false;

        if (keyH.upPressed) {
            direction = "up";
            spriteCounter++;
            keyPressed = true;
        }
        if (keyH.downPressed) {
            direction = "down";
            spriteCounter++;
            keyPressed = true;
        }
        if (keyH.leftPressed) {
            direction = "left";
            spriteCounter++;
            keyPressed = true;
        }
        if (keyH.rightPressed) {
            direction = "right";
            spriteCounter++;
            keyPressed = true;
        }

        collisionOn = false;
        gp.cChecker.checkTile(this);

        if (!collisionOn && keyPressed){

            switch(direction) {
                case "up":  worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }

        if (spriteCounter > 5) {
            spriteCounter = 0;
            spriteNum = !spriteNum;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum)
                    image = up1;
                else image = up2;
                break;
            case "down":
                if (spriteNum)
                    image = down1;
                else image = down2;
                break;
            case "left":
                if (spriteNum)
                    image = left1;
                else image = left2;
                break;
            case "right":
                if (spriteNum)
                    image = right1;
                else image = right2;
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
