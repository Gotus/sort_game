package com.gotus;

import javax.swing.*;
import java.awt.*;

public class Square extends JPanel {

    private Rectangle square;
    private final int STEP = 10;

    public Square(Vector location, int size) {

        this.square = new Rectangle(location.getX(), location.getY(), size, size);

    }

    public int getSquareSize() {
        return (int) square.getHeight();
    }

    public Vector getSquareCoordinates() {
        return new Vector((int) square.getX(), (int) square.getY());
    }

    public void paintComponent(Graphics g) {

        int x = (int) square.getX();
        int y = (int) square.getY();
        int size = (int) square.getHeight();
        g.fillRect(250 + x*(size + STEP), 250 + y*(size + STEP), size, size);
    }

}
