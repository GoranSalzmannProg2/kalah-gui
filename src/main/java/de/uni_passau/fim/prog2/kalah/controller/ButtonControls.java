package de.uni_passau.fim.prog2.kalah.controller;

import de.uni_passau.fim.prog2.kalah.model.Board;
import de.uni_passau.fim.prog2.kalah.model.GameSettings;
import de.uni_passau.fim.prog2.kalah.model.Kalah;
import de.uni_passau.fim.prog2.kalah.model.Model;
import de.uni_passau.fim.prog2.kalah.model.Player;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

/**
 * Generic listener implementing the logic for all buttons.
 */
public class ButtonControls implements ActionListener {
    private Controller controller;

    /**
     * Creates a new listener with access to a view and a controller.
     *
     * @param controller The application's controller.
     */
    public ButtonControls(Controller controller) {
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
        JButton jb = (JButton) e.getSource();
        String cmd = jb.getText();
        switch (cmd) {
            case "QUIT" -> System.exit(0);
            case "SWITCH" -> {
                restartGame(true);
            }
            case "NEW" -> {
                restartGame(false);
            }
            case "UNDO" -> {
                controller.stopWorkerThread();
                model.setBoard(controller.popHistory());
                controller.reload();
            }
            default -> {
            }
        }
    }

    private void restartGame(boolean switching){
        controller.stopWorkerThread();
        controller.clearHistory();
        Board oldModel = controller.getModel().getBoard();
        GameSettings settings = controller.getSelectedSettings();
        Board newModel;
        if (switching){
            newModel = new Kalah(oldModel.getPitsPerPlayer(),
                    oldModel.getSeedsPerPit(),
                    oldModel.getOpeningPlayer().getOpposite(),
                    settings.level());
        }else{
            newModel = new Kalah(settings.pitsPerPlayer(),
                    settings.seedsPerPit(), oldModel.getOpeningPlayer(),
                    settings.level());
        }
        controller.getModel().setBoard(newModel);
        controller.fullReload();
        if(controller.getModel().getBoard().next() == Player.COMPUTER){
            controller.instantiateMachineMove();
        }
    }
}
