public class OkeyGame {

    Player[] players;
    Tile[] tiles;
    Tile lastDiscardedTile;
    int currentPlayerIndex = 0;
    int nextTileIndex; // tracks next tile in the deck

    public OkeyGame() {
        players = new Player[4];
    }

    /*
     * Shuffles the tiles. This is done before distributing the tiles to the players. 
     * The shuffle is done by swapping each tile with a random tile.
     */
    public void shuffleTiles() {
        for (int i = 0; i < tiles.length; i++) {
            int randomIndex = (int) (Math.random() * tiles.length);
            Tile temp = tiles[i];
            tiles[i] = tiles[randomIndex];
            tiles[randomIndex] = temp;
        }
    }

    /*
     * Creates the tiles for the game.
     * There are 7 different values (1-7) and 4 different colors (Y, B, R, K).
     * Each tile has a value and a color.
     * There are 4 copies of each tile.
     */
    public void createTiles() {
        tiles = new Tile[112]; // 7 values * 4 colors * 4 copies = 112 tiles
        int index = 0;
        char[] colors = {'Y', 'B', 'R', 'K'};
        for (int copy = 0; copy < 4; copy++) {
            for (int value = 1; value <= 7; value++) {
                for (char color : colors) {
                    tiles[index++] = new Tile(value, color);
                }
            }
        }
    }

    /*
     * Distributes the starting tiles to the players.
     * Player at index 0 gets 15 tiles; others get 14 tiles.
     * Assumes tiles are already shuffled.
     */
    public void distributeTilesToPlayers() {
        int index = 0;
        for (int i = 0; i < players.length; i++) {
            int numTiles = (i == 0) ? 15 : 14;
            for (int j = 0; j < numTiles; j++) {
                players[i].addTile(tiles[index++]);
            }
        }
        nextTileIndex = index; // remaining tiles start here
    }

    /*
     * Draws the top tile from the tile stack.
     * That tile is no longer in the stack.
     */
    public Tile pickTileFromStack() {
        if (nextTileIndex < tiles.length) {
            return tiles[nextTileIndex];
        } else {
            return null; // No tiles left – game tie condition can be handled here.
        }
    }

    /*
     * Picks the discarded tile for the current player.
     * If the last discarded tile is not picked, it is returned to the discard pile. 
     */
    public Tile pickDiscardTile() {
        Tile temp = lastDiscardedTile;
        lastDiscardedTile = null;
        return temp;
    }

    /*
     * Checks if the current player's hand is a winning hand.
     * Winning condition: at least 3 chains of length 4 (each chain is 4 tiles of the same number in different colors).
     */
    public boolean didGameFinish() {
        return players[currentPlayerIndex].isWinningHand();
    }

    /*
     * For computer players:
     * - If the last discarded tile is useful (i.e. a tile with the same number already in hand exists),
     *   then pick it; otherwise, pick from the tile stack.
     */
    public void pickTileForComputer() {
        if (lastDiscardedTile != null && players[currentPlayerIndex].findPositionOfTile(lastDiscardedTile) != -1) {
            players[currentPlayerIndex].addTile(lastDiscardedTile);
            System.out.println("Computer picked up discarded tile: " + lastDiscardedTile);
            lastDiscardedTile = null;
        } else {
            Tile tile = pickTileFromStack();
            players[currentPlayerIndex].addTile(tile);
            System.out.println("Computer picked up tile from stack: " + tile);
            nextTileIndex++;
        }
    }

    /*
     * Computer discards the least useful tile.
     * It first looks for duplicates (or tiles that contribute to the smallest chain) to discard.
     * If no such tile is found, it discards the first tile in hand.
     */
    public void discardTileForComputer() {
        int minChainLength = 4;
        int minChainIndex = -1;

        for (int i = 0; i < players[currentPlayerIndex].getNumberOfTiles(); i++) {
            Tile currentTile = players[currentPlayerIndex].getTiles()[i];
            int chainLength = 1;
            for (int j = 0; j < players[currentPlayerIndex].getNumberOfTiles(); j++) {
                if (i != j && currentTile.canFormChainWith(players[currentPlayerIndex].getTiles()[j])) {
                    chainLength++;
                }
            }
            if (chainLength < minChainLength) {
                minChainLength = chainLength;
                minChainIndex = i;
            }
        }

        if (minChainIndex != -1) {
            lastDiscardedTile = players[currentPlayerIndex].getTiles()[minChainIndex];
            players[currentPlayerIndex].removeTile(minChainIndex);
            System.out.println(players[currentPlayerIndex].getName() + " discards: " + lastDiscardedTile);
        } else {
            lastDiscardedTile = players[currentPlayerIndex].getTiles()[0];
            players[currentPlayerIndex].removeTile(0);
            System.out.println(players[currentPlayerIndex].getName() + " discards: " + lastDiscardedTile);
        }
    }

    /*
     * Discards the current player's tile at the given index.
     * Sets lastDiscardedTile and removes that tile from the player's hand.
     */
    public void discardTile(int tileIndex) {
        Tile discardedTile = players[currentPlayerIndex].getTiles()[tileIndex];
        players[currentPlayerIndex].removeTile(tileIndex);
        lastDiscardedTile = discardedTile;
    }

    /*
     * Displays the last discarded tile.
     */
    public void displayDiscardInformation() {
        if (lastDiscardedTile != null) {
            System.out.println("Last Discarded: " + lastDiscardedTile);
        }
    }

    public void displayCurrentPlayersTiles() {
        System.out.println();
        players[currentPlayerIndex].displayTiles();
        System.out.println();
    }

    // Getters and setters
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public String getCurrentPlayerName() {
        return players[currentPlayerIndex].getName();
    }

    public void passTurnToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % 4;
    }

    public void setPlayerName(int index, String name) {
        if (index >= 0 && index <= 3) {
            players[index] = new Player(name);
        }
    }

    // Helper method to add a tile to the current player's hand.
    public void addTileToCurrentPlayer(Tile t) {
        players[currentPlayerIndex].addTile(t);
    }

    public int getNextTileIndex() {
        return nextTileIndex;
    }
    
}
