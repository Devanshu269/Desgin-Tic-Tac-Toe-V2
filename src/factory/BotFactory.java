package factory;

import Strategy.Bot.BotPlayingStrategy;
import Strategy.Bot.EasyBot;
import Strategy.Bot.MediumBot;
import Strategy.Bot.HardBot;
import enums.BotLevel;
import model.Bot;

public class BotFactory {
	public static Bot createBot(Character symbol, String name, int playerId, BotLevel level) {
		BotPlayingStrategy strategy = null;
		if (level == BotLevel.EASY) strategy = new EasyBot();
		else if (level == BotLevel.MEDIUM) strategy = new MediumBot();
		else if (level == BotLevel.HARD) strategy = new HardBot();
		return new Bot(symbol, name, playerId, level, strategy);
	}
}
