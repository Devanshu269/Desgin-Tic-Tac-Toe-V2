package model;

import enums.GameStatus;

import java.util.List;
import java.util.ArrayList;

public class Game {
    private Board board;
    private List<Player> player;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int currentPlayerIndex;
    private Player winner;

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

    public List<Player> getPlayer() {
        return player;
    }

    public void setPlayer(List<Player> player) {
        this.player = player;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    private Game (Board board, List<Player> player){
        this.board = board;
        this.player = player;
        this.moves = new ArrayList<>();
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.currentPlayerIndex = 0;
        this.winner = null;
    }

    public static class Builder {
        private Board board;
        private List<Player> player;

        public Builder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public Builder setPlayer(List<Player> player) {
            this.player = player;
            return this;
        }

        public Game build() {
            if (board == null || player == null) {
                throw new IllegalArgumentException("Board and Player must be set \n");
            }

            if(board.getBoardSize() - 1 !=  player.size()){
                throw new IllegalArgumentException("Number of players must be equal to board size " + board.getBoardSize() +  "- 1\n");
            }
            return new Game(board, player);
        }
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
