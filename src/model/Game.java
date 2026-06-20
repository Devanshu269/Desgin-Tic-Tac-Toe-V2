package model;

import Strategy.WinningStrategy;
import enums.GameStatus;

import java.util.List;

public class Game {
    private Board board;
    private Player player;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int currentPlayerIndex;
    private WinningStrategy winningStrategy;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    private Game (Board board, Player player){
        this.board = board;
        this.player = player;
    }

    public static class Builder {
        private Board board;
        private Player player;

        public Builder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public Builder setPlayer(Player player) {
            this.player = player;
            return this;
        }

        public Game build() {
            return new Game(board, player);
        }
    }
}
