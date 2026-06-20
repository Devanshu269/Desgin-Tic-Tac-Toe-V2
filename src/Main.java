import factory.BotFactory;
import model.Board;
import model.Game;
import model.Player;
import model.Bot;
import enums.BotLevel;
import Service.GameService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter board size N (>=3):");
        int n = sc.nextInt();
        if (n < 3) return;
        int playersCount = n - 1;
        Character[] symbols = new Character[]{'X','O','A','B','C','D','E','F','G','H','I','J'};
        List<Player> players = new ArrayList<>();
        System.out.println("Enter which player number (1 to " + playersCount + ") will be the bot: \n" );
        int botIndex = sc.nextInt();
        System.out.println("Select bot level: 1=EASY 2=MEDIUM 3=HARD \n");
        int levelInput = sc.nextInt();
        BotLevel level = BotLevel.EASY;
        if (levelInput == 2) level = BotLevel.MEDIUM;
        else if (levelInput == 3) level = BotLevel.HARD;
        for (int i = 0; i < playersCount; i++) {
            if (i + 1 == botIndex) {
                Bot b = BotFactory.createBot(symbols[i], "Bot" + (i + 1), i + 1, level);
                players.add(b);
            } else {
                Player p = new Player(symbols[i], "P" + (i + 1), i + 1);
                players.add(p);
            }
        }

        Board board = new Board(n);
        Game game = new Game.Builder().setBoard(board).setPlayer(players).build();
        GameService service = new GameService();

        while (game.getGameStatus() == enums.GameStatus.IN_PROGRESS) {
            service.displayBoard(game);
            Player current = service.getCurrentPlayer(game);
            if (current instanceof Bot) {
                Bot bot = (Bot) current;
                model.Move move = bot.makeMove(game.getBoard());
                if (move == null) break;
                service.makeMove(game, bot, move.getRow(), move.getCol());
                try { Thread.sleep(300); } catch (Exception ignored) {}
            } else {
                System.out.println("Player " + current.getName() + " turn. Enter row col or 'u' to undo:\n");
                String line = sc.next();
                if (line.equalsIgnoreCase("u")) {
                    service.undoLastMove(game);
                    continue;
                }
                int row = Integer.parseInt(line);
                int col = sc.nextInt();
                boolean ok = service.makeMove(game, current, row, col);
                if (!ok) System.out.println("Invalid move, try again\n");
            }
        }

        service.displayBoard(game);
        if (game.getGameStatus() == enums.GameStatus.ENDED && game.getWinner() != null) {
            System.out.println("Winner: " + game.getWinner().getName());
        } else if (game.getGameStatus() == enums.GameStatus.DRAW) {
            System.out.println("Draw");
        } else {
            System.out.println("Game ended");
        }
    }
}