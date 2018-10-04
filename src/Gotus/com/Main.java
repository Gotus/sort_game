package Gotus.com;

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
                
                ((Game)panel.getComponent(0)).showField();
            }
        });

        panel.add(new  Game());
        setPreferredSize(new Dimension(300, 300));
        getContentPane().add(panel);
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
