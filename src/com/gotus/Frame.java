package com.gotus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Frame extends JFrame {

    Frame() {
        createGUI();
    }

    private void createGUI() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());

        GameData gameData = new GameData();

        JLabel label = new JLabel("Расставьте фишки соответственно цветам, стоящим над столбцом");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.NORTH);

        setPreferredSize(new Dimension(800, 800));
        getContentPane().add(gameData, BorderLayout.CENTER);
        getContentPane().add(label, BorderLayout.NORTH);

        gameData.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gameData.move(e.getKeyCode());
                repaint();
            }
        });

        gameData.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                gameData.setSelectedCell(e.getX(), e.getY());

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        gameData.setFocusable(true);
        gameData.requestFocusInWindow();

        setVisible(true);
    }
}
