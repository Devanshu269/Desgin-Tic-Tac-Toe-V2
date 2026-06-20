package Service;

import Strategy.Winning.WinningUtils;
import Strategy.Winning.WinningStrategy;
import enums.CellStatus;
import enums.GameStatus;
import model.Board;
import model.Cell;
import model.Game;
import model.Move;
import model.Player;

import java.util.List;

public class GameService {
    public WinningStrategy winningStrategy;

    public GameService() {}

    public GameService(WinningStrategy winningStrategy) {
        this.winningStrategy = winningStrategy;
    }

    public boolean checkWinForLastMove(Game game, Move lastMove) {
        if (game == null || lastMove == null || lastMove.getPlayer() == null) return false;

        Board board = game.getBoard();
        if (board == null) return false;

        int n = board.getBoardSize();
        int r = lastMove.getRow();
        int c = lastMove.getCol();
        Player p = lastMove.getPlayer();
        Cell[][] cells = board.getCells();

        // Row
        boolean rowWin = true;
        for (int j = 0; j < n; j++) {
            if (!WinningUtils.cellMatchesPlayer(cells[r][j], p)) {
                rowWin = false;
                break;
            }
        }
        if (rowWin) return true;

        // Column
        boolean colWin = true;
        for (int i = 0; i < n; i++) {
            if (!WinningUtils.cellMatchesPlayer(cells[i][c], p)) {
                colWin = false;
                break;
            }
        }
        if (colWin) return true;

        // Main diagonal
        if (r == c) {
            boolean mainDiag = true;
            for (int i = 0; i < n; i++) {
                if (!WinningUtils.cellMatchesPlayer(cells[i][i], p)) {
                    mainDiag = false;
                    break;
                }
            }
            if (mainDiag) return true;
        }

        // Anti-diagonal
        if (r + c == n - 1) {
            boolean antiDiag = true;
            for (int i = 0; i < n; i++) {
                if (!WinningUtils.cellMatchesPlayer(cells[i][n - 1 - i], p)) {
                    antiDiag = false;
                    break;
                }
            }
            if (antiDiag) return true;
        }

        return false;
    }

    public boolean makeMove(Game game, Player player, int row, int col) {
        if (game == null || player == null) return false;
        Board board = game.getBoard();
        if (board == null) return false;

        int n = board.getBoardSize();
        if (row < 0 || row >= n || col < 0 || col >= n) return false;

        Cell cell = board.getCells()[row][col];
        if (cell == null) return false;
        if (cell.getCellStatus() != CellStatus.EMPTY) return false;

        // Apply move
        cell.setPlayer(player);
        cell.setCellStatus(CellStatus.FILLED);

        Move move = new Move(player, row, col);
        List<Move> moves = game.getMoves();
        if (moves == null) {
            throw new IllegalStateException("Game moves list is not initialized \n");
        }
        moves.add(move);

        // Check win
        if (checkWinForLastMove(game, move)) {
            game.setGameStatus(GameStatus.ENDED);
            game.setWinner(player);
            return true;
        }

        // Check draw
        if (isBoardFull(board)) {
            game.setGameStatus(GameStatus.DRAW);
            return true;
        }

        // Next player
        int next = (game.getCurrentPlayerIndex() + 1) % game.getPlayer().size();
        game.setCurrentPlayerIndex(next);
        game.setGameStatus(GameStatus.IN_PROGRESS);
        return true;
    }

    public boolean undoLastMove(Game game) {
        if (game == null) return false;
        List<Move> moves = game.getMoves();
        if (moves == null || moves.isEmpty()) return false;

        Move last = moves.remove(moves.size() - 1);
        Board board = game.getBoard();
        if (board == null) return false;
        Cell cell = board.getCells()[last.getRow()][last.getCol()];
        if (cell != null) {
            cell.setPlayer(null);
            cell.setCellStatus(CellStatus.EMPTY);
        }

        // Reset game status/winner: recompute based on last move if any
        if (moves.isEmpty()) {
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setWinner(null);
            game.setCurrentPlayerIndex(0);
            return true;
        }

        Move newLast = moves.get(moves.size() - 1);
        if (checkWinForLastMove(game, newLast)) {
            game.setWinner(newLast.getPlayer());
            game.setGameStatus(GameStatus.ENDED);
        } else if (isBoardFull(board)) {
            game.setGameStatus(GameStatus.DRAW);
            game.setWinner(null);
        } else {
            game.setGameStatus(GameStatus.IN_PROGRESS);
            // current player should be the player after newLast
            int idx = game.getPlayer().indexOf(newLast.getPlayer());
            if (idx >= 0) {
                game.setCurrentPlayerIndex((idx + 1) % game.getPlayer().size());
            }
        }

        return true;
    }

    public boolean isBoardFull(Board board) {
        if (board == null) return false;
        Cell[][] cells = board.getCells();
        if (cells == null) return false;
        int n = board.getBoardSize();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getCellStatus() == CellStatus.EMPTY) return false;
            }
        }
        return true;
    }

    public void displayBoard(Game game) {
        if (game == null || game.getBoard() == null) return;
        Board board = game.getBoard();
        Cell[][] cells = board.getCells();
        int n = board.getBoardSize();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                Cell cell = cells[i][j];
                if (cell == null || cell.getCellStatus() == CellStatus.EMPTY || cell.getPlayer() == null) {
                    sb.append("-");
                } else {
                    Character s = cell.getPlayer().getSymbol();
                    sb.append(s == null ? '?' : s);
                }
                if (j < n - 1) sb.append(" |");
            }
            System.out.println('\n');
            System.out.println(sb.toString());
            System.out.println('\n');

        }
    }

    public Player getCurrentPlayer(Game game) {
        if (game == null) return null;
        List<Player> players = game.getPlayer();
        if (players == null || players.isEmpty()) return null;
        int idx = game.getCurrentPlayerIndex();
        if (idx < 0 || idx >= players.size()) return null;
        return players.get(idx);
    }
}
