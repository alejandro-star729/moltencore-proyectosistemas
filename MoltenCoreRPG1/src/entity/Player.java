package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Keyhandler;
import main.gamepanel;

public class Player {

    // =========================================================
    // REFERENCIAS
    // =========================================================

    gamepanel gp;
    Keyhandler keyH;

    // =========================================================
    // POSICIÓN
    // =========================================================

    public int x;
    public int y;

    // =========================================================
    // VELOCIDAD
    // =========================================================

    public int speed;

    // =========================================================
    // ÁREA DE COLISIÓN
    // =========================================================

    public Rectangle solidArea;

    public boolean collisionOn = false;

    // =========================================================
    // DIRECCIÓN
    // =========================================================

    String direction;

    // =========================================================
    // SPRITES
    // =========================================================

    BufferedImage up1, up2;
    BufferedImage abajo1, abajo2;
    BufferedImage left1, left2;
    BufferedImage right1, right2;

    // =========================================================
    // ANIMACIÓN
    // =========================================================

    int spriteCounter = 0;
    int spriteNum = 1;

    // =========================================================
    // TAMAÑO DEL PERSONAJE
    // =========================================================
    //
    // Lo dejamos separado de tileSize.
    // Así podemos cambiar el tamaño del jugador
    // sin afectar el mapa.
    //

    final int playerWidth = 48;
    final int playerHeight = 48;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Player(gamepanel gp, Keyhandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();

        getPlayerImage();
    }

    // =========================================================
    // VALORES INICIALES
    // =========================================================

    public void setDefaultValues() {

        // Posición inicial
        x = 250;
        y = 430;

        // Velocidad
        speed = 3;

        // Dirección
        direction = "down";

        // =====================================================
        // CAJA DE COLISIÓN
        // =====================================================
        //
        // La caja queda dentro del sprite.
        //
        // x = 8
        // y = 12
        // ancho = 28
        // alto = 34
        //

        solidArea = new Rectangle();

        solidArea.x = 8;
        solidArea.y = 12;

        solidArea.width = 28;
        solidArea.height = 34;
    }

    // =========================================================
    // CARGAR SPRITES
    // =========================================================

    public void getPlayerImage() {

        try {

            System.out.println(
                    getClass().getResource(
                            "/resources/up1.png"
                    )
            );

            System.out.println(
                    getClass().getResource(
                            "/resources/up2.png"
                    )
            );

            System.out.println(
                    getClass().getResource(
                            "/resources/abajo1.png"
                    )
            );

            System.out.println(
                    getClass().getResource(
                            "/resources/abajo2.png"
                    )
            );

            System.out.println(
                    getClass().getResource(
                            "/resources/left1.png"
                    )
            );

            System.out.println(
                    getClass().getResource(
                            "/resources/left2.png"
                    )
            );

            System.out.println(
                    getClass().getResource(
                            "/resources/right1.png"
                    )
            );

            System.out.println(
                    getClass().getResource(
                            "/resources/right2.png"
                    )
            );

            up1 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/resources/up1.png"
                    )
            );

            up2 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/resources/up2.png"
                    )
            );

            abajo1 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/resources/abajo1.png"
                    )
            );

            abajo2 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/resources/abajo2.png"
                    )
            );

            left1 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/resources/left1.png"
                    )
            );

            left2 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/resources/left2.png"
                    )
            );

            right1 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/resources/right1.png"
                    )
            );

            right2 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/resources/right2.png"
                    )
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // ACTUALIZAR
    // =========================================================

    public void update() {

        boolean moving = false;

        int dx = 0;
        int dy = 0;

        // =====================================================
        // DIRECCIÓN
        // =====================================================

        if (keyH.upPressed) {

            dy = -speed;
            direction = "up";
            moving = true;
        }

        if (keyH.downPressed) {

            dy = speed;
            direction = "down";
            moving = true;
        }

        if (keyH.leftPressed) {

            dx = -speed;
            direction = "left";
            moving = true;
        }

        if (keyH.rightPressed) {

            dx = speed;
            direction = "right";
            moving = true;
        }

        // =====================================================
        // MOVIMIENTO HORIZONTAL
        // =====================================================

        if (dx != 0) {

            if (!gp.cChecker.checkTile(this, dx, 0)) {

                x += dx;
            }
        }

        // =====================================================
        // MOVIMIENTO VERTICAL
        // =====================================================

        if (dy != 0) {

            if (!gp.cChecker.checkTile(this, 0, dy)) {

                y += dy;
            }
        }

        // =====================================================
        // ANIMACIÓN
        // =====================================================

        if (moving) {

            spriteCounter++;

            if (spriteCounter > 10) {

                if (spriteNum == 1) {

                    spriteNum = 2;

                } else {

                    spriteNum = 1;
                }

                spriteCounter = 0;
            }

        } else {

            // Imagen quieto
            spriteNum = 1;

            spriteCounter = 0;
        }
    }

    // =========================================================
    // DIBUJAR JUGADOR
    // =========================================================

    public void draw(Graphics g) {

        BufferedImage image = null;

        switch (direction) {

            case "up":

                if (spriteNum == 1) {

                    image = up1;

                } else {

                    image = up2;
                }

                break;

            case "down":

                if (spriteNum == 1) {

                    image = abajo1;

                } else {

                    image = abajo2;
                }

                break;

            case "left":

                if (spriteNum == 1) {

                    image = left1;

                } else {

                    image = left2;
                }

                break;

            case "right":

                if (spriteNum == 1) {

                    image = right1;

                } else {

                    image = right2;
                }

                break;
        }

        // =====================================================
        // DIBUJAR
        // =====================================================

        if (image != null) {

            g.drawImage(
                    image,
                    x,
                    y,
                    playerWidth,
                    playerHeight,
                    null
            );
        }
    }
}