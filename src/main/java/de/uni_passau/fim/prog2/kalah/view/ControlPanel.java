package de.uni_passau.fim.prog2.kalah.view;

import de.uni_passau.fim.prog2.kalah.model.GameSettings;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel that holds all buttons and dropdown menus necessary for controlling
 * the game.
 */
public class ControlPanel extends JPanel {
    private final JComboBox<Integer> pitsPerPlayerControl;
    private final JComboBox<Integer> seedsPerPitControl;
    private final JComboBox<Integer> levelControl;

    private List<JButton> buttons;

    private static final Integer[] PITS_PER_PLAYER_SETTINGS
            = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    private static final Integer[] SEEDS_PER_PIT_SETTINGS
            = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

    private static final Integer[] LEVEL_SETTINGS
            = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    /**
     * Creates new panel and control elements contained with realistic
     * options and default values.
     */
    public ControlPanel() {
        super();
        setLayout(new FlowLayout());
        setBackground(Color.DARK_GRAY);

        // DropDown Menus
        pitsPerPlayerControl
                = new JComboBox<>(PITS_PER_PLAYER_SETTINGS);
        pitsPerPlayerControl.setSelectedIndex(5);
        seedsPerPitControl
                = new JComboBox<>(SEEDS_PER_PIT_SETTINGS);
        seedsPerPitControl.setSelectedIndex(2);
        levelControl
                = new JComboBox<>(LEVEL_SETTINGS);
        levelControl.setSelectedIndex(2);

        // DropDown Menu Labels
        JLabel pLabel = new JLabel("p:");
        pLabel.setForeground(Color.WHITE);
        JLabel sLabel = new JLabel("s:");
        sLabel.setForeground(Color.WHITE);
        JLabel lLabel = new JLabel("l:");
        lLabel.setForeground(Color.WHITE);

        add(pLabel);
        add(pitsPerPlayerControl);
        add(sLabel);
        add(seedsPerPitControl);
        add(lLabel);
        add(levelControl);

        createButtons();
    }

    private void createButtons() {
        buttons = new ArrayList<>();

        JButton newButton = new JButton("NEW");
        JButton switchButton = new JButton("SWITCH");
        JButton undoButton = new JButton("UNDO");
        JButton quitButton = new JButton("QUIT");

        undoButton.setEnabled(false);

        buttons.add(newButton);
        buttons.add(switchButton);
        buttons.add(undoButton);
        buttons.add(quitButton);

        add(newButton);
        add(switchButton);
        add(undoButton);
        add(quitButton);
    }

    /**
     * Returns all buttons.
     *
     * @return list of all buttons.
     */
    public List<JButton> getAllButtons() {
        return buttons;
    }

    /**
     * Returns a record of all the settings that are currently selected.
     *
     * @return All currently selected settings.
     */
    public GameSettings getSelectedSettings() {
        Object level = levelControl.getSelectedItem();
        Object seedsPerPit = seedsPerPitControl.getSelectedItem();
        Object pitsPerPlayer = pitsPerPlayerControl.getSelectedItem();
        if (level == null || seedsPerPit == null || pitsPerPlayer == null) {
            throw new IllegalStateException("Not all settings selected.");
        } else {

            return new GameSettings(
                    (int) level, (int) seedsPerPit, (int) pitsPerPlayer);
        }
    }

    /**
     * Returns a handle to the dropdown menu controlling the games level.
     *
     * @return handle to the level control
     */
    public JComboBox<Integer> getLevelControl() {
        return levelControl;
    }
}
