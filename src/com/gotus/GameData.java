package com.gotus;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Cell {
    private int x;
    private int y;
    private CellState cellState;

    Cell(int x, int y) {

        this.x = x;
        this.y = y;
        this.cellState = null;
    }

    Cell(int x, int y, CellState cellState) {
        this.x = x;
        this.y = y;
        this.cellState = cellState;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    CellState getCellState() {
        return this.cellState;
    }

    void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

}


public class GameData extends JPanel {

    private Field field;
    private static final int FIELD_SIZE = 5;

    private Squares squares = new Squares();

    private Cell selectedCell;

    private boolean gameOver;

    GameData() {

        field = new Field();

        //Расстановка фишек
        placeGameChip();

        selectedCell = field.getFieldElement(new Vector(0, 0));
        gameOver = false;

}

    private void placeGameChip() {
        int num_type[] = {5, 5, 5};
        int value;
        List<Cell> available_cells = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j += 2) {

                    available_cells.add(new Cell(j, i));
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

        for (int j = 0; j < FIELD_SIZE; j += 2) {
            for (int i = 0; i < FIELD_SIZE; i++) {
                field.setFieldElement(new Vector(j, i), stateArray[j/2]);
            }
        }

        field.setFieldElement(new Vector(1, 0), CellState.FREE);
        field.setFieldElement(new Vector(1, 1), CellState.FREE);
    }

    public void showField() {
        for (int i = 0; i < FIELD_SIZE; i++) {

            for (int j = 0; j < FIELD_SIZE; j++) {
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

        int size = squares.getSIZE();
        int step = squares.getSTEP();
        for (int i = 0; i < squares.getNUM_COLUMNS(); i++) {
            for (int j = 0; j < squares.getNUM_ROWS(); j++) {
                switch (field.getFieldElement(new Vector(j, i)).getCellState()) {
                    case TYPE1:
                        g.setColor(Color.RED);
                        g.fillRect(250 + j*(size + step), 250 + i*(size + step), size, size);
                        break;
                    case TYPE2:
                        g.setColor(Color.GREEN);
                        g.fillRect(250 + j*(size + step), 250 + i*(size + step), size, size);
                        break;
                    case TYPE3:
                        g.setColor(Color.BLUE);
                        g.fillRect(250 + j*(size + step), 250 + i*(size + step), size, size);
                        break;
                    case BLOCKED:
                        g.setColor(Color.BLACK);
                        g.fillRect(250 + j*(size + step), 250 + i*(size + step), size, size);
                        break;
                    case FREE:
                        g.setColor(Color.WHITE);
                        g.fillRect(250 + j*(size + step), 250 + i*(size + step), size, size);
                        break;
                }
            }
        }
    }

    void setSelectedCell(int x, int y) {

        if (gameOver) {
            return;
        }

        int i = (y - 250) / 60;
        int j = (x - 250) / 60;

        if ((j < FIELD_SIZE) && (i < FIELD_SIZE)
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

    void move(int keyCode) {

        if (gameOver) {
            if (keyCode == 27) {
                System.exit(0);
            }
            return;
        }

        int x = selectedCell.getX();
        int y = selectedCell.getY();
        CellState state = selectedCell.getCellState();
        Vector movingVector = getAdjacentElement(keyCode);
        Vector currentVector = new Vector(x, y);
        Vector changedVector = currentVector.addVector(movingVector);

        if ((changedVector.getX() >= 0) && (changedVector.getY() >= 0)
                && (changedVector.getX() < FIELD_SIZE)
                && (changedVector.getY() < FIELD_SIZE)
                && (field.getFieldElement(changedVector).getCellState() == CellState.FREE)) {
            field.setFieldElement(changedVector, state);
            field.setFieldElement(currentVector, CellState.FREE);
            selectedCell = field.getFieldElement(changedVector);
        }


        checkGameOver();

    }

    private void checkGameOver() {
        CellState stateArray[] = {CellState.TYPE1, CellState.TYPE2, CellState.TYPE3};

        for (int j = 0; j < FIELD_SIZE; j += 2) {
            for (int i = 0; i < FIELD_SIZE; i++) {
                if (field.getFieldElement(new Vector(j, i)).getCellState() != stateArray[j/2]) {
                    return;
                }
            }
        }

        System.out.println("You Win!");
        gameOver = true;
    }

    private Vector getAdjacentElement(int value) {
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


