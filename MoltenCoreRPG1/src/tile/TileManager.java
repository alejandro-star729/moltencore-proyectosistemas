package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import main.gamepanel;

public class TileManager {

    gamepanel gp;

    tile[] tile;

    public TileManager(gamepanel gp) {

        this.gp = gp;

        tile = new tile[10];

        getTileImage();
    }

    // =========================================================
    // CARGAR IMÁGENES DE TILES
    // =========================================================

    public void getTileImage() {

        tile[0] = new tile();
        tile[1] = new tile();
        tile[2] = new tile();
        tile[3] = new tile();

        try {

            tile[0].image = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/resources/piso.png"
                    )
            );

            tile[1].image = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/resources/pared.png"
                    )
            );

            tile[2].image = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/resources/lava.png"
                    )
            );

            tile[3].image = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/resources/piedra.png"
                    )
            );

        } catch (Exception e) {

            System.out.println(
                    "Error cargando los tiles:"
            );

            e.printStackTrace();
        }
    }

    // =========================================================
    // DIBUJAR TILES
    // =========================================================
    //
    // POR AHORA NO LO UTILIZAMOS.
    //
    // El mapa completo se dibuja mediante mapa.png
    // desde gamepanel.
    //
    // =========================================================

    public void draw(Graphics2D g2) {

        // No dibujamos tiles aquí.
        //
        // El mapa completo se encuentra en:
        //
        // /resources/mapa.png
        //
        // y gamepanel se encarga de dibujarlo.
    }
}