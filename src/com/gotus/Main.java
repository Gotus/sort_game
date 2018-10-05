package com.gotus;

import layout_managers.GameLayoutManager;

import javax.swing.*;
import javax.swing.border.*;
import javax.accessibility.*;
import java.awt.*;
import java.awt.event.*;

class TestFrame extends JFrame {

    private JLabel label;

    public TestFrame() {
        JFrame frame = new JFrame("Test Frame");
        
        createGUI();
    }

    public void createGUI() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        //panel.setLayout(new GridLayout());


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
        label.setAlignmentY(100f);

        Squares squares = new Squares();

        setPreferredSize(new Dimension(800, 800));
        getContentPane().add(panel, BorderLayout.SOUTH);
        getContentPane().add(label, BorderLayout.NORTH);
        getContentPane().add(squares, BorderLayout.CENTER);


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                squares.addSquare(250 + j*60, 250 + i*60, 50, 50);
            }
        }
        //setLocationRelativeTo(null);
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
