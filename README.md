# Tic Tac Toe V2

## Requirements

- N x N board (N >= 3)
- Number of players = N - 1
- Only one bot per game
- Bot difficulty: EASY, MEDIUM, HARD
- Win condition: N in a row horizontally, vertically or diagonally
- Undo last move supported

## Low Level Design (LLD)

### Requirement Gathering

- Support variable board sizes
- Support multiple human players and exactly one bot
- Bot should have three difficulty levels
- Fast winner check after each move
- Undo functionality

### Key Classes

- Board: holds Cell[][] and size
- Cell: holds row, column, player and cell status (EMPTY/FILLED)
- Player: symbol, name, id
- Bot: extends Player, has BotLevel and BotPlayingStrategy
- Move: player, row, col
- Game: holds Board, List<Player>, List<Move>, GameStatus, currentPlayerIndex, winner
- GameService: contains game flow methods: makeMove, undoLastMove, checkWinForLastMove, isBoardFull, displayBoard, getCurrentPlayer
- Winning strategies: RowWinningStrategy, ColWinningStrategy, DiagonalWinningStrategy
- WinningUtils: helper to check whether a cell belongs to a player
- BotFactory: creates bots by level

### Class Diagram (textual)

Game
|-- board: Board
|-- players: List<Player>
|-- moves: List<Move>
|-- gameStatus: GameStatus
|-- currentPlayerIndex: int
|-- winner: Player

Player
|-- symbol: Character
|-- name: String
|-- playerId: int

Bot extends Player
|-- botLevel: BotLevel
|-- botPlayingStrategy: BotPlayingStrategy

Board
|-- cells: Cell[][]
|-- boardSize: int

Cell
|-- row: int
|-- column: int
|-- player: Player
|-- cellStatus: CellStatus

Move
|-- player: Player
|-- row: int
|-- col: int

GameService
|-- makeMove(game, player, row, col)
|-- undoLastMove(game)
|-- checkWinForLastMove(game, move)
|-- isBoardFull(board)
|-- displayBoard(game)
|-- getCurrentPlayer(game)

Winning Strategies
|-- RowWinningStrategy
|-- ColWinningStrategy
|-- DiagonalWinningStrategy

BotPlayingStrategy
|-- EasyBot (random)
|-- MediumBot (try win, block, else random)
|-- HardBot (win, block, center, corner, else random)

### Flow

1. Setup board and players
2. Loop while game IN_PROGRESS
3. Get current player
4. If bot -> bot.makeMove(board) else read human input
5. Apply move via GameService.makeMove
6. GameService checks for win (only affected row/col/diagonals)
7. If win or draw, end game; else advance player
8. Undo pops last move and restores cell

## How to run

1. Build and run Main
2. Follow prompts to enter board size and bot selection

