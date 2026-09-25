package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import entity.Jarron;
import entity.NPC;
import tile.TileManager;

public class gamepanel extends JPanel implements Runnable {

    // =========================================================
    // TAMAÑO REAL DE LA IMAGEN
    // =========================================================

    public final int screenWidth = 1638;
    public final int screenHeight = 864;

    // =========================================================
    // HILO DEL JUEGO
    // =========================================================

    Thread gameThread;

    // =========================================================
    // TECLADO
    // =========================================================

    public Keyhandler keyH = new Keyhandler();

    // =========================================================
    // COLISIONES
    // =========================================================

    public MapCollision mapCollision = new MapCollision();

    public Collisionchecker cChecker = new Collisionchecker(this);

    // =========================================================
    // NPC
    // =========================================================

    public NPC npc = new NPC(this);

    public NPC npcPortal = new NPC(this);

    // =========================================================
    // JARRÓN
    // =========================================================

    public Jarron jarron = new Jarron(this);

    // =========================================================
    // ESTADOS DE INTERACCIÓN
    // =========================================================

    public boolean hablandoConNPC = false;

    public boolean hablandoConJarron = false;

    public boolean tieneLlave = false;
    
    public boolean mostrandoIntro = true;
    
    public int paginaIntro = 0;
    
    public boolean desvaneciendoIntro = false;
    
    public int opacidadIntro = 255;
    
    String[] textoIntro = {

    "Dicen que dentro de cada libro existe un mundo...",

    "Pero algunos niños quedaron atrapados en sus páginas.",

    "Con el tiempo, aquellos que no pudieron regresar",

    "se convirtieron en los guardianes de esos mundos.",

    "Ahora, tú has sido absorbido por uno de ellos.",

    "Tu única forma de regresar es encontrar el camino",

    "y enfrentarte a aquello que habita dentro del libro."

};

    // =========================================================
    // JUGADOR
    // =========================================================

    public Player player = new Player(this, keyH);

    // =========================================================
    // MAPA
    // =========================================================

    BufferedImage fondo;

    // =========================================================
    // ESCAPE
    // =========================================================

    long escapeStartTime = 0;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public gamepanel() {

        this.setPreferredSize(
                new Dimension(screenWidth, screenHeight)
        );

        this.setBackground(Color.BLACK);

        this.setDoubleBuffered(true);

        this.setFocusable(true);

        this.addKeyListener(keyH);

        cargarMapa();

        // Posición inicial del jugador
        player.x = 198;
        player.y = 360;

        // Pedimos el foco del teclado
        this.requestFocusInWindow();
    }

    // =========================================================
    // DIÁLOGO DEL JARRÓN
    // =========================================================

    public void dibujarDialogoJarron(Graphics2D g2) {

        int x = 1050;
        int y = screenHeight - 365;

        int ancho = screenWidth - 1100;
        int alto = 100;

        // Fondo negro
        g2.setColor(Color.BLACK);
        g2.fillRect(x, y, ancho, alto);

        // Borde blanco
        g2.setColor(Color.WHITE);
        g2.drawRect(x, y, ancho, alto);
        g2.drawRect(
                x + 3,
                y + 3,
                ancho - 6,
                alto - 6
        );

        // Corazón rojo
        g2.setColor(Color.RED);

        g2.fillOval(
                x + 25,
                y + 25,
                15,
                15
        );

        g2.fillOval(
                x + 40,
                y + 25,
                15,
                15
        );

        g2.fillPolygon(
                new int[]{
                    x + 25,
                    x + 55,
                    x + 40
                },
                new int[]{
                    y + 35,
                    y + 35,
                    y + 65
                },
                3
        );

        // Texto
        g2.setColor(Color.WHITE);

        g2.setFont(
                new java.awt.Font(
                        "Arial",
                        java.awt.Font.PLAIN,
                        22
                )
        );

        g2.drawString(
                "Encontraste una llave dentro del jarrón.",
                x + 80,
                y + 45
        );

        g2.drawString(
                "Pulsa E para continuar.",
                x + 80,
                y + 80
        );
    
    }
    
