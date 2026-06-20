package Strategy.Winning;

import enums.CellStatus;
import model.Board;
import model.Cell;
import model.Player;

public class DiagonalWinningStrategy implements WinningStrategy {

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
        boolean mainDiagWin = true;
        for (int i = 0; i < size; i++) {
            if (!WinningUtils.cellMatchesPlayer(boardCell[i][i], player)) {
                mainDiagWin = false;
                break;
            }
        }
        if (mainDiagWin) {
            return true;
        }

        boolean antiDiagWin = true;
        for (int i = 0; i < size; i++) {
            if (!WinningUtils.cellMatchesPlayer(boardCell[i][size - 1 - i], player)) {
                antiDiagWin = false;
                break;
            }
        }
        return antiDiagWin;
    }
}
