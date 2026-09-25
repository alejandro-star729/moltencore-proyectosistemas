package entity;

import java.awt.Rectangle;
import main.gamepanel;

public class Jarron {

    gamepanel gp;

    public int x;
    public int y;

    public Rectangle solidArea;

    public boolean llaveObtenida = false;

    public Jarron(gamepanel gp) {
        this.gp = gp;
        setDefaultValues();
    }

    public void setDefaultValues() {

        // ==================================
        // COORDENADAS DEL JARRÓN
        // ==================================

        x = 1270;
        y = 365;

        // ==================================
        // TAMAÑO DE LA ZONA DEL JARRÓN
        // ==================================

        solidArea = new Rectangle(
                0,
                0,
                45,
                40
        );
    }
}