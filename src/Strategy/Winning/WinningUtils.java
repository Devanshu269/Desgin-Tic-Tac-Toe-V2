package Strategy.Winning;

import enums.CellStatus;
import model.Cell;
import model.Player;

/**
 * Small utility helpers shared by winning strategies.
 */
public final class WinningUtils {

    private WinningUtils() {}

    public static boolean cellMatchesPlayer(Cell cell, Player player) {
        if (cell == null) return false;
        if (cell.getCellStatus() == CellStatus.EMPTY) return false;
        if (cell.getPlayer() == null) return false;
        if (cell.getPlayer().getSymbol() == null) return false;
        if (player == null || player.getSymbol() == null) return false;
        return cell.getPlayer().getSymbol().equals(player.getSymbol());
    }
}

