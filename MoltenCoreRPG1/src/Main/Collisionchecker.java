package main;

import entity.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Collisionchecker {

    gamepanel gp;
    MapCollision map;

    public Collisionchecker(gamepanel gp) {

        this.gp = gp;
        this.map = gp.mapCollision;
    }

    // =====================================================
    // COMPROBAR COLISIONES
    // =====================================================

    public boolean checkTile(Player player, int dx, int dy) {

        Rectangle playerArea = new Rectangle(
                player.x + player.solidArea.x + dx,
                player.y + player.solidArea.y + dy,
                player.solidArea.width,
                player.solidArea.height
        );

        // ==========================================
        // OBSTÁCULOS
        // ==========================================

        for (Rectangle obstaculo : map.getColisionesSolidas()) {

            if (playerArea.intersects(obstaculo)) {
                return true;
            }
        }

        // ==========================================
        // LAVA
        // ==========================================

        for (Rectangle lava : map.getZonasLava()) {

            if (playerArea.intersects(lava)) {

                // Posición de respawn
                player.x = 270;
                player.y = 500;

                return true;
            }
        }

        return false;
    }

    // =====================================================
    // MOSTRAR COLISIONES
    // =====================================================

    public void drawDebug(Graphics2D g2) {

        // ------------------------------------------
        // OBSTÁCULOS = ROJO
        // ------------------------------------------

        g2.setColor(new Color(255, 0, 0, 100));

        for (Rectangle obstaculo : map.getColisionesSolidas()) {

            g2.fillRect(
                    obstaculo.x,
                    obstaculo.y,
                    obstaculo.width,
                    obstaculo.height
            );
        }

        // ------------------------------------------
        // LAVA = NARANJA
        // ------------------------------------------

        g2.setColor(new Color(255, 120, 0, 100));

        for (Rectangle lava : map.getZonasLava()) {

            g2.fillRect(
                    lava.x,
                    lava.y,
                    lava.width,
                    lava.height
            );
        }
    }

    // =====================================================
    // HITBOX DEL JUGADOR
    // =====================================================

    public void drawPlayerHitbox(Graphics2D g2, Player player) {

        g2.setColor(Color.GREEN);

        g2.drawRect(
                player.x + player.solidArea.x,
                player.y + player.solidArea.y,
                player.solidArea.width,
                player.solidArea.height
        );
    }
}