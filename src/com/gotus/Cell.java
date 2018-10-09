package com.gotus;

import java.awt.*;

class Cell {
    private int x;
    private int y;
    private CellState cellState;
    private Square cellDrawingData;

    Cell(int x, int y, int cellSize) {

        this.x = x;
        this.y = y;
        this.cellState = CellState.FREE;
        cellDrawingData = new Square(new Vector(x, y), cellSize);
    }

    Cell(int x, int y, CellState cellState, int cellSize) {
        this.x = x;
        this.y = y;
        this.cellState = cellState;
        cellDrawingData = new Square(new Vector(x, y), cellSize);
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

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public void setCellDrawingData(Square cellDrawingData) {
        this.cellDrawingData = cellDrawingData;
    }

    public Square getCellDrawingData() {
        return this.cellDrawingData;
    }

    public void paintComponent(Graphics g) {
        cellDrawingData.paintComponent(g);
    }
}
