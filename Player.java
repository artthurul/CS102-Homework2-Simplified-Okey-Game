public class Player {

    private String playerName;
    private final Tile[] playerTiles;
    private int numberOfTiles;

    /*
     * Constructor for the Player class.
     */
    public Player(String name) {
        this.playerName = name;
        playerTiles = new Tile[15]; // at most 15 tiles at any time
        numberOfTiles = 0;
    }

    /*
     * Removes and returns the tile at the given index.
     */
    public Tile getAndRemoveTile(int index) {
        Tile tileToGet = playerTiles[index];
        for (int i = index; i < numberOfTiles - 1; i++) {
            playerTiles[i] = playerTiles[i + 1];
        }
        numberOfTiles--;
        return tileToGet;
    }

    /*
     * Adds the given tile into playerTiles in sorted order.
     * The hand is maintained in order without using a separate sorting algorithm.
     */
    public void addTile(Tile t) {
        if (numberOfTiles == 15) {
            System.out.println("You can't get more tiles.");
            return;
        }

        int insertIndex = numberOfTiles;
        for (int i = 0; i < numberOfTiles; i++) {
            if (playerTiles[i].getValue() > t.getValue()
                    || (playerTiles[i].getValue() == t.getValue() && playerTiles[i].getColor() > t.getColor())) {
                insertIndex = i;
                break;
            }
        }

        for (int j = numberOfTiles; j > insertIndex; j--) {
            playerTiles[j] = playerTiles[j - 1];
        }

        playerTiles[insertIndex] = t;
        numberOfTiles++;
    }

    /*
     * Checks if this player's hand is winning.
     * Winning condition: the hand contains three chains of length 4.
     * A chain is 4 tiles of the same number in different colors.
     */
    public boolean isWinningHand() {
        int chainsOfFour = 0;
        int currentChainLength = 1;
        int[] colorsInChain = new int[4];
        int colorCount = 0;

        for (int i = 1; i < numberOfTiles; i++) {
            if (playerTiles[i].getValue() == playerTiles[i - 1].getValue()) {
                boolean colorExists = false;
                for (int j = 0; j < colorCount; j++) {
                    if (colorsInChain[j] == playerTiles[i].getColor()) {
                        colorExists = true;
                        break;
                    }
                }
                if (!colorExists) {
                    colorsInChain[colorCount++] = playerTiles[i].getColor();
                    currentChainLength++;
                }
            } else {
                if (currentChainLength == 4 && colorCount == 4) {
                    chainsOfFour++;
                }
                currentChainLength = 1;
                colorCount = 1;
                colorsInChain[0] = playerTiles[i].getColor();
            }
        }

        if (currentChainLength == 4 && colorCount == 4) {
            chainsOfFour++;
        }

        return chainsOfFour >= 3;
    }

    /*
     * Searches for a tile in the hand that matches the input tile.
     * Returns the index if found; otherwise, returns -1.
     * This method is used to find the position of the tile to discard.
     */
    public int findPositionOfTile(Tile inputtedTile) {
        for (int i = 0; i < numberOfTiles; i++) {
            if (playerTiles[i].compareTo(inputtedTile) == 0) {
                return i;
            }
        }
        return -1;
    }

    // Getter method for number of tiles.
    public int getNoTiles() {
        return numberOfTiles;
    }

    /*
     * Displays the tiles in the player's hand.
     */
    public void displayTiles() {
        String title1 = "\n" + playerName + "'s Tiles Below \n\nWe wish you a good game.";
        System.out.println(title1);

        System.out.print("Index Number: ");
        for (int i = 0; i < numberOfTiles; i++) {
            System.out.printf("%2d ", i);
        }

        // Goes to next line for printing the stack.
        System.out.println();

        System.out.print("Tiles       : ");
        for (int i = 0; i < numberOfTiles; i++) {
            System.out.print(playerTiles[i] + " ");
        }
        System.out.println();
    }

    // Getters and Setters  
    public Tile[] getTiles() {
        return playerTiles;
    }

    public void setName(String name) {
        playerName = name;
    }

    public String getName() {
        return playerName;
    }

    public int getNumberOfTiles() {
        return numberOfTiles;
    }

    /*
     * Removes the tile at the given index from the hand.
     * Shifts the remaining tiles to the left and updates the count.
     */
    public void removeTile(int index) {
        for (int i = index; i < numberOfTiles - 1; i++) {
            playerTiles[i] = playerTiles[i + 1];
        }
        numberOfTiles--;
    }
}
