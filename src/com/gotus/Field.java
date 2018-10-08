package com.gotus;

import java.util.ArrayList;
import java.util.List;

public class Field {

    List<List<Cell>> fieldElements;

    private static final int FIELD_SIZE = 5;

    public Field() {

        //Инициализация игрового поля
        fieldElements = new ArrayList<>(FIELD_SIZE);

        for (int i = 0; i < FIELD_SIZE; i++) {

            fieldElements.add(new ArrayList<>(FIELD_SIZE));
            for (int j = 0; j < FIELD_SIZE; j++) {

                fieldElements.get(i).add(new  Cell(j, i, CellState.FREE));
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
}
