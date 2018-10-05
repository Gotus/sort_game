package Gotus.com;

import javafx.scene.image.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TestFrame extends JFrame {

    private JLabel label;

    public TestFrame() {
        super("Test Frame");
        createGUI();
    }

    public void createGUI() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setFocusable(true);

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                int keyCode = e.getKeyCode();

                switch (keyCode) {
                    case 27:
                        System.exit(0);
                        break;
                }
                ((GameData)panel.getComponent(0)).showField();
            }
        });

        panel.add(new GameData());

        JLabel label = new JLabel("Расставьте фишки соответственно цветам, стоящим над столбцом");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.NORTH);

        setPreferredSize(new Dimension(600, 600));
        getContentPane().add(panel);
        getContentPane().add(label);
        setVisible(true);
    }
}

public class Main {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                TestFrame frame = new TestFrame();
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
