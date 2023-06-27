package de.uni_passau.fim.prog2.kalah.view;

import de.uni_passau.fim.prog2.kalah.model.Board;
import de.uni_passau.fim.prog2.kalah.model.Player;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The visual representation of the actual game of Kalah.
 * Shows all pits and corresponding numbers.
 */
public class BoardPanel extends JPanel {

    private final List<PitLabel> pitLabels;

    /**
     * Construct a new panel by analyzing the properties of a model.
     *
     * @param model
     */
    public BoardPanel(Board model) {
        super();
        setBackground(fromRGB(0xda, 0xa0, 0x6d));
        setLayout(new BorderLayout());

        int pitsPerPlayer = model.getPitsPerPlayer();

        JPanel machinePitNumbers = buildMachinePitNumbers(pitsPerPlayer);
        JPanel humanPitNumbers = buildHumanPitNumbers(pitsPerPlayer);

        pitLabels = new ArrayList<>(
                (model.getPitsPerPlayer() + 1) * 2);
        JPanel pits = new JPanel(new GridLayout(1, (pitsPerPlayer + 2)));
        pits.setBackground(fromRGB(0xda, 0xa0, 0x6d));
        buildPits(pits, model, pitsPerPlayer);

        add(machinePitNumbers, BorderLayout.NORTH);
        add(humanPitNumbers, BorderLayout.SOUTH);
        add(pits, BorderLayout.CENTER);
    }

    /**
     * Updates values inside all pits and re-renders them.
     *
     * @param model A board instance with the updated state.
     */
    public void updatePits(Board model) {
        for (PitLabel p : pitLabels) {
            p.setText(String.valueOf(model.getSeeds(p.getPitNumber())));
            if (model.sourcePitOfLastMove() == p.getPitNumber()
                    && model.targetPitOfLastMove() == p.getPitNumber()) {
                p.setForeground(Color.ORANGE);
            } else if (model.sourcePitOfLastMove() == p.getPitNumber()) {
                p.setForeground(Color.RED);
            } else if (model.targetPitOfLastMove() == p.getPitNumber()) {
                p.setForeground(Color.GREEN);
            } else {
                p.setForeground(Color.BLACK);
            }
            if (model.isGameOver() || model.next() != Player.HUMAN) {
                p.setBorder(new LineBorder(Color.RED, 1));
            } else {
                p.setBorder(new LineBorder(Color.DARK_GRAY, 1));
            }
        }
        repaint();
    }

    private void buildPits(JPanel pits, Board model, int pitsPerPlayer) {
        PitLabel machineStore
                = new PitLabel(model, (pitsPerPlayer + 1) * 2);
        pits.add(machineStore);
        pitLabels.add(machineStore);
        for (int i = 1; i <= pitsPerPlayer; i++) {
            JPanel column = new JPanel(new GridLayout(2, 1));
            column.setOpaque(false);
            PitLabel top = new PitLabel(model,
                    ((pitsPerPlayer + 1) * 2) - i);
            PitLabel bottom = new PitLabel(model, i);
            column.add(top);
            column.add(bottom);
            pitLabels.add(top);
            pitLabels.add(bottom);
            pits.add(column);
        }
        PitLabel humanStore = new PitLabel(model, (pitsPerPlayer + 1));
        pits.add(humanStore);
        pitLabels.add(humanStore);
    }

    private JPanel buildMachinePitNumbers(int pitsPerPlayer) {
        JPanel machinePitNumbers = new JPanel();
        machinePitNumbers.setBackground(fromRGB(0x6e, 0x26, 0x0e));
        machinePitNumbers.setLayout(
                new GridLayout(1, pitsPerPlayer + 2));
        for (int i = (pitsPerPlayer + 1) * 2; i > pitsPerPlayer; i--) {
            JLabel number =
                    new JLabel(String.valueOf(i), SwingConstants.CENTER);
            number.setBorder(new EmptyBorder(5, 0, 5, 0));
            number.setForeground(Color.WHITE);
            machinePitNumbers.add(number);
        }
        return machinePitNumbers;
    }

    private JPanel buildHumanPitNumbers(int pitsPerPlayer) {
        JPanel humanPitNumbers = new JPanel();
        humanPitNumbers.setBackground(fromRGB(0x6e, 0x26, 0x0e));
        humanPitNumbers.setLayout(
                new GridLayout(1, pitsPerPlayer + 2));
        for (int i = 0; i <= pitsPerPlayer + 1; i++) {
            JLabel number;
            if (i == 0) {
                number = new JLabel(String.valueOf((pitsPerPlayer + 1) * 2),
                        SwingConstants.CENTER);
            } else {
                number = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            }
            number.setBorder(new EmptyBorder(5, 0, 5, 0));
            number.setForeground(Color.WHITE);
            humanPitNumbers.add(number);
        }
        return humanPitNumbers;
    }

    private Color fromRGB(int r, int g, int b) {
        float[] hsb = Color.RGBtoHSB(r, g, b, null);
        return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
    }

    /**
     * Returns all the individual pits that are part of this panels board.
     *
     * @return all individual labels of pits
     */
    public List<PitLabel> getPitLabels() {
        return Collections.unmodifiableList(pitLabels);
    }
}
