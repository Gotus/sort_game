package com.gotus;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Squares extends JPanel {

    private static final int HEIGHT = 50;
    private static final int WIDTH = HEIGHT;
    private static final int STEP = 10;
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLUMNS = 5;



    private List<Rectangle> squares = new ArrayList<Rectangle>();

    public void addSquare(int x, int y, int width, int height) {

        Rectangle rect = new Rectangle(x, y, width, height);

        squares.add(rect);
    }

    public Dimension getPreferedSize() {
        return new Dimension(NUM_COLUMNS * (STEP + WIDTH),
                NUM_ROWS * (STEP + HEIGHT));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Rectangle rect : squares) {
            g2.draw(rect);
        }
    }
}
