package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/character_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/character_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/character_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/character_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/character_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/character_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/character_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/character_right_2.png")));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if (keyH.upPressed) {
            direction = "up";
            y -= speed;
            spriteCounter++;
        }
        if (keyH.downPressed) {
            direction = "down";
            y += speed;
            spriteCounter++;
        }
        if (keyH.leftPressed) {
            direction = "left";
            x -= speed;
            spriteCounter++;
        }
        if (keyH.rightPressed) {
            direction = "right";
            x += speed;
            spriteCounter++;
        }

        if (spriteCounter > 5){
            spriteCounter = 0;
            spriteNum = !spriteNum;
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch(direction) {
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

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }
}
