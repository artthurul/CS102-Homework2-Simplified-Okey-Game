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

    public int colorNameToInt() {
        switch (color) {
            case 'Y':
                return 0;
            case 'B':
                return 1;
            case 'R':
                return 2;
            default:
                return 3;
        }
    }

    /*
     * Determines if this tile can form a chain with tile t.
     * In our variant, a chain is only formed by tiles of the same number but different colors.
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
