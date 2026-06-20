package Strategy.Bot;

import model.Board;
import model.Move;
import model.Player;
import model.Cell;

import Strategy.Winning.RowWinningStrategy;
import Strategy.Winning.ColWinningStrategy;
import Strategy.Winning.DiagonalWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MediumBot implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board, Player player) {
        if (board == null) return null;
        int n = board.getBoardSize();
        Cell[][] cells = board.getCells();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getCellStatus() == enums.CellStatus.EMPTY) {
                    cells[i][j].setPlayer(player);
                    cells[i][j].setCellStatus(enums.CellStatus.FILLED);
                    boolean win = new RowWinningStrategy().checkWin(board, player) || new ColWinningStrategy().checkWin(board, player) || new DiagonalWinningStrategy().checkWin(board, player);
                    cells[i][j].setPlayer(null);
                    cells[i][j].setCellStatus(enums.CellStatus.EMPTY);
                    if (win) return new Move(player, i, j);
                }
            }
        }

        List<Player> opponents = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Player p = cells[i][j].getPlayer();
                if (p != null && !p.getSymbol().equals(player.getSymbol()) && !opponents.contains(p)) {
                    opponents.add(p);
                }
            }
        }

        for (Player opp : opponents) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (cells[i][j].getCellStatus() == enums.CellStatus.EMPTY) {
                        cells[i][j].setPlayer(opp);
                        cells[i][j].setCellStatus(enums.CellStatus.FILLED);
                        boolean oppWin = new RowWinningStrategy().checkWin(board, opp) || new ColWinningStrategy().checkWin(board, opp) || new DiagonalWinningStrategy().checkWin(board, opp);
                        cells[i][j].setPlayer(null);
                        cells[i][j].setCellStatus(enums.CellStatus.EMPTY);
                        if (oppWin) return new Move(player, i, j);
                    }
                }
            }
        }

        List<int[]> empties = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getCellStatus() == enums.CellStatus.EMPTY) empties.add(new int[]{i, j});
            }
        }
        if (empties.isEmpty()) return null;
        Random rnd = new Random();
        int[] choice = empties.get(rnd.nextInt(empties.size()));
        return new Move(player, choice[0], choice[1]);
    }
}
