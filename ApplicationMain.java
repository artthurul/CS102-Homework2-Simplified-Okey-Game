
import java.util.Scanner;

public class ApplicationMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OkeyGame game = new OkeyGame();

        System.out.print("Please enter your name: ");
        String playerName = sc.next();

        game.setPlayerName(0, playerName);
        game.setPlayerName(1, "John");
        game.setPlayerName(2, "Jane");
        game.setPlayerName(3, "Ted");

        game.createTiles();
        game.shuffleTiles();
        game.distributeTilesToPlayers();

        // developer mode is used for seeing the computer players’ hands, to be used for debugging
        System.out.print("Play in developer's mode with other player's tiles visible? (Y/N): ");
        char devMode = sc.next().charAt(0);
        boolean devModeOn = devMode == 'Y';

        boolean firstTurn = true;
        boolean gameContinues = true;
        int playerChoice = -1;

        while (gameContinues) {

            int currentPlayer = game.getCurrentPlayerIndex();
            System.out.println(game.getCurrentPlayerName() + "'s turn.");

            if (currentPlayer == 0) {
                // Human player's turn
                game.displayCurrentPlayersTiles();
                game.displayDiscardInformation();

                System.out.println("What will you do?");
                if (!firstTurn) {
                    System.out.println("1. Pick From Tiles");
                    System.out.println("2. Pick From Discard");
                } else {
                    // first turn: no tile pickup, only discard allowed
                    System.out.println("1. Discard Tile");
                }

                System.out.print("Your choice: ");
                playerChoice = sc.nextInt();

                if (!firstTurn) {
                    Tile pickedTile; // declare pickedTile once
                    if (playerChoice == 1) {
                        // Pick from tile stack – draw and add to hand
                        pickedTile = game.pickTileFromStack();
                        game.addTileToCurrentPlayer(pickedTile);
                        System.out.println("You picked up: " + pickedTile);
                    } else if (playerChoice == 2) {
                        // Pick from discard pile
                        pickedTile = game.pickDiscardTile();
                        if (pickedTile != null) {
                            game.addTileToCurrentPlayer(pickedTile);
                            System.out.println("You picked up: " + pickedTile);
                        } else {
                            System.out.println("No tile to pick from discard. Picking from tiles instead.");
                            pickedTile = game.pickTileFromStack();
                            game.addTileToCurrentPlayer(pickedTile);
                            System.out.println("You picked up: " + pickedTile);
                        }
                    }
                    game.displayCurrentPlayersTiles();
                } else {
                    // First turn: just discard one tile (hand remains at 15 tiles)
                    firstTurn = false;
                }

                gameContinues = !game.didGameFinish();

                if (gameContinues) {
                    // discard phase
                    System.out.println("Which tile will you discard?");
                    System.out.print("Discard the tile at index (0 to 14): ");
                    playerChoice = sc.nextInt();
                    // Validate index (assuming hand size is 15)
                    while (playerChoice < 0 || playerChoice > 14) {
                        System.out.println("Index is not correct. Please try again.");
                        System.out.print("Discard the tile at index: ");
                        playerChoice = sc.nextInt();
                    }
                    game.discardTile(playerChoice);
                    game.passTurnToNextPlayer();
                } else {
                    System.out.println("Congratulations, you win!");
                }
            } else {
                // Computer player's turn
                if (devModeOn) {
                    game.displayCurrentPlayersTiles();
                }
                game.pickTileForComputer();
                gameContinues = !game.didGameFinish();

                if (gameContinues) {
                    game.discardTileForComputer();
                    game.passTurnToNextPlayer();
                } else {
                    System.out.println(game.getCurrentPlayerName() + " wins.");
                }
            }
        }
        sc.close();
    }
}
