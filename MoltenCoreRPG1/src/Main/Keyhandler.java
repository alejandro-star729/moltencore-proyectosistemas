package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyhandler implements KeyListener {

    // ==========================================
    // MOVIMIENTO
    // ==========================================

    public boolean upPressed = false;
    public boolean downPressed = false;
    public boolean leftPressed = false;
    public boolean rightPressed = false;

    // ==========================================
    // ESCAPE
    // ==========================================

    public boolean escapePressed = false;

    // ==========================================
    // INTERACCIÓN
    // ==========================================

    public boolean interactPressed = false;

    // ==========================================
    // CUANDO SE PRESIONA UNA TECLA
    // ==========================================

@Override
public void keyPressed(KeyEvent e) {

    int code = e.getKeyCode();

    if (code == KeyEvent.VK_W) {
        upPressed = true;
    }

    if (code == KeyEvent.VK_S) {
        downPressed = true;
    }

    if (code == KeyEvent.VK_A) {
        leftPressed = true;
    }

    if (code == KeyEvent.VK_D) {
        rightPressed = true;
    }

    if (code == KeyEvent.VK_UP) {
        upPressed = true;
    }

    if (code == KeyEvent.VK_DOWN) {
        downPressed = true;
    }

    if (code == KeyEvent.VK_LEFT) {
        leftPressed = true;
    }

    if (code == KeyEvent.VK_RIGHT) {
        rightPressed = true;
    }

    if (code == KeyEvent.VK_E) {
        interactPressed = true;
    }

    if (code == KeyEvent.VK_ESCAPE) {
        escapePressed = true;
    }
}

    // ==========================================
    // CUANDO SE SUELTA UNA TECLA
    // ==========================================

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        // ------------------------------------------
        // ARRIBA
        // ------------------------------------------

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }

        // ------------------------------------------
        // ABAJO
        // ------------------------------------------

        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }

        // ------------------------------------------
        // IZQUIERDA
        // ------------------------------------------

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }

        // ------------------------------------------
        // DERECHA
        // ------------------------------------------

        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

        // ------------------------------------------
        // ESCAPE
        // ------------------------------------------

        if (code == KeyEvent.VK_ESCAPE) {
            escapePressed = false;
        }

        // ------------------------------------------
        // INTERACTUAR - E
        // ------------------------------------------

        if (code == KeyEvent.VK_E) {
            interactPressed = false;
        }
    }

    // ==========================================
    // CUANDO SE ESCRIBE UNA TECLA
    // ==========================================

    @Override
    public void keyTyped(KeyEvent e) {
        // No necesitamos utilizar este método.
    }
}