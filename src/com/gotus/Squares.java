package com.gotus;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Squares extends JPanel {

    private static final int HEIGHT = 15;
    private static final int WIDTH = HEIGHT;

    private List<Rectangle> squares = new ArrayList<Rectangle>();

    public void addSquare(int x, int y, int width, int height) {

        Rectangle rect = new Rectangle(x, y, width, height);

        squares.add(rect);
    }

    public Dimension getPreferedSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Rectangle rect : squares) {
            g2.draw(rect);
        }
    }
}
