package com.gotus;

import java.awt.*;

class Cell {
    private int x;
    private int y;
    private CellState cellState;
    private Square cellDrawingData;

    public Cell(int x, int y, int cellSize) {
        this.x = x;
        this.y = y;
        this.cellState = CellState.FREE;
        cellDrawingData = new Square(new Location(x, y), cellSize);
    }

    public Cell(int x, int y, CellState cellState, int cellSize) {
        this(x, y, cellSize);
        this.cellState = cellState;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CellState getCellState() {
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

    public void paintComponent(Graphics g, int step) {
        cellDrawingData.paintComponent(g, step);
    }
}
