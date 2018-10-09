package com.gotus;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Field {

    private static final int FIELD_SIZE = 5;

    List<List<Cell>> fieldElements;

    public Field(int cellSize) {

        //Инициализация игрового поля
        fieldElements = new ArrayList<>(FIELD_SIZE);

        for (int i = 0; i < FIELD_SIZE; i++) {

            fieldElements.add(new ArrayList<>(FIELD_SIZE));
            for (int j = 0; j < FIELD_SIZE; j++) {

                fieldElements.get(i).add(new  Cell(j, i, cellSize));
            }
        }

        //Расстановка непроходимых ячеек
        for (int i = 0; i < 5; i += 2) {
            for (int j = 1; j <= 3; j+= 2) {
                setFieldElement(new Vector(j, i), CellState.BLOCKED);
            }
        }
    }

    public Cell getFieldElement(Vector vector) {
        return fieldElements.get(vector.getY()).get(vector.getX());
    }

    public void setFieldElement(Vector vector, CellState state) {
        fieldElements.get(vector.getY()).get(vector.getX()).setCellState(state);
    }

    public void paintComponent(Graphics g) {

        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                Color color = defineColor(this.getFieldElement(new Vector(j, i)).getCellState());
                g.setColor(color);
                fieldElements.get(i).get(j).paintComponent(g);
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
}
