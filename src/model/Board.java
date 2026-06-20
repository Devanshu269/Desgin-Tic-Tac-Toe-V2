package model;

import enums.CellStatus;

public class Board {
    private Cell[][] cells;
    private int boardSize;

    public Board(Cell[][] cells, int boardSize) {
        this.cells = cells;
        this.boardSize = boardSize;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.cells = new Cell[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                cells[i][j] = new Cell(i, j, null, CellStatus.EMPTY);
            }
        }
    }
}
