package entity;

/**
 * The {@code Moves} class serves as a base class for different types of moves in our Pokémon game.
 * It provides a common structure for moves, including a name attribute.
 */
public class Moves {

    /** The name of the move. */
    String name;

    /**
     * Constructs a new {@code Moves} object with the specified name.
     *
     * @param name The name of the move.
     */
    public Moves(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the move.
     *
     * @return The name of the move.
     */
    public String getName() {
        return name;
    }
}
