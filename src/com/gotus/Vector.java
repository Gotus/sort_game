package com.gotus;

public class Vector {

    private int x;
    private int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector addVector(Vector vector) {
        int new_x = this.x + vector.getX();
        int new_y = this.y + vector.getY();
        return new Vector(new_x, new_y);
    }

}
