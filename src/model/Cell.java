package model;

import enums.CellStatus;

public class Cell {
    private int row;
    private int column;
    private Player player;
    private CellStatus cellStatus;

    public Cell(int row, int column, Player player, CellStatus cellStatus) {
        this.row = row;
        this.column = column;
        this.player = player;
        this.cellStatus = cellStatus;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cell() {
        this.cellStatus = CellStatus.EMPTY;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }
}
