public class Tile {

    private final int value;
    private final char color;

    /*
     * Constructs a tile with the given value and color.
     * Colors: Y (Yellow), B (Blue), R (Red), K (Black). Values range from 1 to 7.
     */
    public Tile(int value, char color) {
        this.value = value;
        this.color = color;
    }

    /*
     * Compares two tiles so they can be maintained in sorted order.
     * First, tiles are sorted by color (Y < B < R < K).
     * If colors are the same, tiles are sorted by value (1 < 2 < ... < 7).
     * Returns -1 if this tile is less than t, 1 if this tile is greater than t, and 0 if they are equal.
     */
    public int compareTo(Tile t) {
        if (colorNameToInt() < t.colorNameToInt()) {
            return -1;
        } else if (colorNameToInt() > t.colorNameToInt()) {
            return 1;
        } else {
            if (getValue() < t.getValue()) {
                return -1;
            } else if (getValue() > t.getValue()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    /*
     * Converts the color of this tile to an integer.
     * Y -> 0, B -> 1, R -> 2, K -> 3
     */
    public int colorNameToInt() {
        return switch (color) {
            case 'Y' -> 0;
            case 'B' -> 1;
            case 'R' -> 2;
            default -> 3;
        };
    }

    /*
     * Determines if this tile can form a chain with tile t.
     * In our variant, a chain is only formed by tiles of the same number but different colors.
     * Returns true if the tiles can form a chain, false otherwise.
     */
    public boolean canFormChainWith(Tile t) {
        return (t.getColor() != color && t.getValue() == value);
    }

    @Override
    public String toString() {
        return "" + value + color;
    }

    public int getValue() {
        return value;
    }

    public char getColor() {
        return color;
    }
}
