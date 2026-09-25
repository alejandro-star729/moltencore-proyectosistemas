package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MapCollision {

    // =========================================================
    // BORDES
    // =========================================================
    public Rectangle paredSuperior;
    public Rectangle paredInferior;
    public Rectangle paredIzquierda;
    public Rectangle paredDerechaSuperior;
    public Rectangle paredDerechaInferior;

    // Salida derecha
    public Rectangle paredSalidaSuperior;
    public Rectangle paredSalidaInferior;
    public Rectangle paredSalidaDerecha;

    // =========================================================
    // PORTAL
    // =========================================================
    public Rectangle portalParedDerecha;
    public Rectangle portalParedInferiorIzquierda;
    public Rectangle portalParedInferiorDerecha;
    public Rectangle portalAnilloSuperior;
    public Rectangle portalAnilloIzquierda;
    public Rectangle portalAnilloDerecha;
    public Rectangle portalAnilloInferior;

    // =========================================================
    // LAVA
    // IMPORTANTE: la lava se corta exactamente donde están
    // los puentes para que el centro del puente sea transitable.
    // =========================================================
    public Rectangle lavaSuperior;
    public Rectangle lavaMedia;
    public Rectangle lavaInferior;

    // Piedra que bordea la lava
    public Rectangle bordeLavaSuperiorIzquierda;
    public Rectangle bordeLavaSuperiorDerecha;
    public Rectangle bordeLavaMediaIzquierda;
    public Rectangle bordeLavaMediaDerecha;
    public Rectangle bordeLavaInferiorIzquierda;
    public Rectangle bordeLavaInferiorDerecha;

    // =========================================================
    // COLUMNAS
    // =========================================================
    public Rectangle columna1;
    public Rectangle columna2;
    public Rectangle columna3;
    public Rectangle columna4;
    public Rectangle columna5;
    public Rectangle columna6;

    // =========================================================
    // RUINAS DERECHA
    // =========================================================
    public Rectangle ruinaIzquierdaSuperior;
    public Rectangle ruinaSalienteIzquierda;
    public Rectangle ruinaIzquierdaInferior;

    public Rectangle ruinaSuperior;
    public Rectangle ruinaDerecha;
    public Rectangle ruinaDerechaInferior;

    public Rectangle ruinaInteriorSuperior;
    public Rectangle ruinaInteriorIzquierda;
    public Rectangle ruinaInteriorHorizontal;
    public Rectangle ruinaInteriorDerecha;
    public Rectangle ruinaInteriorDerechaSuperior;

    public Rectangle ruinaInferiorIzquierda;
    public Rectangle ruinaInferiorDerecha;

    public Rectangle ruinaPiedraPequena;
    public Rectangle ruinaVasija;

    private Rectangle[] colisionesSolidas;
    private Rectangle[] zonasLava;

    public MapCollision() {

        // =====================================================
        // BORDES DEL MAPA
        // =====================================================
        paredSuperior = new Rectangle(25, 20, 1490, 48);
        paredInferior = new Rectangle(25, 785, 1490, 43);
        paredIzquierda = new Rectangle(25, 20, 40, 808);

        // Dejamos libre la zona de salida.
        paredDerechaSuperior = new Rectangle(1490, 20, 30, 350);
        paredDerechaInferior = new Rectangle(1490, 535, 30, 275);

       // =========================================================
// SALIDA DERECHA HACIA EL LIBRO
// =========================================================

// Parte superior de la pared
paredSalidaSuperior = new Rectangle(
        1490, 370,
        115, 35
);

// Parte inferior de la pared
paredSalidaInferior = new Rectangle(
        1490, 500,
        115, 35
);

// IMPORTANTE:
// NO colocar paredSalidaDerecha aquí.
// El espacio entre Y=405 y Y=500 es la puerta.
paredSalidaDerecha = new Rectangle(
        1605, 20,
        30, 350
);

        // =====================================================
        // PORTAL
        // =====================================================
        portalParedDerecha = new Rectangle(370, 65, 25, 365);
        portalParedInferiorIzquierda = new Rectangle(50, 420, 110, 45);
        portalParedInferiorDerecha = new Rectangle(285, 420, 110, 45);

        // Anillo exterior. El centro permanece libre.
        portalAnilloSuperior = new Rectangle(145, 140, 160, 45);
        portalAnilloIzquierda = new Rectangle(110, 175, 40, 140);
        portalAnilloDerecha = new Rectangle(300, 175, 35, 140);
        portalAnilloInferior = new Rectangle(145, 315, 160, 40);

        // =====================================================
        // LAVA
        //
        // TOP:
        //   lava 70 -> 350
        //   PUENTE 350 -> 440
        //
        // MEDIO:
        //   lava 440 -> 565
        //   PUENTE 565 -> 660
        //
        // ABAJO:
        //   lava 660 -> 835
        //
        // Así el centro de ambos puentes queda libre.
        // =====================================================
        lavaSuperior = new Rectangle(690, 70, 120, 280);
        lavaMedia = new Rectangle(690, 440, 120, 125);
        lavaInferior = new Rectangle(690, 660, 120, 175);

        // =====================================================
        // BORDES DE PIEDRA DE LA LAVA
        // Estos sí bloquean, pero NO el centro del puente.
        // =====================================================
        bordeLavaSuperiorIzquierda = new Rectangle(655, 70, 35, 280);
        bordeLavaSuperiorDerecha = new Rectangle(810, 70, 35, 280);

        bordeLavaMediaIzquierda = new Rectangle(655, 440, 35, 125);
        bordeLavaMediaDerecha = new Rectangle(810, 440, 35, 125);

        bordeLavaInferiorIzquierda = new Rectangle(655, 660, 35, 175);
        bordeLavaInferiorDerecha = new Rectangle(810, 660, 35, 175);

        // =====================================================
        // COLUMNAS
        // =====================================================
        columna1 = new Rectangle(580, 210, 50, 100);
        columna2 = new Rectangle(875, 210, 50, 100);

        columna3 = new Rectangle(580, 460, 50, 105);
        columna4 = new Rectangle(875, 460, 50, 105);

        columna5 = new Rectangle(580, 655, 50, 105);
        columna6 = new Rectangle(920, 655, 50, 105);

        // =====================================================
        // RUINAS DERECHA
        // =====================================================

        // Pared exterior izquierda.
        ruinaIzquierdaSuperior = new Rectangle(1110, 65, 40, 160);

        // Saliente de piedra superior izquierdo.
        ruinaSalienteIzquierda = new Rectangle(1110, 225, 70, 30);

        // Parte vertical inferior izquierda.
        // Se mantiene hasta ANTES de la entrada inferior.
        ruinaIzquierdaInferior = new Rectangle(1130, 255, 20, 195);

        // Pared superior exterior.
        ruinaSuperior = new Rectangle(1215, 125, 240, 25);

        // Pared derecha exterior.
        ruinaDerecha = new Rectangle(1435, 145, 40, 265);
        ruinaDerechaInferior = new Rectangle(1510, 425, 40, 65);

        // Estructura interior superior.
        ruinaInteriorSuperior = new Rectangle(1250, 145, 140, 30);
        ruinaInteriorIzquierda = new Rectangle(1215, 145, 35, 180);
        ruinaInteriorHorizontal = new Rectangle(1215, 315, 115, 35);
        ruinaInteriorDerecha = new Rectangle(1390, 180, 40, 145);
        ruinaInteriorDerechaSuperior = new Rectangle(1370, 180, 60, 40);

        // =====================================================
        // PARTE INFERIOR DE LAS RUINAS
        //
        // IMPORTANTE:
        // Entre x=1335 y x=1380 queda la ENTRADA.
        // NO ponemos ninguna colisión ahí.
        // =====================================================
        ruinaInferiorIzquierda = new Rectangle(1130, 450, 205, 40);
        ruinaInferiorDerecha = new Rectangle(1380, 450, 75, 40);

        // Objetos internos.
        ruinaPiedraPequena = new Rectangle(1195, 365, 55, 45);
        ruinaVasija = new Rectangle(1270, 365, 10, 10);

        // =====================================================
        // TODAS LAS COLISIONES SÓLIDAS
        //
        // Los puentes NO aparecen aquí.
        // El centro de los puentes debe ser caminable.
        // =====================================================
        colisionesSolidas = new Rectangle[] {

            // Bordes
            paredSuperior,
            paredInferior,
            paredIzquierda,
            paredDerechaSuperior,
            paredDerechaInferior,

            // Salida
            paredSalidaSuperior,
            paredSalidaInferior,
            paredSalidaDerecha,

            // Portal
            portalParedDerecha,
            portalParedInferiorIzquierda,
            portalParedInferiorDerecha,
            portalAnilloSuperior,
            portalAnilloIzquierda,
            portalAnilloDerecha,
            portalAnilloInferior,

            // Piedra de la lava
            bordeLavaSuperiorIzquierda,
            bordeLavaSuperiorDerecha,
            bordeLavaMediaIzquierda,
            bordeLavaMediaDerecha,
            bordeLavaInferiorIzquierda,
            bordeLavaInferiorDerecha,

            // Columnas
            columna1,
            columna2,
            columna3,
            columna4,
            columna5,
            columna6,

            // Ruinas
            ruinaIzquierdaSuperior,
            ruinaSalienteIzquierda,
            ruinaIzquierdaInferior,
            ruinaSuperior,
            ruinaDerecha,
            ruinaDerechaInferior,
            ruinaInteriorSuperior,
            ruinaInteriorIzquierda,
            ruinaInteriorHorizontal,
            ruinaInteriorDerecha,
            ruinaInteriorDerechaSuperior,
            ruinaInferiorIzquierda,
            ruinaInferiorDerecha,
            ruinaPiedraPequena,
            ruinaVasija
        };

        // =====================================================
        // LAVA
        // =====================================================
        zonasLava = new Rectangle[] {
            lavaSuperior,
            lavaMedia,
            lavaInferior
        };
    }

    public Rectangle[] getColisionesSolidas() {
        return colisionesSolidas;
    }

    public Rectangle[] getZonasLava() {
        return zonasLava;
    }

    public void draw(Graphics2D g2) {

        // ROJO = colisiones sólidas
        g2.setColor(Color.RED);

        for (Rectangle r : colisionesSolidas) {
            g2.drawRect(r.x, r.y, r.width, r.height);
        }

        // NARANJA = lava
        g2.setColor(Color.ORANGE);

        for (Rectangle r : zonasLava) {
            g2.drawRect(r.x, r.y, r.width, r.height);
        }
    }
}
