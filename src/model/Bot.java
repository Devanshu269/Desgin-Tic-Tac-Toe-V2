package model;

import Strategy.BotMoveStrategy;
import enums.BotLevel;

public class Bot extends Player{
    private BotLevel botLevel;
    private BotMoveStrategy botMoveStrategy;


    public Bot(Character symbol, String name, int playerId, BotLevel botLevel, BotMoveStrategy botMoveStrategy) {
        super(symbol, name, playerId);
        this.botLevel = botLevel;
        this.botMoveStrategy = botMoveStrategy;
    }

    public BotLevel getBotLevel() {
        return botLevel;
    }

    public void setBotLevel(BotLevel botLevel) {
        this.botLevel = botLevel;
    }

    public Move makeMove(Board board){
        return botMoveStrategy.makeMove(board, this);
    }
}