    public void dibujarIntroduccion(Graphics2D g2) {

    // Fondo negro
    g2.setColor(Color.BLACK);
    g2.fillRect(0, 0, screenWidth, screenHeight);

    // Título
    g2.setColor(Color.WHITE);

    g2.setFont(
        new java.awt.Font(
            "Arial",
            java.awt.Font.BOLD,
            32
        )
    );

    String titulo = "MOLTEN CORE";

    int tituloX =
        (screenWidth - g2.getFontMetrics().stringWidth(titulo)) / 2;

    g2.drawString(
        titulo,
        tituloX,
        180
    );

    // Texto de la historia
    g2.setFont(
        new java.awt.Font(
            "Arial",
            java.awt.Font.PLAIN,
            24
        )
    );

    String texto = textoIntro[paginaIntro];

    int textoX =
        (screenWidth - g2.getFontMetrics().stringWidth(texto)) / 2;

    g2.drawString(
        texto,
        textoX,
        400
    );

    // Indicador
    g2.setFont(
        new java.awt.Font(
            "Arial",
            java.awt.Font.PLAIN,
            18
        )
    );

    g2.drawString(
        "[E] Continuar / Saltar",
        screenWidth - 230,
        screenHeight - 40
    );

    // Desvanecimiento
    if (desvaneciendoIntro) {

        g2.setColor(
            new Color(
                0,
                0,
                0,
                opacidadIntro
            )
        );

        g2.fillRect(
            0,
            0,
            screenWidth,
            screenHeight
        );
    }
}
    


    public boolean jugadorCercaDelMago() {

        Rectangle jugador = new Rectangle(
                player.x + player.solidArea.x,
                player.y + player.solidArea.y,
                player.solidArea.width,
                player.solidArea.height
        );

        Rectangle zonaMago = new Rectangle(
                npc.x + npc.interactionArea.x,
                npc.y + npc.interactionArea.y,
                npc.interactionArea.width,
                npc.interactionArea.height
        );

        return jugador.intersects(zonaMago);
    }

    // =========================================================
    // CARGAR MAPA
    // =========================================================

    public void cargarMapa() {

        try {

            fondo = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/resources/mapa.png"
                    )
            );

            System.out.println("==========================");
            System.out.println("MAPA CARGADO");

            System.out.println(
                    "ANCHO: " + fondo.getWidth()
            );

            System.out.println(
                    "ALTO: " + fondo.getHeight()
            );

            System.out.println("==========================");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // INICIAR JUEGO
    // =========================================================

    public void startGameThread() {

        gameThread = new Thread(this);

        gameThread.start();
    }

    // =========================================================
    // ACTUALIZAR JUEGO
    // =========================================================

    public void update() {

        
       

    // ==============================
    // INTRODUCCIÓN
    // ==============================

    // ==============================
// INTRODUCCIÓN
// ==============================

// ==============================
// INTRODUCCIÓN
// ==============================

if (mostrandoIntro) {

    if (desvaneciendoIntro) {

        opacidadIntro -= 5;

        if (opacidadIntro <= 0) {
            opacidadIntro = 0;
            mostrandoIntro = false;
            desvaneciendoIntro = false;
        }

        return;
    }

    if (keyH.interactPressed) {

        keyH.interactPressed = false;

        // Si estamos en el último texto
        if (paginaIntro == textoIntro.length - 1) {

            // NO aumentar paginaIntro
            // Comenzar directamente el fade
            desvaneciendoIntro = true;
            opacidadIntro = 255;

        } else {

            // Pasar al siguiente texto
            paginaIntro++;
        }
    }

    return;
}

        // ==========================================
        // SI ESTAMOS HABLANDO CON EL MAGO
        // ==========================================

        if (hablandoConNPC) {

            if (keyH.interactPressed) {

                keyH.interactPressed = false;

                // Avanzar diálogo
                if (npc.dialogueIndex
                        < npc.dialogue.length - 1) {

                    npc.dialogueIndex++;

                } else {

                    // Terminar diálogo
                    hablandoConNPC = false;

                    // Volver al primer diálogo
                    npc.dialogueIndex = 0;
                }
            }

            return;
        }

        // ==========================================
        // SI ESTAMOS HABLANDO CON EL JARRÓN
        // ==========================================

        if (hablandoConJarron) {

            if (keyH.interactPressed) {

                keyH.interactPressed = false;

                hablandoConJarron = false;
            }

            return;
        }

        // ==========================================
        // MOVIMIENTO DEL JUGADOR
        // ==========================================

        player.update();

        // ==========================================
        // INTERACCIÓN CON EL MAGO
        // ==========================================

        if (jugadorCercaDelMago()) {

            if (keyH.interactPressed) {

                System.out.println(
                        "INTERACTUANDO CON MAGO"
                );

                keyH.interactPressed = false;

                hablandoConNPC = true;
            }
        }

        // ==========================================
        // INTERACCIÓN CON EL JARRÓN
        // ==========================================

        if (jugadorCercaDelJarron()) {

            if (keyH.interactPressed) {

                keyH.interactPressed = false;

                if (!jarron.llaveObtenida) {

                    jarron.llaveObtenida = true;

                    tieneLlave = true;

                    hablandoConJarron = true;

                    System.out.println(
                            "=============================="
                    );

                    System.out.println(
                            "¡LLAVE OBTENIDA!"
                    );

                    System.out.println(
                            "=============================="
                    );

                } else {

                    hablandoConJarron = true;
                }
            }
        }

        // ==========================================
        // ESCAPE
        // ==========================================

        if (keyH.escapePressed) {

            if (escapeStartTime == 0) {

                escapeStartTime =
                        System.currentTimeMillis();
            }

            long tiempoPresionado =
                    System.currentTimeMillis()
                    - escapeStartTime;

            if (tiempoPresionado >= 3000) {

                System.exit(0);
            }

        } else {

            escapeStartTime = 0;
        }
    }

