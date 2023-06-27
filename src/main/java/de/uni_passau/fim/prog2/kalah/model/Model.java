package de.uni_passau.fim.prog2.kalah.model;

/**
 * The model of the application containing an instance of the game board.
 */
public class Model {
    private Board board;

    /**
     * Constructs a new model and creates a default board instance.
     */
    public Model() {
        this.board = new Kalah(6, 3, Player.HUMAN, 3);
    }

    /**
     * Construct a new model with a given board.
     * @param board The initial board instance of the model.
     */
    public Model(Board board) {
        this.board = board;
    }

    /**
     * Gets the current board instance.
     *
     * @return A board instance.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Set the current board instance.
     *
     * @param board A board instance.
     */
    public void setBoard(Board board) {
        this.board = board;
    }
}
