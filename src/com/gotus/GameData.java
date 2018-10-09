package com.gotus;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameData extends JPanel {

    private Field field;

    private Cell selectedCell;

    private int field_size;

    private boolean gameOver;

    public GameData() {

        field = new Field();

        field_size = field.getFIELD_SIZE();

        placeGameChip();

        selectedCell = field.getFieldElement(new Vector(0, 0));

        gameOver = false;

}

    private void placeGameChip() {
        int num_type[] = {5, 5, 5};
        int value;
        List<Cell> available_cells = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < field_size; i++) {
            for (int j = 0; j < field_size; j += 2) {

                    available_cells.add(new Cell(j, i, field.getSQUARE_SIZE()));
            }
        }

        for (Cell i: available_cells) {
            boolean is_not_generated = true;
            int x;
            int y;
            while (is_not_generated) {

                value = rand.nextInt(3);

                if (num_type[value] > 0) {
                    x = i.getX();
                    y = i.getY();
                    field.setFieldElement(new Vector(x, y), defineCellState(value));
                    num_type[value]--;
                    is_not_generated = false;
                }
            }
        }
    }

    private void placeGameChipAlmostVictory() {

        CellState stateArray[] = {CellState.TYPE1, CellState.TYPE2, CellState.TYPE3};

        for (int j = 0; j < field_size; j += 2) {
            for (int i = 0; i < field_size; i++) {
                field.setFieldElement(new Vector(j, i), stateArray[j/2]);
            }
        }

        field.setFieldElement(new Vector(1, 0), CellState.FREE);
        field.setFieldElement(new Vector(1, 1), CellState.FREE);
    }

    public void showField() {
        for (int i = 0; i < field_size; i++) {

            for (int j = 0; j < field_size; j++) {
                System.out.print(field.getFieldElement(new Vector(j, i)).getCellState() + " ");
            }

            System.out.println();
        }
    }

    private CellState defineCellState(int type) {
        switch (type) {
            case 0:
                return CellState.TYPE1;
            case 1:
                return CellState.TYPE2;
            case 2:
                return CellState.TYPE3;
        }

        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {

        field.paintComponent(g);

    }

    public void setSelectedCell(int x, int y) {

        if (gameOver) {
            return;
        }

        int i = (y - 250) / 60;
        int j = (x - 250) / 60;

        if ((j < field_size) && (i < field_size)
                && (i >= 0) && (j >= 0)) {
            Cell selectedCell = field.getFieldElement(new Vector(j, i));
            if ((selectedCell.getCellState() != CellState.FREE) &&
                    (selectedCell.getCellState() != CellState.BLOCKED)) {
                this.selectedCell = selectedCell;
            }
        }
    }

    public Cell getSelectedCell() {
        return selectedCell;
    }

    public void move(int keyCode) {

        if (gameOver) {
            if (keyCode == 27) {
                System.exit(0);
            }
            return;
        }

        int x = selectedCell.getX();
        int y = selectedCell.getY();
        CellState state = selectedCell.getCellState();
        Vector movingVector = getDirection(keyCode);
        Vector currentVector = new Vector(x, y);
        Vector changedVector = currentVector.addVector(movingVector);

        if ((changedVector.getX() >= 0) && (changedVector.getY() >= 0)
                && (changedVector.getX() < field_size)
                && (changedVector.getY() < field_size)
                && (field.getFieldElement(changedVector).getCellState() == CellState.FREE)) {
            field.setFieldElement(changedVector, state);
            field.setFieldElement(currentVector, CellState.FREE);
            selectedCell = field.getFieldElement(changedVector);
        }


        checkGameOver();

    }

    private void checkGameOver() {
        CellState stateArray[] = {CellState.TYPE1, CellState.TYPE2, CellState.TYPE3};

        for (int j = 0; j < field_size; j += 2) {
            for (int i = 0; i < field_size; i++) {
                if (field.getFieldElement(new Vector(j, i)).getCellState() != stateArray[j/2]) {
                    return;
                }
            }
        }

        System.out.println("You Win!");
        gameOver = true;
    }

    private Vector getDirection(int value) {
        switch (value) {
            case 37:
                return new Vector(-1, 0);
            case 38:
                return new Vector(0, -1);
            case 39:
                return new Vector(1, 0);
            case 40:
                return new Vector(0, 1);
        }

        return new Vector(0, 0);
    }

}


