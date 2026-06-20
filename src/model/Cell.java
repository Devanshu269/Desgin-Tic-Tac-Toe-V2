package model;

import enums.CellStatus;

public class Cell {
    private int[] row;
    private int[] column;
    private Player player;
    private CellStatus cellStatus;

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
