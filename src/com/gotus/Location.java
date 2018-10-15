package com.gotus;

public class Location {

    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Location addLocation(Location location) {
        int new_x = this.x + location.getX();
        int new_y = this.y + location.getY();

        return new Location(new_x, new_y);
    }

}
