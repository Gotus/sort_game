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

        GameData gameData = new GameData();

        gameData.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gameData.showField();
            }
        });

        JLabel label = new JLabel("Расставьте фишки соответственно цветам, стоящим над столбцом");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.NORTH);

        setPreferredSize(new Dimension(800, 800));
        getContentPane().add(gameData, BorderLayout.CENTER);
        getContentPane().add(label, BorderLayout.NORTH);

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
