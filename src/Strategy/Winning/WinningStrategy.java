package Strategy.Winning;

import model.Board;
import model.Player;

public interface WinningStrategy {
    boolean checkWin(Board board, Player player);
}
