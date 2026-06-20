package model;


public class Player {
    private Character symbol;
    private String name;
    private int playerId;

    public Player(Character symbol, String name, int playerId) {
        this.symbol = symbol;
        this.name = name;
        this.playerId = playerId;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

}
