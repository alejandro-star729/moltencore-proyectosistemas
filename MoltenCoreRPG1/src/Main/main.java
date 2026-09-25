package main;

import javax.swing.JFrame;

public class main {

    public static void main(String[] args) {

        JFrame window = new JFrame();

        window.setTitle("Molten Core");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Mantiene la barra de tareas de Windows visible
        window.setUndecorated(false);

        // Maximiza la ventana, pero NO usa fullscreen exclusivo
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        window.setResizable(true);

        gamepanel gamePanel = new gamepanel();

        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);

        window.setVisible(true);

        // IMPORTANTE: pedir el teclado después de mostrar la ventana
        gamePanel.requestFocusInWindow();

        gamePanel.startGameThread();
    }
}
