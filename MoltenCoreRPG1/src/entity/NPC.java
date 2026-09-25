package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import main.gamepanel;

public class NPC {

    gamepanel gp;

    // ==================================================
    // POSICIÓN
    // ==================================================

    public int x;
    public int y;

    // ==================================================
    // TAMAÑO
    // ==================================================

    public int width = 120;
    public int height = 96;

    // ==================================================
    // COLISIÓN
    // ==================================================

    public Rectangle solidArea;
    
    public Rectangle interactionArea;

    // ==================================================
    // IMAGEN
    // ==================================================

    BufferedImage image;

    // ==================================================
    // DIÁLOGO
    // ==================================================

public String[] dialogue = {

    "Has conseguido atravesar el portal...",

    "Si quieres continuar tu viaje, primero debes encontrar una llave.",

    "La llave se encuentra escondida dentro de un frasco.",

    "Busca cuidadosamente entre las ruinas.",

    "Cuando encuentres la llave, podras abrir el camino hacia el libro.",

    "Ese libro es el siguiente paso de tu aventura.",

    "Buena suerte. Las ruinas pueden esconder mas de lo que parece.",
        
    "pero te dire un acertijo para que te sea mas sencillo",
    
    "dice. ¡Atento, valiente aventurero! Si quieres abrir el gran",
    
    "libro del origen y seguir tu camino, debes descifrar mi acertijo:",
    
    "Duermo en silencio entre muros caídos donde el tiempo ha guardado sus viejos olvidos.",
    
    "No guardo agua fresca ni flores de jardín y mi panza de barro esconde un gran fin.",
    
    "Dentro de mí vive un brillo de metal que es la única llave que abre este portal.",
    
    "¿En dónde estoy y qué debes buscar?",

};

    public int dialogueIndex = 0;

    // ==================================================
    // CONSTRUCTOR
    // ==================================================

    public NPC(gamepanel gp) {

        this.gp = gp;

        setDefaultValues();
        loadImage();
    }

    // ==================================================
    // VALORES INICIALES
    // ==================================================

    public void setDefaultValues() {

        x = 40;
        y = 470;

        solidArea = new Rectangle();

        solidArea.x = 20;
        solidArea.y = 20;
        solidArea.width = 240;
        solidArea.height = 210;
        
        interactionArea = new Rectangle(
        20,
        20,
        50,
        50
);
    }

    // ==================================================
    // CARGAR IMAGEN
    // ==================================================

    public void loadImage() {

        try {

            image = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/resources/mago.png"
                    )
            );

            System.out.println(
                    "Mago cargado correctamente."
            );

        } catch (Exception e) {

            System.out.println(
                    "ERROR: No se pudo cargar mago.png"
            );

            e.printStackTrace();
        }
    }

    // ==================================================
    // DIBUJAR MAGO
    // ==================================================

    public void draw(Graphics2D g2) {

        if (image != null) {

            g2.drawImage(
                    image,
                    x,
                    y,
                    width,
                    height,
                    null
            );
        }
    }
    public void interact() {

    if (dialogueIndex < dialogue.length - 1) {

        dialogueIndex++;

    } else {

        dialogueIndex = 0;
    }
}
}