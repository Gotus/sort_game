package com.gotus;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Squares extends JPanel {

    private final int SIZE = 50;
    private final int STEP = 10;
    private final int NUM_ROWS = 5;
    private final int NUM_COLUMNS = 5;

    private List<Rectangle> squares = new ArrayList<>();

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Rectangle rect : squares) {
            g2.draw(rect);
        }
    }

    int getSIZE() {

        return SIZE;
    }

    int getSTEP() {
        return STEP;
    }

    int getNUM_ROWS() {
        return NUM_ROWS;
    }

    int getNUM_COLUMNS() {
        return NUM_COLUMNS;
    }
}
