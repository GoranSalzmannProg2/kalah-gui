package de.uni_passau.fim.prog2.kalah.view;

import de.uni_passau.fim.prog2.kalah.model.Board;
import de.uni_passau.fim.prog2.kalah.model.GameSettings;
import de.uni_passau.fim.prog2.kalah.model.Player;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.util.List;

/**
 * The actual view containing all relevant parts of rendering the application.
 */
public class View {
    private BoardPanel boardPanel;
    private final ControlPanel controlPanel;
    private final Container contentPane;

    /**
     * Constructs a new view.
     */
    public View() {
        JFrame frame = new JFrame("Kalah");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(800, 350);
        frame.setVisible(true);

        contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        controlPanel = new ControlPanel();
        contentPane.add(controlPanel, BorderLayout.SOUTH);
        contentPane.revalidate();
    }

    /**
     * Updates the seeds inside the pit labels
     *
     * @param model The Board instance with the updated state.
     */
    public void updatePits(Board model) {
        boardPanel.updatePits(model);
    }

    /**
     * Creates a new board panel based on an existing board.
     *
     * @param model A board instance.
     */
    public void createBoardPanel(Board model) {
        if (boardPanel != null) {
            contentPane.remove(boardPanel);
        }
        boardPanel = new BoardPanel(model);
        contentPane.add(boardPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    /**
     * Returns all labels representing single pits.
     *
     * @return List of all pit labels.
     */
    public List<PitLabel> getAllPitLabels() {
        return boardPanel.getPitLabels();
    }

    /**
     * Returns all buttons.
     *
     * @return List of all buttons.
     */
    public List<JButton> getAllControlButtons() {
        return controlPanel.getAllButtons();
    }

    /**
     * Returns a record of the currently selected settings for the game.
     *
     * @return All game settings
     */
    public GameSettings getSelectedSettings() {
        return controlPanel.getSelectedSettings();
    }

    /**
     * Returns the dropdown menu responsible for selecting a difficulty.
     *
     * @return JComboBox for the difficulty.
     */
    public JComboBox<Integer> getLevelControl() {
        return controlPanel.getLevelControl();
    }

    /**
     * Notifies the user of relevant Events inside the model.
     *
     * @param type  The type of event that happened.
     * @param model The board instance with the current state of the model.
     */
    public void showNotification(NotificationType type, Board model) {
        switch (type) {
            case HUMAN_MISS -> {
                JOptionPane.showMessageDialog(contentPane,
                        "You must miss a turn.");

            }
            case COMPUTER_MISS -> {
                JOptionPane.showMessageDialog(contentPane,
                        "Computer must miss a turn.");
            }
            case HUMAN_WIN -> {
                int human = model.getSeedsOfPlayer(Player.HUMAN);
                int computer = model.getSeedsOfPlayer(Player.COMPUTER);
                JOptionPane.showMessageDialog(contentPane,
                        "You won with " + human
                                + " seeds versus " + computer
                                + " seeds of the machine.",
                        "Congratulations!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            case COMPUTER_WIN -> {
                int human = model.getSeedsOfPlayer(Player.HUMAN);
                int computer = model.getSeedsOfPlayer(Player.COMPUTER);
                JOptionPane.showMessageDialog(contentPane,
                        "Machine wins with "
                                + computer + " seeds versus your "
                                + human + ".",
                        "Sorry!", JOptionPane.INFORMATION_MESSAGE);
            }
            case TIE -> {
                int human = model.getSeedsOfPlayer(Player.HUMAN);
                JOptionPane.showMessageDialog(contentPane,
                        "Nobody wins. Tie with "
                                + human + " for each player");
            }
            default -> {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
