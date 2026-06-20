package Strategy.Winning;

import enums.CellStatus;
import model.Board;
import model.Cell;
import model.Player;

public class RowWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWin(Board board, Player player) {
        if (board == null || player == null) {
            return false;
        }

        Cell[][] boardCell = board.getCells();
        if (boardCell == null) {
            return false;
        }

        int size = board.getBoardSize();
        for (int row = 0; row < size; row++) {
            boolean rowWin = true;
            for (int col = 0; col < size; col++) {
                Cell cell = boardCell[row][col];
                if (!WinningUtils.cellMatchesPlayer(cell, player)) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin) {
                return true;
            }
        }

        return false;
    }
}
