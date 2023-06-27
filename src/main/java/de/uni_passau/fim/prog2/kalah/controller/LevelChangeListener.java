package de.uni_passau.fim.prog2.kalah.controller;

import de.uni_passau.fim.prog2.kalah.model.Model;

import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Listener that reacts to changes in the game difficulty, and updates the
 * model accordingly.
 */
public class LevelChangeListener implements ActionListener {
    private Controller controller;

    /**
     * Creates a Listener with access to a view and a controller.
     *
     * @param controller The application's controller.
     */
    public LevelChangeListener(Controller controller) {
        this.controller = controller;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Model model = controller.getModel();
        if (Objects.equals(e.getActionCommand(), "comboBoxChanged")) {
            int level = (int) ((JComboBox) e.getSource()).getSelectedItem();
            model.getBoard().setLevel(level);
        }
    }
}
