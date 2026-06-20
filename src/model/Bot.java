package model;

import Strategy.Bot.BotPlayingStrategy;
import enums.BotLevel;

public class Bot extends Player{
    private BotLevel botLevel;
    private BotPlayingStrategy botPlayingStrategy;


    public Bot(Character symbol, String name, int playerId, BotLevel botLevel, BotPlayingStrategy botPlayingStrategy) {
        super(symbol, name, playerId);
        this.botLevel = botLevel;
        this.botPlayingStrategy = botPlayingStrategy;
    }

    public BotLevel getBotLevel() {
        return botLevel;
    }

    public void setBotLevel(BotLevel botLevel) {
        this.botLevel = botLevel;
    }

    public Move makeMove(Board board){
        return botPlayingStrategy.makeMove(board, this);
    }
}
