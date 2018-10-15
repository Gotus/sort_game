package com.gotus;


import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class Field {

    private final int FIELD_SIZE = 5;
    private final int SQUARE_SIZE = 50;
    private final int STEP = 10;

    List<List<Cell>> fieldElements;

    public Field() {
        fieldElements = new ArrayList<>(FIELD_SIZE);

        for (int i = 0; i < FIELD_SIZE; i++) {
            fieldElements.add(new ArrayList<>(FIELD_SIZE));
            for (int j = 0; j < FIELD_SIZE; j++) {
                fieldElements.get(i).add(new Cell(j, i, SQUARE_SIZE));
            }
        }

        for (int i = 0; i < 5; i += 2) {
            for (int j = 1; j <= 3; j += 2) {
                setFieldElement(new Location(j, i),
                        CellState.BLOCKED);
            }
        }
    }

    public Cell getFieldElement(Location location) {
        return fieldElements.
                get(location.getY()).
                get(location.getX());
    }

    public void setFieldElement(Location location, CellState state) {
        fieldElements.
                get(location.getY()).
                get(location.getX()).
                setCellState(state);
    }

    public void paintComponent(Graphics g) {
        Location location;
        Color color;

        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                location = new Location(j, i);
                color = defineColor(this.
                                    getFieldElement(location).
                                    getCellState());
                g.setColor(color);
                fieldElements.get(i).get(j).paintComponent(g, STEP);
            }
        }
    }

    private Color defineColor(CellState state) {
        switch (state) {
            case TYPE1:
                return Color.RED;
            case TYPE2:
                return Color.GREEN;
            case TYPE3:
                return Color.BLUE;
            case BLOCKED:
                return Color.BLACK;
            case FREE:
                return Color.WHITE;
        }

        return null;
    }

    public int getSQUARE_SIZE() {
        return SQUARE_SIZE;
    }

    public int getFIELD_SIZE() {
        return FIELD_SIZE;
    }

    public int getSTEP() {
        return STEP;
    }
}
