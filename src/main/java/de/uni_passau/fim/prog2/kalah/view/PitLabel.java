package de.uni_passau.fim.prog2.kalah.view;

import de.uni_passau.fim.prog2.kalah.model.Board;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

/**
 * A label that represents a single pit inside the game.
 */
public class PitLabel extends JLabel {
    private int pitNumber;

    /**
     * Creates a label given its number on the board with access to the model.
     * @param model a game of kalah
     * @param pitNumber the pit's number on the board
     */
    public PitLabel(Board model, int pitNumber) {
        super(String.valueOf(model.getSeeds(pitNumber)),
                SwingConstants.CENTER);
        this.pitNumber = pitNumber;
        setBorder(new LineBorder(Color.DARK_GRAY, 1));
        setFont(new Font("sans-serif", Font.PLAIN, 28));
    }

    /**
     * Returns the pits number the label represents.
     * @return number of the corresponding pit
     */
    public int getPitNumber() {
        return pitNumber;
    }
}
