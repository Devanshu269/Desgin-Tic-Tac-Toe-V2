package Strategy.Bot;

import model.Board;
import model.Move;
import model.Player;
import model.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EasyBot implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board, Player player) {
        if (board == null) return null;
        int n = board.getBoardSize();
        Cell[][] cells = board.getCells();
        List<int[]> empties = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getCellStatus() == enums.CellStatus.EMPTY) {
                    empties.add(new int[]{i, j});
                }
            }
        }
        if (empties.isEmpty()) return null;
        Random rnd = new Random();
        int[] choice = empties.get(rnd.nextInt(empties.size()));
        return new Move(player, choice[0], choice[1]);
    }
}