    // =========================================================
    // DETECTAR CERCANÍA AL JARRÓN
    // =========================================================

    public boolean jugadorCercaDelJarron() {

        Rectangle jugador = new Rectangle(
                player.x + player.solidArea.x,
                player.y + player.solidArea.y,
                player.solidArea.width,
                player.solidArea.height
        );

        Rectangle areaJarron = new Rectangle(
                jarron.x,
                jarron.y,
                jarron.solidArea.width,
                jarron.solidArea.height
        );

        Rectangle zonaInteraccion = new Rectangle(
                areaJarron.x - 60,
                areaJarron.y - 60,
                areaJarron.width + 60,
                areaJarron.height + 60
        );

        return jugador.intersects(zonaInteraccion);
    }

    // =========================================================
    // DIÁLOGO DEL MAGO
    // =========================================================

    public void dibujarDialogo(Graphics2D g2) {

        // ==================================================
        // POSICIÓN Y TAMAÑO DE LA CAJA
        // ==================================================

        int x = 80;
        int y = screenHeight - 190;

        int width = screenWidth - 160;
        int height = 130;

        // ==================================================
        // CAJA NEGRA
        // ==================================================

        g2.setColor(Color.BLACK);

        g2.fillRect(
                x,
                y,
                width,
                height
        );

        // ==================================================
        // BORDE BLANCO
        // ==================================================

        g2.setColor(Color.WHITE);

        g2.drawRect(
                x,
                y,
                width,
                height
        );

        // ==================================================
        // CORAZÓN
        // ==================================================

        g2.setColor(Color.RED);

        g2.setFont(
                new java.awt.Font(
                        "Arial",
                        java.awt.Font.BOLD,
                        30
                )
        );

        g2.drawString(
                "♥",
                x + 20,
                y + 45
        );

        // ==================================================
        // NOMBRE DEL NPC
        // ==================================================

        g2.setColor(Color.WHITE);

        g2.setFont(
                new java.awt.Font(
                        "Arial",
                        java.awt.Font.BOLD,
                        20
                )
        );

        g2.drawString(
                "MAGO",
                x + 65,
                y + 28
        );

        // ==================================================
        // TEXTO
        // ==================================================

        g2.setFont(
                new java.awt.Font(
                        "Arial",
                        java.awt.Font.PLAIN,
                        20
                )
        );

        String texto =
                npc.dialogue[npc.dialogueIndex];

        g2.drawString(
                texto,
                x + 65,
                y + 65
        );

        // ==================================================
        // INDICADOR PARA CONTINUAR
        // ==================================================

        g2.setFont(
                new java.awt.Font(
                        "Arial",
                        java.awt.Font.PLAIN,
                        16
                )
        );

        g2.drawString(
                "[E] Continuar",
                x + width - 140,
                y + height - 15
        );
    }

    // =========================================================
    // BUCLE PRINCIPAL
    // =========================================================

    @Override
    public void run() {

        while (gameThread != null) {

            update();

            repaint();

            try {

                Thread.sleep(16);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    // =========================================================
    // DIBUJAR TODO
    // =========================================================

    @Override
protected void paintComponent(Graphics g) {

    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g.create();

    double escalaX = (double) getWidth() / screenWidth;
    double escalaY = (double) getHeight() / screenHeight;

    g2.scale(escalaX, escalaY);

    // ==============================
    // INTRODUCCIÓN
    // ==============================

    if (mostrandoIntro) {

        dibujarIntroduccion(g2);

        g2.dispose();

        return;
    }

    // ==============================
    // JUEGO NORMAL
    // ==============================

    if (fondo != null) {
        g2.drawImage(
            fondo,
            0,
            0,
            screenWidth,
            screenHeight,
            null
        );
    }

    player.draw(g2);

    if (npc != null) {
        npc.draw(g2);
    }

    if (npcPortal != null) {
        npcPortal.draw(g2);
    }

    if (hablandoConJarron) {
        dibujarDialogoJarron(g2);
    }

    if (hablandoConNPC) {
        dibujarDialogo(g2);
    }

    g2.dispose();
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}

