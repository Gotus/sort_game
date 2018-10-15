package com.gotus;

import javax.swing.*;
import java.awt.*;

public class Square extends JPanel {

    private Rectangle square;

    public Square(Location location, int size) {
        this.square = new Rectangle(location.getX(),
                                    location.getY(),
                                    size,
                                    size);
    }

    public int getSquareSize() {
        return (int) square.getHeight();
    }

    public Location getSquareCoordinates() {
        return new Location((int) square.getX(),
                            (int) square.getY());
    }

    public void paintComponent(Graphics g, int step) {
        long x = Math.round(square.getX());
        long y = Math.round(square.getY());
        long size = Math.round(square.getHeight());

        g.fillRect((int) (250 + x * (size + step)),
                   (int) (250 + y * (size + step)),
                   (int) size,
                   (int) size);
    }

}
