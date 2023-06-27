package de.uni_passau.fim.prog2.kalah.controller;

import de.uni_passau.fim.prog2.kalah.model.IllegalMoveException;
import de.uni_passau.fim.prog2.kalah.model.Model;
import de.uni_passau.fim.prog2.kalah.model.Player;
import de.uni_passau.fim.prog2.kalah.view.NotificationType;
import de.uni_passau.fim.prog2.kalah.view.PitLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener reacting to a click on a pit.
 */
public class PitMouseListener implements MouseListener {
    private final Controller controller;

    /**
     * Constructs a new listener with access to the view and the controller.
     *
     * @param controller The application's controller.
     */
    public PitMouseListener(Controller controller) {
        this.controller = controller;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Model model = controller.getModel();
        PitLabel pl = (PitLabel) e.getSource();

        controller.pushHistory(model.getBoard());

        try {
            model.setBoard(model.getBoard().move(pl.getPitNumber()));
        } catch (IllegalMoveException | IllegalArgumentException ie) {
            controller.showNotification(NotificationType.ILLEGAL_MOVE);
            return;
        }

        controller.reload();
        controller.instantiateMachineMove();
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
